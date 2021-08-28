package com.company;

import static com.company.Program.bytt;
import static com.company.Program.maks2;
import static com.company.Ekstremalpunkter.maks;
import static com.company.Uke1sortering.stig;

public class Testprogrammer {
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
    public static int stigsorteringstest(){
        int antallfeil = 0;
        int[] a = {4,5,2,4,7};
        if(stig(a)[0] != 2){
            System.out.println("Arrayet sorterer ikke riktig");
        antallfeil++;
        }
        a = new int[]{1,2,3,4,5,6,7};
        if(stig(a)[0] != 1){
            System.out.println("Arrayet sorterer ikke riktig");
        antallfeil++;
        }

        a = new int[]{};
        boolean unntak = false;
        try{
          stig(a);
        }
        catch (Exception e){
            unntak=true;
            antallfeil++;
            if(!(e instanceof java.util.NoSuchElementException)){
                System.out.println("Feil unntak for tom tabell");
            }
        }
        if(!unntak){
            System.out.println("Mangler unntak for tom tabell");
            antallfeil++;
        }

        return 0;
    }
}
