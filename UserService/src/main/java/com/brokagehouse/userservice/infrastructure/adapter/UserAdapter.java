package com.brokagehouse.userservice.infrastructure.adapter;

import com.brokagehouse.userservice.domain.model.User;
import com.brokagehouse.userservice.domain.service.UserRepository;
import com.brokagehouse.userservice.infrastructure.kafka.dto.VerificationResult;
import com.brokagehouse.userservice.infrastructure.entity.UserDao;
import com.brokagehouse.userservice.infrastructure.exception.DbExceptionCode;
import com.brokagehouse.userservice.infrastructure.mapper.DaoMapper;
import com.brokagehouse.userservice.infrastructure.repository.UserRepositoryJpa;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class UserAdapter implements UserRepository {
    UserRepositoryJpa userRepositoryJpa;
    DaoMapper daoMapper;

    @Override
    public User save(User user) {
        userRepositoryJpa.save(daoMapper.toUserDao(user));
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return daoMapper.toUser(findUserDaoByEmail(email));
    }

    @Override
    public User update(User user) {
        var fromDb = findUserDaoByEmail(user.getEmailAddress());
        userRepositoryJpa.save(new UserDao(fromDb.getId(), user));
        return user;
    }

    @Override
    public Page<User> findUsers(Pageable pageable) {
        return userRepositoryJpa.findAll(pageable)
                .map(daoMapper::toUser);
    }

    @Override
    public void verify(VerificationResult result) {
        var fromDb = findUserDaoByEmail(result.email());
        fromDb.setVerified(result.result());
        userRepositoryJpa.save(fromDb);
        log.info("Verification process from user with email '{}' finished.", result.email());
    }

    @Override
    public boolean existsByEmailAddress(String email) {
        return userRepositoryJpa.existsByEmailAddress(email);
    }

    @Override
    public boolean existsByPersonalIdNumber(String idNumber) {
        return userRepositoryJpa.existsByPersonalIdNumber(idNumber);
    }

    private UserDao findUserDaoByEmail(String email) {
        return userRepositoryJpa.findByEmailAddress(email)
                .orElseThrow(DbExceptionCode.NO_USER::createDbException);
    }
}
