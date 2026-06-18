# ScoreManager Guide

The [ScoreManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/ScoreManager.java) is responsible for recording, loading, and tracking user performance scores. It interfaces with text file storage (`data/scores.txt`) to persist historical quiz or gameplay scores.

## API Architecture

### Singleton Access
Retrieve the shared instance of the score manager using:
```java
ScoreManager scoreManager = ScoreManager.getInstance();
```

### Data Storage
* **File Path**: `data/scores.txt`
* **File Format**: Plain text with one serialized `Score` record per line.
* **Initialization**: The constructor calls `FileUtil.ensureFile("data/scores.txt")` to guarantee that the database file exists at startup.

---

## Logging Scores

There are two primary ways to persist scores:

### 1. Recording a New Result
The [recordResult](file:///d:/Akhil%20don2/AlgoVerse/src/managers/ScoreManager.java#L59) method is a helper to construct and persist a new score:
```java
public void recordResult(String userId, String algorithmId, int score, int timeTakenSeconds)
```
* **Parameters**:
  * `userId`: The ID of the user who obtained the score.
  * `algorithmId`: The ID of the algorithm associated with the activity (e.g. quiz or simulator).
  * `score`: The points or percentage earned.
  * `timeTakenSeconds`: Time spent on the activity.

### 2. Direct Saving
The [addScore](file:///d:/Akhil%20don2/AlgoVerse/src/managers/ScoreManager.java#L24) method saves a pre-constructed `Score` model instance by appending its serialized string format to the text file:
```java
public void addScore(Score score)
```

---

## Retrieving Scores

### 1. All Historical Scores
The [getAllScores](file:///d:/Akhil%20don2/AlgoVerse/src/managers/ScoreManager.java#L29) method reads all lines from `data/scores.txt` and deserializes them into a list of `Score` objects:
```java
public List<Score> getAllScores()
```

### 2. Scores for a Specific User
The [getScoresForUser](file:///d:/Akhil%20don2/AlgoVerse/src/managers/ScoreManager.java#L39) method returns a filtered list of scores belonging to a particular user:
```java
public List<Score> getScoresForUser(String userId)
```

### 3. Top Score Check
The [getBestScore](file:///d:/Akhil%20don2/AlgoVerse/src/managers/ScoreManager.java#L48) method scans the user's history to find their highest score for a particular algorithm:
```java
public int getBestScore(String userId, String algorithmId)
```
* **Returns**: The highest score found (integer value), or `0` if no scores exist for that user and algorithm.

---

## Example Usage

### Recording and Fetching Top Scores
```java
ScoreManager manager = ScoreManager.getInstance();

// Record a completed Sorting Algorithm quiz score of 95 points in 120 seconds
manager.recordResult("user-12345", "bubble-sort", 95, 120);

// Get the user's best score for bubble-sort
int best = manager.getBestScore("user-12345", "bubble-sort");
System.out.println("Personal Best: " + best);
```
