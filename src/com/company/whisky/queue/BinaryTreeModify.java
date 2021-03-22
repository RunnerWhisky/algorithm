package com.company.whisky.queue;

/**
 *
 */
public class BinaryTreeModify {

    public Node connect(Node root) {
        Node Q = null;
        if (root != null) {
            Q = root;
        }
        while (Q != null) {
            Node nextLevelPreNode = null;
            Node nextLevelHead = null;
            Node curLevelNode = Q;
            while (curLevelNode != null) {
                if (curLevelNode.left != null) {
                    if (nextLevelPreNode != null) {
                        nextLevelPreNode.next = curLevelNode.left;
                    }
                    nextLevelPreNode = curLevelNode.left;
                    if (nextLevelHead == null) {
                        nextLevelHead = curLevelNode.left;
                    }
                }
                if (curLevelNode.right != null) {
                    if (nextLevelPreNode != null) {
                        nextLevelPreNode.next = curLevelNode.right;
                    }
                    nextLevelPreNode = curLevelNode.right;
                    if (nextLevelHead == null) {
                        nextLevelHead = curLevelNode.right;
                    }

                }
                curLevelNode = curLevelNode.next;

            }
            Q = nextLevelHead;
        }
        return root;
    }


}



