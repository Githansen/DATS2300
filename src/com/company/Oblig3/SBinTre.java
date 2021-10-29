package com.company.Oblig3;

import Strukturer.*;

import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {

            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }
    public static <T> SBinTre<T> balansert(T[] a, Comparator<? super T> c)
    {
        SBinTre<T> tre = new SBinTre<>(c);          // oppretter et tomt tre
        tre.rot = balansert(a, 0, a.length - 1);    // bruker den rekursive metoden
        tre.antall = a.length;                      // setter antallet
        return tre;                                 // returnerer treet
    }

    public static <T extends Comparable<? super T>> SBinTre<T> balansert(T[] a)
    {
        return balansert(a, Comparator.naturalOrder());
    }
    private static <T> Node<T> balansert(T[] a, int v, int h)  // en rekursiv metode
    {
        if (v > h) return null;                       // tomt intervall -> tomt tre

        int m = (v + h)/2;                            // midten
        T verdi = a[m];                               // midtverdien

        while (v < m && verdi.equals(a[m-1])) m--;    // til venstre

        Node<T> p = balansert(a, v, m - 1);           // venstre subtre
        Node<T> q = balansert(a, m + 1, h);           // høyre subtre

        return new Node<T>(verdi, p, q,null);               // rotnoden
    }
    Liste<T> intervallsøk(T fraverdi, T tilverdi){
    Kø <Node<T>> nivå = new TabellKø<>();
    Node<T> curr = rot;
    Liste<T> s = new TabellListe<>();
    nivå.leggInn(curr);
    while(!nivå.tom()){
        curr = nivå.taUt();
        int cmp = comp.compare(curr.verdi, fraverdi);
        int cmk = comp.compare(curr.verdi, tilverdi);
        if(cmp >= 0 && cmk < 0){
            s.leggInn(curr.verdi);
        }
        if(curr.venstre != null) nivå.leggInn(curr.venstre);
        if(curr.høyre != null) nivå.leggInn(curr.høyre);
    }
    return s;
    }
    public T maks(){
        Node<T> curr = rot;
        while(curr.høyre != null){
            curr = curr.høyre;
        }

        return curr.verdi;
    }
    public T minst (){
        Node<T> curr= rot;
        while(curr.venstre != null){
            curr = curr.venstre;
        }
        return curr.verdi;
    }
    public T mindre(T verdi){
        if(tom()) return null;
        if(comp.compare(verdi, minst()) < 0) return null;
        Node<T> curr = rot;
        T tak = rot.verdi;
        while(curr != null){
            int cmp = comp.compare(curr.verdi, verdi);
            if(cmp < 0) {
                tak = curr.verdi;
                curr= curr.høyre;
            }
            if(cmp > 0) curr= curr.venstre;
            else return curr.verdi;
        }
        return tak;
    }
    public int størstpos(){
        int pos = 1;
        Node<T> curr = rot;
        Kø<Node<T>> kø = new TabellKø<>();
        kø.leggInn(curr);
        Kø<Integer> posisjon = new TabellKø<>();
        posisjon.leggInn(pos);
        while(!kø.tom()){
            curr = kø.taUt();
            pos = posisjon.taUt();
            if(curr.venstre != null){
                kø.leggInn(curr.venstre);
                posisjon.leggInn(pos*2);
            }
            if(curr.høyre != null){
                kø.leggInn(curr.høyre);
                posisjon.leggInn(pos * 2 + 1);
            }
        }

        return pos;
    }
    public T gulv(T verdi)
    {
        Objects.requireNonNull(verdi, "Treet har ingen nullverdier!");
        if (tom()) throw new NoSuchElementException("Treet er tomt!");

        Node<T> p = rot; T gulv = null;

        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi);

            if (cmp < 0) p = p.venstre;  // gulvet ligger til venstre
            else if (cmp > 0)
            {
                gulv = p.verdi;            // nodeverdien er en kandidat
                p = p.høyre;
            }
            else return p.verdi;         // verdi ligger i treet
        }
        return gulv;
    }
    public T tak(T verdi)
    {
        if (tom()) throw new NoSuchElementException("Treet er tomt!");
        if (verdi == null) throw new NullPointerException("Ulovlig nullverdi!");

        Node<T> p = rot;
        T større = null;

        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi);

            if (cmp < 0)
            {
                større = p.verdi;  // en kandidat
                p = p.venstre;
            }
            else                 // den må ligge til høyre
            {
                p = p.høyre;
            }
        }

        return større;
    }
    public void fjernMin()  // hører til klassen SBinTre
    {
        if (tom()) throw new NoSuchElementException("Treet er tomt!");

        if (rot.venstre == null) rot = rot.høyre;  // rotverdien er minst
        else
        {
            Node<T> p = rot.venstre, q = rot;
            while (p.venstre != null)
            {
                q = p;  // q er forelder til p
                p = p.venstre;
            }
            // p er noden med minst verdi
            q.venstre = p.høyre;
        }
        antall--;  // det er nå én node mindre i treet
    }
    public void fjernMaks(){
        if(tom()) throw new NoSuchElementException();
        Node<T> curr = rot.høyre;
        Node <T> p = rot;
        while(curr.høyre != null) {
            p = curr;
            curr = curr.høyre;
        }
        p.høyre = curr.venstre;
        antall--;
    }
    public int fjernAlleMaks()  // hører til klassen SBinTre
    {
        if (tom()) return 0;

        int antallFjernet = 1;

        Node<T> p = rot, q = null;
        while (p.høyre != null)
        {
            if (comp.compare(p.høyre.verdi, p.verdi) > 0)
            {
                q = p;
                antallFjernet = 1;
            }
            else antallFjernet++;
            p = p.høyre;
        }
        if (q == null) rot = rot.venstre;
        else q.høyre = q.høyre.venstre;

        antall -= antallFjernet;
        return antallFjernet;
    }
    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");
        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)
        {
            q = p;
            cmp = comp.compare(verdi,p.verdi);     // Sammenligner ny nodeverdi med noden vi er på
            p = cmp < 0 ? p.venstre : p.høyre;     // Er verdien lavere enn noden vi er på, går vi til venstre-barnet, og motsatt
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node, endring gjort

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0){
            q.venstre = p;
        }
        else{
            q.høyre = p;
        }

        antall++;
        endringer++;
        return true;                             // vellykket innlegging
    }

    public boolean fjern(T verdi) {

        if (verdi == null) return false;  // treet har ingen nullverdier
        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot){ //Dersom roten skal fjernes
                rot = b;
                if(b != null) {
                    b.forelder = null;
                }
            }
            else if (p == q.venstre) {
                q.venstre = b;
                if(b != null) {
                    b.forelder = q;
                }
            }
            else {
                q.høyre = b;
                if(b != null) {
                    b.forelder = q;
                }
            }
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }
            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p){
                s.venstre = r.høyre;
            }
            else{
                s.høyre = r.høyre;
            }

        }

        antall--;   // det er nå én node mindre i treet
        return true;
    }


    public int fjernAlle(T verdi) {
        if(antall == 0) return 0;
        int antallfjernet = 0;
        while(fjern(verdi)) antallfjernet++;

        return antallfjernet;
    }

    public int antall(T verdi) {
        int antall = 0;
        Node<T> curr = rot;
        while(curr != null){
            if(comp.compare(verdi, curr.verdi) == 0) antall++;

            curr = comp.compare(curr.verdi, verdi) > 0 ?  curr.venstre : curr.høyre;
        }

        return antall;
    }

    public void nullstill() {
        slett(rot);
        rot = null;
        endringer++;
        antall = 0;
    }
    private void slett(Node<T> curr){
        if(curr == null) return;
        if(curr.venstre != null) slett(curr.venstre);
        if(curr.høyre != null) slett(curr.høyre);
        curr = null;
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        while(true){
            if(p.venstre != null) p = p.venstre;
            else if(p.høyre != null) p = p.høyre;
            else return p;
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        //Metoden følger instruks fra Kap. 5.1.7 i kompendiet.
        if(p.forelder == null) return null; //Dersom p er rot-noden returneres null da dette er siste noden i postorden
        if(p.forelder.høyre == p) p = p.forelder; //Dersom p er høyre-barnet til sin forelder, er foreldernoden neste
        else if(p.forelder.venstre == p){  //Ellers gjelder følgende:
            if(p.forelder.høyre == null) p = p.forelder; //Dersom p er enebarn er forelderen den neste
            else p = førstePostorden(p.forelder.høyre); //Dersom p ikke er enebarn, er neste tall første i postorden med høyrebarnet som rotnode
        }
        return p;
    }

    public void postorden(Oppgave<? super T> oppgave) {
        Node <T> curr = førstePostorden(rot);
        while(curr != null){
            oppgave.utførOppgave(curr.verdi);
            curr = nestePostorden(curr);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        //metoden ble gjennomgått i forelesninger uke 42, video "Dybde først-traversering
        if(p != null) {
            postordenRecursive(p.venstre, oppgave);
            postordenRecursive(p.høyre, oppgave);
            oppgave.utførOppgave(p.verdi);
        }
    }
    public void preorderit(){
        Stakk<Node <T>> kø = new TabellStakk<>();
        kø.leggInn(rot);
        System.out.println(rot.verdi);
        while(!kø.tom()){
            Node<T> curr = kø.taUt();
            if(curr.forelder != null)
            System.out.println(curr.verdi + " forelder: " + curr.forelder.verdi);
            if(curr.høyre != null)
            kø.leggInn(curr.høyre);
            if(curr.venstre != null)
                kø.leggInn(curr.venstre);
        }
    }
    public ArrayList<T> serialize() {
        ArrayList<T> liste = new ArrayList<>();
        ArrayDeque<Node<T>> kø = new ArrayDeque<>();
        kø.addFirst(rot);
        while(!kø.isEmpty()){
            Node<T> curr = kø.removeFirst();
            if(curr.venstre != null) kø.addLast(curr.venstre);
            if(curr.høyre != null)kø.addLast(curr.høyre);
            liste.add(curr.verdi);
        }
        return liste;
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        SBinTre<K> bintre = new SBinTre<>(c);
        for(int i = 0; i < data.size(); i++){
            bintre.leggInn(data.get(i));
        }

        return bintre;
    }
    public Iterator<T> iterator()     // skal ligge i class BinTre
    {
        return new InordenIterator();
    }
    private class InordenIterator implements Iterator<T>
    {
        private Stakk<Node<T>> stakk;   // hjelpestakk
        private Node<T> p = null;       // hjelpevariabel
        private int iteratorendringer;
        // en privat hjelpemetoder skal inn her

        private InordenIterator()   // konstruktør
        {
            if (tom()) return;               // treet er tomt
            stakk = new TabellStakk<>();     // oppretter stakken
            p = først(rot);                  // bruker hjelpemetoden
            iteratorendringer = endringer;
        }
        private Node<T> først(Node<T> q)   // en hjelpemetode
        {
            while (q.venstre != null)        // starter i q
            {
                stakk.leggInn(q);              // legger q på stakken
                q = q.venstre;                 // går videre mot venstre
            }
            return q;                        // q er lengst ned til venstre
        }
        public T next()
        {
            if (iteratorendringer != endringer)
                throw new ConcurrentModificationException();
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");
            T verdi = p.verdi;                        // tar vare på verdien

            if (p.høyre != null) p = først(p.høyre);  // p har høyre subtre
            else if (stakk.tom()) p = null;           // stakken er tom
            else p = stakk.taUt();                    // tar fra stakken

            return verdi;                             // returnerer verdien

        }

        public boolean hasNext()
        {
            return p != null;
        }
    }

} // ObligSBinTre
