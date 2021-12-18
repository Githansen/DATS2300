package Strukturer;



import java.io.*;                               // filbehandling
import java.net.URL;                            // internett
import java.util.PriorityQueue;

import Strukturer.*;                         // diverse metoder

public class Huffman                            // klasse for komprimering
{
    private static class Node                     // en basisklasse
    {
        private int frekvens;                       // nodens frekvens
        private Node venstre;                       // referanse til venstre barn
        private Node høyre;                         // referanse til høyre barn

        private Node() {}                           // standardkonstruktør

        private Node(int frekvens, Node v, Node h)  // konstruktør
        {
            this.frekvens = frekvens;
            venstre = v;
            høyre = h;
        }
    }  // class Node

    private static class BladNode extends Node    // en subklasse
    {
        private final char tegn;                    // bladnodens tegn

        private BladNode(char tegn, int frekvens)   // konstruktør
        {
            super(frekvens, null, null);     // basisklassens konstruktør
            this.tegn = tegn;
        }
    }  // class BladNode
    public void bygger(int [] a){

           Node f =  byggHuffmanTre(a);
            System.out.println(f.høyre.venstre.frekvens);
    }
    private static Node byggHuffmanTre(int[] frekvens)
    {
        PriorityQueue<Node> kø =
                new PriorityQueue<>((p, q) -> p.frekvens - q.frekvens);

        for (int i = 0; i < frekvens.length; i++)
            if (frekvens[i] > 0)          // dette tegnet skal være med
                kø.offer(new BladNode((char)i,frekvens[i]));

        if (kø.size() < 2)            // må ha minst to noder
            throw new IllegalArgumentException("Det er for få tegn!");

        for (Node rot = null;;)
        {
            Node v = kø.poll();           // blir venstre barn
            Node h = kø.poll();           // blir høyre barn

            rot = new Node(v.frekvens + h.frekvens, v, h);

            if (kø.isEmpty()) return rot;     // treet er ferdig - roten returneres
            else kø.offer(rot);         // legger noden inn i køen

        }
    }
}  // class Huffman