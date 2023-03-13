UserService is a Java-based microservice for the Brokage House project.

![microservicesGraph](https://user-images.githubusercontent.com/77538971/221441130-b00c1952-1efd-43c7-baed-8b951e79b720.jpg)

Its main functionalities include account creation, data editing, and password recovery.

Features
Account Creation: Users can create an account by providing their personal information and setting up a secure password.

Data Editing: Users can update their personal information, such as their email address, and phone number, and etc by accessing their account settings.

Password Recovery: In case users forget their password, they can use the password recovery feature to reset their password and regain access to their account.

Getting Started
To get started with UserService, follow these steps:

Clone the repository to your local machine.

Install the required dependencies by running the following command:

Copy code
mvn clean install
Set up the environment variables by creating a .env file and specifying the required variables, such as the database URL and the port number.

Start the server by running the following command:

bash
Copy code
java -jar target/user-service-0.0.1-SNAPSHOT.jar
You can now access the UserService API by sending requests to the specified port number.

API Documentation
For detailed information on the UserService API and its endpoints, refer to the API documentation provided in the docs folder of this repository.

Contributing
Contributions to UserService are welcome and encouraged! To contribute, please follow the guidelines provided in the CONTRIBUTING.md file of this repository.

License
UserService is licensed under the MIT License. See the LICENSE file of this repository for more information.
