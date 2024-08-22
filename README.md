# leetcodegenerator
generate 8 leetcode challenges daily with different category

## How to use

1. Go to LeetcodeGeneratorApplication, change this line: new FileWriter("ADDRESS OF YOUR TEXT FILE THAT YOU WANT TO PUT THE LEETCODE NUMBET", true)

2. Build Artifacts through your compiler, then find the jar file and write it into a bat file: (put: java -jar leetcodegenerator-0.0.1-SNAPSHOT.jar) into a text file and change the suffix to .bat, then run it

## 项目简介 | Project Overview
LeetCode题目生成器是一款用于每天随机生成三道LeetCode题目的工具。它旨在帮助用户系统地复习LeetCode题目，避免题目在短时间内重复出现，并根据人类记忆曲线科学安排复习时间。该工具通过读取本地文件，确保生成的题目符合既定条件，并将新生成的题目记录到文件中，以便后续使用。

LeetCode Problem Generator is a tool designed to randomly generate three LeetCode problems daily. It helps users systematically review LeetCode problems by avoiding repetition in a short period and scientifically scheduling review times based on the human memory curve. The tool reads from a local file to ensure the generated problems meet the defined conditions and records the newly generated problems into the file for future use.

### 功能 | Features
随机生成题目：每天随机生成三道LeetCode题目，避免题目在短时间内重复。

记忆曲线复习：根据记忆曲线安排题目的复习时间，确保有效复习。

本地文件管理：通过读取和更新本地文件，跟踪已生成题目的历史记录。

去重处理：在生成新题目时，确保题目列表中的题号不会重复。

Random Problem Generation: Randomly generates three LeetCode problems daily, avoiding repetition within a short period.

Memory Curve Review: Schedules problem reviews based on the memory curve to ensure effective revision.

Local File Management: Tracks the history of generated problems by reading from and updating a local file.

De-duplication: Ensures that problem numbers in the list are not duplicated when generating new problems.

### 使用方法 | Usage
初始化问题列表：在程序启动时，通过initial()方法将所有LeetCode题目列表初始化，并去除重复题目。

生成每日题目：运行程序时，调用generateDailyProblems()方法，从去重后的列表中随机选择三道题目，并检查这些题目是否符合条件（即是否在5天内重复出现）。如果符合条件，则将题目记录到本地文件中。

更新文件：每次生成新题目后，程序会自动更新本地文件letcodetracker.txt，记录题号和生成日期。

Initialize Problem List: When the program starts, initialize the list of all LeetCode problems using the initial() method, removing duplicate problems.

Generate Daily Problems: When running the program, call the generateDailyProblems() method to randomly select three problems from the de-duplicated list and check if these problems meet the criteria (i.e., whether they have appeared in the last 5 days). If they meet the criteria, the problems are recorded in the local file.

Update File: After generating new problems, the program automatically updates the local file letcodetracker.txt, recording the problem numbers and the generation date.

### 系统需求 | System Requirements
Java 11或更高版本

Spring Boot 3.0或更高版本

访问本地文件系统的权限

Java 11 or later

Spring Boot 3.0 or later

Permission to access the local file system

### 文件结构 | File Structure
letcodetracker.txt：本地文件，用于存储已生成的题号和对应的日期。每行格式为题号 日期。

letcodetracker.txt: Local file used to store the generated problem numbers and corresponding dates. Each line is formatted as ProblemNumber Date.