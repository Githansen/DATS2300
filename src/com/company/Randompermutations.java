package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static com.company.IntroSortering.bytt;

public class Randompermutations {
    public static int[] randPerm1(int n)  // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall

        Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

        for (int k = n - 1; k > 0; k--)  // løkke som går n - 1 ganger
        {
            int i = r.nextInt(k+1);        // en tilfeldig tall fra 0 til k
            bytt(a,k,i);                   // bytter om
        }

        return a;                        // permutasjonen returneres
    }

    public static int[] randPerm2(int n)  // virker, men er ineffektiv
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall
        boolean[] har = new boolean[n];  // en boolsk tabell

        for (int i = 0; i < n; )         // vi skal legge inn n tall
        {
            int k = r.nextInt(n);          // trekker en indeks k
            if (har[k] == false)           // sjekker
            {
                har[k] = true;               // oppdaterer den boolske tabellen
                a[i++] = k + 1;              // legger inn k + 1 i a
            }
        }
        return a;                        // tabellen returneres
    }
    public static int[] randPerm3(int n)  // virker, men er ineffektiv
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];// en tabell med plass til n tall


        for (int i = 1; i < n; )         // vi skal legge inn n tall
        {
            int k = r.nextInt(n);// trekker en indeks k
            if(a[k] ==0) {              
                a[k] = i++;// legger inn k + 1 i a

            }
        }
        return a;                        // tabellen returneres
    }
    public static int[] randPerm4(int n)  // virker, men er svært ineffektiv
    {
        Random r = new Random();      // en randomgenerator
        int[] a = new int[n];         // en tabell med plass til n tall

        for (int i = 0; i < n; )      // vi skal legge inn n tall
        {
            int k = r.nextInt(n) + 1;   // trekker et nytt tall k

            int j = 0;
            for ( ; j < i; j++)         // leter i intervallet a[0:i>
            {
                if (a[j] == k) break;     // stopper hvis vi har k fra før
            }
            if (i == j) a[i++] = k;     // legger inn k og øker i
        }
        return a;                     // tabellen returneres
    }
    public static void omstokk(int[] a, int v, int h){
        Random r = new Random();
        for(int i = v; i<=h; i++){
            int k = r.nextInt(h-v) + v;
            System.out.println(k);
            bytt(a,i,k);
        }
    }
    public static int[] randPerm5(int n){
        ArrayList<Integer> liste = new ArrayList();
        for(int i = 1; i <= n; i++){
        liste.add(i);
        }
        Collections.shuffle(liste);
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = liste.get(i);
        }
        return a;
    }
    //Her er input minste og største tall i arrayet
    public static int[] randPerm6(int n, int k){
        int[] a = new int[1+k-n];
        Random r = new Random();
        for(int i = n; i <= k;){
            int x = r.nextInt(k-n+1);
            if(a[x] == 0){
                a[x] = i;
                i++;
            }
        }
        System.out.println(Arrays.toString(a));
        return a;
    }
    public static int[] scramble(int[]a){
        Random r = new Random();
            for(int i = 0; i < a.length; i++){
                int x = r.nextInt(a.length);
                bytt(a,i,x);
            }

        return a;
    }
}
