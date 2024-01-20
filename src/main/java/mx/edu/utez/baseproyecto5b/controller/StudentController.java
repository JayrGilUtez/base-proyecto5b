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
    BoxStore boxStore = BoxStoreManager.getBoxStore();
    Box<Student> studentBox = BoxStoreManager.getBoxStore().boxFor(Student.class);

    //<editor-fold desc="Elementos @FXML">
    @FXML
    private Button backButton;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonInsert;

    @FXML
    private Button buttonUpdate;

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


    //<editor-fold desc="Metodos para los botones">


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
        List<Student> studentsSelected =  tableView.getSelectionModel().getSelectedItems();
        for(Student student: studentsSelected){
            studentBox.remove(student);
        }
        refreshTableData();
    }

    @FXML
    public void onUpdateButtonClick() {
        refreshTableData();

    }

    //</editor-fold>
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

    @FXML
    private void refreshTableData() {
        tableView.getItems().clear();
        tableView.getItems().addAll(studentBox.getAll());
    }

    @FXML
    private void cleanTextFields(){
        txtFieldName.setText("");
        txtFieldSurname.setText("");
        txtFieldLastName.setText("");
        txtFieldAge.setText("");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Student> studentsCollection = FXCollections.observableArrayList(studentBox.getAll());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        tableView.setItems(studentsCollection);

        updateData();

        // solo para ver los datos alamacenados en la consola
        List<Student> list = tableView.getItems();
        System.out.println("------------------------------------------------------");
        for (Student student : list){
            //System.out.println(student);
            System.out.println(student.getName() + " subjects:{\n");
            for(Subject s : student.subjects){
                System.out.println(s);
            }
            System.out.println("\n}\n");
        }

    }

    private void updateData(){
        nameColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        nameColumn.setOnEditCommit(event ->{
            Student student = event.getTableView().getItems().get(event.getTablePosition().getRow());
            student.setName(event.getNewValue());
            studentBox.put(student);
        });
        // ---
        surnameColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        surnameColumn.setOnEditCommit(event ->{
            Student student = event.getTableView().getItems().get(event.getTablePosition().getRow());
            student.setSurname(event.getNewValue());
            studentBox.put(student);
        });
        // ---
        lastnameColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        lastnameColumn.setOnEditCommit(event ->{
            Student student = event.getTableView().getItems().get(event.getTablePosition().getRow());
            student.setLastname(event.getNewValue());
            studentBox.put(student);
        });
        // ---
        ageColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        ageColumn.setOnEditCommit(event ->{
            Student student = event.getTableView().getItems().get(event.getTablePosition().getRow());
            student.setAge(event.getNewValue());
            studentBox.put(student);
        });
        // ---



    }

}
