package com.serendipity.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/12 14:24
 * FileName: Code_01_Exist
 * Description: com.toOffer_v2
 */
public class Code_01_Exist {
    public void exist(char[][] board, String word) {
        Map<Character, Character[]> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Character character = board[i][j];
                Character[] characters = new Character[4];
                if (i == 0) {
                    if (j == 0) {
                        if (board[0].length < 2) {
                            characters[2] = board[i][j + 1];
                        }
                        characters[3] = board[i + 1][j + 1];
                    } else {
                    }
                    map.put(character, characters);
                }
            }
        }
    }
}
