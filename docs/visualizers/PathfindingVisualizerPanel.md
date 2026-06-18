# PathfindingVisualizerPanel Guide

The [PathfindingVisualizerPanel](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/PathfindingVisualizerPanel.java) implements grid-based shortest path simulators. It supports breadth-first, depth-first, and heuristic shortest path algorithms on a customizable 2D grid matrix.

---

## Grid Architecture

* **Dimensions**: 22 rows by 42 columns (`ROWS = 22`, `COLS = 42`).
* **Cell Attributes**: Cells are represented as integers inside `grid[ROWS][COLS]` with these states:
  * `EMPTY` (0): Accessible space.
  * `WALL` (1): Impassable obstacle blocking path traversals.
  * `START` (2): Source starting node (represented as "S").
  * `END` (3): Destination target node (represented as "E").
  * `VISITED` (4): Processed node.
  * `FRONTIER` (5): Nodes in processing queue (neighbors discovered).
  * `PATH` (6): Shortest path nodes traced back.

### Color Mapping ([CELL_COLORS](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/PathfindingVisualizerPanel.java#L26-L34))
* Uses HSL themes from `ThemeManager` (e.g. green for Start, pink/red for End, cyan for Visited, and the primary theme accent color for Path).

### Mouse Bindings ([GridCanvas](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/PathfindingVisualizerPanel.java#L95))
* **Left Click**: Toggles Wall nodes.
* **Right Click**: Cycles state: `Empty` $\rightarrow$ `Wall` $\rightarrow$ `Start` $\rightarrow$ `End`.

---

## Heuristics & Search Algorithms

Search loops run on a Swing `Timer` triggering [stepSearch()](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/PathfindingVisualizerPanel.java#L264) iteratively.

### 1. BFS (Breadth-First Search)
* Uses a standard FIFO Queue (`frontier`). Explores equally in all directions, guaranteeing the shortest path on unweighted grids.

### 2. DFS (Depth-First Search)
* Uses a LIFO Stack behavior (peek and poll from the back of the double-ended `frontier` LinkedList). Explores paths fully before backtracking.

### 3. Dijkstra
* Tracks distances in `dist[ROWS][COLS]` initialized to `Integer.MAX_VALUE`. At each step, [pickLowestCost()](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/PathfindingVisualizerPanel.java#L326) searches the frontier for the element with the lowest path cost.

### 4. A* Search
* Optimizes Dijkstra using a Manhattan distance heuristic function:
  ```java
  private int heuristic(int r, int c) {
      return Math.abs(r - endR) + Math.abs(c - endC);
  }
  ```
* [pickLowestFScore()](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/PathfindingVisualizerPanel.java#L335) selects nodes by minimizing $f(n) = g(n) + h(n)$ (where $g(n)$ is accumulated path cost, and $h(n)$ is the heuristic guess).

---

## Path Traceback & Completion

### Path Reconstruction
Once the target end node is reached, the visualizer traces the path backwards using the `cameFrom` map coordinate lookup keys (e.g. `"row,col"`) and flags cells as `PATH`:
```java
private void reconstructPath()
```

### Gamification & Scoring
Upon successfully finding a path, the visualizer awards the user **60 XP** via [awardXP()](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/PathfindingVisualizerPanel.java#L408-L416) using the activity key:
* `"PATH_" + algorithmName.toUpperCase()` (e.g. `PATH_BFS`, `PATH_A*`).
