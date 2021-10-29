package Eksempelklasser;

@FunctionalInterface                // legges i mappen eksempelklasser
public interface Komparator<T>      // et funksjonsgrensesnitt
{
    int compare(T o1, T o2);          // en abstrakt metode

    public static <T extends Comparable<? super T>> Komparator<T> naturligOrden()
    {
        return (x, y) -> x.compareTo(y);
    }

    public static <T extends Comparable<? super T>> Komparator<T> omvendtOrden()
    {
        return (x, y) -> y.compareTo(x);
    }
}
