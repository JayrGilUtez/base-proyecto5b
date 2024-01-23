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

public class StudentController implements Initializable {

    //<editor-fold desc="BoxStore">
    Box<Student> studentBox = BoxStoreManager.getBoxStore().boxFor(Student.class);
    //</editor-fold>

    //<editor-fold desc="Buttons">
    @FXML
    private Button backButton;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonInsert;

    @FXML
    private Button buttonUpdate;
    //</editor-fold>

    //<editor-fold desc="TextFields">
    @FXML
    private TextField txtFieldName;

    @FXML
    private TextField txtFieldSurname;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldAge;
    //</editor-fold>

    //<editor-fold desc="TableView">
    @FXML
    private TableView<Student> tableView = new TableView<>();

    @FXML
    private TableColumn<Student, String> nameColumn = new TableColumn<>();

    @FXML
    private TableColumn<Student, String> surnameColumn = new TableColumn<>();

    @FXML
    private TableColumn<Student, String> lastnameColumn = new TableColumn<>();

    @FXML
    private TableColumn<Student, String> ageColumn = new TableColumn<>();


    //</editor-fold>

    //<editor-fold desc="onClick methods">
    @FXML
    public void onClickBackButton() {
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
    public void onDeleteButtonClick() {
        List<Student> studentsSelected = tableView.getSelectionModel().getSelectedItems();
        for (Student student : studentsSelected) {
            studentBox.remove(student);
        }
        refreshTableData();
    }
    @FXML
    public void onUpdateButtonClick() {
        refreshTableData();

    }
    @FXML
    public void onInsertButtonClick() {
        if (
                txtFieldName.getText().isBlank()
                        || txtFieldSurname.getText().isBlank()
                        || txtFieldLastName.getText().isBlank()
                        || txtFieldAge.getText().isBlank()
        ) {
            // uno o mas campos estan vacios
            System.out.println("No se han llenado todos los campos");
        } else {
            Student student = new Student(
                    0, txtFieldName.getText(),
                    txtFieldSurname.getText(),
                    txtFieldLastName.getText(),
                    txtFieldAge.getText()
            );

            studentBox.put(student);
            refreshTableData();
            cleanTextFields();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Refresh table and clean fields">

    @FXML
    private void refreshTableData() {
        tableView.getItems().clear();
        tableView.getItems().addAll(studentBox.getAll());
    }
    @FXML
    private void cleanTextFields() {
        txtFieldName.setText("");
        txtFieldSurname.setText("");
        txtFieldLastName.setText("");
        txtFieldAge.setText("");

    }
    //</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Student> studentsCollection = FXCollections.observableArrayList(studentBox.getAll());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        // Agregamos los datos a la tabla
        tableView.setItems(studentsCollection);

        // Para actualizar los datos que hayan sido editados por medio de las celdas.
        updateData();

        //Con esto podemos ver por medio de la consola los datos
        //alamacenados en la base de datos y verificar que las relaciones esten funcionando correctamente

        List<Student> list = studentBox.getAll();
        System.out.println("------------------------------------------------------");
        for (Student student : list) {
            System.out.println("Alumno: " + student.getName() + "\nMaterias:");
            for (Subject s : student.subjects) {
                System.out.println(s);
            }
            System.out.println("------------------------------------------------------");
        }
    }

    //<editor-fold desc="Edit and updateData">

    // Este metodo es para poder editar y actualizar los datos por medio de las celdas
    private void updateData() {
        // to edit name cell
        nameColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            Student student = event.getTableView().getItems().get(event.getTablePosition().getRow());
            student.setName(event.getNewValue());
            studentBox.put(student);
        });
        // to edit surname cell
        surnameColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        surnameColumn.setOnEditCommit(event -> {
            Student student = event.getTableView().getItems().get(event.getTablePosition().getRow());
            student.setSurname(event.getNewValue());
            studentBox.put(student);
        });
        // to edit lastname cell
        lastnameColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        lastnameColumn.setOnEditCommit(event -> {
            Student student = event.getTableView().getItems().get(event.getTablePosition().getRow());
            student.setLastname(event.getNewValue());
            studentBox.put(student);
        });
        // to edit age cell
        ageColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        ageColumn.setOnEditCommit(event -> {
            Student student = event.getTableView().getItems().get(event.getTablePosition().getRow());
            student.setAge(event.getNewValue());
            studentBox.put(student);
        });
    }
    //</editor-fold>

}
