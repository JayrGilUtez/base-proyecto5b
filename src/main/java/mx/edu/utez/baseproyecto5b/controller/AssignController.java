package mx.edu.utez.baseproyecto5b.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AssignController {

    @FXML
    private TextField studentSearchField;
    @FXML
    private TextField subjectSearchField;

    @FXML
    private Button studentSearchBtn;
    @FXML
    private Button subjectSearchBtn;


    @FXML
    private Button toLeftBtn;
    @FXML
    private Button toRightBtn;
    @FXML
    private Button backBtn;


    @FXML
    private TableView<?> studentsTable;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> surnameColumn;
    @FXML
    private TableColumn<?, ?> lastnameColumn;
    @FXML
    private TableColumn<?, ?> ageColumn;

    @FXML
    private TableView<?> subjectTable;
    @FXML
    private TableColumn<?, ?> subjectColumn;
    @FXML
    private TableColumn<?, ?> teacherColumn;

    @FXML
    void buscarButtonClicked() {

    }
    @FXML
    void onBackButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx.edu.utez.baseproyecto5b/menu-window.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) backBtn.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.setTitle("Estudiantes");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
