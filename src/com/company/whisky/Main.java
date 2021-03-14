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
