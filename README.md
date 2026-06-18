# AlgoVerse — Algorithm Visualizer & Learning Platform

An interactive Java-based platform for learning and practicing algorithms through gamified visualizations, interactive challenges, and real-time performance tracking.

##  Features

- **Interactive Algorithm Games**
  - **Dynamic Programming**: Fibonacci, 0/1 Knapsack, Longest Common Subsequence (LCS)
  - **Greedy Algorithms**: Greedy challenges with the scoring system
  - Real-time feedback and scoring

- **Algorithm Visualizers**
  - **Sorting Visualizer**: Step-by-step visualization of a sorting algorithms
  - **Pathfinding Visualizer**: Interactive maze solving and pathfinding demonstration
  - **Divide & Conquer Visualizer**: Visual breakdown of the divide & conquer algorithm

- **User Management**
  - User authentications (Login/Register)
  - User profiles with a statistics tracking
  - Session management

- **Gamification**
  - XP (Experience Points) system
  - Level progression
  - Daily streaks
  - Leaderboard rankings
  - Algorithm completion tracking

- **Dashboard**
  - Real-time statistics (Total XP, Level, Day Streak, Algorithms Completed)
  - Progress visualization with a animated progress bars
  - Quick access to different learning module

##  Requirements

- **Java**: JDK 17 or higher
- **System**: Windows/Linux/Mac with graphical interface

##  Installation & Setup

1. **Clone or extract the project**
   ```bash
   cd AlgoVerse1
   ```

2. **Run the application**
   - **Windows**: Double-click `run.bat`
   - **Linux/Mac**: 
     ```bash
     chmod +x run.sh
     ./run.sh
     ```

The `run.bat` script will:
- Verify Java installation
- Create necessary output and data directories
- Compile all Java source files
- Launch the application

##  Project Structure

```
AlgoVerse1/
├── src/
│   ├── Main.java                          # Application entry point
│   ├── games/                             # Algorithm game implementations
│   │   ├── DPGamePanel.java              # DP games (Fibonacci, Knapsack, LCS)
│   │   └── GreedyGamePanel.java          # Greedy algorithm challenges
│   ├── visualizers/                       # Algorithm visualization panels
│   │   ├── SortingVisualizerPanel.java
│   │   ├── PathfindingVisualizerPanel.java
│   │   └── DivideConquerPanel.java
│   ├── ui/                                # User interface components
│   │   ├── MainFrame.java                # Main application window
│   │   ├── LoginPanel.java               # Login screen
│   │   ├── RegisterPanel.java            # Registration screen
│   │   ├── DashboardPanel.java           # Main dashboard
│   │   ├── LeaderboardPanel.java         # Leaderboard view
│   │   ├── AlgorithmModule.java          # Algorithm module UI
│   │   ├── SidebarPanel.java             # Navigation sidebar
│   │   └── components/                   # Custom UI components
│   │       ├── RoundedButton.java
│   │       ├── RoundedPanel.java
│   │       ├── StatsCard.java
│   │       ├── AnimatedProgressBar.java
│   │       └── AlgorithmInfoPanel.java
│   ├── managers/                          # Business logic managers
│   │   ├── AuthManager.java              # Authentication logic
│   │   ├── UserManager.java              # User management
│   │   ├── ScoreManager.java             # Score tracking
│   │   └── SessionManager.java           # Session management
│   ├── models/                            # Data models
│   │   ├── User.java                     # User data model
│   │   └── Score.java                    # Score data model
│   ├── utils/                             # Utility classes
│   │   ├── FileUtil.java                 # File I/O operations
│   │   ├── PasswordUtil.java             # Password hashing/validation
│   │   └── ThemeManager.java             # UI theme management
│   └── visualizers/                       # Additional visualization panels
├── data/                                  # User data storage
│   ├── users.txt                         # User accounts
│   └── scores.txt                        # Score records
├── out/                                   # Compiled bytecode (generated)
├── run.bat                                # Build & run script (Windows)
└── README.md                              # This file
```

##  How to Use

### Starting the Application
1. Run `run.bat` (Windows) or `./run.sh` (Linux/Mac)
2. The application will compile and launch automatically

### User Account
- **New Users**: Click "Register" and create a account
- **Existing Users**: Login with your credentials
- Passwords are securely hashed before storage

### Navigation
- Use the **Sidebar** on the left to navigate between modules:
  - Dashboard: View your statistics and progress
  - Algorithm Games: Practice DP and Greedy algorithms
  - Visualizers: Watch algorithm execution
  - Leaderboard: See rankings and compare scores

### Playing Games
1. Select a game from the sidebar or dashboard
2. Follow the on-screen instructions
3. Complete challenges to earn XP
4. Scores are automatically saved to your profile

### Tracking Progress
- **XP System**: Earn experience points for each completed challenge
- **Levels**: Unlock levels as you gain XP
- **Streaks**: Maintain daily participation streaks
- **Leaderboard**: Compete with other users

##  Themes & UI

- **Modern Dark Theme**: Optimized for extended study sessions
- **Responsive Layout**: Adapts to different screen sizes
- **Custom Components**: Rounded buttons, animated progress bars, styled cards
- **Color-Coded Feedback**: Visual indicators for correct/incorrect answers

##  Data Storage

- **User Data**: Stored in `data/users.txt`
- **Score Records**: Stored in `data/scores.txt`
- Automatic backup and data persistence

## 🔧 Development

### Compiling Manually
```bash
javac -encoding UTF-8 -d out -sourcepath src src/**/*.java
```

### Running Compiled Code
```bash
java -cp out Main
```

### Key Technologies
- **Swing Framework**: Cross-platform GUI
- **File I/O**: For persistent data storage
- **Object-Oriented Design**: Modular architecture
- **MVC Pattern**: Separation of concerns

##  Algorithm Implementations

### Dynamic Programming Games
- **Fibonacci**: Classic DP problem with memoization
- **0/1 Knapsack**: Optimal items selection challenge
- **LCS**: Find longest common subsequence

### Greedy Algorithms
- Interactive greedy strategy challenges
- Real-time capacity and score tracking

### Visualizations
- Step-by-step algorithm execution
- Interactive visual feedback
- Adjustable animation speed

##  Troubleshooting

| Issue | Solution |
|-------|----------|
| Java not found | Install JDK 17+ and add to system PATH |
| Compilation errors | Check Java version: `java -version` |
| Data not saving | Ensure `data/` directory exists and is writable |
| UI not displaying correctly | Try maximizing the window or adjusting screen resolution |

##  Learning Resources

The platform is designed to teach:
- Algorithm fundamentals through gamification
- Problem-solving techniques
- Time complexity and optimization
- Visual understanding of algorithm execution

##  Performance Tracking

All completed algorithms record:
- Completion timestamp
- XP earned
- Time taken
- Accuracy/score

View statistics anytime on the Dashboard.



---

**Version**: 1.0  
**Last Updated**: June 2026  
**Platform**: Java Swing (Cross-platform)
