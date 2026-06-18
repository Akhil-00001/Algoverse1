# GreedyGamePanel Guide

The [GreedyGamePanel](file:///d:/Akhil%20don2/AlgoVerse/src/games/GreedyGamePanel.java) controls interactive game modes dedicated to Greedy heuristics. It incorporates a tabbed layout wrapping three sub-games that challenge players to determine if a greedy choice leads to an optimal solution.

---

## Architecture & Layout

* **Outer Container**: Extends `JPanel` using `BorderLayout` layout manager.
* **Tabs Layout**: Utilizes a `JTabbedPane` hosting:
  1. **Activity Selection** — [ActivitySelectionGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/GreedyGamePanel.java#L51)
  2. **Coin Change** — [CoinChangeGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/GreedyGamePanel.java#L227)
  3. **Fractional Knapsack** — [KnapsackGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/GreedyGamePanel.java#L353)

### Shared Gamification Hook
All sub-games use the package-private static helper [awardXP](file:///d:/Akhil%20don2/AlgoVerse/src/games/GreedyGamePanel.java#L41-L46) to update user records:
```java
static void awardXP(String id, int score, int time)
```
* Integrates with [SessionManager](file:///d:/Akhil%20don2/AlgoVerse/src/managers/SessionManager.java) to fetch the logged-in user.
* Grants **80 XP** to the user via [UserManager.completeAlgorithm](file:///d:/Akhil%20don2/AlgoVerse/src/managers/UserManager.java#L98).
* Records performance runs using [ScoreManager.recordResult](file:///d:/Akhil%20don2/AlgoVerse/src/managers/ScoreManager.java#L59).

---

## Sub-Game Modes

### 1. Activity Selection Game ([ActivitySelectionGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/GreedyGamePanel.java#L51-L222))
Teaches scheduling intervals.
* **Greedy Strategy**: Sort activities by their finish times. Pick the first activity, then sequentially select the next activity that starts *after* the last chosen activity's finish time.
* **Gameplay**: Generates 8 random activities. Players check checkboxes to select their proposed set of non-overlapping activities.
* **Key Methods**:
  * `computeGreedy()`: Sorts activity indices by finish time and returns a boolean array indicating which items are greedily selected.
  * `checkAnswer()`: Compares the user checkboxes selection against the calculated greedy array.
* **Activity Key**: `"GREEDY_ACTIVITY"`

### 2. Coin Change Game ([CoinChangeGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/GreedyGamePanel.java#L227-L348))
Demonstrates greedy choices in currency denominations.
* **Greedy Strategy**: Uses coin values: `₹1, ₹5, ₹10, ₹25, ₹50`. To achieve a target amount using the minimum number of coins, always select the largest coin denomination that is less than or equal to the remaining target.
* **Gameplay**: Generates a random target amount (5 to 100 cents). Players enter the counts of each coin denomination using spinners.
* **Key Methods**:
  * `computeGreedy()`: Calculates the optimal greedy counts starting from the highest coin value downwards.
  * `checkAnswer()`: Validates that the sum matches the target and that the coin count is optimal.
* **Activity Key**: `"GREEDY_COIN"`

### 3. Fractional Knapsack Game ([KnapsackGame](file:///d:/Akhil%20don2/AlgoVerse/src/games/GreedyGamePanel.java#L353-L496))
Teaches item selection based on density.
* **Greedy Strategy**: Sort items in descending order of their value-to-weight ratio (`value / weight`). Greedily place the dense items in the knapsack first, until capacity is reached.
* **Gameplay**: Generates 6 to 8 items with random weights and values, and a random total capacity. Players select items via checkboxes.
* **Key Methods**:
  * `computeGreedyValue()`: Sorts items by ratio and returns the maximum total value achievable by choosing full items greedily.
  * `checkAnswer()`: Ensures total weight does not exceed capacity and compares user value against the greedy heuristic.
* **Activity Key**: `"GREEDY_KNAPSACK"`

---

## UI Components & Feedback
* **Style Elements**: Integrates fonts and theme palettes from [ThemeManager](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java).
* **Controls**: Uses custom [RoundedButton](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components/RoundedButton.java) items for Submissions and Round Resets.
* **Success Indicator**: Feedback labels flash Green (`ACCENT_GREEN`) for correct/optimal answers, Yellow (`ACCENT_YELLOW`) for valid but sub-optimal solutions, and Red (`ERROR`) for incorrect values.
