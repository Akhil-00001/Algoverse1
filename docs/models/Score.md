# Score Model Guide

The [Score](file:///d:/Akhil%20don2/AlgoVerse/src/models/Score.java) class is a data model that represents a single completion event of a game or algorithm module by a user. It contains fields detailing performance and timestamp metrics.

---

## Fields & Format

Each score record is serialized to a single pipe-delimited (`|`) line in `data/scores.txt`.

### Class Properties
* `userId`: The Unique identifier of the user (e.g. `john_doe` or UUID).
* `algorithmId`: The unique ID of the algorithm mode (e.g. `DP_KNAPSACK`, `GREEDY_COIN`).
* `score`: The points achieved during the attempt.
* `timeTakenSeconds`: The duration of the attempt in seconds.
* `completedAt`: The timestamp of completion formatted as `yyyy-MM-dd HH:mm:ss` (via [FMT](file:///d:/Akhil%20don2/AlgoVerse/src/models/Score.java#L15)).

### Storage Format
```text
userId|algorithmId|score|timeTakenSeconds|completedAt
```

---

## Constructors

### 1. Default Constructor
```java
public Score()
```
Used primarily during deserialization to instantiate a blank score object prior to populating its fields.

### 2. Parameterized Constructor
```java
public Score(String userId, String algorithmId, int score, int timeTakenSeconds)
```
* Generates a new score model.
* Automatically initializes the `completedAt` timestamp to the current local date and time:
  ```java
  this.completedAt = LocalDateTime.now().format(FMT);
  ```

---

## Serialisation & Deserialisation

### 1. Serialization
The [serialize()](file:///d:/Akhil%20don2/AlgoVerse/src/models/Score.java#L36-L40) method converts the model instance into a pipe-delimited string string suitable for writing to file:
```java
public String serialize()
```
* Internally calls `FileUtil.join(userId, algorithmId, String.valueOf(score), String.valueOf(timeTakenSeconds), completedAt)`.

### 2. Deserialization
The static [deserialize(String)](file:///d:/Akhil%20don2/AlgoVerse/src/models/Score.java#L42-L55) method parses a serialized text line back into a `Score` object:
```java
public static Score deserialize(String line)
```
* Triggers `FileUtil.split(line)` to parse elements.
* Validates that at least 5 segments exist.
* Gracefully handles potential `NumberFormatException` when parsing the integer values for `score` and `timeTakenSeconds`.

---

## API Summary / Getters

The model exposes standard getter methods for access by business logic (e.g. [ScoreManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/ScoreManager.java)):
* `getUserId()`
* `getAlgorithmId()`
* `getScore()`
* `getTimeTakenSeconds()`
* `getCompletedAt()`
