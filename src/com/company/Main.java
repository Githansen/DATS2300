package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import static com.company.Ekstremalpunkter.maks;
import static com.company.Uke1sortering.*;


public class Main {

    public static void main(String[] args) {
        int[] stigende = {2,3,4,5,6,7,8,9,10,11};
        int[] synkende = {11,10,9,8,7,6,5,4,3,2,1,1};
        int[] tilfeldig ={5,3,7,4,3,5,7,8,6,7};




        //gjennomsnittlig antall tall som er større enn alle foran er det harmoniske tallet -1
        System.out.println("Antall tall i gjennomsnitt som er høyere enn alle tall foran, gitt N: ");
            System.out.println(Uke1sortering.harmonisk(100000000)-1);

        System.out.println("Samme som over, gitt ved log(n)-0.423");
            System.out.println(Math.log(100000000) -0.423);

        System.out.println("Samme som over, med en metode");
            System.out.println(Uke1sortering.eulers(100000000));

        System.out.println("Laget ulike måter å sette tall fra 1 til n i n-stort array: ");
        int n = 60000;

        //Teller hvor lang tid det tar å lage tilfeldig array. 1.1.10
        long tid = System.currentTimeMillis();
         Randompermutations.randPerm1(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid + "MS -> Rand");


        tid = System.currentTimeMillis();
        Randompermutations.randPerm2(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid + "MS -> Rand2");


        tid = System.currentTimeMillis();
        Randompermutations.randPerm3(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid + "MS -> Rand3");

/*
        tid = System.currentTimeMillis();
        Randompermutations.randPerm4(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid + " Rand4");
*/


        tid = System.currentTimeMillis();
        Randompermutations.randPerm5(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid + "MS -> Rand5");
        int x [] = {3,4,5,6,7,8,9,19,22};



    int [] ppp = {3,3,3,3,3,3,3,4,5,6,7,8,2354,224,19,22};

        //Oppgave 1.1.9 2
        System.out.println("Tar inn et array og sorterer det tilfeldig: ");
        System.out.println(Arrays.toString(Tabell.randomstokk(x)));
        System.out.println(Testprogrammer.makstest());
        Uke1sortering.snu(x);
        Uke1sortering.sorterned(ppp);
        Testprogrammer.stigsorteringstest();
        int[] a = {2, 3, 5, 7, 10, 12, 12, 15, 18, 20};
        System.out.println(Uke2nest.maks(a));
        System.out.println(Søkealgoritmer.finn(a,55));
        System.out.println(Arrays.toString(Uke1sortering.rotasjon(a)));
        int xxx[] = {2,3,22,4,6,8};
        Tabell.skriv(xxx,0,xxx.length);
        System.out.println();
        System.out.println(Arrays.toString(Tabell.naturligetallfratil(1,8)));

         a = Tabell.randPerm(20); // tilfeldig permutasjon av 1 . . 20
        int[] b = Tabell.nestMaks(a);  // metoden returnerer en tabell

        int m = b[0], nm = b[1];       // m for maks, nm for nestmaks

        Tabell.skrivalle(a);        // se Oppgave 5 i Avsnitt 1.2.2
        System.out.println();
        System.out.print("Størst(" + a[m] + ") har posisjon " + m);
        System.out.println(", nest størst(" + a[nm] + ") har posisjon " + nm);

        // Eksempel på en utskrift:

        // 12 16 15 6 10 8 9 2 14 19 5 18 20 13 3 7 11 1 4 17
        // Størst(20) har posisjon 12, nest størst(19) har posisjon 9

    int papaya[] = {1,2,3,-4,4,5,6,7,8,9};

        System.out.println(Arrays.toString(Tabell.finn2max(papaya,0,papaya.length)));

    }



}
