package com.example.leetcodegenerator;

import com.example.service.lcengine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.service.lcengine.loadExistingNumbers;

@SpringBootApplication
public class LeetcodegeneratorApplication {

	public static void main(String[] args) {

		lcengine.initial();

		try {
			// Load the existing numbers from the file first.
			loadExistingNumbers();

			int[] ans = lcengine.engine();

			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\ssunt\\OneDrive\\桌面\\letcodetracker.txt", true));
			StringBuilder sb = new StringBuilder();
			for (int n : ans) {
				sb.append(n);
				sb.append(" ");
			}
			bw.write(sb + "\r\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
