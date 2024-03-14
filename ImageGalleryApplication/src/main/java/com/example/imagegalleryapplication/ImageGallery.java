package com.example.imagegalleryapplication;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageGallery extends Application {

    private Stage fullSizeStage;
    private ImageView fullSizeImageView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Gallery");

        // Creating a grid pane
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-padding: 10px;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Loading and adding thumbnails to the grid
        try {
            // Thumbnail 1
            Image thumbnail1 = new Image(new FileInputStream("src/main/resources/Images/heaven.jpg"));
            ImageView imageView1 = createImageView(thumbnail1, "src/main/resources/Images/heaven.jpg");
            gridPane.add(imageView1, 0, 0);

            // Thumbnail 2
            Image thumbnail2 = new Image(new FileInputStream("src/main/resources/Images/checklist.jpg"));
            ImageView imageView2 = createImageView(thumbnail2, "src/main/resources/Images/checklist.jpg");
            gridPane.add(imageView2, 1, 0);

            // Thumbnail 3
            Image thumbnail3 = new Image(new FileInputStream("src/main/resources/Images/latest hc.jpg"));
            ImageView imageView3 = createImageView(thumbnail3, "src/main/resources/Images/latest hc.jpg");
            gridPane.add(imageView3, 0, 1);

            // Thumbnail 4
            Image thumbnail4 = new Image(new FileInputStream("src/main/resources/Images/holy culture no2.jpg"));
            ImageView imageView4 = createImageView(thumbnail4, "src/main/resources/Images/holy culture no2.jpg");
            gridPane.add(imageView4, 1, 1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Setting up the scene
        Scene scene = new Scene(gridPane, 800, 600);
        scene.getStylesheets().add("file:src/main/resources/css/styles.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView createImageView(Image image, String fullSizeImagePath) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        imageView.setOnMouseClicked(event -> {
            // Show full-size image when thumbnail is clicked
            try {
                Image fullSizeImage = new Image(new FileInputStream(fullSizeImagePath));
                showFullSizeImage(fullSizeImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        return imageView;
    }

    private void showFullSizeImage(Image fullSizeImage) {
        fullSizeImageView = new ImageView(fullSizeImage);
        fullSizeImageView.setPreserveRatio(true);
        fullSizeImageView.setFitWidth(400); // Set maximum width for the full-size image

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(event -> {
            // Return to thumbnail view when "Clear" button is clicked
            fullSizeStage.close();
        });

        VBox vbox = new VBox(fullSizeImageView, clearButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        StackPane fullSizePane = new StackPane(vbox);
        fullSizePane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");

        Scene fullSizeScene = new Scene(fullSizePane);
        fullSizeStage = new Stage();
        fullSizeStage.setScene(fullSizeScene);
        fullSizeStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
