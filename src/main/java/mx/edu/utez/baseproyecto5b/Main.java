package mx.edu.utez.baseproyecto5b;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.edu.utez.baseproyecto5b.model.MyObjectBox;
import mx.edu.utez.baseproyecto5b.model.Student;
import java.io.IOException;
import java.util.List;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Sujetos de prueba XD
        /*
        Student student1 = new Student(0, "Jayr", "Galicia", "Jimenez", "21");
        Student student2 = new Student(0, "DoCa", "Dorantes", "Castaneda", "19");
        Student student3 = new Student(0, "Diego", "Hernandez", "Palma", "20");
        Student student4 = new Student(0, "Alan", "Benitez", "Garcia", "19");
         */


        // insercion
        /*
        studentBox.put(student1);
        studentBox.put(student2);
        studentBox.put(student3);
        studentBox.put(student4);
         */

//        // Recuperación por Id
//        Student recuperado = studentBox.get(1);
//        System.out.println("Recuperado con id = 1 " + recuperado);
//
//        // Recuperar todos los registros
//        List<Student> allStudents = studentBox.getAll();
//        System.out.println("Lista de todos los usuarios:");
//        for (Student s : allStudents) {
//            System.out.println(s);
//        }


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/mx.edu.utez.baseproyecto5b/menu-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480); //320 240
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}