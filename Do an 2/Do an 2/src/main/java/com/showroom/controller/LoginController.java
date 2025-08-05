package com.showroom.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller cho màn hình đăng nhập
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label messageLabel;

    /**
     * Xử lý sự kiện đăng nhập
     */
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Vui lòng nhập đầy đủ thông tin đăng nhập!");
            return;
        }

        // TODO: Implement authentication logic
        if (authenticateUser(username, password)) {
            showSuccess("Đăng nhập thành công!");
            // TODO: Navigate to dashboard
        } else {
            showMessage("Tên đăng nhập hoặc mật khẩu không đúng!");
        }
    }

    /**
     * Xác thực người dùng
     */
    private boolean authenticateUser(String username, String password) {
        // TODO: Implement real authentication
        // For now, use hardcoded credentials
        return ("leader".equals(username) && "password".equals(password)) ||
               ("account".equals(username) && "password".equals(password)) ||
               ("sale1".equals(username) && "password".equals(password)) ||
               ("sale2".equals(username) && "password".equals(password));
    }

    /**
     * Hiển thị thông báo lỗi
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: #e74c3c;");
    }

    /**
     * Hiển thị thông báo thành công
     */
    private void showSuccess(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: #27ae60;");
    }

    /**
     * Hiển thị dialog thông báo
     */
    private void showAlert(String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Xử lý sự kiện khi nhấn Enter
     */
    @FXML
    private void handleEnterPressed() {
        handleLogin();
    }
} 