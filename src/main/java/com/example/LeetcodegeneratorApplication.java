package com.example;

import com.example.service.LcengineNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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
            // 获取命令行参数，默认为general
            String sourceType = "general";
            if (args.length > 0) {
                sourceType = args[0].toLowerCase();
            }

            // 初始化指定来源的问题集合
            lcEngine.initial(sourceType);

            // 生成每日题目
            List<Integer> dailyProblems = lcEngine.generateDailyProblems();

            // 打印生成的题号
            System.out.println("Today's problems: " + dailyProblems);
            System.out.println("Press any key to continue");

            new Scanner(System.in).nextLine();

            // 正常退出应用程序
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}