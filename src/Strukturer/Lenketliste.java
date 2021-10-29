package Strukturer;

import java.util.List;

public class Lenketliste <T> {
    class Node<T> {
    T verdi;
    Node<T> next;
    Node<T> prev;
    public Node(T verdi){
        this.verdi = verdi;
    }

    }
    public Lenketliste(int[] a){
        if(a.length == 0)return;
        head = new Node(a[0]);
        Node curr = head;
        for(int i = 1; i < a.length; i++){
            curr.next = new Node(a[i]);
            curr.next.prev = curr;
            curr = curr.next;
        }
        tail = curr;
        antall = a.length;
    }
    public int size(){
        if(head == null) return 0;
        int antall = 1;
        Node curr = head;
        while (curr.next != null){
            antall++;
            curr = curr.next;
        }

        return antall;
    }
    public Lenketliste(){
        antall = 0;
    }
   private Node head;
   private Node tail;
   private int antall;
    public void legginn(T verdi){
        if(head == null){
            head = new Node(verdi);
            tail = head;
            antall++;
        }
        else{
            Node curr = head;
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = new Node(verdi);
            tail = curr.next;
            tail.prev = curr;
            antall++;
        }
    }
    public void legginn(T verdi, int indeks){
        if(indeks >= antall || indeks < 0){
            throw new IllegalArgumentException("Indeks er feil");
        }
        Node <T> curr = new Node(verdi);
        Node <T> p = head;
        if(indeks == 0){
          head = curr;
          head.next = p;
          p.prev = curr;
           if(antall == 0) head = tail;
           antall++;
        }
       else if(indeks == antall-1){
           Node <T> q = tail;
            tail = curr;
            curr.prev = q;
            q.next = curr;
            antall++;
        }
       else{
        Node pre = finnode(indeks-1);
        Node post = finnode(indeks);
        pre.next = curr;
        post.prev = curr;
        curr.prev = pre;
        curr.next = post;
        antall++;
        }
    }

    public void slett(int indeks){
        if(indeks >= antall || indeks < 0) {
            throw new UnsupportedOperationException("UGYLDIG INDEKS");
        }
        if(antall == 1) head = tail = null;
        if(indeks == 0){
            head = head.next;
            head.prev = null;
            antall --;
        }
         else if(indeks < antall-1){
            Node<T> pre = finnode(indeks-1);
            Node<T> post = finnode(indeks+1);
            pre.next = post;
            post.prev = pre;
            antall--;
        }
         if(indeks == antall-1){
             tail = tail.prev;
             tail.next = null;
             antall--;
         }

    }
    public int finnNode(T verdi){
        int indeks = 0;
        Node <T> curr = head;
        while(curr != null){
            if(curr.verdi == verdi){
                return indeks;
            }
            indeks++;
        }
        return indeks;
    }
    public Node<T> finnode(int indeks){
        Node <T> p = head;
        for(int i = 0; i <indeks; i++){
            p = p.next;
        }

        return p;
    }
    public void skriv(){
        Node curr = head;
        while(curr.next != null){
            System.out.println(curr.verdi);
            curr = curr.next;
        }
        System.out.println(curr.verdi);
    }
    public void skrivbak(){
        Node curr = tail;
        while(curr.prev != null){
            System.out.println(curr.verdi);
            curr = curr.prev;
        }
        System.out.println(curr.verdi);
    }
    public Lenketliste merge(Lenketliste b){
        Lenketliste retur = new Lenketliste();
        int max = Math.max(getAntall(), b.getAntall());
        Node astart = head;
        Node bstart = b.head;
        for(int i =0; i < max; i++){
            if(i < getAntall()){
                retur.legginn(astart.verdi);
                astart = astart.next;
            }
            if(i < b.getAntall()){
                retur.legginn(bstart.verdi);
                bstart = bstart.next;
            }
        }
        return retur;
    }
    public int getAntall(){
        return this.antall;
    }

}
