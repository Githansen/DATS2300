package com.company;

public class Uke1 {
    public static int størstetallindeks(int[] numbers) {
        int størst = 0;
        int maksverdi = numbers[0];
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] > maksverdi){
                størst = i;
                maksverdi = numbers[i];
            }
        }
        return størst;
    }
    public static int minstetallindeks(int[] numbers){
        int minst = 0;
        int minverdi = numbers[0];
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] < minverdi) {
                minst = i;
                minverdi = numbers[i];
            }
        }
        return minst;
    }
    public static int[] minmax(int[] tall){
        return new int[]{minstetallindeks(tall),størstetallindeks(tall)};
    }
    public static int fakultet(int start){
        if(start==1){
            return 1;
        }

        return start * fakultet(start -1);
    }
    public static int maks(int[] a) // versjon 3 av maks-metoden
    {
        if(a.length == 0){
            throw new java.util.NoSuchElementException("Tabellen a er tom!");
        }
        int sist = a.length - 1; // siste posisjon i tabellen
        int m = 0; // indeks til største verdi
        int maksverdi = a[0]; // største verdi
        int temp = a[sist]; // tar vare på siste verdi
        a[sist] = 0x7fffffff; // legger tallet 2147483647 sist
        for (int i = 0; ; i++) // i starter med 0
            if (a[i] > maksverdi) // denne blir sann til slutt
            {
                if (i == sist) // sjekker om vi er ferdige
                {
                    a[sist] = temp; // legger siste verdi tilbake
                    return temp > maksverdi ? sist : m; // er siste størst?
                }
                else
                {
                    maksverdi = a[i]; // maksverdi oppdateres
                    m = i; // m oppdateres
                }
            }
    } // maks
}
