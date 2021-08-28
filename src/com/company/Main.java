package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import static com.company.Ekstremalpunkter.maks;
import static com.company.Uke1sortering.*;


public class Main {

    public static void main(String[] args) {
        int[] stigende = {2,3,4,5,6,7,8,9,10,11};
        int[] synkende = {11,10,9,8,7,6,5,4,3,2,1};
        int[] tilfeldig ={5,3,7,4,3,5,7,8,6,7};

        System.out.println("Skriver ut indeks til største tall i liste med tilfeldige talL: ");
        System.out.println(maks(tilfeldig));

        System.out.println("Skriver ut indeks til minstet tall i liste med tilfeldige tall: ");
        System.out.println(Ekstremalpunkter.min(tilfeldig));

        System.out.println("Sorterer oddetall på venstre og partall på høyre side: ");
        System.out.println(Arrays.toString(Uke1sortering.delsortering(synkende)));

        System.out.println("Finner antall ulike tall: ");
        System.out.println(Uke1sortering.antallulike(tilfeldig));

        System.out.println("Roterer tallene i arrayet ønsket lengde");
        System.out.println(Arrays.toString(Uke1sortering.roterx(stigende,-3)));
        double sum = 0;
        for(double i = 2; i <= 5; i++){
            sum += 1/i;
        }

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


        System.out.println(Arrays.toString(x));
        System.out.println("Starter med denne ^^");
        //Oppgave 1.1.8 12
        System.out.println("Lager array ved gitt lavest og størst tall");
        Randompermutations.randPerm6(3,6);
    int [] ppp = {3,3,3,3,3,3,3,4,5,6,7,8,2354,224,19,22};
        System.out.println(Uke1sortering.antallulike(ppp));
        //Oppgave 1.1.9 2
        System.out.println("Tar inn et array og sorterer det tilfeldig: ");
        System.out.println(Arrays.toString(Randompermutations.scramble(x)));
        System.out.println(Testprogrammer.makstest());
        Uke1sortering.snu(x);
        Uke1sortering.sorterned(ppp);
        Testprogrammer.stigsorteringstest();
        int[] a = {2, 3, 5, 7, 10, 12, 12, 15, 18, 20};


        System.out.println(Søkealgoritmer.finn(a,1));


    }



}
