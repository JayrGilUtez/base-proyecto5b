package mx.edu.utez.baseproyecto5b.controller;


import io.objectbox.Box;
import io.objectbox.BoxStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.edu.utez.baseproyecto5b.database.BoxStoreManager;
import mx.edu.utez.baseproyecto5b.model.Student;
import mx.edu.utez.baseproyecto5b.model.Subject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssignController implements Initializable {

    //<editor-fold desc="BoxStore">
    BoxStore boxStore = BoxStoreManager.getBoxStore();
    Box<Student> studentBox = BoxStoreManager.getBoxStore().boxFor(Student.class);
    Box<Subject> subjectBox = BoxStoreManager.getBoxStore().boxFor(Subject.class);

    //</editor-fold>

    //<editor-fold desc="Search fields">
    @FXML
    private TextField studentSearchField;
    @FXML
    private TextField subjectSearchField;
    //</editor-fold>

    //<editor-fold desc="Buttons">
    @FXML
    private Button studentSearchBtn;
    @FXML
    private Button subjectSearchBtn;
    @FXML
    private Button studentsToSubjects;
    @FXML
    private Button subjectsToStudents;
    @FXML
    private Button backBtn;
    //</editor-fold>

    //<editor-fold desc="Students table">
    @FXML
    private TableView<Student> studentsTable = new TableView<>();
    @FXML
    private TableColumn<Student, String> nameColumn = new TableColumn<>();
    @FXML
    private TableColumn<Student, String> surnameColumn = new TableColumn<>();
    @FXML
    private TableColumn<Student, String> lastnameColumn = new TableColumn<>();
    @FXML
    private TableColumn<Student, Integer> ageColumn = new TableColumn<>();
    //</editor-fold>

    //<editor-fold desc="Subject table">
    @FXML
    private TableView<Subject> subjectTable = new TableView<>();
    @FXML
    private TableColumn<Subject, String> subjectColumn = new TableColumn<>();
    @FXML
    private TableColumn<Subject, String> teacherColumn = new TableColumn<>();

    //</editor-fold>

    @FXML
    public void onStudentsToSubjects() {
        List<Student> studentsSelected = studentsTable.getSelectionModel().getSelectedItems();
        List<Subject> subjectsSelected = subjectTable.getSelectionModel().getSelectedItems();

        for(Student student : studentsSelected){
            student.subjects.addAll(subjectsSelected);
            boxStore.boxFor(Student.class).put(student);
        }
    }

    @FXML
    public void onSubjectsToStudents() {
        List<Student> studentsSelected = studentsTable.getSelectionModel().getSelectedItems();
        List<Subject> subjectsSelected = subjectTable.getSelectionModel().getSelectedItems();
        for (Subject subject : subjectsSelected) {
            subject.students.addAll(studentsSelected);
            boxStore.boxFor(Subject.class).put(subject);

        }



    }

    //<editor-fold desc="Other methods">
    @FXML
    void onsearchButtonClicked() {

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
    //</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Obtenemos todos los estudiantes y los mostramos a travez de la ObservableList: studentsCollection
        ObservableList<Student> studentsCollection = FXCollections.observableArrayList(studentBox.getAll());
        studentsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        studentsTable.setItems(studentsCollection);

        // Obtenemos todas las materias y las mostramos a travez de la ObservableList: subjectsCollection
        ObservableList<Subject> subjectsCollection = FXCollections.observableArrayList(subjectBox.getAll());
        subjectTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        subjectTable.setItems(subjectsCollection);


        //<editor-fold desc="Dinamic search alumnos">

        // Filtered list de alumnos
        FilteredList<Student> studentsFilteredData = new FilteredList<>(studentsCollection, b -> true);
        // Lambda function para hacer la busqueda dinamica de alumnos
        studentSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            studentsFilteredData.setPredicate(student -> {
                // se compara si hay concidendias (si hay concidencias: return true;)
                if (newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String searchedText = newValue.toLowerCase();
                if (student.getName().toLowerCase().contains(searchedText)) {
                    return true;
                } else if (student.getSurname().toLowerCase().contains(searchedText)) {
                    return true;

                } else if (student.getLastname().toLowerCase().contains(searchedText)) {
                    return true;

                } else if (student.getAge().toLowerCase().contains(searchedText)) {
                    return true;

                } else {
                    return false;
                }

            });
        });

        // Ordenamos los resultados con una sortedList a partir de los datos filtrados
        SortedList<Student> studentsSortedList = new SortedList<>(studentsFilteredData);
        // Enlazamos la lista ordenada de resultados con la lista de la tabla
        studentsSortedList.comparatorProperty().bind(studentsTable.comparatorProperty());
        // Asignamos los datos filtrados y ordenados a la tabla
        studentsTable.setItems(studentsSortedList);

        //</editor-fold>



        //<editor-fold desc="Dinamic search materias">

        // Filtered list de mateias
        FilteredList<Subject> subjectsFilteredData = new FilteredList<>(subjectsCollection, b -> true);
        // Lambda function para hacer la busqueda dinamica de materias
        subjectSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            subjectsFilteredData.setPredicate(subject -> {
                // se compara si hay concidendias (si hay concidencias: return true;)
                if (newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String searchedText = newValue.toLowerCase();
                if (subject.getName().toLowerCase().contains(searchedText)) {
                    return true;
                } else if (subject.getTeacher().toLowerCase().contains(searchedText)) {
                    return true;

                } else {
                    return false;
                }
            });
        });

        // Ordenamos los resultados con una sortedList a partir de los datos filtrados
        SortedList<Subject> subjectsSortedList = new SortedList<>(subjectsFilteredData);
        // Enlazamos la lista ordenada de resultados con la lista de la tabla
        subjectsSortedList.comparatorProperty().bind(subjectTable.comparatorProperty());
        // Asignamos los datos filtrados y ordenados a la tabla
        subjectTable.setItems(subjectsSortedList);

        //</editor-fold>

    }


}
