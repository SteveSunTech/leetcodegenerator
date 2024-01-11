package com.example.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class lcengine {
    public static List<Integer> arr;
    public static List<Integer> list;
    public static List<Integer> str;
    public static List<Integer> tre;
    public static List<Integer> bktk;
    public static List<Integer> bi;
    public static List<Integer> dp;
    public static List<Integer> stk;
    public static List<Integer> other;
    private static Map<Integer, Integer> existingNumbers = new HashMap<>();

    public static void initial() {
        arr = Arrays.asList(27,
                26,
                80,
                277,
                189,
                299,
                134,
                118,
                119,
                229,
                274,
                275,
                243,
                244,
                245,
                217,
                219,
                220,
                55,
                45,
                121,
                122,
                309,
                11,
                42,
                334,
                128,
                164,
                287,
                135,
                330,
                4,
                289,
                57,
                56,
                252,
                253,
                239,
                295,
                53,
                325,
                209,
                238,
                152	,
                228,
                163,
                88,
                75,
                283,
                376,
                280,
                33,
                300,
                998,
                560,
                1010,
                523,
                487,
                554,
                27,
                53,
                48
        );

        list = Arrays.asList(206,
                141,
                24,
                328,
                92,
                237,
                19,
                83,
                203,
                82,
                369,
                2,
                160,
                21,
                234,
                143,
                142,
                148,
                25,
                61,
                86,
                23,
                147,
                25,
                127,
                138);

        str = Arrays.asList(28,
                14,
                58,
                387,
                383,
                1002,
                844,
                389,
                344,
                151,
                186,
                791,
                358,
                288,
                345,
                647,
                205,
                293,
                294,
                290,
                242,
                49,
                249,
                87,
                161,
                38,
                358,
                316,
                271,
                168,
                171,
                13,
                12,
                273,
                246,
                247,
                248,
                157,
                158,
                76,
                30,
                3,
                340,
                395,
                159,
                125,
                266,
                5,
                9,
                214,
                516,
                336,
                131,
                132,
                267,
                20,
                22,
                32,
                241,
                301,
                392,
                115,
                187);

        tre = Arrays.asList(144,
                94,
                145,
                102,
                100,
                101,
                226,
                257,
                112,
                113,
                222,
                95,
                129,
                298,
                111,
                104,
                110,
                124,
                250,
                366,
                337,
                107,
                103,
                199,
                98,
                235,
                236,
                108,
                108,
                109,
                173,
                230,
                297,
                285,
                270,
                272,
                99,
                116,
                117,
                314,
                96,
                993,
                662,
                268,
                150);

        bktk = Arrays.asList(78,
                90,
                77,
                39,
                40,
                216,
                377,
                254,
                46,
                47,
                31,
                60,
                139,
                17,
                417,
                694,
                320,
                93,
                282,
                140,
                351,
                200,
                286,
                130,
                339,
                364,
                320,
                127,
                994,
                773,
                329,
                51,
                52,
                126,
                133,
                399,
                486,
                310,
                62,
                356,
                261,
                323,
                305);

        bi = Arrays.asList(278,
                35,
                81,
                153,
                154,
                162,
                374,
                34,
                349,
                4,
                350,
                300,
                48,
                54,
                59,
                73,
                329,
                378,
                74,
                240,
                370,
                79,
                296,
                317,
                258,
                875,
                410,
                139,
                302);

        dp = Arrays.asList(70,
                62,
                63,
                120,
                279,
                139,
                375,
                312,
                322,
                256,
                265,
                64,
                72,
                97,
                174,
                221,
                85,
                363,
                198,
                213,
                276,
                91,
                10,
                44,
                389,
                136,
                318,
                393,
                201,
                371,
                338,
                207,
                210,
                269,
                89,
                146,
                268,
                198,
                213,
                337,
                256,
                931,
                191,
                190,
                137,
                260,
                384,
                398,
                382,
                380,
                381,
                138);

        stk = Arrays.asList(155,
                232,
                225,
                150,
                739,
                735,
                84,
                769,
                71,
                388,
                394,
                224,
                227,
                856,
                385,
                84,
                215,
                347,
                313,
                373,
                332,
                341,
                224,
                227,
                772,
                207,
                210,
                269,
                1472);

    }

    public static void loadExistingNumbers() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ssunt\\OneDrive\\桌面\\letcodetracker.txt"));
        String line;
        int index = 0;
        while ((line = br.readLine()) != null) {
            String[] numbers = line.trim().split(" ");
            for (String num : numbers) {
                if (!num.isEmpty()) { // Only parse if the string is not empty
                    existingNumbers.put(Integer.parseInt(num), index++);
                }
            }
        }
        br.close();
    }

    private static int getUniqueRandom(List<Integer> list) {
        int randNum;
        randNum = new Random().nextInt(list.size());

        if(!existingNumbers.containsKey(list.get(randNum))) {
            return list.get(randNum);
        }else {
            while(existingNumbers.containsKey(list.get(randNum)) && existingNumbers.size() - existingNumbers.get(list.get(randNum)) < 14) {
                randNum = new Random().nextInt(list.size());
            }
        }
        return list.get(randNum);
    }

    public static int[] engine() {
        int[] ans = new int[8];

        ans[0] = getUniqueRandom(arr);
        ans[1] = getUniqueRandom(list);
        ans[2] = getUniqueRandom(bktk);
        ans[3] = getUniqueRandom(dp);
        ans[4] = getUniqueRandom(str);
        ans[5] = getUniqueRandom(stk);
        ans[6] = getUniqueRandom(bi);
        ans[7] = getUniqueRandom(tre);

        return ans;
    }
}
