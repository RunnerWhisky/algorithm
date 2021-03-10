package com.company.whisky;

import java.util.Stack;

public class StackDataStruct {
    /**
     * 字符串中只有字符'('和')'。合法字符串需要括号可以配对
     * <p>
     * 复杂度分析：每个字符都入栈一次并出栈一次，所以时间复杂度为 O(N)，而空间复杂度为 O(N)，因为最差情况下可能会把整个字符串都入栈。
     * 优化：
     * 深度：这种解法还可以怎么优化
     * 广度：这种解法具有普适性吗？可以推广吗？
     * 深度拓展之后，你会发现栈中的元素都是一样的，【栈中元素都相同时，实际上没有必要使用栈，只需要记录栈中元素个数】
     */
    public boolean isValid(String str) {

        if (str == null || str.length() == 0) {
            return false;
        }
        if (str.length() % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();

            }
        }
        return stack.isEmpty();
    }

    /**
     * 深度拓展之后： 使用计数的方式优化
     * <p>
     * 复杂度分析：每个字符只入栈一次，出栈一次，所以时间复杂度为 O(N)，而空间复杂度为 O(1)，因为我们已经只用一个变量来记录栈中的内容
     */
    public boolean isValidNoStack(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        if (str.length() % 2 == 1) {
            return false;
        }

        int leftBraceNumber = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                leftBraceNumber++;
            } else if (c == ')') {
                if (0 >= leftBraceNumber) {
                    return false;
                }
                --leftBraceNumber;

            }
        }
        return leftBraceNumber == 0;
    }


    /**
     * 广度拓展
     * 栈中只存放了一个维度的信息：左括号'('和右括号')'。如果栈中的内容变得更加丰富一点，就可以得到下面这道扩展题。
     * 题目：
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。有效字符串需满足：
     * 1.左括号必须用相同类型的右括号闭合
     * 2.左括号必须以正确的顺序闭合
     * 3.注意空字符串可被认为是有效字符串
     */

    public boolean isValidWidth(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        str = str.trim();

        if (str.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else if (c == ']' || c == '}' || c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                Character peek = stack.peek();
                if (peek == '[' && c == ']' || peek == '{' && c == '}' || peek == '(' && c == ')') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     * 大鱼吃小鱼
     * 题目：在水中有许多的鱼停放在x轴上。给定两个数组Size，Dir，Size[i]表示鱼的大小，Dir[i]表示鱼的方向
     * （0 表示像左游，1表示向右游）。这两个数组分别表示鱼的大小和游动的方向，并且两个数组的长度相等。鱼的行为符合以下几个条件：
     * 1.所有的鱼都同时开始游动，每次按照鱼的方向，都游动一个单位的距离
     * 2.当方向相对时，大鱼会吃掉小鱼
     * 3.鱼的大小都不一样
     * 输入：Size = [4,2,5,3,1] , Dir = [1, 1 , 0, 0 ,0 ]
     * 输出： 3
     * 完成以下接口来计算还剩下几条鱼？
     * <p>
     * 复杂度分析：每只鱼只出栈一次，入栈一次，所以时间复杂度为 O（N），而空间复杂度为 O(n),因为最差的情况可能所有鱼都入栈
     */

    public int fishSolution(int[] fishSize, int[] fishDirection) {
        //首先拿到鱼的数量
        //如果鱼的数量小于或是等于1 ，就直接返回鱼的数量
        final int fishNumber = fishSize.length;

        if (fishNumber <= 1) {
            return fishNumber;
        }
        //定义左右的变量
        final int left = 0;
        final int right = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < fishNumber; i++) {
            //当前鱼的的情况： 游动方向 ；鱼的大小
            final int curFishDirection = fishDirection[i];
            final int curFishSize = fishSize[i];
            //当前鱼是否没吃掉
            boolean hasEat = false;
            //如果占中还有鱼，并且栈中鱼向右 ，当前鱼向左游，那么就会有相遇的可能性
            while (!stack.empty() && fishDirection[stack.peek()] == right && curFishDirection == left) {
                //如果栈顶的鱼比较大，那么把新来的吃掉
                if (fishSize[stack.peek()] > curFishSize) {
                    hasEat = true;
                    break;
                }
                // 如果栈中的鱼较小，那么会把栈中的鱼吃掉，栈中的鱼被消除，所以需要弹栈。
                stack.pop();
            }
            // 如果新来的鱼，没有被吃掉，那么压入栈中。
            if (!hasEat) {
                stack.push(i);
            }

        }
        return stack.size();
    }

//    public int[] findRightSmall(int[] A) {
//
//
//
//    }
}
