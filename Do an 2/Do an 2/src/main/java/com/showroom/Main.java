package com.showroom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class cho ứng dụng Car Showroom Management System
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML file
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        
        // Set up the scene
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        
        // Configure the stage
        primaryStage.setTitle("Car Showroom Management System");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Khởi động hệ thống quản lý showroom ô tô...");
        launch(args);
    }
} 