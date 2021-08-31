package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;


import static com.company.Ekstremalpunkter.min;
import static com.company.Program.maks2;

//div algoritmer for testing
public class Uke1sortering {
    //returnerer indeks til største tall




        //returnerer indeks til minste tall


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
            double sum = harmonisk(n) - Math.log(n);
            return sum;
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
        int m = fra;
        for(int i = fra+1; i <= til; i++){
            if(a[i] > maxverdi){
                m = i;
                maxverdi = a[i];
            }
        }

        return m;
    }
    public static int[] sorterfratiL(int[] a, int fra, int til){
        for(int i = fra; i<til; i++){
            for(int j = i+1; j <=til; j++){
                if(a[i] < a[j])bytt(a,i,j);
            }

        }
System.out.println(Arrays.toString(a));
        return a;
    }
    public static int[] delsortering(int [] a){
            if(a.length < 1){
                throw new IllegalArgumentException("Arrayet er tomt");
            }
                    int venstre = 0;
                    int høyre = a.length-1;
                while (venstre < høyre){
                    while(a[venstre] % 2 != 0){
                        venstre++;
                    }
                    while(a[høyre] % 2== 0 ){
                        høyre--;
                    }
                    bytt(a,venstre, høyre);
                }
                for(int i  =0; i < venstre ; i++){
                    for(int j = i+1; j < venstre ; j++){
                        if(a[i] > a[j]) bytt(a,i,j);
                    }
                }
                for(int i = venstre; i < a.length; i++){
                    for(int j = i+1; j < a.length; j++){
                        if(a[i] > a[j]) bytt(a,i,j);
                    }
                }

            return a;
    }
    public static int[] rotasjon(int[] a){
            for(int i = a.length-1; i >0; i--){
                bytt(a, i, i-1);
            }


            return a;
    }

}
