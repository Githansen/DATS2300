package Strukturer;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EnkeltLenketListe<T> implements Kø<T>
{
    private static final class Node<T>       // en indre nodeklasse
    {
        private T verdi;                       // nodens verdi
        private Node<T> neste;                 // den neste noden

        private Node(T verdi,Node<T> neste)    // konstruktør
        {
            this.verdi = verdi;
            this.neste = neste;
        }
    }  // Node

    private Node<T> hode, hale;  // pekere til første og siste node

    private int antall;          // antall verdier/noder i listen

    private int endringer;
    public EnkeltLenketListe()   // standardkonstruktør
    {
        hode = hale = null;        // hode og hale til null
        antall = 0;                // ingen verdier - listen er tom
        endringer = 0;
    }
    public EnkeltLenketListe(T[] a){
        if(a.length == 0) return;
        int sjekk = 0;
        while(a[sjekk] == null){
            sjekk++;
        }
        antall = 1;
        hode = new Node(a[sjekk], null);
        Node<T> curr = hode;
        for(int i = sjekk+1; i < a.length; i++){
            if(a[i] != null) {
                curr.neste = new Node(a[i], null);
                curr = curr.neste;
                antall++;
            }
        }
        hale = curr;

    }
    @Override
    public boolean leggInn(T verdi)   // verdi legges bakerst
    {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        if (antall == 0)  hode = hale = new Node<>(verdi, null);  // tom liste
        else hale = hale.neste = new Node<>(verdi, null);         // legges bakerst

        antall++;        // en mer i listen
        return true;     // vellykket innlegging
    }

    @Override
    public T kikk() {
        return hode.verdi;
    }

    @Override
    public T taUt() {
        T temp = hode.verdi;
        fjern(0);
        return temp;
    }


    public void leggInn(int indeks, T verdi)    // verdi til posisjon indeks
    {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, true);        // true: indeks = antall er lovlig

        if (indeks == 0)                     // ny verdi skal ligge først
        {
            hode = new Node<>(verdi, hode);    // legges først
            if (antall == 0) hale = hode;      // hode og hale peker på samme node
        }
        else if (indeks == antall)           // ny verdi skal ligge bakerst
        {
            hale = hale.neste = new Node<>(verdi, null);  // legges bakerst
        }
        else
        {
            Node<T> p = hode;                  // p flyttes indeks - 1 ganger
            for (int i = 1; i < indeks; i++) p = p.neste;

            p.neste = new Node<>(verdi, p.neste);  // verdi settes inn i listen
        }

        antall++;                            // listen har fått en ny verdi
    }


    public boolean inneholder(T t)
    {
       return indeksTil(t) != -1;
    }


    public T hent(int indeks)
    {
        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig
        return finnNode(indeks).verdi;
    }


    public int indeksTil(T t)
    {
        Node<T> curr = hode;
        int indeks = 0;
        while(curr != null){

            if(curr.verdi.equals(t)){
                return indeks;
            }
            indeks++;
            curr = curr.neste;
        }
        return -1;
    }
    private boolean indeksKontroll(int indeks, boolean x){
        if(indeks < 0 || indeks >= antall) return false;
        else return true;
    }

    public T oppdater(int indeks, T t)
    {
        Objects.requireNonNull(t, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig

        Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;

        p.verdi = t;
        return gammelVerdi;
    }
    private Node<T> finnNode(int indeks)
    {
        Node<T> p = hode;
        for (int i = 0; i < indeks; i++) p = p.neste;
        return p;
    }

    public T fjern(int indeks)
    {
        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig

        T temp;                              // hjelpevariabel

        if (indeks == 0)                     // skal første verdi fjernes?
        {
            temp = hode.verdi;                 // tar vare på verdien som skal fjernes
            hode = hode.neste;                 // hode flyttes til neste node
            if (antall == 1) hale = null;      // det var kun en verdi i listen
        }
        else
        {
            Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
            Node<T> q = p.neste;               // q skal fjernes
            temp = q.verdi;                    // tar vare på verdien som skal fjernes

            if (q == hale) hale = p;           // q er siste node
            p.neste = q.neste;                 // "hopper over" q
        }

        antall--;                            // reduserer antallet
        return temp;                         // returner fjernet verdi
    }


    public boolean fjern(T t)
    {

        int k = indeksTil(t);

        if(inneholder(t)) {
            if(k == 0){
                hode = hode.neste;

            }
            else {
                Node<T> pre = finnNode(k - 1);
                Node<T> riktig = finnNode(k);
                Node<T> etter = finnNode(k + 1);
                pre.neste = etter;
                antall--;
                return true;
            }
            }

        return false;
    }

    @Override
    public int antall()
    {
       return antall;
    }

    @Override
    public boolean tom()
    {
        return antall == 0;
    }

    @Override
    public void nullstill()
    {
       Node<T> curr = hode, q = null;
       while(curr != null){
           q = curr.neste;
           curr.neste = null;
           curr.verdi = null;
           curr = q;
       }
       antall = 0;
       hode = hale = null;
    }


    public Iterator<T> iterator()
    {
        return new EnkeltLenketListeIterator();
    }
    public void forEachRemaining(Consumer<? super T> handling)
    {
        Node <T> p = hode;
        Objects.requireNonNull(handling, "handling er null!");
        while (p != null)
        {
            handling.accept(p.verdi);
            p = p.neste;
        }
    }
    public void forEach(Consumer<? super T> handling)
    {
        Objects.requireNonNull(handling, "handling er null!");

        Node<T> p = hode;
        while (p != null)
        {
            handling.accept(p.verdi);
            p = p.neste;
        }
    }
    public boolean fjernHvis(Predicate<? super T> p){
        Objects.requireNonNull(p, "null-predikat!");
        Node<T> curr = hode;
        boolean fjernet = false;

        while(curr != null){
            if(p.test(curr.verdi)){
                fjern(curr.verdi);
                fjernet = true;
            }
            curr = curr.neste;
        }

return fjernet;
    }
    @Override
    public String toString()
    {
       StringJoiner sj = new StringJoiner(", ", "[", "]");
       Node curr = hode;
       while(curr != null){
           sj.add(curr.verdi + "");
           curr = curr.neste;
       }
       return sj.toString();
    }
    private class EnkeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> p = hode;         // p starter på den første i listen
        private boolean fjernOK = false;  // blir sann når next() kalles
        private int iteratorendringer = endringer;  // startverdi
        @Override
        public boolean hasNext() {
            return p != null;  // p er ute av listen hvis den har blitt null
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");

            fjernOK = true;            // nå kan remove() kalles
            T denneVerdi = p.verdi;    // tar vare på verdien i p
            p = p.neste;               // flytter p til den neste noden

            return denneVerdi;         // returnerer verdien
        }
        public void remove()
        {
            if (!fjernOK) throw new IllegalStateException("Ulovlig tilstand!");

            fjernOK = false;               // remove() kan ikke kalles på nytt
            Node<T> q = hode;              // hjelpevariabel

            if (hode.neste == p)           // skal den første fjernes?
            {
                hode = hode.neste;           // den første fjernes
                if (p == null) hale = null;  // dette var den eneste noden
            }
            else
            {
                Node<T> r = hode;            // må finne forgjengeren
                // til forgjengeren til p
                while (r.neste.neste != p)
                {
                    r = r.neste;               // flytter r
                }

                q = r.neste;                 // det er q som skal fjernes
                r.neste = p;                 // "hopper" over q
                if (p == null) hale = r;     // q var den siste
            }

            q.verdi = null;                // nuller verdien i noden
            q.neste = null;                // nuller nestereferansen
            iteratorendringer++;
            endringer ++;
            antall--;                      // en node mindre i listen
        }


    } // class EnkeltLenketListeIterator
}  // EnkeltLenketListe