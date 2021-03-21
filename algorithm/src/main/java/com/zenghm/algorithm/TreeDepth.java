package com.zenghm.algorithm;

import java.util.ArrayList;

/**
 * @author Airlen
 * @date 2021/3/20
 * @description xxx
 */
public class TreeDepth {


    public class TreeNode {
      int val = 0;
      TreeNode left = null;
      TreeNode right = null;
    }

    /**
     *
     * @param root TreeNode类
     * @return int整型
     */
    public int maxDepth (TreeNode root) {
        // write code here、
        if(root==null){
            return 0;
        }
        ArrayList<Integer> depths = new ArrayList<Integer>();
        int depth = 0;
        innerDepth(root,depth,depths);
        return depths.stream().max(Integer::compareTo).get();
    }

    void innerDepth(TreeNode node,Integer currentDepth,ArrayList<Integer> arr){
        if(node!=null){
            currentDepth++;
            innerDepth(node.left,currentDepth,arr);
            innerDepth(node.right,currentDepth,arr);
        }else{
            arr.add(currentDepth);
        }
    }
}
