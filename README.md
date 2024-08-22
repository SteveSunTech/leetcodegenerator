# leetcodegenerator
generate 8 leetcode challenges daily with different category

## How to use

1. Go to LeetcodeGeneratorApplication, change this line: new FileWriter("ADDRESS OF YOUR TEXT FILE THAT YOU WANT TO PUT THE LEETCODE NUMBET", true)

2. Build Artifacts through your compiler, then find the jar file and write it into a bat file: (put: java -jar leetcodegenerator-0.0.1-SNAPSHOT.jar) into a text file and change the suffix to .bat, then run it

## Summary
This application is designed to significantly enhance the efficiency and effectiveness of LeetCode problem-solving by integrating a scientifically supported spaced repetition algorithm, grounded in the principles of the forgetting curve. This ensures that users not only practice a wide range of problems but also revisit them at optimal intervals to maximize retention and understanding.

Key Features and Implementation Details:
### Daily Problem Generation:
#### Functionality: The application generates three random LeetCode problems every day. Unlike traditional methods that categorize problems into specific domains (e.g., arrays, strings, dynamic programming), this application pools all problems into a single unified list.
#### Implementation: The problems are stored in a list, and each day, three unique problems are selected randomly. The randomness ensures a diverse range of topics and challenges, preventing the user from becoming too comfortable with any single category.

### Spaced Repetition Algorithm:
#### Functionality: The application uses a dynamic spaced repetition schedule that is designed based on the forgetting curve. This means that the intervals between subsequent reviews of the same problem increase over time (e.g., after 1 day, then after 3 days, 7 days, etc.). This approach is grounded in cognitive science, which shows that reviewing information at increasing intervals helps reinforce memory.
#### Implementation: Each problem’s review schedule is managed by a ReviewInfo class, which tracks the last time the problem was seen and the number of times it has been reviewed. The interval before the next review is calculated using an exponential backoff formula, where the interval doubles after each successful review. For example, if a problem was reviewed today, it will be scheduled for review again after 2 days, then 4 days, and so on.

### Priority Scheduling with a Priority Queue:
#### Functionality: The application uses a priority queue (min-heap) to manage the selection of problems due for review. Problems that are due for review soonest are given priority in the queue, ensuring that the user revisits problems at the most effective times to reinforce learning.
#### Implementation: The priority queue orders problems by their next scheduled review date. When the application generates the daily problems, it first checks the queue to select problems that are due for review. This ensures that the selected problems are both random and timely, adhering to the spaced repetition schedule.

### Prevention of Early Repeats:
#### Functionality: The application ensures that the same problem is not repeated within a short, scientifically unjustified timeframe. This prevents overfamiliarity with particular problems, which can lead to a false sense of mastery.
#### Implementation: The getUniqueRandom method ensures that any problem selected for review must not have been reviewed too recently (e.g., within the last 14 days). This is managed by checking the last review date stored in the problemHistory map before a problem is selected.

### Long-Term Coverage and Review Tracking:
#### Functionality: The application is designed to cover all the problems in the pool over approximately six months. This timeframe balances the need for comprehensive coverage with the principles of spaced repetition.
#### Implementation: The application's review algorithm tracks how often and when each problem has been reviewed. By adjusting the intervals dynamically based on review history, the system ensures that all problems are revisited appropriately within the desired timeframe. Additionally, the review schedule is saved to a persistent storage (e.g., a file) so that the progress is not lost between sessions.

### Persistent Storage for Review History:
#### Functionality: The application keeps a record of when each problem was last reviewed and how many times it has been reviewed.
#### Implementation: This information is saved in a file (letcodetracker.txt), which is loaded at the beginning of each session. The file format includes the problem number, the last review date, and the review count. This allows the application to pick up where it left off, maintaining continuity in the user’s practice sessions.

### Advantages:
Optimized Learning: By implementing a spaced repetition schedule, the application ensures that users are reviewing problems at the most effective intervals, leading to better retention and deeper understanding.
Comprehensive Problem Coverage: The method guarantees that all problems are covered within a reasonable period, with enough repetition to reinforce learning without leading to overfamiliarity.
Balanced Practice: Random selection ensures a diverse range of problems each day, keeping the practice challenging and varied.

## 总结
这个应用程序旨在通过整合科学支持的间隔重复算法，显著提高LeetCode问题练习的效率和效果。该算法基于遗忘曲线的原理，确保用户不仅练习了各种问题，还在最佳时间间隔内重温这些问题，以最大化记忆和理解。

主要功能及实现细节：
每日题目生成：

功能：应用程序每天生成三道随机的LeetCode题目。与传统方法不同，该方法不将问题按特定领域（如数组、字符串、动态编程）分类，而是将所有问题汇集到一个统一的列表中。
实现：问题存储在一个列表中，每天随机选择三个独特的问题。这种随机性确保涵盖各种主题和挑战，防止用户对任何单一类别过于熟悉。
间隔重复算法：

功能：应用程序使用基于遗忘曲线的动态间隔重复计划。这意味着同一问题的复习间隔随着时间的推移而增加（例如，1天后复习，然后3天后，7天后，等等）。这一方法基于认知科学的研究，显示在递增的间隔内复习信息有助于强化记忆。
实现：每个问题的复习计划由一个ReviewInfo类管理，该类跟踪问题上次被复习的时间和已经复习的次数。下次复习的间隔通过一个指数回退公式计算，每次成功复习后间隔时间加倍。例如，如果今天复习了一个问题，则2天后会再次复习，然后4天后，依此类推。
优先级调度与优先队列：

功能：应用程序使用优先队列（最小堆）来管理待复习问题的选择。即将到期的复习问题在队列中优先处理，确保用户在最有效的时间内重新接触这些问题以强化学习。
实现：优先队列根据问题的下次复习日期进行排序。当应用程序生成每日问题时，它首先检查队列，选择即将到期的复习问题。这确保选中的问题既是随机的，又符合间隔重复计划的要求。
防止过早重复：

功能：应用程序确保同一问题不会在短时间内重复出现，以避免不科学的重复。这防止用户对特定问题过于熟悉，从而导致虚假的掌握感。
实现：getUniqueRandom方法确保任何选定的问题在被选择之前，必须确保其最近一次复习的时间超过指定的间隔（例如14天内不重复）。这是通过检查存储在problemHistory映射中的最后一次复习日期来实现的。
长期覆盖与复习跟踪：

功能：应用程序旨在大约六个月内涵盖池中的所有问题。这个时间框架平衡了全面覆盖的需要与间隔重复的原则。
实现：应用程序的复习算法跟踪每个问题的复习频率和复习时间。通过根据复习历史动态调整间隔，系统确保所有问题在所需的时间范围内得到适当的复习。此外，复习计划保存到持久存储中（如文件），以确保在每次会话之间不会丢失进度。
复习历史的持久存储：

功能：应用程序记录每个问题的最后一次复习时间和复习次数。
实现：这些信息保存在一个文件（letcodetracker.txt）中，每次会话开始时加载。文件格式包括问题编号、最后复习日期和复习次数。这允许应用程序在下次会话中继续之前的进度，保持用户练习的连续性。
优势：
优化学习：通过实施间隔重复计划，应用程序确保用户在最有效的时间间隔内复习问题，从而增强记忆和理解。
全面覆盖问题：该方法保证所有问题在合理的时间内得到覆盖，并通过足够的重复强化学习，但不会导致过度熟悉。
平衡练习：随机选择确保每天练习涵盖各种问题，保持挑战性和多样性。


