package com.company;

public class Uke1sortering {
    //returnerer indeks til største tall
        public static int maks(int[] numbers){
            int m = 0;
            int maksverdi = numbers[0];
            for(int i =1; i < numbers.length; i++){
                if(numbers[i] > maksverdi){
                    m = i;
                    maksverdi = numbers[i];
                }
            }
            return m;
        }
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
}
