package com.company.whisky;


import java.util.Arrays;

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

        System.out.println("===========================" );
        //验证
        System.out.println(stackDataStruct.isValidWidth(""));
        System.out.println(stackDataStruct.isValidWidth(null));
        System.out.println(stackDataStruct.isValidWidth("  "));
        System.out.println(stackDataStruct.isValidWidth(" (){}[] "));
        System.out.println(stackDataStruct.isValidWidth(" ({)}[] "));
        System.out.println(stackDataStruct.isValidWidth(" ({}[]) "));

    }
}
