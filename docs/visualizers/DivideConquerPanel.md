# DivideConquerPanel Guide

The [DivideConquerPanel](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/DivideConquerPanel.java) controls interactive visualizations of Divide and Conquer algorithms. It implements the [AlgorithmModule](file:///d:/Akhil%20don2/AlgoVerse/src/ui/AlgorithmModule.java) interface to fit inside the main application shell routing system.

---

## Component Layout & Navigation

* **Outer Container**: Extends `JPanel` with a `BorderLayout` layout.
* **Layout Design**:
  * **Center**: A nested JPanel utilizing a `CardLayout` (`cardsPanel`) to swap between sub-visualizers.
  * **East**: An [AlgorithmInfoPanel](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components/AlgorithmInfoPanel.java) (`infoPanel`) displaying theoretical explanations, complexities, and execution timing metrics.
* **Module Routing Integration**:
  * `getAlgorithms()`: Returns `{"Tower of Hanoi", "Binary Search", "Merge Sort Tree"}`.
  * `onAlgorithmSelected(String)`: Updates the info panel view, switches the CardLayout view, and resets visual states.

---

## Visualizer Modes

### 1. Tower of Hanoi ([HanoiPanel](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/DivideConquerPanel.java#L59))
Visualizes recursive disk scheduling between pegs.
* **Algorithm**: Runs the standard recursive algorithm `generateHanoi(diskCount, 0, 2, 1)` to pre-populate an array list of moves.
* **Execution Timing**: Captures CPU computation time in nanoseconds and passes it to the `infoPanel` timing callback:
  ```java
  long t0 = System.nanoTime();
  generateHanoi(diskCount, 0, 2, 1);
  long t1 = System.nanoTime();
  timeCallback.accept(t1 - t0);
  ```
* **Animation**: A JTimer pops disks from source pegs and pushes them to target pegs sequentially, repainting the pegs and disk rounded rectangles.

### 2. Binary Search ([BinarySearchPanel](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/DivideConquerPanel.java#L214))
Visualizes lookups in sorted collections.
* **Algorithm**: Performs binary search on a 20-element sorted array. Saves snapshots containing `{left, mid, right, target}` indices at each decision step.
* **Visual Rendering**: Displays array index values as horizontal cells:
  * **Left (L)** and **Right (R)** pointers are highlighted.
  * **Mid (M)** index cell flashes green when target matches, or shifts range markers.
  * Unsearched ranges are grayed out.

### 3. Merge Sort Tree ([MergeSortTreePanel](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/DivideConquerPanel.java#L364))
Visualizes the recursive divide-and-conquer call tree.
* **Algorithm**: Recursively divides a comma-separated array input (e.g. `38,27,43,3,9,82,10`) and logs the sub-array states at each recursion level.
* **Visual Rendering**: Paints a vertical branching tree layout where arrays at each level are color-coded by recursion depth.
