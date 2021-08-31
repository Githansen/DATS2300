package com.company;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

import static com.company.Ekstremalpunkter.maks;
import static com.company.Uke1sortering.bytt;
import static com.company.Uke1sortering.maksfratil;

public class Tabell {
    private Tabell(){};
    public static void makstest(){
      int [] a = {};
      int[] b = randPerm(10);
      maksfratil(b,-1,2);
        maksfratil(a,0,1);
      maks(a);

    }
    public static int[] nestMaks(int[] a)  // legges i class Tabell
    {
        int n = a.length;   // tabellens lengde

        if (n < 2) throw   // må ha minst to verdier!
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = maks(a);  // m er posisjonen til tabellens største verdi

        int nm;           // nm skal inneholde posisjonen til nest største verdi

        if (m == 0)                            // den største ligger først
        {
            nm = maksfratil(a, 1, n);                  // leter i a[1:n>
        }
        else if (m == n - 1)                   // den største ligger bakerst
        {
            nm = maksfratil(a, 0, n - 1);              // leter i a[0:n-1>
        }
        else
        {
            int mv = maksfratil(a, 0, m);              // leter i a[0:m>
            int mh = maksfratil(a, m + 1, n);          // leter i a[m+1:n>
            nm = a[mh] > a[mv] ? mh : mv;        // hvem er størst?
        }

        return new int[] {m,nm};      // m i posisjon 0 , nm i posisjon 1

    } // nestMaks
    public static int minfratil(int[] a, int fra, int til){
       fratilkontroll(a.length,fra,til);

        int min = fra;
        int miniverdi = a[fra];
        for(int i = fra; i < til; i++){
            if(a[i] < miniverdi){
                min = i;
                miniverdi = a[i];
            }
        }
        return min;
    }
    public static int min(int[]a){
        return minfratil(a,0,a.length);
    }
    public static int maksfratil(int[] a, int fra, int til){
        if(a == null){
            throw new NullPointerException("Tabellen er NULL");
        }
        fratilkontroll(a.length,fra,til);
        int m = fra;
        int maksverdi = a[fra];
        int sist = til-1;
        int temp = a[sist];
        a[sist] = 0x7fffffff;
        for(int i = fra; i<til; i++){
            if(a[i] > maksverdi){
                if(i == sist){
                    a[sist] = temp;
                    return temp >= maksverdi ? sist : m;

                }
                else{
                    maksverdi=a[i];
                    m = i;
                }
            }
        }
        return m;
    }
    public static int maks(int[]a){

        return maksfratil(a,0,a.length);
    }
    public static int[] randomstokk(int[] a){
        Random r = new Random();
        for(int i = 0; i < a.length; i++){
           int j = r.nextInt(a.length);
            bytt(a,i,j);
        }

        return a;
    }
    public static int[] randPerm(int n)  // en effektiv versjon
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
    public static void bytt(int[] numbers, int i, int j){
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;

    }
    public static void byttchar(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;

    }
    public static void skriv(int[] a, int fra, int til){
        fratilkontroll(a.length,fra,til);
        String ut = "";
        for(int i = fra; i<til; i++){
            ut += a[i] + " ";
        }
        System.out.print(ut);
    }
    public static void skrivalle(int[] a){
        skriv(a,0,a.length);
    }
    public static void skrivln(int [] a, int fra, int til){
        fratilkontroll(a.length,fra,til);
        skriv(a,fra,til);
        System.out.println();
    }
    public static void skrivlnalle(int[]a){
        skriv(a,0,a.length);
        System.out.println();
    }
    public static void skrivchar(char[] a, int fra, int til){
        String ut = "";
        for(int i = fra; i < til; i++){
            ut += a[i] + " ";
        }
        fratilkontroll(a.length,fra,til);
        System.out.println(ut);
    }
    public static void skrivallechar(char[]a){
        skrivchar(a,0,a.length);
    }
    public static int [] naturligetall(int n){
        if(n < 1){
            throw new IllegalArgumentException("N er for lavt");
        }
        int a[] = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = i+1;
        }
        return a;
    }
    public static int[] naturligetallfratil(int fra, int til){

        int[] a = new int[til-fra];

        for(int i = 0; i < til-fra; i++){
            a[i] = fra+i;
        }
        return a;
    }
    public static void fratilkontroll(int tablengde,int fra,int til){
        if (fra < 0)                                  // fra er negativ
            throw new ArrayIndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        if (fra == til)
            throw new NoSuchElementException
                    ("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");
    }
    public static void vhKontroll(int tablengde, int v, int h)
    {
        if (v < 0)
            throw new ArrayIndexOutOfBoundsException("v(" + v + ") < 0");

        if (h >= tablengde)
            throw new ArrayIndexOutOfBoundsException
                    ("h(" + h + ") >= tablengde(" + tablengde + ")");

        if (v > h + 1)
            throw new IllegalArgumentException
                    ("v = " + v + ", h = " + h);
        if(v == h){
            throw new NoSuchElementException("Tomt tabellintervall");
        }
    }
    public static int neststørst(int [] a){
        int m = maks(a);
        if(m == 0){
            return maksfratil(a,1,a.length);
        }
        if(m == a.length -1){
            return maksfratil(a,0,a.length-1);
        }
        int mv = maksfratil(a,0,m);
        int mh = maksfratil(a,m+1,a.length);
        if(a[mv] > a[mh])return mv;
        else return mh;
    }
    public static void snu(int[] a, int v, int h){
        vhKontroll(a.length,v,h);
       while(v<h){
           bytt(a,v,h);
           v++;
           h--;
       }
        System.out.println(Arrays.toString(a));
    }
    public static void snuchar(char[]a, int v, int h){

       while(v < h){
           byttchar(a,v,h);
           v++;
           h--;
       }

        System.out.println(Arrays.toString(a));
    }
    public static int[] nestmaksimal(int [] a){
        int m = maks(a);
        bytt(a,m,0);
        int nm = maksfratil(a,1,a.length);
        if(nm == m){
            nm = 0;
        }
        bytt(a,m,0);
        return new int[]{m,nm};
    }
    public static int[] nestmaksimalsist(int[]a){
        int m = maks(a);
        bytt(a,m,a.length-1);
        int nm = maksfratil(a,0,a.length-1);
        if(nm == m) nm = a.length-1;
        bytt(a,m,a.length-1);
        return new int[]{m,nm};
    }
    public static int[] sortering(int[] a){
            for(int i = a.length; i > 0; i--){
                int m = maksfratil(a,0,i);
                bytt(a,m,i-1);
            }
System.out.println(Arrays.toString(a));
        return a;
    }
    public static int[]sorterned(int[] a){
        for(int i = a.length; i > 0; i--){
            int m = minfratil(a,0,i);
            bytt(a,m,i-1);
        }
        System.out.println(Arrays.toString(a));
        return a;
    }
}
