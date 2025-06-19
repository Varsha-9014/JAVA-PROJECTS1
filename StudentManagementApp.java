package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

public class StudentManagementApp extends Application {

    Connection connection;

    TextField nameField, phoneField, emailField, courseField, semesterField;
    Label messageLabel;

    @Override
    public void start(Stage stage) {
        connectToDatabase();

        // UI Labels and TextFields
        Label nameLabel = new Label("Name:");
        nameField = new TextField();

        Label phoneLabel = new Label("Phone:");
        phoneField = new TextField();

        Label emailLabel = new Label("Email:");
        emailField = new TextField();

        Label courseLabel = new Label("Course:");
        courseField = new TextField();

        Label semesterLabel = new Label("Semester:");
        semesterField = new TextField();

        // Buttons
        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button searchButton = new Button("Search");
        Button clearButton = new Button("Clear");

        // Style buttons
        insertButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        updateButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
        searchButton.setStyle("-fx-background-color: #fd7e14; -fx-text-fill: white;");
        clearButton.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white;");

        messageLabel = new Label();

        // Button actions
        insertButton.setOnAction(e -> insertStudent());
        updateButton.setOnAction(e -> updateStudent());
        deleteButton.setOnAction(e -> deleteStudent());
        searchButton.setOnAction(e -> searchStudent());
        clearButton.setOnAction(e -> clearFields());

        // Grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-font-family: Arial; -fx-font-size: 14;");

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(phoneLabel, 0, 1);
        grid.add(phoneField, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(courseLabel, 0, 3);
        grid.add(courseField, 1, 3);
        grid.add(semesterLabel, 0, 4);
        grid.add(semesterField, 1, 4);

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10, 0, 10, 0));
        buttonBox.getChildren().addAll(insertButton, updateButton, deleteButton, searchButton, clearButton);
        grid.add(buttonBox, 0, 5, 2, 1);

        grid.add(messageLabel, 0, 6, 2, 1);

        // ✅ Set background image using correct file path (replace with your actual full path)
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:/C:/Users/J%20JEEVAN%20KUMAR/OneDrive/Pictures/Images/Organization-Student-Management-System.webp", 500, 400, false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );
        grid.setBackground(new Background(backgroundImage));

        // Scene and stage setup
        Scene scene = new Scene(grid, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Student Management System");
        stage.show();
    }

    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/stdmanagement?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "1234"; // Replace with your MySQL password
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to database.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to connect to database.");
        }
    }

    private void insertStudent() {
        try {
            String sql = "INSERT INTO stdmanagement (name, course, semester, email, phone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nameField.getText());
            stmt.setString(2, courseField.getText());
            stmt.setString(3, semesterField.getText());
            stmt.setString(4, emailField.getText());
            stmt.setString(5, phoneField.getText());

            int result = stmt.executeUpdate();
            messageLabel.setText(result > 0 ? "✅ Inserted successfully!" : "❌ Insert failed.");
        } catch (SQLException e) {
            e.printStackTrace();
            messageLabel.setText("❌ Error inserting data.");
        }
    }

    private void updateStudent() {
        try {
            String sql = "UPDATE stdmanagement SET name=?, course=?, semester=?, email=? WHERE phone=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nameField.getText());
            stmt.setString(2, courseField.getText());
            stmt.setString(3, semesterField.getText());
            stmt.setString(4, emailField.getText());
            stmt.setString(5, phoneField.getText());

            int result = stmt.executeUpdate();
            messageLabel.setText(result > 0 ? "✅ Updated successfully!" : "❌ Update failed.");
        } catch (SQLException e) {
            e.printStackTrace();
            messageLabel.setText("❌ Error updating data.");
        }
    }

    private void deleteStudent() {
        try {
            String sql = "DELETE FROM stdmanagement WHERE phone=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, phoneField.getText());

            int result = stmt.executeUpdate();
            messageLabel.setText(result > 0 ? "✅ Deleted successfully!" : "❌ Delete failed.");
        } catch (SQLException e) {
            e.printStackTrace();
            messageLabel.setText("❌ Error deleting data.");
        }
    }

    private void searchStudent() {
        try {
            String sql = "SELECT * FROM stdmanagement WHERE phone=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, phoneField.getText());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                courseField.setText(rs.getString("course"));
                semesterField.setText(rs.getString("semester"));
                emailField.setText(rs.getString("email"));
                messageLabel.setText("🔍 Student found.");
            } else {
                messageLabel.setText("❌ Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            messageLabel.setText("❌ Error searching student.");
        }
    }

    private void clearFields() {
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        courseField.clear();
        semesterField.clear();
        messageLabel.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
