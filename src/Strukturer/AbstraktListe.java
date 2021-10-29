package Strukturer;

import java.util.Iterator;

public abstract class AbstraktListe<T> extends AbstraktBeholder<T> implements Liste<T>
{
    public abstract void leggInn(int indeks, T t);

    protected void indeksKontroll(int indeks)
    {
        if (indeks < 0 )
            throw new IndexOutOfBoundsException("Indeks " +
                    indeks + " er negativ!");
        else if (indeks >= antall())
            throw new IndexOutOfBoundsException("Indeks " +
                    indeks + " >= antall(" + antall() + ") noder!");
    }

    public boolean leggInn(T t)
    {
        leggInn(antall(),t);
        return true;
    }

    public T hent(int indeks)
    {
        indeksKontroll(indeks);

        Iterator<T> i = iterator();

        for (int k = 0; k < indeks; k++) i.next();

        return i.next();
    }

    public int indeksTil(T t)
    {
        Iterator<T> i = iterator();
        for (int indeks = 0; i.hasNext(); indeks++)
        {
            if (i.next().equals(t)) return indeks;
        }
        return -1;
    }

    public T oppdater(int indeks, T t)
    {
        indeksKontroll(indeks);

        T gammel = fjern(indeks);

        leggInn(indeks,t);

        return gammel;
    }

    public T fjern(int indeks)
    {
        indeksKontroll(indeks);

        Iterator<T> i = iterator();

        for (int k = 0; k < indeks; k++) i.next();

        T temp = i.next();
        i.remove();
        return temp;
    }

} // AbstraktListe
