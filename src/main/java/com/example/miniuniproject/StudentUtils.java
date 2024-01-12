package com.example.miniuniproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentUtils {
    private static StudentUtils student;
    public static Statement statement = DBConnection.getStatement();

    private StudentUtils() {
    }

    public static StudentUtils getInstance() {
        if (student == null) {
            student = new StudentUtils();
        }
        return student;
    }

    public void addStudent(Student student) throws SQLException {
        String sql = String.format("INSERT INTO STUDENT (ID, FIRST_NAME, LAST_NAME, GENDER, FACULTY) VALUES('%d', '%s', '%s', '%s', '%s')",
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGender(),
                student.getFaculty());
        statement.executeUpdate(sql);
    }

    public Optional<Student> getStudentById(Long id) throws SQLException {
        String sql = "SELECT * FROM STUDENT WHERE ID = " + id;
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            Long studentId = resultSet.getLong("ID");
            String firstName = resultSet.getString("FIRST_NAME");
            String lastName = resultSet.getString("LAST_NAME");
            String gender = resultSet.getString("GENDER");
            String faculty = resultSet.getString("FACULTY");
            return Optional.of(new Student(studentId, firstName, lastName, gender, faculty));
        }
        return Optional.empty();
    }

    public void deleteStudentById(Long id) throws SQLException {
        String sql = "DELETE FROM STUDENT WHERE ID = " + id;
        statement.executeUpdate(sql);
    }

    public void updateStudentById(Long id, Student student) throws SQLException {
        String sql = String.format(
                "UPDATE STUDENT SET ID = '%d', FIRST_NAME = '%s', LAST_NAME = '%s', GENDER = '%s', FACULTY = '%s' WHERE ID = '%s'",
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGender(),
                student.getFaculty(),
                id);
        statement.executeUpdate(sql);
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String firstName = resultSet.getString("FIRST_NAME");
            String lastName = resultSet.getString("LAST_NAME");
            String gender = resultSet.getString("GENDER");
            String faculty = resultSet.getString("FACULTY");

            Student student = new Student(id, firstName, lastName, gender, faculty);
            students.add(student);
        }

        return students;
    }
}
