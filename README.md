# SQLServer Android App

This is an Android application that demonstrates basic CRUD (Create, Read, Update, Delete) operations with a SQL Server database.

## Features

- Insert a new user record
- Retrieve a user record by ID
- Update an existing user record
- Delete a user record by ID

## Prerequisites

- Android Studio
- SQL Server database
- JDBC driver for SQL Server

## Setup

1. **Clone the repository:**
    ```sh
    git clone https://github.com/yourusername/SQLServer.git
    cd SQLServer
    ```

2. **Open the project in Android Studio:**
    - Launch Android Studio.
    - Select `Open an existing Android Studio project`.
    - Navigate to the cloned repository and open it.

3. **Configure the database connection:**
    - Open `DatabaseHelper.java`.
    - Update the connection string with your SQL Server details.

4. **Run the application:**
    - Connect your Android device or start an emulator.
    - Click on the `Run` button in Android Studio.

## Usage

- **Insert a new user:**
    - Enter the user ID, name, and address.
    - Click the `Save` button.

- **Retrieve a user:**
    - Enter the user ID.
    - Click the `Get` button to fetch and display the user details.

- **Update a user:**
    - Enter the user ID, new name, and new address.
    - Click the `Update` button.

- **Delete a user:**
    - Enter the user ID.
    - Click the `Delete` button.


