package com.CK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public int findTilt(TreeNode root) {
        return tiltOfTree(root);
    }

    private int tiltOfTree(TreeNode root){
        if (root == null) return 0;
        int tiltNode = tiltOfNode(root);
        int tiltLeft = root.left == null ? 0 : tiltOfTree(root.left);
        int tiltRight = root.right == null ? 0 : tiltOfTree(root.right);
        return tiltLeft + tiltNode + tiltRight;
    }

    private int tiltOfNode(TreeNode root) {
        if (root == null) return 0;
        int leftSum = root.left == null ? 0 : sumOfNode(root.left);
        int rightSum = root.right == null ? 0 : sumOfNode(root.right);
        return Math.abs(leftSum - rightSum);
    }

    private int sumOfNode(TreeNode node) {
        if (node == null) return 0;
        int leftSum = node.left == null ? 0 : sumOfNode(node.left);
        int rightSum = node.right == null ? 0 : sumOfNode(node.right);
        return leftSum + rightSum + node.val;
    }
}

// Using Recursion
class Solution2 {
    int tilt = 0;
    public int findTilt(TreeNode root) {
        traverse(root);
        return tilt;
    }
    public int traverse(TreeNode root)
    {
        if (root == null )
            return 0;
        int left = traverse(root.left);
        int right = traverse(root.right);
        tilt += Math.abs(left-right);
        return left + right + root.val;
    }
}

public class Main {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            int ret = new Solution().findTilt(root);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}