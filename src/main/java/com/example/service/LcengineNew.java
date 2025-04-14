package com.example.service;

import com.example.common.Problems;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@Component
public class LcengineNew {
    private static final String FILE_PATH = "C:\\Users\\ssunt\\OneDrive\\桌面\\letcodetracker.txt";
    public static List<Integer> prblms;
    private final Map<Integer, LocalDate> problemHistory = new HashMap<>();
    private final Problems problems;

    public LcengineNew(Problems problems) {
        this.problems = problems;
    }

    public void initial(String sourceType) {
        Set<Integer> allProblems = new HashSet<>();

        // 根据传入的参数选择题目来源
        switch (sourceType.toLowerCase()) {
            case "aws":
                allProblems.addAll(problems.aws);
                break;
            case "google":
                allProblems.addAll(problems.google);
                break;
            case "meta":
                allProblems.addAll(problems.meta);
                break;
            case "bytedance":
                allProblems.addAll(problems.bytedance);
                break;
            case "micro":
                allProblems.addAll(problems.micro);
                break;
            case "general":
            default:
                // 使用通用题目集合
                allProblems.addAll(problems.arr);
                allProblems.addAll(problems.list);
                allProblems.addAll(problems.str);
                allProblems.addAll(problems.tre);
                allProblems.addAll(problems.bktk);
                allProblems.addAll(problems.bi);
                allProblems.addAll(problems.dp);
                allProblems.addAll(problems.stk);
                allProblems.addAll(problems.curted);
                break;
        }

        prblms = new ArrayList<>(allProblems);
        System.out.println("Initialized problem set from: " + sourceType + " with " + prblms.size() + " problems");
    }

    // 兼容老代码的方法，默认general
    public void initial() {
        initial("general");
    }

    public void loadExistingNumbers() throws IOException {
        File file = new File(FILE_PATH);

        // 如果文件不存在，创建一个空文件
        if (!file.exists()) {
            file.createNewFile();
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.trim().split(" ");
            if (data.length >= 2) {
                try {
                    int problemNumber = Integer.parseInt(data[0]);
                    LocalDate lastSeenDate = LocalDate.parse(data[1]);
                    problemHistory.put(problemNumber, lastSeenDate);
                } catch (NumberFormatException | DateTimeParseException e) {
                    // 处理异常，例如记录日志，跳过无效行等
                    System.err.println("Error parsing line: " + line);
                }
            }
        }
        br.close();
    }

    public List<Integer> generateDailyProblems() throws IOException {
        List<Integer> selectedProblems = new ArrayList<>();
        Random rand = new Random();

        // 每次从文件中加载现有的题号
        loadExistingNumbers();

        // 检查是否有足够的题目可供选择
        if (prblms.isEmpty()) {
            throw new RuntimeException("No problems available in the selected list.");
        }

        int attempts = 0;
        while (selectedProblems.size() < 5 && attempts < 100) {
            // 确保有足够的随机题目可选
            if (prblms.size() == 0) {
                break;
            }

            int randomProblem = prblms.get(rand.nextInt(prblms.size()));

            // 检查是否存在于文件中以及是否符合重新生成的条件
            if (isProblemEligible(randomProblem) && !selectedProblems.contains(randomProblem)) {
                selectedProblems.add(randomProblem);
                updateFileWithNewProblem(randomProblem); // 生成后更新文件
            } else {
                System.out.println("Problem already selected or recently reviewed: " + randomProblem);
            }
            attempts++;
        }

        if (selectedProblems.size() < 5) {
            System.out.println("Warning: Could only generate " + selectedProblems.size() + " problems. Consider reviewing your problem pool or history criteria.");
        }

        return selectedProblems;
    }

    private boolean isProblemEligible(int problemNumber) {
        LocalDate lastSeenDate = problemHistory.get(problemNumber);
        return lastSeenDate == null || LocalDate.now().isAfter(lastSeenDate.plusDays(5));
    }

    private void updateFileWithNewProblem(int problemNumber) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(problemNumber + " " + LocalDate.now().toString());
        bw.newLine();
        bw.close();

        // 更新内存中的记录
        problemHistory.put(problemNumber, LocalDate.now());
    }
}