Intelligent Ad Allocation Engine

A command-line based ad allocation system built using Java and MySQL that simulates how modern ad-tech platforms make real-time decisions on which advertisements to display. The system is designed to go beyond simple rule-based selection and instead models a state-aware decision engine that incorporates user behavior, advertiser constraints, and system-level controls.

_________________________________________________________________________________________________________________________________________________________________

Introduction :

Digital advertising systems operate under extremely dynamic conditions. Every user interaction creates an opportunity to serve an ad, and each decision must balance multiple competing factors — relevance, revenue, fairness, and constraints.

Most simplified systems treat each request independently. This leads to inefficient outcomes such as irrelevant ads, repeated exposure, poor budget usage, and lack of control over conflicts between advertisers.

This project attempts to model a more realistic system by introducing the concept of a continuous state engine, where every decision is influenced not only by the current request but also by accumulated system knowledge.

_________________________________________________________________________________________________________________________________________________________________


Problem :

Modern ad systems often suffer from three major structural limitations:
1.Contextual myopia They fail to connect long-term user intent with current page context, resulting in irrelevant or poorly targeted advertisements.
2.State fragmentation There is no consistent tracking of advertiser health such as budget pacing or ad fatigue, which leads to over-exposure or inefficient budget consumption.
3.Orchestration blindness The system lacks control over conflicts between advertisers, making it possible for competing brands to appear together or for allocation to ignore strategic constraints.

_________________________________________________________________________________________________________________________________________________________________

Objective

The goal of this project is to design and implement a state-aware ad allocation engine that:
● understands user intent using both current input and historical signals
● optimizes advertiser outcomes through budget pacing and performance tracking
● enforces system-level rules such as conflict avoidance and fair distribution
● selects ads in real time using efficient data structures and algorithms

_________________________________________________________________________________________________________________________________________________________________


System Overview
The system operates as a decision pipeline. For every user interaction, it performs a sequence of steps that progressively refine the candidate ads and select the most optimal ones.
At a high level, the system determines:
● which ads are relevant to the current context
● which ads are eligible based on constraints
● how each ad should be scored
● which ads should be selected for available slots
● what price should be paid using auction logic

_________________________________________________________________________________________________________________________________________________________________


Core Features :

1.Ad Allocation Engine
The central component responsible for making real-time decisions. It integrates scoring, filtering, conflict resolution, and auction logic into a single pipeline.

2. User Management
The system supports user registration and authentication, along with session handling. Each user interaction contributes to a history that is later used for personalization.

3. Advertiser Management
Advertisers can create ads, define bids, manage budgets, and specify conflicts with competitors. The system tracks their performance through impressions, clicks, and CTR.
4. Simulation Engine
A dedicated module allows testing different strategies and observing how the system behaves under varying conditions.
_________________________________________________________________________________________________________________________________________________________________


Ad Scoring Model :

Each ad is evaluated using a composite scoring function:
Score = bid × CTR × slotWeight × contextMatch × memoryBoost × fatigueControl
This formula ensures that:
● higher bids increase competitiveness
● better-performing ads are rewarded
● relevant context improves ranking
● recent user interactions influence personalization
● repeated exposure is controlled

_________________________________________________________________________________________________________________________________________________________________


Ad Slots
The system supports multiple ad slots, each with different visibility and importance.
Ad Slots Configuration

The system supports multiple ad slots, each with different visibility and importance.

| Slot     | Weight | Cooldown |
|----------|--------|----------|
| TOP      | 1.5    | 3 seconds |
| SIDEBAR  | 1.0    | 2 seconds |
| FOOTER   | 0.7    | 1 second  |

*Note:
- Higher weight increases the probability of an ad being selected during allocation.  
- Cooldown ensures that the same ad is not repeatedly shown in a short time, reducing ad fatigue and improving user experience.

_________________________________________________________________________________________________________________________________________________________________

Allocation Flow
The allocation process follows a structured pipeline:
1. the user enters a query or triggers a request
2. the system extracts keywords and context
3. matching ads are retrieved using trie-based search
4. irrelevant ads are filtered out based on constraints
5. conflict graph removes incompatible ads
6. remaining ads are scored using the scoring engine
7. a max heap selects the top candidates
8. a second-price auction determines the final cost
9. selected ads are assigned to available slots
    
_________________________________________________________________________________________________________________________________________________________________


Data Structures Used
1. The system makes extensive use of core data structures to ensure efficiency and scalability.
2. HashMap and HashSet Used for constant-time access to users, ads, and advertisers. Also used for tracking uniqueness and preventing duplicate allocations.
3. ArrayList Stores collections such as ads, campaigns, and slots. Enables efficient iteration and filtering.
4. Queue (Max Heap) Maintains ads ordered by score. Ensures that the highest priority ad can be retrieved efficiently.
5. Trie and Aho-Corasick Algorithm Used for keyword matching and context detection. Allows fast matching of multiple patterns within user queries and page content.
6. Sliding Window Tracks recent user interactions. Helps incorporate short-term memory into decision-making.
7. Conflict Graph Represents relationships between advertisers. Prevents conflicting ads from being displayed together.
8. Segment Tree Supports efficient range queries on metrics such as impressions and budget usage.

_________________________________________________________________________________________________________________________________________________________________

Algorithms Implemented
The system integrates multiple algorithms into a cohesive pipeline:
● greedy selection for real-time decision making
● multi-factor scoring and ranking algorithm
● constraint-based filtering to reduce candidate set
● heap operations (build, insert, extract, heapify)
● graph traversal and conflict resolution
● prefix matching and DFS traversal in trie
● hashing for constant-time operations

Each algorithm contributes to either performance optimization or decision quality.
Project Structure
app/ : main entry point
cli/ : command interface
engine/ : allocation and scoring logic
dsa/ : custom data structure implementations
context/ : keyword matching and analysis
manager/ : system controllers
repository/ : database interaction (JDBC)
model/ : core entities
database/ : schema and connection setup
logs/ : logging system
utils/ : helper utilities
exceptions/ : custom error handling

_________________________________________________________________________________________________________________________________________________________________

Tech Stack
● Java (core programming and object-oriented design)
● MySQL (persistent storage using JDBC)
● CLI interface for interaction

_________________________________________________________________________________________________________________________________________________________________

Running the Project
Load the database schema:
mysql -u root -p < database/Schema.sql
Compile the project:
javac -cp ".;mysql-connector-java.jar" app/Main.java
Run the application:
java -cp ".;mysql-connector-java.jar" app.Main

_________________________________________________________________________________________________________________________________________________________________

Example Scenario
A user searches for:
AI camera iPhone
The system:
● detects intent related to smartphones and cameras
● matches Apple ads with high relevance
● removes Samsung ads due to conflict
● ranks remaining ads using scoring
● allocates ads across slots based on priority
Metrics and Analytics
The system continuously tracks:
● click-through rate (CTR)
● impressions and clicks
● advertiser budget usage
● slot-wise performance
● popular search queries

These metrics help evaluate system effectiveness and advertiser ROI.

_________________________________________________________________________________________________________________________________________________________________


Why This Project Stands Out
This project is not a simple CRUD-based application. It demonstrates how core computer science concepts can be applied to build a real-time decision system.
It combines:
● multiple data structures working together
● real-time ranking and selection
● constraint handling using graph models
● auction-based pricing mechanisms

The result is a system that closely resembles the conceptual design of real-world ad platforms.

_________________________________________________________________________________________________________________________________________________________________

Conclusion
This project demonstrates how theoretical concepts from data structures and algorithms can be transformed into a practical system that solves a complex real-world problem.
It highlights the importance of combining efficiency, logic, and system design to build scalable and intelligent applications.
