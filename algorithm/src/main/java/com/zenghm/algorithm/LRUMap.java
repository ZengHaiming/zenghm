package com.zenghm.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 最近最少使用集合 ，非线程安全实现
 */
class LRUMap {
    public class LRU<K, V> {
        private HashMap<K, LinkNode> hashMap;
        private int count;
        private LinkNode head;
        private LinkNode last;

        public LRU(int num) {
            this.count = num;
            hashMap = new HashMap<K, LinkNode>(num);
        }

        class LinkNode {
            K key;
            V value;
            LinkNode before;
            LinkNode after;

            LinkNode(K key,V val) {
                this.key = key;
                this.value = val;
            }
        }

        public void put(K key, V value) {
            LinkNode node = new LinkNode(key,value);
            if (hashMap.size() == 0) {
                head = node;
                last = node;
            } else if (hashMap.size() < count) {
                node.after = head;
                head.before = node;
                head = node;
            } else {
                hashMap.remove(last.key);

                node.after = head;
                head.before = node;
                head = node;

                last = last.before;
                last.after = null;

            }
            hashMap.put(key, node);
        }

        public V get(K key) {
            if (!hashMap.containsKey(key)) {
                return null;
            }

            LinkNode node = hashMap.get(key);
            //当前取的不是第一个
            if(node!=head){
                //不是最后一个
                if(node.after!=null){
                    node.before.after = node.after;
                    node.after.before = node.before;
                }else {
                    //最后一个
                    node.before.after = null;
                    last = node.before;
                }
                head.before = node;
                node.after = head;
                node.before = null;
                head = node;
            }
            return node.value;
        }
    }
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        LRU<Integer,Integer> lru = new LRU<Integer,Integer>(k);
        if(operators==null||operators.length==0){
            return new int[0];
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int[] operator:operators){
            if(operator[0]==1){
                lru.put(operator[1],operator[2]);
            }
            if(operator[0]==2){
                Integer temp = lru.get(operator[1]);
                if(temp==null){
                    result.add(-1);
                }else{
                    result.add(temp);
                }
            }
        }
        int[] getResult = new int[result.size()];
        int indedx = 0;
        for (Integer r :result){
            getResult[indedx] = r;
            indedx++;
        }
        return getResult;
    }

    /**
     *
     * @param n int整型
     * @param m int整型
     * @return int整型
     */
    public int ysf (int n, int m) {
        // write code here
        ArrayList<Integer> numberList = new ArrayList<Integer>(n);
        for(int i =1 ;i<=n;i++){
            numberList.add(i);
        }
        int count = 0;
        while(numberList.size()!=1){
            Iterator<Integer> it = numberList.iterator();
            while(it.hasNext()){
                it.next();
                count++;
                if(count%m==0){
                    it.remove();
                }
            }
        }
        return numberList.get(0);
    }

    public static void main(String[] args) {
//        int[][] test = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
//        int k = 3;
//        Solution solution = new Solution();
//        int[] r = solution.LRU(test,k);
//        System.out.println(r);
        LRUMap solution = new LRUMap();
        int r = solution.ysf(5,2);
        System.out.println(r);
    }
}




