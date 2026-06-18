# SessionManager Guide

The [SessionManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java) handles runtime session tracking for the currently logged-in user. It is a Singleton class that acts as a global state provider for session attributes throughout the application.

## API Architecture

### Singleton Access
Retrieve the shared instance of the session manager using:
```java
SessionManager session = SessionManager.getInstance();
```

---

## Session State Methods

### Checking Authentication Status
Use [isLoggedIn()](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java#L20) to check if a user is currently logged in:
```java
boolean active = session.isLoggedIn();
```

### Retrieving Active User
Use [getCurrentUser()](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java#L18) to get the [User](file:///d:/Akhil%20don2/AlgoVerse/src/models/User.java) model instance of the active session:
```java
User user = session.getCurrentUser();
```

### Setting Active User
Use [setCurrentUser(User)](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java#L19) to set the active user context manually:
```java
session.setCurrentUser(user);
```

---

## Session Lifecycle

### Auto-Loading Remembered Sessions
At application startup, the system tries to restore any previously remembered session using [loadRememberedSession()](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java#L32-L37):
```java
session.loadRememberedSession();
```
* **Process Flow**:
  1. Queries [UserManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java) for any registered user profile that has a non-empty `rememberToken`.
  2. If found, sets that user as the `currentUser`.

### Performing Logout
The [logout()](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java#L22-L29) method terminates the active session:
```java
session.logout();
```
* **Process Flow**:
  1. If `currentUser` is not null, it clears the user's `rememberToken` (setting it to `""`).
  2. Saves the updated user information using `UserManager.getInstance().updateUser(currentUser)` to invalidate the login session permanently.
  3. Sets `currentUser` to `null`.

---

## Example Usage

### Startup Session Check
```java
SessionManager session = SessionManager.getInstance();

// Auto-login if a remember-me token was saved previously
session.loadRememberedSession();

if (session.isLoggedIn()) {
    System.out.println("Welcome back, " + session.getCurrentUser().getUsername() + "!");
} else {
    System.out.println("Please log in to continue.");
}
```
