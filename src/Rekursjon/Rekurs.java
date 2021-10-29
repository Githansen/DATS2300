package Rekursjon;

import com.company.Tabell;

import java.util.Arrays;

public class Rekurs {
    public static void recursivepermutation(int []a, int k){

        if(k == a.length-1) {
            System.out.println(Arrays.toString(a));
        }

            for (int i = k; i < a.length; i++) {
                Tabell.bytt(a, i, k);
                recursivepermutation(a, k + 1);
                Tabell.bytt(a, k, i);
            }
    }
    public static int tverrsum(int n){
        if(n/10 < 1){
            return n;
        }
        return n % 10 + tverrsum(n/10);
    }
    public static int euklid(int a, int b){

        if(b== 0) return a;
        return euklid(b,a%b);
    }

    public static int sum(int n){
        if(n == 1) return 1;
        return n + sum(n-1);
    }
    public static int arrsum(int []a, int n){
        if(n == 0) return a[n];
        return a[n] + arrsum(a,n-1);
    }
    public static int sifferrot(int n){
        n = tverrsum(n);
        if(n < 10) return n;
        return sifferrot(n);
    }

}
