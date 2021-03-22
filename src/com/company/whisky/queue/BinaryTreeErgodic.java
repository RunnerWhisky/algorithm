package com.company.whisky.queue;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从上到下按层打印二叉树，同一个层级从左到右的顺序打印，每一层打印到一行
 * <p>
 * 复杂度分析：由于二叉树的每个节点，我们只访问了一遍，所以时间复杂度为O(n).
 * 如果不算返回的数组，那么空间复杂度为O(k),这里的 k 表示二叉树横向最宽的那一层的结点数目
 */
public class BinaryTreeErgodic {

    public List<List<Integer>> levelOrder(TreeNode root) {
        //生成FIFO队列
        Queue<TreeNode> Q = new LinkedList<>();
        //如果节点不为空，那么加入FIFO队列
        if (root != null) {
            Q.offer(root);
        }
        //ans用于保存层次遍历的结果
        List<List<Integer>> ans = new LinkedList<>();
        //开始利用FIFO队列层次遍历
        while (Q.size() > 0) {
            //取出当前层里面元素的个数
            final int qSize = Q.size();
            //当前层的结果存在tmp的链表中
            List<Integer> tmp = new LinkedList<>();
            //遍历当前层的每个节点
            for (int i = 0; i < qSize; i++) {
                //当前层的节点先出
                TreeNode current = Q.poll();
                //把结果存放在当前层中
                tmp.add(current.val);
                //把下一层的节点入队，注意入队时候需要非空才能入队
                if (current.left != null) {
                    Q.offer(current.left);
                }
                if (current.right != null) {
                    Q.offer(current.right);
                }
            }
            //把当前层的结果放到放回值里
            ans.add(tmp);
        }
        return ans;
    }

    /**
     *
     * FIFO队列不仅仅可以用Queue表示，还可以用两层的ArrayList表示
     */
    public List<List<Integer>> levelOrderTwo(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        //初始化当前层的节点
        List<TreeNode> currentLevel = new ArrayList<>();
        if (root != null) {
            currentLevel.add(root);
        }
        while (currentLevel.size() > 0) {
            //准备来存放 下一个节点
            List<TreeNode> nextLevel = new ArrayList<>();
            //用来存放当前层的结果
            List<Integer> currentResult = new ArrayList<>();
            //遍历当前层的节点
            for (TreeNode current : currentLevel) {
                //把当前的值存放当前结果里面
                currentResult.add(current.val);
                //生成下一层
                if (current.left != null) {
                    nextLevel.add(current.left);
                }

                if (current.right != null) {
                    nextLevel.add(current.right);
                }
                //注意这里的更迭滚动前进
                currentLevel = nextLevel;
                //把当前层的值放到结果里面
                ans.add(currentResult);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }
}
