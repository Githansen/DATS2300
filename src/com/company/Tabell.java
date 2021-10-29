package com.company;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

import static com.company.Ekstremalpunkter.maks;
import static com.company.Uke1sortering.bytt;
import static com.company.Uke1sortering.maksfratil;

public class Tabell {

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

       while(v<h){
           bytt(a,v,h);
           v++;
           h--;
       }
        System.out.println(Arrays.toString(a));
    }
    public static void snuchar(char[]a, int v, int h){
            vhKontroll(a.length,v,h);
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
        if(nm == m) nm = 0;

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
    public static int[] finn2max(int[]a, int fra, int til){
        int max = a[fra];
        int m = fra;
        int nmax = a[fra+1];
        int nm = fra+1;

        for(int i = fra+2; i < til; i++){
            System.out.println(nm + " " + m);
            if(a[i] > nmax){
                if(a[i]> max){
                    nmax = max;
                    nm = m;
                    max = a[i];
                    m = i;

                }
                else{
                    nmax = a[i];
                    nm = i;
                }
            }
        }
        return new int[]{m,nm};
    }
    public static int[] finn2maxhele(int[] a){
        return finn2max(a,0,a.length);
    }
    public static int[] nestMakstre(int[] a)   // en turnering
    {
        int n = a.length;                // for å forenkle notasjonen

        if (n < 2) // må ha minst to verdier!
            throw new IllegalArgumentException("a.length(" + n + ") < 2!");

        int[] b = new int[2*n];          // turneringstreet
        System.arraycopy(a,0,b,n,n);     // legger a bakerst i b

        for (int k = 2*n-2; k > 1; k -= 2)   // lager turneringstreet
            b[k/2] = Math.max(b[k],b[k+1]);

        int maksverdi = b[1], nestmaksverdi = Integer.MIN_VALUE;
        for (int m = 2*n - 1, k = 2; k < m; k *= 2)
        {
            int tempverdi = b[k+1];  // ok hvis maksverdi er b[k]
            if (maksverdi != b[k]) { tempverdi = b[k]; k++; }
            if (tempverdi > nestmaksverdi) nestmaksverdi = tempverdi;
        }
        int mindeks = 0;
        int nmindeks = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] == maksverdi)mindeks = i;
            else if(a[i] == nestmaksverdi)nmindeks = i;
        }
        System.out.println(Arrays.toString(b));
        return new int[] {mindeks,nmindeks}; // størst og nest størst

    } // nestMaks
    public static void kopier(int[] a, int i, int[] b, int j, int lengde){
        for(int s = i; s < lengde+i; s++,j++) {
            b[j] = a[s];
        }
    }
    public static void tre(int[] a){
        int [] b = new int[a.length*2];
        System.arraycopy(a,0,b,a.length,a.length);
        for(int i = a.length-1; i >= 0; i--){
            b[i] = Math.max(b[i*2], b[i*2+1]);
        }
        System.out.println(Arrays.toString(b));
    }

    public static boolean nestepermutasjon(int[] a)
    {
        int v = a.length-2;
        int h = a.length-1;
        while(a[v] > a[v+1]) v--;
        if(v < 0) return false;
        while(a[h] < a[v])h--;
        bytt(a,h,v); Tabell.snu(a,v+1,h);
        return true;
    }
    public static int binsøk(int[]a,int verdi, int fra, int til){
       int v = fra;
       int h = til;
       int m = (fra + til)/2;
       if(v < h){

           if(a[m] > verdi) return binsøk(a,verdi,fra,m-1);
           if(a[m] < verdi) return  binsøk(a,verdi,m+1,til);
       }
       if(a[m] == verdi){
           while(a[m]==verdi){
               m--;
           }
           return m+1;
       }
       return -(v+1);
    }
    public static void innsettingfratil(int[] a, int fra,int til)
    {
        for (int i = fra+1; i < til; i++)  // starter med i = 1
        {
            int verdi = a[i];
                    int j = i - 1;      // verdi er et tabellelement, j er en indeks
            for (; j >= fra && verdi < a[j]; j--) a[j+1] = a[j];  // sammenligner og flytter
            a[j + 1] = verdi;                 // j + 1 er rett sortert plass
        }
        System.out.println(Arrays.toString(a));
    }
    public static int inversjoner1(int [] a){
        int antall = 0;
        for(int i = 0; i <a.length; i++){
            for(int j = i+1; j < a.length; j++){
                if(a[i] > a[j])antall++;
            }
        }
        return antall;
    }
    public static void utvalgssortering(int[] a)
    {
       for(int i = 0; i < a.length; i++){
           bytt(a,i,minfratil(a,i,a.length));
       }
    }
    public static void selectionsortering(int[]a){
        for(int i = 0; i < a.length -1; i ++){
            int min = i;
            int minverdi = a[i];
            for(int j = i+1; j < a.length; j++){
                if(a[j] < minverdi){
                    min = j;
                    minverdi=a[j];
                }
            }
            a[min] = a[i];
            a[i] = minverdi;
        }
        System.out.println(Arrays.toString(a));
    }
    public static void utvalgfratil(int[]a, int fra, int til){
       for(int i = fra; i < til; i++){
           int min = i;
           int minverdi = a[i];
           for(int j = i+1; j < a.length; j++){
               if(a[j] < minverdi){
                   min = j;
                   minverdi = a[j];
               }
           }
           bytt(a,i,min);
       }
       System.out.println(Arrays.toString(a));
    }
    public static int lineærsøk(int[]a,int verdi,int k){
        int pos = a.length-1;
        while(pos >0 && a[pos] > verdi){
            pos-=k;
        }
        for(int i = pos; i < pos+k && i <a.length; i++){
            if(a[i] == verdi) return i;
        }
         return -(pos+2);
    }
    public static void bubblesort(int[] a){
        for (int n = a.length; n > 1; )            // n er intervallgrense
        {
            int byttindeks = 0;                      // hjelpevariabel
            for (int i = 1; i < n; i++)              // går fra 1 til n
            {
                if (a[i - 1] > a[i])                   // sammenligner
                {
                    bytt(a, i - 1, i);                   // bytter
                    byttindeks = i;                      // høyre indeks i ombyttingen
                }
            }
            n = byttindeks;                          // ny intervallgrense
        }
    }




    private static void flett(int[] a, int[] b, int fra, int m, int til)
    {
        int n = m - fra;                // antall elementer i a[fra:m>
        System.arraycopy(a,fra,b,0,n);  // kopierer a[fra:m> over i b[0:n>

        int i = 0, j = m, k = fra;      // løkkevariabler og indekser

        while (i < n && j < til)        // fletter b[0:n> og a[m:til> og
        {                               // legger resultatet i a[fra:til>
            a[k++] = b[i] <= a[j] ? b[i++] : a[j++];
        }

        while (i < n) a[k++] = b[i++];  // tar med resten av b[0:n>
    }
    public static void flettesortering(int[] a, int[] b, int fra, int til)
    {
        if (til - fra <= 1) return;   // a[fra:til> har maks ett element
        int m = (fra + til)/2;        // midt mellom fra og til

        flettesortering(a,b,fra,m);   // sorterer a[fra:m>
        flettesortering(a,b,m,til);   // sorterer a[m:til>

        if (a[m-1] > a[m]) flett(a,b,fra,m,til);  // fletter a[fra:m> og a[m:til>
    }
    public static void fletting(int [] a, int [] b){
        int [] x = new int[a.length + b.length];
        int i = 0; int j = 0; int k = 0;
        while(j < a.length && k < b.length){
                x[i++] = a[j++];
                x[i++] = b[k++];
        }
        while(j < a.length) x[i++] = a[j++];
        while(k < b.length) x[i++] = b[k++];
    }
    private static int inv(int[] a, int[] b, int fra, int m, int til)
    {
        int n = m - fra;                           // antall elementer i a[fra:m>
        System.arraycopy(a,fra,b,0,n);             // kopierer a[fra:m> over i b[0:n>
        int i = 0, j = m, k = fra;                 // indekser
        int antall = 0;                            // antall inversjoner
        while (i < n && j < til)                   // går gjennom tabellene
        {
            if (b[i] > a[j]) antall += (n - i);      // nye inversjoner
            a[k++] = b[i] < a[j] ? b[i++] : a[j++];  // kopierer
        }
        while (i < n) a[k++] = b[i++];             // tar med resten av b[0:n>

        return antall;                             // antall inversjoner
    }
    private static int inversjoner(int[] a, int[] b, int fra, int til)
    {
        if (til - fra <= 1) return 0;         // maks ett element - 0 inversjoner
        int m = (fra + til)/2;                // deler a[fra:til> på midten

        return inversjoner(a, b, fra, m)      // inversjoner av første type
                + inversjoner(a, b, m, til)      // inversjoner av andre type
                + inv(a, b, fra , m, til);       // inversjoner av tredje type
    }

    public static int effinversjoner(int[] a)
    {
        int[] b = new int[a.length/2];          // hjelpetabell
        return inversjoner(a, b, 0, a.length);  // kaller metoden over
    }
    public static int[] union(int []a, int []b){
        int []c = new int[a.length+b.length];
       System.arraycopy(a,0,c,0,a.length);
        System.arraycopy(b,0,c,a.length,b.length);
        return c;
    }
    private static int sParter0(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h - 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }
    private static int parter0(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) {
                System.out.println(a[v] + " er større enn " + skilleverdi + ", v++");
                v++;   // h er stoppverdi for v
            }
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }
    private static void kvikksortering0(int[] a, int v, int h)  // en privat metode
    {

            if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
            int k = sParter0(a, v, h, (v + h) / 2);  // bruker midtverdien
            kvikksortering0(a, v, k - 1);     // sorterer intervallet a[v:k-1]
            kvikksortering0(a, k + 1, h);     // sorterer intervallet a[k+1:h]

    }

    public static void kvikksortering(int[] a, int fra, int til) // a[fra:til>
    {
        kvikksortering0(a, fra, til - 1);  // v = fra, h = til - 1
    }

    public static void kvikksortering(int[] a)   // sorterer hele tabellen
    {
        kvikksortering0(a, 0, a.length - 1);
    }
    private static int sParter0synkende(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0synkende(a, v, h - 1, a[h]);  // partisjonerer a[v:h - 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }
    private static int parter0synkende(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] >= skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] < skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }
    private static void kvikksortering0synkende(int[] a, int v, int h)  // en privat metode
    {
        if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
        int k = sParter0synkende(a, v, h, (v + h)/2);  // bruker midtverdien
        kvikksortering0synkende(a, v, k - 1);     // sorterer intervallet a[v:k-1]
        kvikksortering0synkende(a, k + 1, h);     // sorterer intervallet a[k+1:h]
    }

    public static void kvikksorteringsynkende(int[] a, int fra, int til) // a[fra:til>
    {
        kvikksortering0synkende(a, fra, til - 1);  // v = fra, h = til - 1
    }

    public static void kvikksorteringsynkende(int[] a)   // sorterer hele tabellen
    {
        kvikksortering0(a, 0, a.length - 1);
    }
    public static int[] indeksTabell(char[] c){
        int [] indekser = new int[c.length];
        for(int i = 0; i < c.length; i++) indekser[i] = i;
        for(int i = 0; i < c.length; i++){
            for(int j = i+1; j<c.length; j++){
                if(c[j] < c[i]) {
                    byttchar(c,i,j);
                    bytt(indekser,i,j);
                }
            }
        }
        return indekser;
    }

}
