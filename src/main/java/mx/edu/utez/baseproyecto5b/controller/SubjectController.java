package mx.edu.utez.baseproyecto5b.controller;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import mx.edu.utez.baseproyecto5b.database.BoxStoreManager;
import mx.edu.utez.baseproyecto5b.model.Student;
import mx.edu.utez.baseproyecto5b.model.Subject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SubjectController implements Initializable {

    //<editor-fold desc="BoxStore">
    BoxStore boxStore = BoxStoreManager.getBoxStore();
    Box<Subject> subjectBox = BoxStoreManager.getBoxStore().boxFor(Subject.class);

    //</editor-fold>


    //<editor-fold desc="textFields">

    @FXML
    private TextField txtFieldSubject;

    @FXML
    private TextField txtFieldTeacher;

    //</editor-fold>


    //<editor-fold desc="tableView">


    @FXML
    private TableView<Subject> tableView = new TableView<>();

    @FXML
    private TableColumn<Subject, String> subjectColumn = new TableColumn<>();

    @FXML
    private TableColumn<Subject, String> teacherColumn = new TableColumn<>();

    //</editor-fold>

    //<editor-fold desc="Buttons">

    @FXML
    private Button backButton;

    @FXML
    private Button insertBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    //</editor-fold>

    //<editor-fold desc="OnActions">

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
    void onDeleteButtonClick() {
        List<Subject> subjectsSelected = tableView.getSelectionModel().getSelectedItems();
        for(Subject subject : subjectsSelected){
            subjectBox.remove(subject);
        }
        refreshTableData();
    }

    @FXML
    void onInsertButtonClick() {
        if (txtFieldSubject.getText().isBlank() || txtFieldTeacher.getText().isBlank()) {
            System.out.println("No se han llenado todos los campos");
        } else {
            Subject subject = new Subject(0, txtFieldSubject.getText(), txtFieldTeacher.getText());
            subjectBox.put(subject);
            refreshTableData();
        }
        cleanTextFields();

    }

    @FXML
    void onUpdateButtonClick() {
        refreshTableData();

    }

    @FXML
    private void refreshTableData() {
        tableView.getItems().clear();
        tableView.getItems().addAll(subjectBox.getAll());
    }

    @FXML
    private void cleanTextFields(){
        txtFieldSubject.setText("");
        txtFieldTeacher.setText("");
    }
    //</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Subject> subjectsCollection = FXCollections.observableArrayList(subjectBox.getAll());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        tableView.setItems(subjectsCollection);

        updateData();
        // solo para ver los datos alamacenados en la consola
        List<Subject> list = subjectBox.getAll();
        System.out.println("------------------------------------------------------");
        for (Subject subject : list) {
            System.out.println("Materia: " + subject.getName() + "\nAlumnos:\n");
            for(Student s : subject.students){
                System.out.println("- "+ s);
            }
            System.out.println("------------------------------------------------------");
        }

    }


    //<editor-fold desc="Methods">

    private void updateData() {
        subjectColumn.setCellFactory(TextFieldTableCell.<Subject>forTableColumn());
        subjectColumn.setOnEditCommit(event -> {
            Subject subject = event.getTableView().getItems().get(event.getTablePosition().getRow());
            subject.setName((event.getNewValue()));
            subjectBox.put(subject);
        });

        // ---

        teacherColumn.setCellFactory(TextFieldTableCell.<Subject>forTableColumn());
        teacherColumn.setOnEditCommit(event -> {
            Subject subject = event.getTableView().getItems().get(event.getTablePosition().getRow());
            subject.setTeacher((event.getNewValue()));
            subjectBox.put(subject);
        });

    }

    //</editor-fold>


}
