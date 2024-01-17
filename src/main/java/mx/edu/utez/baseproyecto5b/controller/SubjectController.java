package mx.edu.utez.baseproyecto5b.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SubjectController {

    @FXML
    private Button backButton;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonInsert;

    @FXML
    private Button buttonUpdate;

    @FXML
    private TableColumn<?, ?> column1;

    @FXML
    private TableColumn<?, ?> column2;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    void onBackButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx.edu.utez.baseproyecto5b/menu-window.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.setTitle("Estudiantes");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void onInsertButtonClick(ActionEvent event) {

    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) {

    }

}
