package com.example;

import com.example.service.LcengineNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class LeetcodegeneratorApplication implements CommandLineRunner {

    private final LcengineNew lcEngine;

    @Autowired
    public LeetcodegeneratorApplication(LcengineNew lcEngine) {
        this.lcEngine = lcEngine;
    }

    public static void main(String[] args) {
        SpringApplication.run(LeetcodegeneratorApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            // 初始化所有问题的集合
            lcEngine.initial();

            // 生成每日题目
            List<Integer> dailyProblems = lcEngine.generateDailyProblems();

            // 打印生成的题号
            System.out.println("Today's problems: " + dailyProblems);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}