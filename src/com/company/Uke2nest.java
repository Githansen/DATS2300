package com.company;

public class Uke2nest {
    public static int[] maksnest(int [] a){
        int maksverdi = a[0];
        int nestmaksverdi = a[1];
        int maksin = 0;
        int nestmaksin = 1;

        for(int i = 0; i<a.length; i++){
            if(a[i] > nestmaksverdi){
                if(a[i] > maksverdi){
                    nestmaksverdi = a[maksin];
                    nestmaksin = maksin;
                    maksin = i;
                    maksverdi = a[i];
                }
                else{
                    nestmaksin = i;
                    nestmaksverdi = a[i];
                }
            }

        }
        System.out.println(maksin + " " + nestmaksin);
        return new int[]{maksin,nestmaksin};
    }
    public static int minfratil(int[] a, int fra, int til){
        if (fra < 0 || til > a.length || fra >= til)
        {
            throw new IllegalArgumentException("Illegalt intervall!");
        }

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
        int m = fra;
        int maksverdi = a[fra];
        int sist = a.length-1;
        int temp = a[sist];
        a[sist] = 0x7fffffff;
        for(int i = fra; i <til; i++){
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
}
