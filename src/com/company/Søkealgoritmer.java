package com.company;

public class Søkealgoritmer {
    public static int finn(int[] a, int verdi){
        int m = (a.length)/2;
        int i = m;
        if(verdi > a[a.length-1])return -a.length-1;
        else if(verdi >= a[m]){
            while(a[i] <= verdi){
                if(a[i] == verdi)return i;
                i++;
            }
        }
        else{
            i = 0;
            while(a[i] <= verdi){
                if(a[i] == verdi)return i;
                i++;
            }
        }
        return -(i+1);

    }
}
