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
     * <p>
     * <p>
     * 总结：
     * 一。消除行为不同
     * 1.对于括号的消除是成对的消除
     * 2.对于大鱼吃小鱼的配对只会有一个消除
     * 二。栈中存放的内容不同
     * 1。括号的例题中存放的是内容本身
     * 2。大鱼吃小鱼的存放的是索引，可以通过索引查找内容
     * 三。弹栈的方式 不一样
     * 1。弹一个元素就可以
     * 2。用 while 语句一直弹，直到满足某个条件为止
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

    /**
     * 单调栈：是指栈中的元素必须是按升序或是降序排列的栈
     * 就是说任何时候都需要保证栈的有序性
     * <p>
     * <p>
     * <p>
     * 找出数组中右边比我小的元素
     * 题目：一个整数数组A,找到每个元素：右边第一个比我小的下标位置，没有则用-1表示
     * 输入：[5,2]
     * 输出：[1,-1]
     * <p>
     * 复杂度分析：每个元素只入栈一次，出栈一次，所以时间复杂度是O（N），而空间复杂度为O(N),最差的情况是可能是所有的元素都入栈
     */
    public int[] findRightSmall(int[] A) {
        //结果数组
        int[] ans = new int[A.length];
        //注意，栈中记录的是下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            final int x = A[i];
            //每个元素都向左遍历栈中的元素完成消除动作
            while (!stack.empty() && A[stack.peek()] > x) {
                //消除的时候，记录一下被谁消除了
                ans[stack.peek()] = i;
                //消除的时候，值更大的需要从栈中消失
                stack.pop();
            }
            //剩余的入栈
            stack.push(i);
        }
        //依旧在栈中的元素，由于没有能消除他们，因此只能将结果设置为-1
        while (!stack.empty()) {
            ans[stack.peek()] = -1;
            stack.pop();
        }
        return ans;
    }

    /**
     * 练习题目1：
     * 给定一个数组，要找到这个数组里每个元素右边比我大的元素的位置
     * - 注意：是右边第一个比我大的，如果有多个的话
     * - 如果没有，那么用-1表示
     * - 返回：一个数组，表示右边比我大的数的下标位置
     */
    public int[] findFirstBigSolution(int[] sourceData) {
        //边界处理
        if (sourceData == null || sourceData.length == 0) {
            return new int[0];
        }
        //结果数组
        int[] ans = new int[sourceData.length];
        //注意栈中的元素记录的是下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < sourceData.length; i++) {
            final int x = sourceData[i];
            //每个元素都向左遍历栈的元素完成消除动作
            //这里是递减栈
            //如果发现进来的元素X与栈中的元素相比
            //如果大于栈中的元素，那么要把栈中的元素弹出去
            while (!stack.empty() && sourceData[stack.peek()] < x) {
                //消除的时候，记录一下谁被消除的了
                ans[stack.peek()] = i;
                //消除的时候，值更大的需要从栈中消失
                stack.pop();
            }
            //剩下的入栈
            stack.push(i);
        }
        while (!stack.empty()) {
            ans[stack.peek()] = -1;
            stack.pop();
        }
        return ans;
    }


    /**
     * 练习题2：
     * 数组中左边比我小的位置的位置
     */
    /**
     * 练习题3：
     * 数组中左边第一个比我大的元素的位置
     */

    /**
     * 如果我们进一步归纳，会发现消除的时候，这里仍然是消除第一个元素，保留一个元素，弹栈的时候，仍然是一直弹栈，直到满足某个条件为止
     */
}
