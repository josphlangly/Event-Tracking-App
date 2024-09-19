# Langley Event Tracking App

### Overview
The **Langley Event Tracking App** is an Android application designed to help users manage and track personal events. The app provides user authentication, event management (add, edit, delete), automatic birthday event creation, and first-time login prompts for SMS notifications. It utilizes a clean architecture with Repository and DAO patterns for efficient data handling and leverages `RecyclerView` for smooth event list management.

---

## Features

- **User Authentication**:
  - **Login**: Secure login using email and password.
  - **Registration**: New users can create an account with input validation to prevent duplicate emails and ensure data integrity.

- **Event Management**:
  - **Add, Edit, Delete Events**: Users can manage events with titles, dates, and descriptions.
  - **Automatic Birthday Event**: On first login, the app creates a birthday event for the user.
  - **Event List Display**: Efficient event list rendering using `RecyclerView` and `EventAdapter`.

- **First-Time Login Prompts**:
  - **SMS Notification Prompt**: Users are prompted to set up SMS notifications on their first login.

- **Data Management**:
  - **Repository Pattern**: `EventAppRepository` abstracts data access, ensuring a clean separation between UI and data layers.
  - **Room Database**: `EventAppDatabase` handles database creation and provides DAOs for `User` and `Event` entities.

- **Utility Functions**:
  - **Input Validation**: `AppUtility` class provides methods for input validation and date selection, enhancing code reusability and cleanliness.

---

## Installation and Setup

### Requirements:

- **Android Studio**: [Download Android Studio](https://developer.android.com/studio).
- **Minimum SDK**: API level 21 (Android 5.0 Lollipop) or higher.
- **Database**: Uses SQLite with Room persistence library.

### Steps to Run the Project:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/langleyeventtracking.git
   ```
2. **Open in Android Studio**:
   - Launch Android Studio.
   - Select **"Open an existing Android Studio project"**.
   - Navigate to the cloned repository folder and open it.
3. **Sync Gradle Files**:
   - Android Studio will prompt you to sync Gradle files.
   - Ensure all dependencies are downloaded.
4. **Run the App**:
   - Connect an Android device or start an emulator.
   - Click the **Run** button or use the **Shift + F10** shortcut.

---

## Usage

### Registration:

1. **Open the App**: Launch the app on your device or emulator.
2. **Navigate to Registration**: Click on the **"Register"** button.
3. **Fill in Details**:
   - **Name**: Enter your full name.
   - **Birthday**: Use the date picker to select your birthday.
   - **Phone**: Enter your phone number.
   - **Email**: Provide a valid email address.
   - **Password**: Create a password (minimum 6 characters).
4. **Submit**: Click **"Create Account"**.
5. **Validation**:
   - The app checks for empty fields, valid email format, and password length.
   - If the email already exists, an error is displayed.

### Login:

1. **Enter Credentials**:
   - **Email**: Enter your registered email.
   - **Password**: Enter your password.
2. **Login**: Click the **"Login"** button.
3. **First-Time Login Prompts**:
   - **SMS Notification**: A prompt appears to set up SMS notifications.
   - **Birthday Event**: The app automatically creates a birthday event for you.
4. **Access Events**: You are directed to the main event screen.

### Event Management:

- **Add Event**:
  - Click the **"Add Event"** button.
  - Fill in the event **Title**, **Date** (using the date picker), and **Description**.
  - Click **"Save"** to add the event.
- **Edit Event**:
  - Tap on an event from the list.
  - Modify the event details.
  - Click **"Update"** to save changes.
- **Delete Event**:
  - While editing an event, click the **"Delete"** button to remove it.

---

## Code Components

### 1. `EventAppDatabase`:

- **Purpose**: Serves as the main database configuration using Room.
- **Functionality**:
  - Implements Singleton pattern to ensure a single database instance.
  - Provides access to DAOs (`UserDao`, `EventDao`).

### 2. `EventAppRepository`:

- **Purpose**: Acts as a mediator between the app and the database.
- **Functionality**:
  - Abstracts data access operations.
  - Provides clean APIs for data manipulation (insert, update, delete).
  - Manages threading to prevent blocking the main UI thread.

### 3. DAOs (Data Access Objects):

- **`UserDao`**:
  - Methods for user-related database operations (insert, query).
- **`EventDao`**:
  - Methods for event-related database operations (insert, update, delete, query).

### 4. `EventAdapter`:

- **Purpose**: Binds event data to views in a `RecyclerView`.
- **Functionality**:
  - Efficiently displays events.
  - Handles item clicks for editing and deleting.

### 5. `AppUtility`:

- **Purpose**: Provides utility methods to avoid code duplication.
- **Functionality**:
  - **Input Validation**: Ensures user inputs meet required criteria.
  - **Date Picker Dialog**: Simplifies date selection.
  - **Toast Messages**: Centralizes toast message display.
  - **Clear Text Fields**: Resets input fields when necessary.

### 6. Activities:

- **`LoginActivity`**:
  - Manages user login functionality.
  - Validates inputs and authenticates users.
- **`RegisterActivity`**:
  - Handles new user registration.
  - Validates inputs and checks for existing emails.
- **`EventActivity`**:
  - Main screen displaying user's events.
  - Allows adding, editing, and deleting events.
  - Checks for first-time login to prompt SMS setup and create birthday event.
- **`EditEventActivity`**:
  - Manages event creation and editing.
  - Provides a user-friendly interface for event details input.

---

## Technologies Used

- **Android SDK**: Core framework for building Android applications.
- **Java**: Programming language used for development.
- **SQLite with Room Persistence Library**:
  - Simplifies database management.
  - Provides an abstraction layer over SQLite.
- **LiveData & ViewModel**:
  - Manages UI-related data in a lifecycle-aware manner.
  - Ensures UI components observe data changes.
- **RecyclerView & DiffUtil**:
  - Efficiently displays lists with automatic updates on data change.
- **Repository Pattern**:
  - Promotes separation of concerns.
  - Makes code more maintainable and testable.
- **DatePickerDialog**:
  - Provides an intuitive way for users to select dates.
- **Singleton Pattern**:
  - Ensures a single instance of the database is used throughout the app.

---

## Future Enhancements

- **Push Notifications**: Implement notifications for upcoming events.
- **Recurring Events**: Allow users to set events that repeat on a schedule.
- **Cloud Synchronization**: Sync data across devices using cloud services.
- **Profile Management**: Enable users to update their profile information.
- **Search Functionality**: Add the ability to search events by title or date.
- **Dark Mode Support**: Enhance UI with dark mode for better user experience.

---

## Credits

- **Author**: Joseph Langley

---

## Contact

For any questions or suggestions, please contact:

- **Email**: [joseph.o.langley@gmail.com](mailto:joseph.o.langley@example.com)
- **LinkedIn**: [Joseph Langley](https://www.linkedin.com/in/josphlangly)
