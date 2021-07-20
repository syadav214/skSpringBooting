import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private int standard;

    Student(String name, int age, int standard){
        this.name = name;
        this.age = age;
        this.standard = standard;
    }
}
