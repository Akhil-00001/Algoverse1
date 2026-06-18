# User Model Guide

The [User](file:///d:/Akhil%20don2/AlgoVerse/src/models/User.java) class is a data model representing an AlgoVerse user account. It includes profile details, authentication credentials (password hashes, remember tokens), and gamification states (XP, levels, and completed algorithms).

---

## Fields & Storage Format

Each user record is serialized to a single pipe-delimited (`|`) line in the user database (`data/users.txt`).

### Properties
* `id`: An 8-character alphanumeric string generated via a truncated UUID.
* `username`: Selected display name.
* `passwordHash`: Hashed string representation of the password.
* `xp`: Total experience points accumulated.
* `level`: Calculated level based on XP thresholds.
* `streak`: Login frequency streak value.
* `completedAlgorithms`: A list of algorithm IDs completed by the user (serialized as a comma-separated list).
* `lastLogin`: Timestamp of the last login event (`yyyy-MM-dd HH:mm:ss`).
* `rememberToken`: A secure key used to restore login sessions.

### Serialization Format
```text
id|username|passwordHash|xp|level|streak|algo1,algo2,algo3|lastLogin|rememberToken
```

---

## Constants

### XP Thresholds
* **Level Progression Rate**: Defined by [XP_PER_LEVEL](file:///d:/Akhil%20don2/AlgoVerse/src/models/User.java#L31) ($500$).
* **Level Formula**: 
  $$\text{Level} = \lfloor\frac{\text{XP}}{\text{XP\_PER\_LEVEL}}\rfloor + 1$$

---

## Constructors

### 1. Default Constructor
```java
public User()
```
* Instantiates an empty `completedAlgorithms` array list.

### 2. Registration Constructor
```java
public User(String username, String passwordHash)
```
* Generates a unique 8-character ID from a random UUID:
  ```java
  this.id = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
  ```
* Sets initial levels: `xp = 0`, `level = 1`, `streak = 0`.
* Assigns the username and hashed password credentials.

---

## Serialization & Deserialization

### 1. Serialization
The [serialize()](file:///d:/Akhil%20don2/AlgoVerse/src/models/User.java#L54-L62) method transforms the model to a pipe-joined string:
```java
public String serialize()
```
* Converts `completedAlgorithms` into a comma-delimited string (e.g. `DP_FIBONACCI,GREEDY_COIN`).
* Joins all properties with pipe delimiters using `FileUtil.join`.

### 2. Deserialization
The static [deserialize(String)](file:///d:/Akhil%20don2/AlgoVerse/src/models/User.java#L64-L84) method recreates the model instance:
```java
public static User deserialize(String line)
```
* Splits string elements via `FileUtil.split`.
* Splits comma-separated values in the 7th field into the `completedAlgorithms` list.

---

## Leveling & Gamification Methods

### 1. Awarding XP
The [addXP(int)](file:///d:/Akhil%20don2/AlgoVerse/src/models/User.java#L89-L94) method awards experience points and dynamically recalculates the user's level:
```java
public boolean addXP(int amount)
```
* **Returns**: `true` if the XP addition caused the user's level to increase, `false` otherwise.

### 2. Completing Algorithms
The [completeAlgorithm(String)](file:///d:/Akhil%20don2/AlgoVerse/src/models/User.java#L97-L103) method registers algorithm completion:
```java
public boolean completeAlgorithm(String algoId)
```
* **Returns**: `true` if the algorithm has *never* been completed by this user before, `false` if it is a repeat completion.

### 3. XP Progress Helpers
For UI progress bars (e.g. within [MainFrame](file:///d:/Akhil%20don2/AlgoVerse/src/ui/MainFrame.java)), the class exposes progress calculation helpers:
* [getXpInCurrentLevel()](file:///d:/Akhil%20don2/AlgoVerse/src/models/User.java#L110): Returns $\text{XP} \pmod{500}$.
* [getLevelProgress()](file:///d:/Akhil%20don2/AlgoVerse/src/models/User.java#L120): Returns the fraction progress ($0.0 \dots 1.0$) inside the current level.
