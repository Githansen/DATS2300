package Strukturer;

import java.util.Iterator;

public abstract class AbstraktBeholder<T> implements Beholder<T>
{
    @Override
    public abstract boolean leggInn(T t);    // en abstrakt metode

    @Override
    public boolean inneholder(T t)
    {
        if (t == null)  // tillatt med null-verdier?
        {
            for (T s : this) if (s == null) return true;
        }
        else
        {
            for (T s : this) if (t.equals(s)) return true;
        }
        return false;
    }

    @Override
    public boolean fjern(T t)
    {
        Iterator<T> i = iterator();

        if (t == null)
        {
            while (i.hasNext())
            {
                if (i.next() == null)
                {
                    i.remove();
                    return true;
                }
            }
        }
        else
        {
            while (i.hasNext())
            {
                if (t.equals(i.next()))
                {
                    i.remove();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int antall()
    {
        int antall = 0;
        for (T t : this) antall++;   // bruker en forAlle-løkke
        return antall;
    }

    @Override
    public boolean tom()
    {
        return antall() == 0;    // ferdig kode
    }

    @Override
    public void nullstill()
    {
        for (Iterator<T> i = iterator(); i.hasNext(); )
        {
            i.next();
            i.remove();
        }
    }

    @Override
    public abstract Iterator<T> iterator();    // en abstrakt metode

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder("[");

        Iterator<T> i = iterator();

        if (i.hasNext()) s.append(i.next());  // første verdi

        while (i.hasNext())
        {
            s.append(',');        // komma
            s.append(' ');        // blank
            s.append(i.next());   // verdi
        }

        s.append(']');

        return s.toString();
    }

} // AbstraktBeholder