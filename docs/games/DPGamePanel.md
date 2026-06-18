# DPGamePanel Guide

The [DPGamePanel](file:///d:/Akhil%20don2/AlgoVerse/src/games/DPGamePanel.java) handles the Dynamic Programming (DP) game modes. It wraps three interactive sub-games in a JTabbedPane, teaching players core DP concepts through step-by-step table filling.

---

## Architecture & Layout

* **Outer Container**: Extends `JPanel` and sets layout to `BorderLayout`. 
* **Tab Selection**: Hosts a `JTabbedPane` that loads:
  1. **Fibonacci DP** — [FibonacciDPGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/DPGamePanel.java#L50)
  2. **0/1 Knapsack DP** — [KnapsackDPGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/DPGamePanel.java#L187)
  3. **LCS (Longest Common Subsequence)** — [LCSGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/DPGamePanel.java#L344)

### Shared Gamification Hook
All sub-games use the package-private static helper [award](file:///d:/Akhil%20don2/AlgoVerse/src/games/DPGamePanel.java#L40-L45) to reward players upon completion:
```java
static void award(String id, int score, int timeSec)
```
* Checks if a user session is active using [SessionManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java).
* Completes the activity in [UserManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java) (awarding **90 XP**).
* Appends the score run to [ScoreManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/ScoreManager.java).

---

## Sub-Game Modes

### 1. Fibonacci DP Game ([FibonacciDPGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/DPGamePanel.java#L50-L182))
Teaches basic 1D state representation and memoisation.
* **Math Recurrence**: `F(n) = F(n-1) + F(n-2)` with base cases `F(0) = 0`, `F(1) = 1`.
* **Gameplay**: Players fill a 1D array of text fields (length 10).
* **Key Methods**:
  * `check()`: Compares input field integers against pre-computed Fibonacci sequence. Correct fields turn green, incorrect ones turn red.
  * `fillAnswer()`: Fills the cells automatically for study/debug.
* **Activity Key**: `"DP_FIBONACCI"`

### 2. 0/1 Knapsack DP Game ([KnapsackDPGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/DPGamePanel.java#L187-L339))
Teaches 2D DP decision tables representing item sub-problems.
* **Math Recurrence**:
  * `dp[i][w] = max(dp[i-1][w], dp[i-1][w-wt[i]] + val[i])` if `wt[i] <= w`
  * `dp[i][w] = dp[i-1][w]` otherwise
* **Gameplay**: Generates random item weights (1 to capacity) and values (2 to 12). Renders an input matrix where columns are capacities ($W=0 \dots \text{Cap}$) and rows are items.
* **Key Methods**:
  * `newRound()`: Generates 3 or 4 random items, computes the DP table solution, and updates the UI grid.
  * `check()`: Matches user matrix cells against calculated states.
* **Activity Key**: `"DP_KNAPSACK"`

### 3. LCS Game ([LCSGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/DPGamePanel.java#L344-L501))
Teaches 2D string alignment operations.
* **Math Recurrence**:
  * `dp[i][j] = dp[i-1][j-1] + 1` if `s1.charAt(i-1) == s2.charAt(j-1)`
  * `dp[i][j] = max(dp[i-1][j], dp[i][j-1])` otherwise
* **Gameplay**: Picks two random strings from a predefined word pool. Players fill the matching grid matrix.
* **Key Methods**:
  * `computeLCS()`: Populates the standard DP table solution.
  * `buildTable()`: Resizes the grid dynamically based on generated string lengths.
* **Activity Key**: `"DP_LCS"`

---

## UI Components & Styles
All DP sub-games utilize:
* **Theming**: Integrated colors from [ThemeManager](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java) (e.g., `BG_PRIMARY`, `BG_SURFACE`, `TEXT_PRIMARY`).
* **Interactive Elements**: Uses [RoundedButton](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components/RoundedButton.java) styles for actions.
* **Feedback Indicators**: Correct entries flash green (`new Color(30, 90, 55)`), wrong entries flash red (`new Color(110, 35, 42)`), and solutions use purple (`new Color(50, 46, 120)`).
