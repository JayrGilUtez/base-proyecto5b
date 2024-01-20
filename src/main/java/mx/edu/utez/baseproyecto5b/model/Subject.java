package mx.edu.utez.baseproyecto5b.model;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class Subject {
    @Id
    private long id;
    private String name;
    private String teacher;
    @Backlink(to = "subjects")
    public ToMany<Student> students;



    //<editor-fold desc="Adding example">

    /*
    Teacher teacher1 = new Teacher("Teacher 1");
    Teacher teacher2 = new Teacher("Teacher 2");

    Student student1 = new Student();
    student1.teachers.add(teacher1);
    student1.teachers.add(teacher2);

    Student student2 = new Student();
    student2.teachers.add(teacher2);

    // Puts students and teachers:
    boxStore.boxFor(Student.class).put(student1, student2);

     */


    //</editor-fold>

    public Subject() {



    }

    public Subject(long id, String name, String teacher, ToMany<Student> students) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.students = students;
    }

    public Subject(long id, String name, String teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return name;


    }
    /*
    return "Subject {" + "name: " + name + "teacher:" + teather +"}";
     */

}
