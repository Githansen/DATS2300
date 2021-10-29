package Strukturer;

import com.company.Generics;
import com.company.Tabell;

import java.util.*;

public class Test {
    public static void kvikk(int [] a, int v, int h){
        if(v >= h) return;
        int m = (h+v)/2;
        Tabell.bytt(a,h,m);
        int k = parter(a,v,h-1,a[h]);
        Tabell.bytt(a,k,h);
        kvikk(a,v,k-1);
        kvikk(a,k+1,h);
    }
    public static int parter(int []a, int v, int h, int skilleverdi){
            while(true){
             while( v <= h && a[v] < skilleverdi)v++;
             while( v<= h && a[h] >= skilleverdi)h--;
                if(v < h)Tabell.bytt(a,v,h);
                else return v;
            }
    }
    public static void oddpar(int []a){
        int v = 0, h = a.length-1;
        while(v <= h){
            while(v<=h && a[v] % 2 == 0)v++;
            while(v<=h && a[h] % 2 == 1)h--;
            if(v < h){
                int temp = a[v];
                a[v] = a[h];
                a[h] = temp;
            }
            else return;
        }
    }
    public static int binsøk(int []a, int verdi, int v, int h){
        int m  = (v+h)/2;
        if(v <= h) {
            if (a[m] == verdi) return m;
            if (a[m] < verdi) return binsøk(a, verdi, m+1, h);
            if (a[m] > verdi) return binsøk(a, verdi, v, m - 1);
        }
        return -(v+1);
    }
    public static int omorganiser(char[] c) {
        int antall = 0, v = 0, h = c.length - 1;

        while (v <= h) {
            while (v <= h && c[v] > 'Z') v++;
            while (v <= h && c[h] <= 'Z') h--;
            if (v < h) {
                char temp = c[h];
                c[h] = c[v];
                c[v] = temp;
            }
        }
            return antall;
        }
    public static <T> int compare(Liste<T> a, Liste<T> b, Comparator<? super T> comp){
        int cmp = 0;
        int min = Math.min(a.antall(), b.antall());
        for(int i = 0; i < min; i++){
             cmp = comp.compare(a.hent(i),b.hent(i));
             if(cmp != 0) return cmp;
        }
        return a.antall() - b.antall();
    }
    public static <T> int fjernBakerst(Queue<T> kø, int antall){

        if(antall > kø.size()) kø.clear();
        else{
            for(int i = kø.size()-antall; i > 0; i--){
               kø.add(kø.remove());
            }
            for(int i = 0; i < antall; i++){
                kø.remove();
            }
        }

        return Math.min(antall, kø.size());
    }
    public static void roter(int []a, int antall){
        System.out.println(Arrays.toString(a));
        if(antall < 0) antall+= a.length;
     int [] b = new int[antall];
     int j = 0;
     for(int i = a.length-antall; i < a.length; i++){
         b[j] = a[i];
         j++;
     }
     System.out.println(Arrays.toString(b));
      System.arraycopy(a,0,a,antall,a.length-antall);
     System.arraycopy(b,0,a,0,b.length);
      System.out.println(Arrays.toString(a));
    }
    public static <T> int indeks(Stakk<T> s, T verdi){
        Stakk<T> hjelp = new TabellStakk<>();
        int n = s.antall();
        int indeks = 0;
        for(int i = 0; i < n; i++){
            if(s.kikk() == verdi){
                indeks = i;
                break;
            }
            hjelp.leggInn(s.taUt());
        }
        if(s.antall() == 0) indeks = -1;
        while (hjelp.antall() > 0) s.leggInn(hjelp.taUt());
        return indeks;
    }
    public static <T> void flytt(Kø<T> A, Kø<T> B){

    int n = A.antall();
    int m = B.antall();
    for(int i = 0; i < n; i++){
        A.leggInn(A.taUt());
        if(B.antall() > 0) {
            A.leggInn(B.taUt());
        }
    }
    while(!B.tom())A.leggInn(B.taUt());
    }
    public static boolean girBinærtre(int[] a){
        boolean eks;
        for(int i : a){
            eks = false;
            for(int j : a){
                if(i == 1 || i / 2 == j){
                    eks = true;
                }
            }
            if(!eks) return false;
        }

        return true;
    }
    public static int[] utvid(int[] a){
        Arrays.sort(a);
     int [] b = new int [a[a.length-1]+1];
        boolean[] finnes = new boolean[a[a.length-1]+1];
     int j = 0;
     for(int i = 0; i < a.length; i++){
         int temp = a[i];
         while(temp > 1){
             if(!finnes[temp]) {
                 finnes[temp] = true;
                 b[j++] = temp;
             }
             temp /= 2;
                }
     }
    b =  Arrays.copyOf(b,j);
     Arrays.sort(b);
     System.out.println(Arrays.toString(b));
        return b;
    }

    public static int fjernDuplikater(int [] a){
        int antall = 0;
        for(int i = 0; i < a.length-1; i++){
            if(a[i+1] == a[i]){
                a[i] = 0;
                antall++;
            }
        }
        Arrays.sort(a);
        System.arraycopy(a,antall,a,0,a.length-antall);
        for(int i = a.length-antall; i < a.length; i++) a[i] = 0;
        return a.length -antall;
    }

    public static boolean girFulltBinærtre(int[] a){
        Arrays.sort(a);
       if(!girBinærtre(a)) return false;
       if(a.length % 2 == 0) return false;
        for (int i = 1; i < a.length; i += 2)
        {
            if (a[i] + 1 != a[i+1]) return false;
            else if (a[i]/2 != a[i+1]/2) return false;
        }

        return true;
    }
    public static <T> void omvendtkopi(Stack<T> a, Stack<T> b){
        Stack<T> ny = new Stack<>();
        while(!a.isEmpty()){
            ny.add(a.pop());
            b.add(ny.peek());
        }
        while(!ny.isEmpty()){
            a.add(ny.pop());
        }
    }

}
