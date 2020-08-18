package com.zenghm.algorithm;

import java.util.*;

/**
 * @author ZJBR
 * @date 2020/8/13
 * @description xxx
 */
public class MeetingMaxValue {
    /**
     * @param meeting: the meetings
     * @param value: the value
     * @return: calculate the max value
     */
    public int maxValue(int[][] meeting, int[] value) {
        // write your code here
        return 0;
    }

    public int[][][] meetingGroup(int[][] meeting,int[] value){
        List<Meeting> meetingList = new ArrayList<Meeting>(meeting.length);
        for (int meetOrder=0;meetOrder<meeting.length;meetOrder++){
            meetingList.add(new Meeting(meeting[meetOrder][0],meeting[meetOrder][1],value[meetOrder]));
        }
        meetingList.sort(Comparator.comparing(o -> o.start));
        List<Set<Meeting>> groups = new ArrayList<Set<Meeting>>();
        for (Meeting meeting1:meetingList){

        }
        return null;
    }

    class Meeting implements  Comparable<Meeting>{
        Integer start;
        Integer end;
        Integer value;

        public Meeting(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Meeting o) {
            return start.compareTo(o.start);
        }
    }

    /**
     * @param offset: the number of items that the customer has viewed
     * @param n: the number of items that can be displayed on a page
     * @param len1: the length of L1
     * @param len2: the length of L2
     * @return: returns the intervals of goods displayed in L1 and L2
     */
    public List<Integer> ProductList(int offset, int n, int len1, int len2) {
        if(offset+n<=len1){
            Integer[] r = new Integer[]{offset,offset+n,0,0};
            return Arrays.asList(r);
        }else if(offset<len1&&offset+n>len1){
            Integer[] r = new Integer[]{offset,len1,0,n+offset-len1};
            return Arrays.asList(r);
        }else if(offset+n<=len1+len2){
            Integer[] r = new Integer[]{0,0,offset,offset+n};
            return Arrays.asList(r);
        }else if(offset<len1+len2&&offset+n>len1+len2){
            Integer[] r = new Integer[]{0,0,offset,len2};
            return Arrays.asList(r);
        }else {
            Integer[] r = new Integer[]{0,0,0,0};
            return Arrays.asList(r);
        }
    }
}
