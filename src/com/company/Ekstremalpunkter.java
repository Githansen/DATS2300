package com.company;

import jdk.jshell.execution.Util;

public class Ekstremalpunkter {
    public static int min(int[] numbers){
        int m = 0;
        int minverdi = numbers[0];
        for(int i = 1; i < numbers.length; i++){
            if(numbers[i] < minverdi){
                m=i;
                minverdi = numbers[i];
            }
        }
        return m;
    }
    public static int[] minmaks(int[] numbers){
        return new int[]{maks(numbers), min(numbers)};
    }
    public static int maks(int[] a)  // a er en heltallstabell
    {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("a er tom");
        int antall = 0;
        int m = 0;  // indeks til største verdi

        for (int i = 1; i < a.length; i++) // obs: starter med i = 1
        {
            if (a[i] > a[m]) m = i;  // indeksen oppdateres
        }

        return m;  // returnerer indeksen/posisjonen til største verdi

    } // maks

    public static int makstil(int[] a, int fra, int til){
        if(a.length < 1){
            throw new IllegalArgumentException("Listen er tom");
        }
        if(fra >= til || fra < 1 || til <1 || til > a.length){
            throw new java.util.NoSuchElementException("Feil ved indeksering");
        }
        int maksverdi = a[fra];
        int maksindeks = fra;
        for(int i = fra+1; i <= til; i++){
            if(a[i] > maksverdi){
                maksverdi = a[i];
                maksindeks = i;
            }
        }

        return maksindeks;
    }
}
