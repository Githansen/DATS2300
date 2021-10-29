package oblig1;




import com.company.SøkingogSortering;
import com.company.Tabell;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.CompletionService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Oblig1 {
    public static void bytt(int[] a, int b, int c){
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
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
    public static int maks(int[] a){
        if(a.length ==0){
            throw new NoSuchElementException("Arrayet er tomt");
        }
        for(int i = 0; i < a.length-1; i++){
               if(a[i] > a[i+1]) {
                bytt(a, i, i + 1);
            }

        }

        System.out.println(Arrays.toString(a));
        return a[a.length-1];
    }
    public static int ombyttinger(int[] a){
        int antall = 0;
        for(int i = 0; i < a.length-1; i++){

            if(a[i] > a[i+1]) {
                bytt(a, i, i + 1);
                antall++;
            }

        }
        return antall;
    }
    //OPPGAVE 2
    public static int antallUlikeSortert(int[] a){
        if(!ersortert(a)){
            throw new IllegalStateException("Tabellen er ikke sortert");
        }
        int antallulike = a.length;
        for(int i = 0; i < a.length-1; i++){
            if(a[i] == a[i+1]) antallulike--;
        }
        System.out.println(antallulike);
        return antallulike;
    }
    public static boolean ersortert(int[] a){
        boolean sortert = true;
        for(int i = 0; i < a.length-1; i++){
            if(a[i] > a[i+1])return false;
        }

        return sortert;
    }
    //OPPGAVE 3
    public static int antallUlikeUsortert2(int[] a){
        int antall = 0;
        for(int i = 0; i<a.length; i++){
            for(int j = i+1; j<a.length; j++){
                if(a[i] == a[j]) {
                    antall--;
                    break;
                }
            }
        }
        return antall;
    }
    //OPPGAVE 4
    public static void delsortering(int[] a){
        //oddetall til venstre, partall til høyre
        int v = 0, h = a.length-1;
        int antall = 0;
        for(int i = 0; i < a.length; i++) {
            int sjekkvenstre = v;
           int sjekkhøyre = h;

            while (a[v] % 2 != 0 && v < h) {
                v++;
            }
            while (a[h] % 2 == 0 && v < h) {
                h--;
            }
            bytt(a,v,h);
            antall++;
            //Hvis ingenting har endret seg er sorteringen ferdig, og vi bryter ut av loopen
            if(sjekkvenstre == v && sjekkhøyre == h) break;
        }
        sorterfratil(a,0,v);
        sorterfratil(a,h,a.length);
        System.out.println(antall);
        System.out.println(Arrays.toString(a));
    }
    public static void sorterfratil(int[]a, int fra, int til){
        for(int i = fra; i < til; i++){
            for(int j = i+1; j<til; j++){
                if(a[j] < a[i]) bytt(a,i,j);
            }
        }
    }
    //oppgave 5
    public static void byttchar(char[] a, int b, int c){
        char temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }
    public static void rotasjonc(char[] a){
        for(int i = a.length-1; i> 0; i--){
            byttchar(a,i,i-1);
        }
        System.out.println(Arrays.toString(a));
    }
    //Oppgave 6
    public static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }
    public static void rotasjon(char[] c, int d)    // 3. versjon
    {
        int n = c.length;  if (n < 2) return;         // ingen rotasjon
        if ((d %= n) < 0) d += n;                     // motsatt vei?

        int s = gcd(n, d);                            // største felles divisor

        for (int k = 0; k < s; k++)                   // antall sykler
        {
            char verdi = c[k];                          // hjelpevariabel

            for (int i = k - d, j = k; i != k; i -= d)  // løkke
            {
                if (i < 0) i += n;                        // sjekker fortegnet til i
                c[j] = c[i]; j = i;                       // kopierer og oppdaterer j
            }

            c[k + d] = verdi;                           // legger tilbake verdien
        }

    }
    //Oppgave 7
    public static String flett(String s, String t){

        String ut = "";
        int i = 0, j = 0;
        while(i < s.length() && j < t.length()){
            ut += s.charAt(i++);
            ut += t.charAt(j++);
        }
        while(i < s.length()) ut += s.charAt(i++);
        while(j < t.length()) ut += t.charAt(j++);
        return ut;
    }
    //OPPGAVE 7B
    public static String flettfler(String... s){
                String ut = "";
                int lengst = s[0].length();
                for(int i = 0; i < s.length; i++){
                    if(s[i].length() > lengst) lengst = s[i].length();
                }
            for(int i = 0; i < lengst; i++){
                for(int j = 0; j < s.length; j++){
                    if(s[j].length() > i) {
                        ut += s[j].charAt(i);
                    }
                    }
            }
            System.out.println(ut);
        return ut;
    }
    //OPPGAVE 8
    public static int[] minfratil(int [] a, int fra, int til){
        int indeks = fra;
        int min = a[fra];
        for(int i = fra; i < til; i++){
            if(a[i] < min){
                indeks = i;
                min = a[i];
            }
        }
        return new int[]{a[indeks],indeks};
    }


    public static int[] indekssortering(int[] a){
        int[] indekser = new int[a.length];
        int [] b = new int[a.length];
        System.arraycopy(a,0,b,0,a.length);
        for(int i = 0; i < a.length; i++){
            indekser[i] = i;
        }
        for(int i = 0; i < b.length; i++){
            for(int j = i+1; j < b.length; j++){
                if(b[j] < b[i]){
                    bytt(indekser,j,i);
                    bytt(b,i,j);
                }
            }
        }


        return indekser;
    }
    //Oppgave 9
    public static int[] tredjeMin(int[] a){
        if(a.length < 3) throw new NoSuchElementException("JAO");
        int verdier[] = new int[3];
        for(int i = 0; i < verdier.length; i++){
            verdier[i] = a[i];
        }
        indekssortering(verdier);
        int mindeks = verdier[0];
        int nestmindeks = verdier[1];
        int tredjemindeks = verdier[2];
        int minst = a[mindeks];
        int nestminst=a[nestmindeks];
        int tredjeminst = a[tredjemindeks];
        for(int i = 3; i < a.length; i++){
         if(a[i] < tredjeminst){
             if(a[i] < nestminst){
                 if(a[i] < minst){
                     tredjemindeks = nestmindeks;
                     tredjeminst = nestminst;
                     nestminst = minst;
                     nestmindeks = mindeks;
                     mindeks = i;
                     minst = a[i];
                 }
                 else{
                     tredjemindeks = nestmindeks;
                     tredjeminst = nestminst;
                     nestminst = a[i];
                     nestmindeks = i;
                 }
             }
             else{
                 tredjemindeks = i;
                 tredjeminst = a[i];
             }
         }
        }

        return new int[]{mindeks,nestmindeks,tredjemindeks};
    }

    public static boolean inneholdt3(String a, String b){
        if(a.equals(""))return true;
        if(a.equals(b))return true;
        if(a.length() > b.length()) return false;
        char[] listea = a.toCharArray();
        char[] listeb = b.toCharArray();
        Arrays.sort(listea);
        Arrays.sort(listeb);
        int i = 0, j = 0;
        while(i < listea.length && j < listeb.length){
            if(listea[i] == listeb[j]){
                i++;
                j++;
            }
            else{
                j++;
            }
        }
        if(i < listea.length)return false;
        return true;
    }
    public static boolean inneholdt(String a, String b){
        if(a.equals("")) return true;
        if(a.length() > b.length())return false;

        int [] bokstavtall = new int[256];
                                                            //Eksempel: "AAA" , "AAB"
        for(int i = 0; i < a.length(); i++){
            bokstavtall[bokstavNr(a.charAt(i))] += 1; //Legger til 1 i arrayet jeg opprettet for plassen til char
        }                                               // Her vil f.eks AAA føre til at bokstavtall[65] = 3
        for(int j = 0; j < b.length(); j++){
            int tall = bokstavNr(b.charAt(j));
            if(bokstavtall[tall] > 0) bokstavtall[tall] -= 1; //Trekker fra 1 i arrayet jeg opprettet, dersom jeg la til 1 i forrige loop, her vil f.eks bokstavtall[65] trekkes fra 2 ganger i.la loopen
        }                                                     // Ny verdi for bokstavtall[65] vil være 1

        for (int i = 0; i < a.length(); i++){ //Looper gjennom String a igjen, for å sjekke at alle tall er 0
            int tall = bokstavNr(a.charAt(i));
            if(bokstavtall[tall] > 0) return false; // Dersom en plass i bokstavtall ikke er 0 og vi la til noe i første loop, vil false returneres. Her er som nevnt bokstavtall[65] lik 1 fordi det var fler A-er i String a enn b
        }
        return true;
    }
    public static int bokstavNr(char bokstav) {

        return bokstav;
    }
   static void oppgave10() {
        int antallFeil = 0;
        boolean b = false;

        try {
            b = Oblig1.inneholdt("", "");  // kaller metoden
        } catch (Exception e) {
            System.out.println(e);
            System.out.println
                    ("Oppgave 10: a) Skal ikke kaste unntak for to tomme ord!!");
            antallFeil++;
        }

        if (b != true) {
            System.out.println
                    ("Oppgave 10: b) Svaret skal bli lik true her!");
            antallFeil++;
        }

        try {
            b = Oblig1.inneholdt("", "A");  // kaller metoden
        } catch (Exception e) {
            System.out.println(e);
            System.out.println
                    ("Oppgave 10: c) Skal ikke kaste unntak for et tomt ord!!");
            antallFeil++;
        }

        if (b != true) {
            System.out.println
                    ("Oppgave 10: d) Svaret skal bli lik true her!");
            antallFeil++;
        }

        try {
            b = Oblig1.inneholdt("A", "");  // kaller metoden
        } catch (Exception e) {
            System.out.println(e);
            System.out.println
                    ("Oppgave 10: e) Skal ikke kaste unntak for et tomt ord!!");
            antallFeil++;
        }

        if (b != false) {
            System.out.println
                    ("Oppgave 10: f) Svaret skal bli lik false her!");
            antallFeil++;
        }

        b = Oblig1.inneholdt("ABBA", "ABBA");
        if (b != true) {
            System.out.println
                    ("Oppgave 10: g) Svaret skal bli lik true her!");
            antallFeil++;
        }

        b = Oblig1.inneholdt("XYYX", "AAAAAAAYXXY");
        if (b != true) {
            System.out.println
                    ("Oppgave 10: h) Svaret skal bli lik true her!");
            antallFeil++;
        }

        b = Oblig1.inneholdt("ABBA", "RABARBRA");
        if (b != true) {
            System.out.println
                    ("Oppgave 10: i) Svaret skal bli lik true her!");
            antallFeil++;
        }

        b = Oblig1.inneholdt("ABBA", "BARBERER");
        if (b != false) {
            System.out.println
                    ("Oppgave 10: j) Svaret skal bli lik false her!");
            antallFeil++;
        }

        b = Oblig1.inneholdt("ABBA", "AKROBAT");
        if (b != false) {
            System.out.println
                    ("Oppgave 10: k) Svaret skal bli lik false her!");
            antallFeil++;
        }

        b = Oblig1.inneholdt("ØÅÅØ", "ØØÅØØ");
        if (b != false) {
            System.out.println
                    ("Oppgave 10: l) Svaret skal bli lik false her!");
            antallFeil++;
        }

        b = Oblig1.inneholdt("ØÅÅØ", "ÅØØÅØ");
        if (b == false) {
            System.out.println
                    ("Oppgave 10: m) Svaret skal bli lik true her!");
            antallFeil++;
        }

        char[] x = new char[100000];
        for (int i = 0; i < 50000; i++) {
            x[2 * i] = 'X';
            x[2 * i + 1] = 'Y';
        }
        String t = String.copyValueOf(x);

        char[] y = new char[100000];
        for (int i = 0; i < 49999; i++) {
            y[2 * i] = 'X';
            y[2 * i + 1] = 'Y';
        }
        y[99998] = 'Z';
        y[99999] = 'Z';
        String s = String.copyValueOf(y);

        long tid = System.currentTimeMillis();
        b = Oblig1.inneholdt(s, t);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid);
        if (tid > 100) {
            System.out.println
                    ("Oppgave 10: n) Dette (" + tid + " ms) gikk sakte! Finn en bedre algoritme!");
            antallFeil++;
        }

        if (b != false) {
            System.out.println
                    ("Oppgave 10: o) Svaret skal bli lik false her!");
            antallFeil++;
        }
        assertEquals(0, antallFeil, "Du har for mange feil i oppgave 10");
    }

    public static int recursivebinær(int []a, int verdi, int fra, int til){
        if(!ersortert(a)) throw new IllegalArgumentException("ikke sortert");
        int v = fra; int h = til;
        if(til>= 1 && fra < a.length) {
            int m = (v + h) / 2;
            if (m == v || m == h) return -(v);
            if (a[m] > verdi) return recursivebinær(a, verdi, v, m - 1);
            else return recursivebinær(a, verdi, m + 1, h);
        }
            return -v;
    }

    public static boolean nestePermutasjon(int[] a) {
        int n = a.length;
        int i = n - 2;

        while (i >= 0 && a[i] > a[i + 1]) i--;

        if (i < 0) return false;

        int verdi = a[i];
        int j = n - 1;

        while (verdi > a[j]) j--;
        bytt(a, i, j);

        i++;
        j = n - 1;
        while (i < j) bytt(a, i++, j--);
        return true;
    }
   static void oppgave6() {
        int antallFeil = 0;

        char[] a = {};

        try {
            Oblig1.rotasjon(a, 1);  // kaller metoden
        } catch (Exception e) {
            System.out.println(e);
            System.out.println
                    ("Oppgave 6: a) Skal ikke kaste unntak for en tom tabell!!");
            antallFeil++;
        }

        char[] b = {'A'};
        char[] b0 = {'A'};
        Oblig1.rotasjon(b, 0);
        if (!Arrays.equals(b, b0)) {
            System.out.println("Oppgave 6: b) Feil hvis tabellen har ett element!");
            antallFeil++;
        }

        Oblig1.rotasjon(b, 1);
        if (!Arrays.equals(b, b0)) {
            System.out.println("Oppgave 6: c) Feil hvis tabellen har ett element!");
            antallFeil++;
        }

        Oblig1.rotasjon(b, -1);
        if (!Arrays.equals(b, b0)) {
            System.out.println("Oppgave 6: d) Feil hvis tabellen har ett element!");
            antallFeil++;
        }

        char[] c = {'A', 'B'};
        char[] c0 = {'B', 'A'};
        Oblig1.rotasjon(c, 1);

        if (!Arrays.equals(c, c0)) {
            System.out.println("Oppgave 6: e) Feil hvis tabellen har to elementer!");
            antallFeil++;
        }

        c = new char[]{'A', 'B'};

        Oblig1.rotasjon(c, -1);
        if (!Arrays.equals(c, c0)) {
            System.out.println("Oppgave 6: f) Feil hvis tabellen har to elementer!");
            antallFeil++;
        }

        char[] d = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        char[] d0 = {'G', 'H', 'I', 'J', 'A', 'B', 'C', 'D', 'E', 'F'};

        Oblig1.rotasjon(d, 4);
        if (!Arrays.equals(d, d0)) {
            System.out.println("Oppgave 6: g) Feil hvis tabellen har flere elementer!");
            antallFeil++;
        }

        d = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        Oblig1.rotasjon(d, -6);
        if (!Arrays.equals(d, d0)) {
            System.out.println("Oppgave 6: h) Feil hvis tabellen har flere elementer!");
            antallFeil++;
        }

        char[] x = new char[100_000];
        long tid = System.currentTimeMillis();
        Oblig1.rotasjon(x, 99_999);
        tid = System.currentTimeMillis() - tid;

        if (tid > 20) {
            System.out.println("Oppgave 6: i) Metoden "
                    + "er for ineffektiv. Må forbedres!");
            antallFeil++;
        }

        tid = System.currentTimeMillis();
        Oblig1.rotasjon(x, 199_999);
        tid = System.currentTimeMillis() - tid;

        if (tid > 20) {
            System.out.println("Oppgave 6: j) Metoden "
                    + "er for ineffektiv. Må forbedres!");
            antallFeil++;
        }

        tid = System.currentTimeMillis();
        Oblig1.rotasjon(x, 50_000);
        tid = System.currentTimeMillis() - tid;

        if (tid > 20) {
            System.out.println("Oppgave 6: k) Metoden "
                    + "er for ineffektiv. Må forbedres!");
            antallFeil++;
        }

        tid = System.currentTimeMillis();
        Oblig1.rotasjon(x, -40_000);
        tid = System.currentTimeMillis() - tid;

        if (tid > 20) {
            System.out.println("Oppgave 6: l) Metoden "
                    + "er for ineffektiv. Må forbedres!");
            antallFeil++;
        }

        assertEquals(0, antallFeil, "Du har for mange feil i oppgave 6");
    }

    public static void main(String[] args){

        int [] c = {4,2,6,8,4,11,4,22,44,21,11,9,99,444};
        char[]x = {'2','c','p','a','v'};
        System.out.println(flett("","AB"));
    }
}
