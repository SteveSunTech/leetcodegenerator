package com.example.service;

import com.example.common.Problems;
import org.springframework.beans.factory.parsing.Problem;
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

    public void initial() {
        Set<Integer> allProblems = new HashSet<>();
        allProblems.addAll(problems.arr);
        allProblems.addAll(problems.list);
        allProblems.addAll(problems.str);
        allProblems.addAll(problems.tre);
        allProblems.addAll(problems.bktk);
        allProblems.addAll(problems.bi);
        allProblems.addAll(problems.dp);
        allProblems.addAll(problems.stk);
        allProblems.addAll(problems.curted);
        allProblems.addAll(problems.aws);
        allProblems.addAll(problems.google);
        allProblems.addAll(problems.meta);
        allProblems.addAll(problems.linkedin);
        allProblems.addAll(problems.micro);

        prblms = new ArrayList<>(allProblems);
    }

    public void loadExistingNumbers() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
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
                    e.printStackTrace();
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

        int attempts = 0;
        while (selectedProblems.size() < 3 && attempts < 100) {
            int randomProblem = prblms.get(rand.nextInt(prblms.size()));

            // 检查是否存在于文件中以及是否符合重新生成的条件
            if (isProblemEligible(randomProblem)) {
                selectedProblems.add(randomProblem);
                updateFileWithNewProblem(randomProblem); // 生成后更新文件
            } else {
                System.out.println("Problem already selected or recently reviewed: " + randomProblem);
            }
            attempts++;
        }

        if (selectedProblems.size() < 3) {
            throw new RuntimeException("Failed to generate 3 unique problems after 100 attempts.");
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
    }
}