package com.serendipity.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName LeetCode_1344_rankTeams
 * Description TODO
 * Author 11931
 * Date 2022-10-19:22:44
 * Version 1.0
 **/
public class LeetCode_1366_rankTeams {
    public static String rankTeams(String[] votes) {
        if (votes == null || votes.length == 0) {
            return "";
        }
        if (votes.length == 1) {
            return votes[0];
        }
        Map<Character, int[]> map = new HashMap<>();
        int teamsLen = votes[0].length();
        //["ABC","ACB","ABC","ACB","ACB"]
        for (int i = 0; i < teamsLen; i++) {
            char team = votes[0].charAt(i); // A B C
            int[] teamScore = new int[votes.length];
            for (int j = 0; j < votes.length; j++) {
                teamScore[j] = votes[j].indexOf(team);
            }
            map.put(team, teamScore);
        }
        // A:[0,0,0,0,0]
        // B:[1,2,1,2,2]
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < teamsLen; i++) {
            Character e = getE(map, votes[0], i);
            sb.append(e);
        }
        // 队伍数量
        return sb.toString();
    }

    public static Character getE(Map<Character, int[]> map, String vote, int target) {
        int max = 0;
        char maxTeam = ' ';
        for (int i = 0; i < map.keySet()
                               .size(); i++) {
            char team = vote.charAt(i);
            int[] ints = map.get(team);
            int ans = 0;
            for (int j = 0; j < ints.length; j++) {
                if (ints[j] == target) {
                    ans++;
                }
            }
            if (ans >= max) {
                maxTeam = team;
                max = ans;
            }
        }
        return maxTeam;
    }

    public static void main(String[] args) {
        String[] str = {"BCA", "CAB", "CBA", "ABC", "ACB", "BAC"};
        String s = rankTeams(str);
        System.out.println(s);
    }
}
