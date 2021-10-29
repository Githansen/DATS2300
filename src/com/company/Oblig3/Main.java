package com.company.Oblig3;

import Strukturer.Liste;

import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;

public class Main {
    public static void main(String ... args){
        Integer[] a = {2,8,6,1,7,4,3,9,5,10};                  // verdier
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for(Integer i : a) tre.leggInn(i);
        Iterator<Integer> i = tre.iterator();      // en iterator er opprettet
        tre.leggInn(6);  // en innlegging er en endring
        i.next();        // kaster en ConcurrentModificationException

    }
}
