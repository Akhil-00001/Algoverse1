# ThemeManager Guide

The [ThemeManager](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java) class governs the look and feel, color constants, typography, and theme styling configurations of the AlgoVerse Swing client.

---

## Theme Color Palette

### 1. Backgrounds
* `BG_PRIMARY`: Deep dark core layout canvas background (`#0D0D1A`).
* `BG_SECONDARY`: Sidebar and visualization container background (`#13132A`).
* `BG_SURFACE`: Form input surface elements (`#1A1A3E`).
* `BG_CARD`: Dashboard stats indicators card background (`#1E1E45`).

### 2. Accents & Indicators
* `ACCENT` / `ACCENT_HOVER`: Primary brand color purple (`#6C63FF` / `#8B84FF`).
* `ACCENT_PINK` / `ACCENT_GREEN` / `ACCENT_YELLOW` / `ACCENT_CYAN` / `ACCENT_ORANGE`: Highlighting values, visual paths, or charts.
* `BORDER`: Standard line border color (`#2A2A5A`).

### 3. Typography Styles
* Titles: `Segoe UI Bold, 30` ([FONT_TITLE](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java#L37)).
* Large headers: `Segoe UI Bold, 22` ([FONT_LARGE](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java#L38)).
* Buttons & sub-headers: `Segoe UI Bold, 16` ([FONT_MEDIUM](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java#L39)).
* Standard text: `Segoe UI Plain, 14` ([FONT_NORMAL](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java#L40)).
* Captions & status: `Segoe UI Plain, 12` ([FONT_SMALL](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java#L41)).
* Data arrays & input fields: `Consolas Plain, 13` ([FONT_MONO](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java#L42)).

---

## Styling Configurations

The [applyDarkTheme()](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java#L45) method configures the LookAndFeel at startup.

### 1. FlatLaf Integration (Preferred)
* Attempts to load `com.formdev.flatlaf.FlatDarkLaf` dynamically via reflection.
* If found, sets standard UI arcs:
  * Buttons: 12px.
  * Panels/Components: 10px.
  * Progress Bars: 8px.
  * Text Fields: 8px.

### 2. Manual Fallback
* If FlatLaf is not present, falls back to Java's Cross-Platform LookAndFeel and manually injects the ThemeManager colors, caret colors, selection highlights, and borders into the global `UIManager` map.

---

## Graphics Helpers

### 1. Color Interpolation
The [lerp(Color, Color, float)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java#L130) method computes a linear interpolation between two colors:
```java
public static Color lerp(Color a, Color b, float t)
```
* **Formula**:
  $$C_{\text{out}} = C_a + (C_b - C_a) \times t \quad \text{for } t \in [0.0, 1.0]$$
* **Usage**: Powering custom mouse hover animations (e.g. in [RoundedButton](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components/RoundedButton.java)).

### 2. Gradient Paint
The [paintGradient(Graphics2D, Color, Color, int, int, int, int)](file:///d:/Akhil%20don2/AlgoVerse/src/utils/ThemeManager.java#L140) helper fills a rectangular shape with a horizontal linear gradient:
```java
public static void paintGradient(Graphics2D g2, Color c1, Color c2, int x, int y, int w, int h)
```
