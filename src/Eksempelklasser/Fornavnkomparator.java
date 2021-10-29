package Eksempelklasser;

class FornavnKomparator implements Komparator<Person>
{
    public int compare(Person p1, Person p2)        // to personer
    {
        return p1.fornavn().compareTo(p2.fornavn());  // sammenligner fornavn
    }
}
