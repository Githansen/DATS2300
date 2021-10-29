package Eksempelklasser;

import java.util.Arrays;

public enum Måned {
    JAN (1,"Januar"),
    FEB (2, "Februar"),
    MAR (3,"Mars"),
    APR (4, "April"),
    MAI (5, "Mai"),
    JUN (6, "Juni"),
    JUL (7, "Juli"),
    AUG (8, "August"),
    SEP (9, "September"),
    OKT (10, "Oktober"),
    NOV (11, "November"),
    DES (12, "Desember");
    private final int mndnr;
    private final String fulltnavn;
    private Måned(int mndnr, String fulltnavn){
        this.mndnr = mndnr;
        this.fulltnavn = fulltnavn;
    }
    public int mndnr(){
        return mndnr;
    }
    public String toString(){
        return mndnr + " " + fulltnavn;
    }
    static Måned [] høst(){
        return Arrays.copyOfRange(values(), 8,10);
    }
    static Måned[] vår(int mndnr){
        return Arrays.copyOfRange(values(),3,5);
    }
    static Måned[] vinter(int mndnr){
        return new Måned[] { NOV, DES, JAN, FEB, MAR };
    }
    static Måned[] sommer(int mndnr){
        return Arrays.copyOfRange(values(),6,7);
    }
}
