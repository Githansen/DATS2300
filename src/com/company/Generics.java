package com.company;

import Eksempelklasser.Komparator;

import java.util.Arrays;
import java.util.Comparator;

public class Generics {
    public static
    <T extends Comparable <? super T>>
     int maks(T []a, int fra, int til){
        int m = fra;
        T maks = a[m];
        for(int i = fra; i <til; i++){
            if(a[i].compareTo(maks) > 0){
                m = i;
                maks = a[i];
            }
        }
        return m;
    }
    public static <T> void innsettingssortering(T[] a, Comparator<? super T> c)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet
            int  j = i - 1;        // j er en indeks

            // sammenligner og forskyver:
            for (; j >= 0 && c.compare(verdi,a[j]) < 0 ; j--) a[j+1] = a[j];

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }
    public static
    <T extends Comparable <? super T>>
    T[] sort(T []a){
        for(int i = 0; i < a.length; i++){
         for(int j = i+1; j < a.length; j++){
             if(a[j].compareTo(a[i])<0) bytt(a,i,j);
         }
        }
        System.out.println(Arrays.toString(a));
        return a;
    }
    public static
    <T extends Comparable <? super T>>
    void bytt(T[] a, int i, int j){
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static <T> int maks(T[] a, Komparator<? super T> c){
        int m = 0;
        T maksverdi = a[m];
        for(int i = 0; i < a.length; i++){
            if(c.compare(a[i],maksverdi) > 0){
                maksverdi = a[i];
                m = i;
            }
        }
        return m;
    }
}
