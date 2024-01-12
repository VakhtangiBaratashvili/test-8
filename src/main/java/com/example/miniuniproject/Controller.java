package com.example.miniuniproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private final StudentUtils studentUtils = StudentUtils.getInstance();

    @FXML
    private Button addBtn = new Button();

    @FXML
    private Button deleteBtn = new Button();

    @FXML
    private TableView<Student> table = new TableView<>();

    @FXML
    private TableColumn<Student, String> facultyColumn = new TableColumn<>();

    @FXML
    private TableColumn<Student, String> firstNameColumn = new TableColumn<>();

    @FXML
    private TableColumn<Student, String> genderColumn = new TableColumn<>();

    @FXML
    private TableColumn<Student, Long> idColumn = new TableColumn<>();

    @FXML
    private TableColumn<Student, String> lastNameColumn = new TableColumn<>();

    @FXML
    private Button searchById = new Button();

    @FXML
    private TextField searchField;

    @FXML
    private Button updateBtn = new Button();


    @FXML
    void addStudent(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-student.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Add Student");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            updateTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteStudent(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Delete");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            updateTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchStudent(ActionEvent event) {
        try {
            if (!searchField.getText().isBlank() && searchField.getText() != null) {
                Long id = Long.parseLong(searchField.getText());
                Student student = studentUtils.getStudentById(id).orElseThrow();
                ObservableList<Student> students = FXCollections.observableArrayList();
                students.add(student);
                table.setItems(students);
            }
            else {
                updateTableView();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateStudent(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("update.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Delete");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            updateTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button addStudentBtn = new Button();

    @FXML
    private ChoiceBox<Faculty> facultyChoiceBox = new ChoiceBox<>();


    @FXML
    private RadioButton femaleConfirmBtn;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField lastNameField;

    @FXML
    private RadioButton maleConfirmBtn;

    @FXML
    void add(ActionEvent event) {
        try {
            Long id = Long.parseLong(idField.getText());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String gender = maleConfirmBtn.isSelected() ? "Male" : "Female";
            String faculty = facultyChoiceBox.getValue().toString();
            Student newStudent = new Student(id, firstName, lastName, gender, faculty);
            updateTableView();
            studentUtils.addStudent(newStudent);
            Stage stage = (Stage) addStudentBtn.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void femaleConfirm(ActionEvent event) {
        maleConfirmBtn.setSelected(false);
    }

    @FXML
    void maleConfirm(ActionEvent event) {
        femaleConfirmBtn.setSelected(false);
    }

    @FXML
    private Button confirmBtn = new Button();

    @FXML
    private TextField studentIdField;

    @FXML
    void confirm(ActionEvent event) {
        try {
            Long id = Long.parseLong(studentIdField.getText());
            studentUtils.deleteStudentById(id);
            Stage stage = (Stage) confirmBtn.getScene().getWindow();
            updateTableView();
            stage.close();
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private ChoiceBox<Faculty> newFacultyChoiceBox = new ChoiceBox<>();

    @FXML
    private RadioButton newFemaleConfirm;

    @FXML
    private TextField newFirstNameField;

    @FXML
    private TextField newIdField;

    @FXML
    private TextField newLastNameField;

    @FXML
    private RadioButton newMaleConfirm;

    @FXML
    private TextField oldIdField;

    @FXML
    private Button updateStudentBtn;

    @FXML
    void confirmNewFemale(ActionEvent event) {
        newMaleConfirm.setSelected(false);
    }

    @FXML
    void confirmNewMale(ActionEvent event) {
        newFemaleConfirm.setSelected(false);
    }

    @FXML
    void update(ActionEvent event) {
        try {
            Long oldId = Long.parseLong(oldIdField.getText());
            Long newId = Long.parseLong(newIdField.getText());
            String firstName = newFirstNameField.getText();
            String lastName = newLastNameField.getText();
            String gender = newMaleConfirm.isSelected() ? "Male" : "Female";
            String faculty = newFacultyChoiceBox.getValue().toString();
            Student newStudent = new Student(newId, firstName, lastName, gender, faculty);
            studentUtils.updateStudentById(oldId, newStudent);
            Stage stage = (Stage) updateStudentBtn.getScene().getWindow();
            stage.close();
            updateTableView();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateTableView() {
        try {
            ObservableList<Student> students = FXCollections.observableArrayList();
            List<Student> studentList = studentUtils.getAllStudents();

            students.addAll(studentList);
            table.setItems(students);
            initialize();
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        facultyChoiceBox.setItems(FXCollections.observableArrayList(Faculty.values()));
        newFacultyChoiceBox.setItems(FXCollections.observableArrayList(Faculty.values()));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
    }
}
