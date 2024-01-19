package mx.edu.utez.baseproyecto5b.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button assignButton;

    @FXML
    private Button backButton;

    @FXML
    private Button studentButton;

    @FXML
    private Button subjectButton;

    @FXML
    public void onAssignButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx.edu.utez.baseproyecto5b/assign-window.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) assignButton.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.setTitle("Estudiantes");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void onCloseButtonClick() {

    }

    @FXML
    public void onStudentButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx.edu.utez.baseproyecto5b/student-crud.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) studentButton.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.setTitle("Estudiantes");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void onSubjectButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx.edu.utez.baseproyecto5b/subject-crud.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) subjectButton.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.setTitle("Materias");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
