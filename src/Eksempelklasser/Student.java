package Eksempelklasser;

public class Student extends Person{
    private final Studium studium;

    public Student(String fornavn, String etternavn, Studium studium) {
        super(fornavn, etternavn);
        this.studium = studium;
    }
    public String toString(){
        return fornavn() + " " + etternavn() + " " + studium.name();
    }
    public Studium studium(){
        return studium;
    }
}
