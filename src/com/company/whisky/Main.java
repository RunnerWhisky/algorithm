package com.company.whisky;


public class Main {

    public static void main(String[] args) {
        StackDataStruct stackDataStruct = new StackDataStruct();
        boolean valid = stackDataStruct.isValid("()");
        System.out.println(valid + "");

        valid = stackDataStruct.isValid(null);
        System.out.println(valid + "");

        valid = stackDataStruct.isValid("");
        System.out.println(valid + "");

        valid = stackDataStruct.isValid("(((");
        System.out.println(valid + "");

        valid = stackDataStruct.isValid("(()())");
        System.out.println(valid + "");

        System.out.println("===========================");
        //验证
        System.out.println(stackDataStruct.isValidWidth(""));
        System.out.println(stackDataStruct.isValidWidth(null));
        System.out.println(stackDataStruct.isValidWidth("  "));
        System.out.println(stackDataStruct.isValidWidth(" (){}[] "));
        System.out.println(stackDataStruct.isValidWidth(" ({)}[] "));
        System.out.println(stackDataStruct.isValidWidth(" ({}[]) "));

        //验证 大鱼吃小鱼算法
        int[] fishSize = {4, 2, 5, 3, 1};
        int[] fishDirection = {1, 1, 0, 0, 0};
        int i = stackDataStruct.fishSolution(fishSize, fishDirection);
        System.out.println("fishSolution +++  " + i);

        //验证 找数组中最小的元素下标
        int[] sourceData = new int[]{5, 4, 1, 3, 9};
        int[] rightSmall = stackDataStruct.findRightSmall(sourceData);
        StringBuilder stringBuilder = getPrintArray(rightSmall);
        System.out.println("findRightSmall =     " + stringBuilder.toString());

        //找出数组中最大数的第一个下标
        int[] sourceDataA = new int[]{5, 4, 1, 2, 7, 9};
        int[] firstBigSolution = stackDataStruct.findFirstBigSolution(sourceDataA);
        System.out.println("findRightLarge = " + getPrintArray(firstBigSolution));

        Verify.DoubleCheck(new int[]{2, 4, 56, 324, 45, 23});
        Verify.RandomCheck();


    }

    /**
     * 验证对应查找的结果
     */
    public static class Verify {
        private static int findRightLarge(int[] A, int i) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] > A[i]) {
                    return j;
                }
            }
            return -1;
        }

        private static void check(int[] A, int[] ans) {
            if (A == null || A.length == 0) {
                return;
            }
            final int N = A.length;
            for (int i = 0; i < N; i++) {
                final int r = ans[i];
                if (r != findRightLarge(A, i)) {
                    System.out.println("ERROR");
                }
            }

        }

        public static void DoubleCheck(int[] A) {
            StackDataStruct stackDataStruct = new StackDataStruct();
            int[] ans = stackDataStruct.findFirstBigSolution(A);
            check(A, ans);
        }

        public static void RandomCheck() {
            for (int i = 0; i < 100; i++) {
                final int len = nextInt() + 1;
                int[] A = new int[len];
                for (int j = 0; j < len; j++) {
                    A[j] = nextInt();
                }
                DoubleCheck(A);
            }

        }

        private static int nextInt() {
            final double d = Math.random();
            final int i = (int) (d * 1000);
            return i;
        }

    }

    private static StringBuilder getPrintArray(int[] rightSmall) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int j = 0; j < rightSmall.length; j++) {
            if (j == rightSmall.length - 1) {
                stringBuilder.append(rightSmall[j]).append("]");
            } else {
                stringBuilder.append(rightSmall[j]).append(",");
            }
        }
        return stringBuilder;
    }
}
