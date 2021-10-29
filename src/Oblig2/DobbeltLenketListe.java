package Oblig2;

////////////////// class DobbeltLenketListe //////////////////////////////


import Strukturer.Lenketliste;
import Strukturer.Liste;

import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        antall = 0;
        endringer = 0;
        hode = null;
        hale = null;
    }

    public DobbeltLenketListe(T[] a) {

        endringer = 0;
        antall = 0;
        if(a == null)throw new NullPointerException("a er null");
        if(a.length == 0)return;
        int sjekk = 0;
        while(sjekk < a.length && a[sjekk] == null) sjekk++; //Finner første verdi som ikke er null
        if(sjekk < a.length){
            hode = hale = new Node<T>(a[sjekk]);
            antall ++;
        }
        Node curr = hode;
        for(int i = sjekk+1; i < a.length; i++){
            if(a[i] != null) {
                curr.neste = new Node<T>(a[i]);
                curr.neste.forrige = curr;
                antall++;
                curr = curr.neste;
            }
        }
        hale = curr;

    }
    public Liste<T> subliste(int fra, int til){
        if(tom()) return this;
        if( fra < 0 || til > antall) throw new IndexOutOfBoundsException("");
        if(fra > til) throw new IllegalArgumentException();
        Liste <T> retur = new DobbeltLenketListe<>();
       Node <T> curr = finnode(fra);
       for(int i = fra; i < til; i++) {
           retur.leggInn(curr.verdi);
           curr = curr.neste;
       }

        return retur;
    }
    /*
    public Liste<T> subliste2(int fra, int til) {
        if(tom()) return this;
        if( fra < 0 || til > antall) throw new IndexOutOfBoundsException("");
        if(fra > til) throw new IllegalArgumentException();
        T[] liste = (T[]) new Object[til-fra];
        Node<T> curr = finnode(fra);
        int indeks = 0;
        for(int i = fra; i < til; i++){
            liste[indeks] = curr.verdi;
            curr = curr.neste;
            indeks++;
        }
        DobbeltLenketListe<T> retur = new DobbeltLenketListe(liste);
        return retur;
    }
*/

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        if(verdi.equals(null)) return false;
        if(antall == 0){
            hode = new Node(verdi);
            hale = hode;
        }
        else{
            Node<T> curr = hale;
            curr.neste = new Node(verdi);
            hale = curr.neste;
            hale.forrige = curr;
        }
        antall++;
        endringer ++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        if(antall == 0 && indeks == 0){
            leggInn(verdi);
            return;
        }
        verdi = Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt");
        if(indeks < 0 || indeks > antall) throw new IndexOutOfBoundsException();
        Node<T> curr = new Node(verdi);
       Node<T> p = hode;

        if(indeks == 0){
            hode = curr;
            hode.neste = p;
            p.forrige = curr;
            antall++;
            endringer++;
        }
        else if(indeks == antall){
          Node<T> q = hale;
            hale = curr;
            curr.forrige = q;
            q.neste = curr;
            antall++;
            endringer++;
        }
        else{
            Node<T> pre = finnode(indeks-1);
            Node <T>post = finnode(indeks);
            pre.neste = curr;
            post.forrige = curr;
            curr.forrige = pre;
            curr.neste = post;
            antall++;
            endringer++;
        }
    }
    public Node<T> finnode(int indeks){
        indeksKontroll(indeks,false);

        if(indeks < antall/2) {
            Node<T> p = hode;
            int teller = 0;
            while(teller != indeks){
                p = p.neste;
                teller++;
            }
            return p;
        }
        else{
            int teller = antall-1;
            Node <T> p = hale;
          while(teller != indeks){
              p = p.forrige;
              teller--;
          }
            return p;
        }
    }
    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks) {

        indeksKontroll(indeks,false);
        Node <T> curr = finnode(indeks);
            return curr.verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node <T> curr = hode;
        for(int i = 0; i < antall; i++){
            if(curr.verdi.equals(verdi)){
                return i;
            }
            curr = curr.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false);
        if(nyverdi == null) throw new NullPointerException();
        Node <T> curr = finnode(indeks);
        T temp = curr.verdi;
        curr.verdi = nyverdi;
        endringer++;
        return temp;
    }



   @Override public boolean fjern(T verdi){
       Node <T> curr = hode;
       while(curr != null){
           if(curr.verdi.equals(verdi))break;
           curr = curr.neste;
       }
       if(curr == null) return false;
        if(curr == hode){
            if(antall > 1){
                hode = hode.neste;
                hode.forrige = null;
            }
            else hode = hale = null;
        }
        else if(curr == hale){
            hale = hale.forrige;
            hale.neste = null;
        }
        else{
            Node <T> pre = curr.forrige;
            Node <T> post = curr.neste;
            pre.neste = post;
            post.forrige = pre;
        }

        antall --;
        endringer++;
        return true;
   }


    @Override
    public T fjern(int indeks) {

        indeksKontroll(indeks, false);
        T temp;
        Node<T> p = hode;
        if (indeks == 0) {
            if(antall == 1){
                temp = p.verdi;
                hode = hale = null;
            }
            else {
                p = hode;
                temp = p.verdi;
                hode = hode.neste;
                hode.forrige = null;
            }

        }
        else if (indeks == antall-1) {
            p = hale;
            temp = p.verdi;
            hale = hale.forrige;
            hale.neste = null;
        } else {
            for (int i = 0; i < indeks; i++) {
                p = p.neste;
            }
            Node<T> q = p.forrige;
            Node<T> r = p.neste;
            q.neste = r;
            r.forrige = q;

            temp = p.verdi;
        }
        antall--;
        endringer++;
        return temp;
    }

    @Override
    public void nullstill() {
        Node curr = hode, p;
        while(curr != null){
            p = curr.neste;
            curr.neste = null;
            curr.verdi = null;
            curr = p;
        }
        endringer = antall;
        antall = 0;
        hode = hale = null;

    }

    public void nullstill2(){
        while(antall > 0){
            fjern(0);
        }
    }
    @Override
    public String toString() {
        if(hode == null) return "[]";
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        Node<T> curr = hode;
        while(curr != null){
            sj.add(curr.verdi.toString());
            curr = curr.neste;
        }


    return sj.toString();
    }

    public String omvendtString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        if(hode == null) return "[]";
        Node <T> curr = hale;
        while(curr != null){
            sj.add(curr.verdi.toString());
            curr = curr.forrige;
        }

        return sj.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
       indeksKontroll(indeks,false);
       return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // denne starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
           if(iteratorendringer != endringer){ //Sjekker først om iteratorendringer er lik endringer
               throw new ConcurrentModificationException("Feil i endringer/iteratorendringer");
           }        //Herfra og ned er det relativt likt Programkode 3.3.4 c) fra kompendiet
           if(!hasNext()){
              throw new NoSuchElementException("Neste finnes ikke");
           }
           fjernOK = true;
           T denneverdi =  denne.verdi;
           denne = denne.neste;
           return denneverdi;
        }

        @Override
        public void remove() {
          if(!fjernOK) throw new IllegalStateException("Ulovlig tilstand");
          if(antall == 0 || fjernOK == false) throw new IllegalStateException("");
            fjernOK = false;
            if(antall == 1) hode = hale = null;
            else if(denne == hode.neste){
                hode = hode.neste;
                hode.forrige = null;
            }
            else if(denne == null) {
                hale = hale.forrige;
                hale.neste = null;
            }
            else{
                Node <T> fjern = denne.forrige;
                fjern.forrige.neste = denne;
                denne.forrige = fjern.forrige;
            }

            antall --;
            endringer ++;
            iteratorendringer ++;
        }

    } // class DobbeltLenketListeIterator


    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
       kvikksortering(liste,0,liste.antall()-1, c);
    }
    private static<T> void byttverdier(Liste <T> liste, int i, int j){
        T temp = liste.hent(i);
        liste.oppdater(i,liste.hent(j));
        liste.oppdater(j,temp);
    }
    private static<T> void kvikksortering(Liste <T> liste, int fra, int til, Comparator<? super T> c){

        if(fra >= til) return;
        int m = (til+fra)/2;
        byttverdier(liste,m,til);
        int k = partering(liste,fra,til-1, liste.hent(til), c);
        byttverdier(liste, til, k);
        kvikksortering(liste, fra, k-1, c);
        kvikksortering(liste, k+1, til, c);
    }
    private static<T> int partering(Liste <T> liste, int v, int h,  T skilleverdi, Comparator<? super T> c){

        while(true){
            while(v<= h && c.compare(liste.hent(v), skilleverdi) < 0){
                v++;
            }
            while( v<=h && c.compare(liste.hent(h), skilleverdi) >= 0) h--;
            if(v < h) {
                byttverdier(liste, v,h);
            }
            else {
            return v;
            }
        }
    }
} // class DobbeltLenketListe


