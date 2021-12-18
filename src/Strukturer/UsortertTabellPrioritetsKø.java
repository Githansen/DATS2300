package Strukturer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class UsortertTabellPrioritetsKø<T> implements PrioritetsKø<T> {
    private T[] a;                       // en usortert tabell
    private int antall;                  // antall verdier i køen
    private Comparator<? super T> c;     // en komparator
    public UsortertTabellPrioritetsKø(int størrelse, Comparator<? super T> c)
    {
        a = (T[])new Object[størrelse];   // tabellens startstørrelse
        antall = 0;
        this.c = c;
    }

    public UsortertTabellPrioritetsKø(Comparator<? super T> c)
    {
        this(8,c);  // bruker 8 som startstørrelse
    }
    public static <T extends Comparable<? super T>> PrioritetsKø<T> naturligOrdenKø()
    {
        return new UsortertTabellPrioritetsKø<>(Comparator.naturalOrder());
    }
    public void leggInn(T verdi)
    {
        if (verdi == null) throw new IllegalArgumentException("Nullverdi!");

        if (antall == a.length) a = Arrays.copyOf(a,2*antall+1);  // utvider

        a[antall++] = verdi;          // verdi legges inn bakesrt
    }
    public int antall()
    {
        return antall;
    }

    public boolean tom()
    {
        return antall == 0;
    }

    private int min()  // finner posisjonen til den minste
    {
        int m = 0;
        T minverdi = a[0];

        for (int i = 0; i < antall; i++)
            if (c.compare(a[i],minverdi) < 0)
            {
                m = i;
                minverdi = a[i];
            }

        return m;        // returnerer posisjonen til den minste
    }
    public T kikk()
    {
        if (tom()) throw new NoSuchElementException("Køen er tom!");

        return a[min()];     // returnerer den minste
    }

    public T taUt()
    {
        if (tom()) throw new NoSuchElementException("Køen er tom!");

        int m = min();       // posisjonen til den minste
        T verdi = a[m];      // tar vare på den minste verdien

        antall--;            // reduserer antallet
        a[m] = a[antall];    // tetter igjen ved å flytte den siste verdien

        a[antall] = null;    // nuller for resirkulering
        return verdi;        // returnerer den minste
    }

    @Override
    public boolean taUt(T verdi) {
        return false;
    }

    public void nullstill()
    {
        while (antall > 0) a[antall--] = null;
    }

}
