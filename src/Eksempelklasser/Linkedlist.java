package Eksempelklasser;

import java.util.Comparator;

public class Linkedlist{
    public Linkedlist() {
    }

    private static final class Node<T>{
        T verdi;
        Node forrige, neste;
        public Node(T verdi){
            this.verdi = verdi;
            this.forrige = null;
            this.neste = null;
        }
    }
    public <T extends Comparable<?super T>> Linkedlist(T[] a){
        Node ny = new Node(a[0]);
        head = ny;
        for(int i = 1; i < a.length; i++){
            ny.neste = new Node(a[i]);
            ny.neste.forrige = ny;
            ny = ny.neste;
        }
        tail = ny;
        }


    Node head;
    Node tail;
    int antall;
    public void legginn(int verdi){
        Node ny = new Node(verdi);
        if(head == null){
            head = ny;
            tail = ny;
            antall++;

        }

        else{

           tail.neste = ny;
           ny.forrige = tail;
            tail = ny;
            antall++;
        }
    }

    public String toString(){
        Node t = head;
        StringBuilder s = new StringBuilder();
        while(t.neste != null){
            s.append(t.verdi + " ");
            t = t.neste;
        }
        s.append(t.verdi);
        return s.toString();
    }
}
