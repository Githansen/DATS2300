package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;


import static com.company.Program.maks2;

//div algoritmer for testing
public class Uke1sortering {
    //returnerer indeks til største tall
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
    public static int[] delsortering(int[] a){
        int venstre = 0;
        int høyre = a.length-1;
        for(int i = 0; i < a.length; i++){
            while (a[venstre]%2 != 0 && venstre < høyre){
                venstre++;
            }
            while (a[høyre]%2 ==0 && høyre > venstre){
                høyre--;
            }
            bytt(a,venstre,høyre);
        }

        return a;
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
    public static double harmonisk(int n)
    {
        double sum = 0.0;
        for (int i = 1; i <= n; i++) sum += 1.0/i;
        return sum;
    }
        public static String eulers(double n){
            double sum = Math.log(n) - 0.423;

            DecimalFormat df = new DecimalFormat("#.#");
            return df.format(sum);
        }
        public static double eulersdiff(int n){
            double sum = harmonisk(n) - Math.log10(n);
            return sum;
        }

    public static int makstest()
    {
        int antallfeil = 0;
        int[] a = {8,3,5,7,9,6,10,2,1,4,5};  // maksverdien 10 er i posisjon 6
        if (maks2(a) != 6) { // kaller maks-metoden
            System.out.println("Kodefeil: Gir feil posisjon for maksverdien!");
            antallfeil++;
        }

        a = new int[0];  // en tom tabell, lengde lik 0
        boolean unntak = false;
        try
        {
            maks(a);  // kaller maks-metoden
        }
        catch (Exception e)
        {

            unntak = true;
            if (!(e instanceof java.util.NoSuchElementException)) {
                System.out.println("Kodefeil: Feil unntak for en tom tabell!");
                antallfeil++;
            }
            }

        if (!unntak) {
            System.out.println("Kodefeil: Mangler unntak for en tom tabell!");
        }
        a = new int[]{11,9,8,7};
        if(maks(a) != 0){
            antallfeil++;
            System.out.println("Kodefeil: Det første tallet skal være stærst");
        }
        a = new int[]{1,2,3,4,11};
        if(maks(a) != a.length-1){
            antallfeil++;
            System.out.println("Kodefeil: Det siste tallet skal være størst");
        }
        a=new int[]{2,2,4,4,4,4,4};
        if(maks(a) != 2){
            antallfeil++;
            System.out.println("Kodefeil: Feil i koden");
        }
        a = new int[]{8,5,9,9,6};
        if(maks(a) !=2){
            antallfeil++;
            System.out.println("Kodefeil: Feil indeks");
        }

            return antallfeil;
    }
        public static int[] stig(int[] a){
            for(int i = a.length-1; i > 0; i--){
                for(int j = 0; j< i ; j++){
                    if(a[j] > a[i]){
                        bytt(a,i,j);
                    }
                }
            }

        return a;
        }

    public static void snu(int[] a){
        int i = 0;
        int j = a.length -1;
        while(i <j){
            bytt(a,i,j);
            i++;
            j--;
        }
        System.out.println(Arrays.toString(a));
    }
    public static void sorterned(int[] a){

        int i = 0;
        while(i < a.length){
            int max = maksfratil(a,i,a.length-1);
            bytt(a, i,max);
            i++;
        }
        System.out.println(Arrays.toString(a));

    }
    public static int maksfratil(int[]a, int fra, int til){
        int maxverdi = a[fra];
        int m = 0;
        for(int i = fra; i <= til; i++){
            if(a[i] >= maxverdi){
                m = i;
                maxverdi = a[i];
            }
        }

        return m;
    }

}
