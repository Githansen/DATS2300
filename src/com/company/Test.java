package com.company;

import java.util.Arrays;
import java.util.Random;

public class Test {
     static <T extends Number> void skriv(T t)  // 1. versjon
    {
        System.out.println("1. versjon " + t);
    }

    public static void skriv(Integer t)  // 2. versjon
    {
        System.out.println("2. versjon " + t);
    }
    static void skriv(Object[] a, int fra, int til){
        for(int i = 0; i  < a.length; i ++){
            System.out.print(a[i] + " ");
        }
    }
    public static
    <T extends Comparable<? super T>>
    void bytt(T [] a, int i, int j){
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    static void bytt(Object[] a, int i, int j){
         Object temp = a[i];
         a[i] = a[j];
         a[j] = temp;
    }
    public static Integer[] randPermInteger(int n)
    {
        Integer[] a = new Integer[n];               // en Integer-tabell
        Arrays.setAll(a, i -> i + 1);               // tallene fra 1 til n

        Random r = new Random();   // hentes fra  java.util

        for (int k = n - 1; k > 0; k--)
        {
            int i = r.nextInt(k+1);  // tilfeldig tall fra [0,k]
            bytt(a,k,i);             // bytter om
        }
        return a;  // tabellen med permutasjonen returneres
    }
    public static <T extends Comparable<? super T>> void insertionsort(T[] a){
         for(int i = 0; i < a.length; i++){
             int byttetall = Generics.maks(a, i, a.length);
             Generics.bytt(a,i,byttetall);
         }
    }
    public static int omorganiser(char[] c){
         int v = 0;
         int h = c.length-1;
         while(true){
             while(v <= h && c[v] > 'Z')v++;
             while(v <= h && c[h] <= 'Z')h--;
             if(v < h){
                 char temp = c[v];
                 c[v] = c[h];
                 c[h] = temp;
             }
             else return v;
         }
    }
    public static void main(String... args)
    {
        char[] c = "AbaAcBbAAaCCbcAB".toCharArray();
        int antall = omorganiser(c);
        System.out.println(antall + "  " + Arrays.toString(c));
    }
}
