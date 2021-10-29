package Strukturer;

import Oblig2.DobbeltLenketListe;
import com.company.Tabell;
import java.util.*;

public class run {
    public static void main(String ... args) {
        BinTre<Integer> s = new BinTre<>();
        s.leggInn(1,1);
        s.leggInn(2,3);
        s.leggInn(3,5);
        s.leggInn(4,7);
        s.leggInn(5,6);
        s.leggInn(6,8);
        s.leggInn(7,11);
        s.leggInn(10,12);
        s.leggInn(11,10);
        s.leggInn(22,14);
        s.leggInn(23,18);
        s.leggInn(13,10);
        s.leggInn(14,15);
        s.leggInn(28,15);
        s.leggInn(29,20);
        System.out.println(s.ermintreiterativ(Comparator.naturalOrder()));
    }
    }

