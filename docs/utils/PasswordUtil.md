# PasswordUtil Guide

The [PasswordUtil](file:///d:/Akhil%20don2/AlgoVerse/src/utils/PasswordUtil.java) class provides cryptographic utilities to handle password hashing, verification, and remember-me session tokens securely.

---

## Constants

* **Salt Size**: Defined by [SALT_LENGTH](file:///d:/Akhil%20don2/AlgoVerse/src/utils/PasswordUtil.java#L11) ($16$ bytes).

---

## Core Security Features

### 1. Password Hashing
The [hash(String)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/PasswordUtil.java#L14) method hashes passwords using a salted SHA-256 algorithm:
```java
public static String hash(String password)
```
* **Process**:
  1. Generates a random 16-byte salt using `SecureRandom`.
  2. Concatenates the salt and the UTF-8 bytes of the password.
  3. Computes the SHA-256 checksum digest.
  4. Returns the salt and digest as Base64 strings separated by a colon (`:`).
* **Format**:
  ```text
  saltBase64:digestBase64
  ```

### 2. Password Verification
The [verify(String, String)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/PasswordUtil.java#L29) method compares a plain-text password attempt against the stored hashed entry:
```java
public static boolean verify(String password, String stored)
```
* **Process**:
  1. Splits the stored string on the `:` delimiter.
  2. Base64 decodes the salt and the stored digest.
  3. Concatenates the decoded salt and the password attempt bytes, and re-computes the SHA-256 digest.
  4. Compares the resulting byte arrays using a constant-time check:
     ```java
     MessageDigest.isEqual(stored256, attempt)
     ```
* **Note**: Constant-time comparison prevents timing side-channel attacks.

### 3. Session Token Generation
The [generateToken()](file:///d:/Akhil%20don2/AlgoVerse/src/utils/PasswordUtil.java#L43) method generates secure tokens for the "Remember Me" features:
```java
public static String generateToken()
```
* **Process**:
  1. Generates 24 random bytes using `SecureRandom`.
  2. Returns a URL-safe Base64 encoded string with padding characters removed.
* **Usage**: Stored on [User](file:///d:/Akhil%20don2/AlgoVerse/src/models/User.java) profiles for auto-login checking in [SessionManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java).
