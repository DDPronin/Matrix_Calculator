package com.example.matrixcalc;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    final int SCREEN_HEIGHT = 650;
    final int SCREEN_WIDTH = 540;

    final String[] OPERATIONS_LIST = new String[]{"FIND DETERMINANT", "SOLVE THE EQUATION"};
    @Override
    public void start(Stage stage) throws IOException {


        ScrollPane sp = new ScrollPane();
        VBox vBox = new VBox();

        Scene scene = new Scene(sp, SCREEN_WIDTH, SCREEN_HEIGHT);

        for (String operation_name: OPERATIONS_LIST) {
            Button back_to_main_menu_button = new Button("BACK");
            back_to_main_menu_button.setPrefSize(SCREEN_WIDTH,40);
            back_to_main_menu_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    stage.setScene(scene);
                }
            });

            BorderPane borderPane = new BorderPane();
            Scene new_scene = new Scene(borderPane,SCREEN_WIDTH,SCREEN_HEIGHT);
            borderPane.setBottom(back_to_main_menu_button);

            Button main_screen_button = new Button(operation_name);
            main_screen_button.setPrefSize(SCREEN_WIDTH-20,40);
            main_screen_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    stage.setScene(new_scene);
                }
            });
            vBox.getChildren().add(main_screen_button);
        }
        sp.setContent(vBox);

        stage.setTitle("Matrix");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}