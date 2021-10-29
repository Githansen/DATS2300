package Eksempelklasser;

import java.util.Objects;

public class Person implements Comparable<Person>{
    private final String fornavn;         // personens fornavn
    private final String etternavn;       // personens etternavn

    public Person(String fornavn, String etternavn)   // konstruktør
    {
        Objects.requireNonNull(fornavn, "fornavn er null");
        Objects.requireNonNull(etternavn, "etternavn er null");
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public String fornavn() { return fornavn; }       // aksessor
    public String etternavn() { return etternavn; }   // aksessor

    public int compareTo(Person p)    // pga. Comparable<Person>
    {
        if(etternavn.compareTo(p.etternavn) != 0) return etternavn.compareTo(p.etternavn);
        else{
            return fornavn.compareTo(p.fornavn);
        }
    }

    public boolean equals(Person o)      // vår versjon av equals
    {
        if(o == null) return false;
        if (o == this) return true;
        if (getClass() != o.getClass()) return false;
        return etternavn.equals(o.etternavn) && fornavn.equals(o.fornavn);
    }

    public int hashCode() { return Objects.hash(etternavn, fornavn); }

    public String toString() { return fornavn + " " + etternavn; }
}
