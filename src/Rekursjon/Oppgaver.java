package Rekursjon;

import com.company.Tabell;

public class Oppgaver {
    public static int a(int n){
        int sum = 0;
        for(; n >= 2; n--){
            sum += 2*(n-1) + 3*(n-2);
        }
        return sum;
    }
    public static int tverr(int n){

            System.out.println("tverrsum(" + n + ") starter!");
            int sum = (n < 10) ? n : tverr(n / 10) + (n % 10);
            System.out.println("tverrsum(" + n + ") er ferdig!");
            return sum;

    }
    public static int iteeuklid(int a, int b){
        while(b != 0){
            int x = b;
            a = a%b;
            b = a;
            a = x;

        }
        return a;
    }
    //Oppgave 7
    public static int kvadratsum(int n){
        if(n == 1) return 1;
        return n*n + kvadratsum(n-1);
    }
    public static int sumfratil(int k, int j){
        if(k == j) return k;
        int m = (k+j)/2;
        return sumfratil(k,m) + sumfratil(m+1,j);
    }
    public static int fakultet(int k){
        if(k == 1) return 1;

        return k * fakultet(k-1);
    }
    public static int fib(int n)         // det n-te Fibonacci-tallet
    {
        if (n <= 1) return n;              // fib(0) = 0, fib(1) = 1
        else return fib(n-1) + fib(n-2);   // summen av de to foregående
    }
  public static void quicksort(int [] a, int v, int h){
        if(v>=h) return;
    int m = (v+h)/2;
    Tabell.bytt(a,m,h);
    int k = parter(a,v,h-1,a[h]);
    Tabell.bytt(a,h,k);
    quicksort(a,v,k-1);
    quicksort(a,k+1,h);

  }
  public static int parter(int []a, int v, int h, int verdi){

     while(v<=h){
         while(v <=h && a[v] < verdi)v++;
         while(v <= h && a[h] >= verdi)h--;
         if(v < h) {
             Tabell.bytt(a, v, h);
         }
         else return v;
         }
     return v;
  }
public static boolean ersortert(int []a){
        for(int i = 1; i < a.length; i++){
            if(a[i] < a[i-1]) return false;
        }
        return true;
}
}
