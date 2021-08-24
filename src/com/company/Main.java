package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import static com.company.Uke1sortering.*;


public class Main {

    public static void main(String[] args) {
        int[] stigende = {2,3,4,5,6,7,8,9,10,11};
        int[] synkende = {11,10,9,8,7,6,5,4,3,2,1};
        int[] tilfeldig ={5,3,7,4,3,5,7,8,6,7};

        System.out.println(maks(tilfeldig));
        System.out.println(Uke1sortering.min(tilfeldig));
        System.out.println(Arrays.toString(Uke1sortering.delsortering(synkende)));
        System.out.println(Uke1sortering.antallulike(tilfeldig));
        System.out.println(Arrays.toString(Uke1sortering.roterx(stigende,-3)));
        double sum = 0;
        for(double i = 2; i <= 5; i++){
            sum += 1/i;
        }
        System.out.println(Math.log(50)-0.423);
        //gjennomsnittlig antall tall som er større enn alle foran er det harmoniske tallet -1
            System.out.println(Uke1sortering.harmonisk(100000000)-1);
            System.out.println(Math.log(100000000) -0.423);
            System.out.println(Uke1sortering.eulers(100000000));

        int n = 60000;
        long tid = System.currentTimeMillis();
         Randompermutations.randPerm1(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid + " Rand");


        tid = System.currentTimeMillis();
        Randompermutations.randPerm2(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid + " Rand2");


        tid = System.currentTimeMillis();
        Randompermutations.randPerm3(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid + " Rand3");

/*
        tid = System.currentTimeMillis();
        Randompermutations.randPerm4(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid + " Rand4");
*/
        tid = System.currentTimeMillis();
        Randompermutations.randPerm5(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid + " Rand5");
        int x [] = {3,4,5,6,7,8,9,19};
        System.out.println(Arrays.toString(x));
        Randompermutations.randPerm6(3,6);
        Randompermutations.scramble(x);
    }

}
