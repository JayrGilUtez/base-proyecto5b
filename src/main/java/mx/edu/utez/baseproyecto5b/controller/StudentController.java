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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.edu.utez.baseproyecto5b.database.BoxStoreManager;
import mx.edu.utez.baseproyecto5b.model.Student;

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
    void onDeleteButtonClick() {
        studentBox.removeAll();

    }

    @FXML
    void onUpdateButtonClick() {

    }

    //</editor-fold>
    @FXML
    void onInsertButtonClick() {
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

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Student> studentsCollection = FXCollections.observableArrayList(studentBox.getAll());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        tableView.setItems(studentsCollection);

        List<Student> allStudents = studentBox.getAll();
        System.out.println("Lista de todos los usuarios:");
        for (Student s : allStudents) {
            System.out.println(s);
        }

    }

}
