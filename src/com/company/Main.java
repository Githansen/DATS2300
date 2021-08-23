package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] stigende = {2,3,4,5,6,7,8,9,10,11};
        int[] synkende = {11,10,9,8,7,6,5,4,3,2,1};
        int[] tilfeldig ={2,5,3,67,11,4,9,6,7,32};
        System.out.println(Arrays.toString(stigende));
        System.out.println(Uke1sortering.maks(tilfeldig));
        System.out.println(Uke1sortering.min(tilfeldig));

    }
}
