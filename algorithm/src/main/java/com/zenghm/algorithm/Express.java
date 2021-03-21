package com.zenghm.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Airlen
 * @date 2021/3/20
 * @description xxx
 */
public class Express {
    private static char CH1 = '?';
    private static char CH2 = '*';

    public boolean isMatch(String s, String p) {
        int sIndex = 0, pIndex = 0;
        return innerMatch(sIndex, pIndex, s, p);
    }

    private boolean innerMatch(int sStartIndex, int pStartIndex, String s, String p) {
        int sIndex = sStartIndex, pIndex = pStartIndex;
        boolean flag = false;
        int flag_s = 0, flag_p = 0;
        while (sIndex < s.length()) {
            if (s.charAt(sIndex) == p.charAt(pIndex) || CH1 == p.charAt(pIndex)) {
                sIndex++;
                pIndex++;
            } else if (CH2 == p.charAt(pIndex)) {
                //1、* 认为是无符号对应
                flag = true;
                flag_p = ++pIndex;
                flag_s = sIndex;
            } else {
                if (flag) {
                    sIndex = ++flag_s;
                    pIndex = flag_p;
                } else {
                    return false;
                }
            }
        }
        if (pIndex < p.length() && CH2 == p.charAt(pIndex)) {
            pIndex++;
        }
        if (sIndex == s.length() && pIndex == p.length()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断岛屿数量
     *
     * @param grid char字符型二维数组
     * @return int整型
     */
    public int solve(char[][] grid) {
        // write code here
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        int count = 0;
        for (int indexr = 0; indexr < nr; indexr++) {
            for (int indexc = 0; indexc < nc; indexc++) {
                if (grid[indexr][indexc] == '1') {
                    count++;
                    dsf(grid, indexr, indexc);
                }
            }
        }
        return count;
    }

    void dsf(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        if (r < 0 || c < 0 || r >=nr || c >= nc || grid[r][c] == '0'){
            return;
        }
        grid[r][c] = '0';
        dsf(grid, r - 1, c);
        dsf(grid, r + 1, c);
        dsf(grid, r, c + 1);
        dsf(grid, r, c - 1);
    }
/////////////////////////////////////////////////////////////////////////////////
    public int FirstNotRepeatingChar(String str) {
        if(str==null||str.length()==0){
            return -1;
        }
        Map<Character,Boolean> map = new LinkedHashMap<>();
        for(int  i=0;i<str.length();i++){
            Character ch = str.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch,Boolean.FALSE);
            }else{
                map.put(ch,Boolean.TRUE);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        //Express express = new Express();
        //Boolean isMatch = express.isMatch("aa", "*");
        int  or = 2&6;
        System.out.println(or);
        //System.out.println(isMatch);
    }
}
