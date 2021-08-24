package com.company;

// 1.1.10
import java.util.Random;
public class Program
{
// Mange av de samme metodene ligger i Uke1sortering.java men har de her også for oversikten
    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static int[] randPerm(int n)
    {
        int[] a = new int[n]; // fyller tabellen med 1, 2, . . , n
        for (int i = 0; i < n; i++) a[i] = i+1;

        Random r = new Random();  // hentes fra java.util

        for (int k = n-1; k > 0; k--)
        {
            int i = r.nextInt(k+1);  // tilfeldig tall fra [0,k]
            bytt(a,k,i);
        }

        return a; // tabellen med permutasjonen returneres

    } // randPerm

    public static int kostnader(int[] a) // legges i class Program
    {
        int m = 0;
        for (int i = 1; i < a.length; i++) {} // en tom blokk
        return m;
    }

    public static double maks1(int[] a)  // a er en heltallstabell
    {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("a er tom");

        int m = 0;  // indeks til største verdi
        double antall = 0;
        for (int i = 0; i < a.length; i++) // obs: starter med i = 1
        {
            if (a[i] > a[m]){ m = i; antall++;};  // indeksen oppdateres
        }

        return antall;  // returnerer indeksen/posisjonen til største verdi

    } // maks
    public static double antallhøyere(int[] a)  // a er en heltallstabell
    {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("a er tom");

        int m = 0;  // indeks til største verdi
        double antall = 0;
        for (int i = 0; i < a.length; i++) // obs: starter med i = 1
        {
            if (a[i] > a[m]){ m = i; antall++;};  // indeksen oppdateres
        }

        return antall;  // returnerer indeksen/posisjonen til største verdi

    } // maks
    public static int maks2(int[] a)   // versjon 2 av maks-metoden
    {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("a er tom");
        int m = 0;             // indeks til største verdi
        int maksverdi = a[0];  // største verdi

        for (int i = 1; i < a.length; i++) if (a[i] > maksverdi)
        {
            maksverdi = a[i];  // største verdi oppdateres
            m = i;             // indeks til største verdi oppdaters
        }
        return m;  // returnerer indeks/posisjonen til største verdi

    } // maks

    public static int maks3(int[] a)  // versjon 3 av maks-metoden
    {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("a er tom");
        int sist = a.length - 1;       // siste posisjon i tabellen
        int m = 0;                     // indeks til største verdi
        int maksverdi = a[0];          // største verdi
        int temp = a[sist];            // tar vare på siste verdi
        a[sist] = 0x7fffffff;          // legger tallet 2147483647 sist

        for (int i = 1; ; i++)         // i starter med 1
            if (a[i] >= maksverdi)       // denne blir sann til slutt
            {
                if (i == sist)             // sjekker om vi er ferdige
                {
                    a[sist] = temp;          // legger siste verdi tilbake
                    return a[sist] >= maksverdi ? sist : m; // er siste størst?
                }
                else
                {
                    maksverdi = a[i];        // maksverdi oppdateres
                    m = i;                   // m oppdateres
                }
            }
    } // maks

    //Oppgave 1.1.10 laget til et program for oversikt
    public static void main(String[] args)
    {
        int n = 100_000;        // tabellstørrelse
        double antall = 2_000;     // antall gjentagelser

        long tid = 0;           // for tidsmåling

        int a[] = randPerm(n);
        double sum = 0;
        for (int i = 0; i < antall; i++) sum+=antallhøyere(randPerm(100000));
        System.out.println("Antall tall som er større enn alle foran. n = " +n + " Testes antall: " + antall);
        double t = sum/antall;
        System.out.println(t);

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) kostnader(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Faste kostnader: " + tid + " millisek");

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) maks1(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Maks1-metoden: " + tid + " millisek");


        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) maks2(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Maks2-metoden: " + tid + " millisek");

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) maks3(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Maks3-metoden: " + tid + " millisek");

        int xtest[] = {6,7,8,2,3,4,5,6,92,11};
       sum = 0;
       antall = 20000;
       System.out.println("Antall tall som er større enn alle foran. n = 10 Testes antall: 2000:");
        for (int i = 0; i < antall; i++) {Randompermutations.scramble(xtest); sum+=antallhøyere(xtest); }
        System.out.println(sum/antall);
        /* Svar skrevet i fasit
         n = 100000;
         sum = 0;
         antall = 10;

        int[] c = new int[n];
        for (int i = 0; i < n; i++) c[i] = i+1; // a = {1,2,3, . . }

        for (int i = 0; i < antall; i++)
        {
            Randompermutations.scramble(c);
            double ant =  antallhøyere(c);
            sum += ant;

        }
        double gjsnitt = (double)sum/antall;
        System.out.println("\nGjennomsnitt = " + gjsnitt);

         */
    }

    }
