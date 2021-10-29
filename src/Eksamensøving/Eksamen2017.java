package Eksamensøving;

import com.company.Tabell;

import java.util.Arrays;
import java.util.spi.AbstractResourceBundleProvider;

public class Eksamen2017 {
    public static void byttchar(char[]c, int i, int j){
        char temp = c[i];
        c[i] = c[j];
        c[j]=temp;
    }
    public static int omorganiser(char[]c){

      int v = 0;
      int h = c.length-1;

      while(v < h) {
          while (v < h && c[v] > 'Z') v++;
          while (v < h && c[h] <= 'Z') h--;
          byttchar(c,v,h);
      }
        return v;
    }
    public static int[] tuntre(int[]a){
       int [] b = new int[a.length*2];
       System.arraycopy(a,0,b,a.length,a.length);
       for(int i = a.length-1; i > 0; i--){
           b[i] = Math.max(b[i*2], b[i*2+1]);
       }
        return b;
    }
    public static int kvikkparter(int []a,int fra,int til) {
  int v = fra;
  int h = til;
  int piv = a[fra];
        System.out.println(Arrays.toString(a) +" v = " + v + " h = " + h);
  while(v < h){
      while(v<h && a[v] < piv)v++;
      while(v<h && a[h] > piv)h--;
      Tabell.bytt(a,v,h);
  }
  return v;
    }
    public static void kvikksorter(int [] a, int fra, int til) {
            if (fra < til) {
                int piv = kvikkparter(a, fra, til);
                kvikksorter(a, fra, piv - 1);
                kvikksorter(a, piv + 1, til);
            }
    }
    public static boolean ersortert(int []a){
        for(int i = 0; i < a.length-1; i++){
            if(a[i] < a[i+1])return false;
        }
        return true;
    }
    public static int binsøk(int []a, int verdi){
        int v = 0;
        int h = a.length-1;
        while(v <= h){
            int m = (v+h)/2;
            if(a[m] > verdi) h = m-1;
           else if(a[m] < verdi) v= m+1;
           else if(a[m] == verdi) return m;
        }

        return -(v+1);
    }

    public static void main(String[] args){
        char[] c = "AbaAcBbAAaCCbcABa".toCharArray();
        int[] a = {2, 3, 5, 7, 10, 12, 15, 20,18};
        kvikksorter(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
