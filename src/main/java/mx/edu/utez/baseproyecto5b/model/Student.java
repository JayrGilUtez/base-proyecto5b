package mx.edu.utez.baseproyecto5b.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;
import io.objectbox.relation.ToMany;
import javafx.scene.control.Button;


@Entity
public class Student {
    @Id
    private long id;
    private String name;
    private String surname;
    private String lastname;
    private String age;

    public ToMany<Subject> subjects;

    public Student(){

    }

    public Student(long id, String name, String surname, String lastname, String age, ToMany<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
        this.subjects = subjects;
    }

    public Student(long id, String name, String surname, String lastname, String age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
    }

    public Student(String name, String surname, String lastname, String age) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
    }


    public Student(String name, String surname, String lastname, String age, ToMany<Subject> subjects) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
        this.subjects = subjects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
    /*
    return "Student:{" + "id: " + id + " name: " + name +  "}";
     */
}
