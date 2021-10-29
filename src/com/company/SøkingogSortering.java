package com.company;

import java.util.Arrays;

import static com.company.Tabell.bytt;

public class SøkingogSortering {
    public static int[] insertionsort(int[] a){

        for(int i = 1; i < a.length; i++){
            int j = i-1;
            int temp = a[i];
            while(j >= 0 && a[j] > temp){
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = temp;
        }
        System.out.println(Arrays.toString(a));
        return a;
    }
    public static int[] enkelfletting(int[] a, int[]b){
        int c[] = new int[a.length+b.length];
        int i = 0, j = 0, x = 0;
        while(i < a.length && j < b.length){
            c[x++] = a[i++];
            c[x++] = b[j++];
        }
        while(i < a.length) c[x++] = a[i++];
        while(j < b.length) c[x++]=  b[j++];
        System.out.println(Arrays.toString(c));
        return c;
    }


    public static int binærsøk(int[] a, int verdi){
      int v = 0, h = a.length-1;
      while(v<=h){

          int m = (v+h)/2;
          if(a[m] == verdi) return m;
         else if(a[m] > verdi)h = m-1;
          if(a[m] < verdi) v = m+1;

      }
      return -(v+1);
    }

    public static int størst(int[]a){

        for(int i = 0; i < a.length; i++){
            for(int j = a.length-1; j > i; j--){
                if(a[i] >a[j]) bytt(a,i,j);
            }
        }
        System.out.println(Arrays.toString(a));
        return a[a.length-1];
    }
    public static void partisjoner(int[]a, int skilleverdi){
        int v = 0;
        int h = a.length-1;
        while(v<h) {
            while (a[v] < skilleverdi && v < h) v++;
            while (a[h] > skilleverdi && v < h) h--;
            bytt(a, v, h);
        }
        System.out.println(Arrays.toString(a));
    }

}
