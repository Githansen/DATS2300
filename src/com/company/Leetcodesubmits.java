package com.company;

public class Leetcodesubmits {
    public static int maximumWealth(int[][] accounts) {
        int [] sumarray = new int[accounts.length];
        System.out.println(accounts[accounts.length-1][accounts.length]);
        for(int i = 0; i < accounts.length; i++){
            int sum = 0;
            System.out.println("----");
            for(int j = 0; j < accounts[i].length; j++){
                sum += accounts[i][j];
                System.out.println(accounts[i][j]);
            }
            sumarray[i] = sum;
        }
        int størst = 0;
        for(int i: sumarray){
            if(i > størst) størst = i;
        }
        return størst;
    }
}
