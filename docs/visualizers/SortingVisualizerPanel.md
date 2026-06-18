# SortingVisualizerPanel Guide

The [SortingVisualizerPanel](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java) controls sorting algorithm visualizations. It draws values as vertical bars of proportional heights and captures comparative snapshots at each algorithmic iteration.

---

## Animation State Architecture

To visualizer array sorting step-by-step without blocking Swing's UI Thread, the panel uses an offline step generation approach.

### The Step Record
Prior to starting the animation, the visualizer runs the chosen sorting algorithm on a clone of the array and logs each operational state into a list of [Step](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java#L26) objects:
```java
private record Step(int[] arr, int[] compare, int[] swap, int[] sorted, int pivot, String msg)
```
* **`arr`**: The current state of the array values.
* **`compare`**: Array indices undergoing value comparisons.
* **`swap`**: Array indices undergoing value exchanges.
* **`sorted`**: Array indices confirmed to be in their final sorted positions.
* **`pivot`**: The pivot element index (used in Quick Sort).
* **`msg`**: Description text of the step (e.g. `"Comparing 5 & 8"`, `"Swapped!"`).

### Visual Color Coding ([CELL_COLORS](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java#L18-L23))
* **Default Bar**: Purple (`COL_DEFAULT`)
* **Comparison Bar**: Yellow (`COL_COMPARE`)
* **Swap/Move Bar**: Pink (`COL_SWAP`)
* **Sorted Bar**: Green (`COL_SORTED`)
* **Pivot Bar**: Orange (`COL_PIVOT`)
* **Current Marker**: Cyan (`COL_CURRENT`)

---

## Supported Sorting Algorithms

Each sorting function modifies a cloned array and registers steps by calling [addStep()](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java#L260):

### 1. Bubble Sort ([bubbleSort](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java#L265))
* Iteratively compares adjacent items and swaps them if they are in the wrong order. Gradually marks elements from the end of the array as sorted.

### 2. Selection Sort ([selectionSort](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java#L281))
* Divides the array into sorted and unsorted sections. Repeatedly searches the unsorted section for the minimum value and swaps it into the next sorted position.

### 3. Insertion Sort ([insertionSort](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java#L299))
* Gradually builds up a sorted section at the front of the array by shifting elements out of the way to insert the current key element in its proper place.

### 4. Merge Sort ([mergeSortSteps](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java#L316))
* Recursive divide-and-conquer algorithm. Splits arrays into halves, sorts them, and then merges them back together. Logs temporary merge indices as compare states.

### 5. Quick Sort ([quickSortSteps](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java#L340))
* Partitions the array around a pivot element. Recursively sorts elements less than the pivot and elements greater than the pivot. Displays pivot elements in orange.

### 6. Heap Sort ([heapSort](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java#L363))
* Builds a binary max-heap. Continually extracts the maximum element from the root and re-heapifies the remaining elements.

---

## Interactive Controls & UI

* **JSlider Speed Control**: Maps slider inputs to timer delay ticks:
  $$\text{Delay} = \max(5, 210 - \text{speedValue})$$
* **Custom Array Parser**: Parses comma-separated values from the input field (e.g. `5,3,8,1,9`), validating integers.
* **Gamification & Scoring**: Calls [awardXP()](file:///d:/Akhil%20don2/AlgoVerse/src/visualizers/SortingVisualizerPanel.java#L468-L476) on completion to award the user **50 XP** under the database key:
  * `"SORT_" + currentAlgorithm.replace(" ", "_").toUpperCase()` (e.g. `SORT_BUBBLE_SORT`, `SORT_MERGE_SORT`).
