package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Uke1sortering {
    //returnerer indeks til største tall
    public static int maks(int[] a)  // a er en heltallstabell
    {
        if (a.length < 1) throw new IllegalArgumentException("a er tom");

        int m = 0;  // indeks til største verdi

        for (int i = 1; i < a.length; i++) // obs: starter med i = 1
        {
            if (a[i] > a[m]) m = i;  // indeksen oppdateres
        }

        return m;  // returnerer indeksen/posisjonen til største verdi

    } // maks



        //returnerer indeks til minste tall
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
        public static int[] stigende(int[] numbers){
            for(int i = 0; i < numbers.length; i++){
               for(int j = i+1; j <numbers.length;j++ ){
                   if (numbers[i] > numbers[j]) {
                       int temp = numbers[i];
                       numbers[i] = numbers[j];
                       numbers[j] = temp;
                   }
               }
            }

            return numbers;
        }
      public static int[] delsortering(int[] verdier){
    int fra = 0;
    int til = verdier.length -1;
    for(int i = 0; i < verdier.length; i++){
        while(verdier[fra] %2 != 0 && fra < til){
            fra++;
        }
        while(verdier[til] %2 == 0 && fra < til){
            til--;
        }
        bytt(verdier, fra,til);
    }

          return verdier;
      }
      public static int[] bytt(int[] numbers, int i, int j){
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
            return numbers;
      }
      public static int antallulike(int[]numbers){
            int antall = 0;
            for(int i = 0; i < numbers.length; i++){
                for(int j = i+1; j < numbers.length; j++){
                    if(numbers[i] == numbers[j]){
                        antall++;
                        break;
                    }
                }
            }

            return numbers.length - antall;
      }
      public static int[] roter(int[] numbers){
            for(int i = numbers.length-1; i >0; i--){
                bytt(numbers, i, i-1);
            }

            return numbers;
      }
      public static int [] roterx(int[] numbers, int x){
            if(x<0){
                for(int i = 0; i < numbers.length+x; i++){
                    bytt(numbers, i , i-x);
                }
            }
            else {
                for (int i = numbers.length - 1; i > x - 1; i--) {
                    bytt(numbers, i, i - x);
                }
            }
            return numbers;
      }
        public static double harmonisk(int n){
            double sum = 0;
            for(double i = 1; i <= n; i++){
                sum += 1/i;
            }

            return sum;
        }
        public static String eulers(double n){
            double sum = Math.log(n) - 0.423;

            DecimalFormat df = new DecimalFormat("#.#");
            return df.format(sum);
        }
        public static double eulersdiff(int n){
            double sum = harmonisk(n) - Math.log(n);
            return sum;
        }
    public static int[] randomarray(int n)  // en effektiv versjon
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
    public static int[] randPermB(int n)  // virker, men er ineffektiv
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
}
