# FileUtil Guide

The [FileUtil](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java) class provides general-purpose helper functions for flat-file database storage. It supports line-by-line reading, writing, appending, and escaping field values to prevent parsing conflicts.

---

## File Database Delimiters

* **Field Delimiter**: Pipe character `|` ([DELIMITER](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L10)).
* **Collection Delimiter**: Comma `,` ([LIST_SEP](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L11)).
* **Null/Empty Value Marker**: Dash `-` ([EMPTY_MARKER](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L12)).

---

## Core File Operations

### 1. Reading Files
The [readLines(String)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L15) method reads all lines from a text file, filtering out empty rows and comment lines:
```java
public static List<String> readLines(String filePath)
```
* **Filtering**: Trims whitespace and ignores empty lines or lines starting with `#`.
* **Safety**: Returns an empty list if the target file does not exist.

### 2. Overwriting Files
The [writeLines(String, List<String>)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L32) method overwrites or creates a file with a given list of lines:
```java
public static void writeLines(String filePath, List<String> lines)
```
* Automatically creates all necessary parent directories before writing.

### 3. Appending to Files
The [appendLine(String, String)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L48) method appends a single line to the end of a file:
```java
public static void appendLine(String filePath, String line)
```

### 4. Ensuring File Existence
The [ensureFile(String)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L91) method checks if a file exists, creating it and its parent directories if not:
```java
public static void ensureFile(String filePath)
```

---

## Escaping & Delimiting Values

To prevent user inputs (like a custom username) containing the pipe character `|` from breaking the database parsing, values are escaped.

### Escaping
The [esc(String)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L62) method prepares a field for serialization:
```java
public static String esc(String value)
```
* Replaces any existing `|` character with `\|`.
* If the value is null or empty, it returns the empty marker `-`.

### Unescaping
The [unesc(String)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L68) method restores a field after deserialization:
```java
public static String unesc(String value)
```
* Replaces `\|` back with `|`.
* If the value is the empty marker `-`, it returns `""`.

### Joining Fields
The [join(String...)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L74) method joins multiple fields into a single pipe-delimited line:
```java
public static String join(String... fields)
```

### Splitting Lines
The [split(String)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/FileUtil.java#L84) method splits a pipe-delimited line back into its individual unescaped fields:
```java
public static String[] split(String line)
```
