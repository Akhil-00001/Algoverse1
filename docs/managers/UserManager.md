# UserManager Guide

The [UserManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java) is responsible for user profile management, persistence in text files (`data/users.txt`), gamification features (XP, levels, leaderboard sorting), and record-keeping of algorithm completions.

## API Architecture

### Singleton Access
Retrieve the shared instance of the user manager using:
```java
UserManager userManager = UserManager.getInstance();
```

### Data Storage
* **File Path**: `data/users.txt`
* **File Format**: Plain text with serialized `User` profile records (one per line).
* **Date Formatter**: Timestamps use the format `yyyy-MM-dd HH:mm:ss` ([FMT](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L14)).

---

## User Queries and Persistence

### 1. Retrieve All Profiles
The [getAllUsers](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L30) method loads and deserializes all registered user profiles:
```java
public List<User> getAllUsers()
```

### 2. Profile Lookups
* **By Username**: [findByUsername(String)](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L41) (Case-insensitive check)
* **By ID**: [findById(String)](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L48) (Exact match check)
* **By Remember Token**: [findByRememberToken()](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L56) (Finds a user profile with an active, non-empty remember token)

### 3. Creating a Profile
The [createUser](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L67) method adds a new user to storage after ensuring the username is unique:
```java
public boolean createUser(User user)
```
* **Returns**: `true` if profile created, `false` if username already exists.

### 4. Updating a Profile
The [updateUser](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L75) method performs an in-place file rewrite replacing the old profile serialized record with the new one:
```java
public void updateUser(User updated)
```

---

## Gamification & Activity Hooks

### 1. Awarding XP
The [awardXP](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L91) method increments user XP, checks if they leveled up, records their last activity timestamp, and saves:
```java
public boolean awardXP(User user, int xp)
```
* **Returns**: `true` if this award triggered a Level Up, `false` otherwise.

### 2. Completing an Algorithm Activity
The [completeAlgorithm](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L98) method marks an algorithm as completed. If it's the first time they completed it, it awards bonus XP:
```java
public void completeAlgorithm(User user, String algoId, int xpReward)
```

### 3. Record Login Time
The [recordLogin](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L104) method updates the login time stamp on a user and persists it:
```java
public void recordLogin(User user)
```

### 4. Leaders Board
The [getLeaderboard](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L110) method returns all users sorted by XP in descending order:
```java
public List<User> getLeaderboard()
```

---

## Example Usage

### Awarding XP on Activity Completion
```java
UserManager userManager = UserManager.getInstance();
User currentUser = SessionManager.getInstance().getCurrentUser();

if (currentUser != null) {
    // Award 50 XP
    boolean leveledUp = userManager.awardXP(currentUser, 50);
    
    if (leveledUp) {
        System.out.println("Congratulations! You leveled up to Level " + currentUser.getLevel() + "!");
    }
}
```
