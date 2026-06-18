# AuthManager Guide

The [AuthManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/AuthManager.java) handles user registration, validation, login credentials checking, session initiation, and logout cleanup. It utilizes a Singleton pattern to provide a global authentication service.

## API Architecture

### Singleton Access
Retrieve the shared instance of the authentication manager using:
```java
AuthManager auth = AuthManager.getInstance();
```

---

## Registration

The [register](file:///d:/Akhil%20don2/AlgoVerse/src/managers/AuthManager.java#L23) method registers a new user with validation checks:
```java
public RegisterResult register(String username, String password, String confirm)
```

### Validation Rules
1. **Username Length**: Must be at least 3 characters (after trimming).
2. **Username Characters**: Must match `[A-Za-z0-9_]+` (alphanumeric and underscores).
3. **Password Length**: Must be at least 6 characters.
4. **Password Match**: `password` and `confirm` values must match exactly.
5. **Duplicate Check**: Username must not already exist (checked via `UserManager.usernameExists`).

### Return Values ([RegisterResult](file:///d:/Akhil%20don2/AlgoVerse/src/managers/AuthManager.java#L20-L21))
* `SUCCESS`: Registration successful; user created in file storage.
* `USERNAME_TAKEN`: Username already in use.
* `USERNAME_INVALID`: Username is null, too short, or contains invalid characters.
* `PASSWORD_TOO_SHORT`: Password is null or shorter than 6 characters.
* `PASSWORDS_DO_NOT_MATCH`: Confirmed password does not match.

### Message Translation
Get user-friendly feedback using [messageFor(RegisterResult)](file:///d:/Akhil%20don2/AlgoVerse/src/managers/AuthManager.java#L70-L78):
```java
String feedback = AuthManager.messageFor(result);
```

---

## Login and Sessions

The [login](file:///d:/Akhil%20don2/AlgoVerse/src/managers/AuthManager.java#L44) method validates user credentials and sets up the active session:
```java
public LoginResult login(String username, String password, boolean rememberMe)
```

### Process Flow
1. Queries [UserManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java) to find the user by username.
2. Compares the hash of the provided password against the stored password hash using `PasswordUtil.verify`.
3. If `rememberMe` is enabled, generates a remember token and updates it on the user profile.
4. Updates the user's login timestamp.
5. Sets the active user inside the [SessionManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java).

### Return Values ([LoginResult](file:///d:/Akhil%20don2/AlgoVerse/src/managers/AuthManager.java#L42))
* `SUCCESS`: Successful login; session is now active.
* `USER_NOT_FOUND`: The username was not found in the database.
* `WRONG_PASSWORD`: The username was found but the password hash did not match.

### Message Translation
Get user-friendly feedback using [messageFor(LoginResult)](file:///d:/Akhil%20don2/AlgoVerse/src/managers/AuthManager.java#L80-L86):
```java
String feedback = AuthManager.messageFor(result);
```

---

## Logout

The [logout](file:///d:/Akhil%20don2/AlgoVerse/src/managers/AuthManager.java#L64-L66) method clears the user session:
```java
auth.logout();
```
* Delegates the logout handling to the [SessionManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java#L22-L29), which cleans up the local session and clears the persistent remember token.

---

## Example Usage

### Performing Registration
```java
AuthManager auth = AuthManager.getInstance();
RegisterResult regResult = auth.register("john_doe", "password123", "password123");

if (regResult == RegisterResult.SUCCESS) {
    System.out.println("User registered successfully!");
} else {
    System.out.println("Failed: " + AuthManager.messageFor(regResult));
}
```

### Performing Login
```java
AuthManager auth = AuthManager.getInstance();
LoginResult loginResult = auth.login("john_doe", "password123", true);

if (loginResult == LoginResult.SUCCESS) {
    System.out.println("Logged in! Session started.");
} else {
    System.out.println("Failed to log in: " + AuthManager.messageFor(loginResult));
}
```
