# UI Components Guide

The **[ui.components](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components)** package contains the custom JComponents and JPanels utilized to build the AlgoVerse UI.

---

## 1. RoundedPanel ([RoundedPanel.java](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components/RoundedPanel.java))
An extension of `JPanel` that paints custom rounded rectangles instead of default sharp panels.

### Visual Configurations
* **Arc Radius**: Customizable arc values (default $16$).
* **Gradients**: Supports dual-color horizontal/vertical gradient fills via `withGradient()`.
* **Borders**: Render custom outline strokes with specified color and thickness via `withBorder()`.

### Implementation Strategy
* Overrides `paintComponent` to draw vector round rectangles (`RoundRectangle2D.Float`).
* Disables standard border rendering (`paintBorder`) to prevent Swing from painting default borders.
* Example:
  ```java
  RoundedPanel panel = new RoundedPanel(ThemeManager.BG_CARD, 16)
          .withBorder(ThemeManager.BORDER, 1.5f);
  ```

---

## 2. RoundedButton ([RoundedButton.java](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components/RoundedButton.java))
A custom button component that implements modern styles and micro-animations.

### Visual Styles ([Style](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components/RoundedButton.java#L12))
* `PRIMARY`: Gradient fill between the Accent color and purple.
* `SECONDARY`: Dark background with an accent-fading border.
* `DANGER`: Solid red warning button.
* `GHOST`: Borderless design that fades in a light background overlay.

### Micro-Animations
* **Hover Transition**: Integrates a Swing `Timer` (firing every $16$ ms) to interpolate a `hoverAlpha` variable between `0.0f` and `1.0f`.
* **Color LERPing**: Uses `ThemeManager.lerp` to smoothly mix base and hover colors based on the current `hoverAlpha`.
* **Press Effect**: Renders a dark, semi-transparent overlay when the mouse is pressed.

---

## 3. AnimatedProgressBar ([AnimatedProgressBar.java](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components/AnimatedProgressBar.java))
A JComponent representing a progress tracker (e.g. user XP progress towards the next level).

### Features
* **Linear Interpolation**: Instantiates a Swing `Timer` that smoothly increments the visible progress towards the target progress value:
  $$\text{current} = \text{current} + (\text{target} - \text{current}) \times 0.08$$
* **Text Rendering**: Renders a centered text label inside the progress bar.
* **Gradients**: The fill color is drawn as a linear gradient from `fillColor1` to `fillColor2`.

---

## 4. StatsCard ([StatsCard.java](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components/StatsCard.java))
An information card used in the user dashboard to display scores, level progression, or active streaks.

### Structure
* Inherits from **[RoundedPanel](file:///d:/Akhil%20don2/AlgoVerse/src/ui/components/RoundedPanel.java)**.
* **Upper Section**: Displays a large emoji icon label (using Segoe UI Emoji font).
* **Lower Section**: Displays a large value label (e.g. `1200 XP`) and a smaller uppercase description title label.
* **Border Styling**: Creates a transparent accent color border at startup:
  ```java
  withBorder(new Color(accent.getRed(), accent.getGreen(), accent.getBlue(), 60));
  ```
