package com.company;

public class Bil implements Comparable<Bil>{
    String regnr;
    int hjul;
    public Bil(String regnr, int hjul){
        this.regnr = regnr;
        this.hjul = hjul;
    }
    public int compareTo(Bil t){
        if(this.hjul > t.hjul)return 1;
        if(this.hjul < t.hjul) return -1;
        else {
            if(this.regnr.compareTo(t.regnr) < 0) {

                return -1;
            }
            else{

                return 1;
            }
        }

    }
    public String toString(){

        return this.regnr + " " + this.hjul;
    }
}
