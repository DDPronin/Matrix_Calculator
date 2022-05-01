package com.example.matrixcalc;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    int SCREEN_HEIGHT; // Screen.getPrimary().getBounds().getWidth();
    int SCREEN_WIDTH; //= Screen.getPrimary().getBounds().getHeight();

    final String STAGE_TITTLE = "MATRIX";
    final String[] OPERATIONS_LIST = new String[]{"FIND DETERMINANT", "SOLVE THE EQUATION"};

    public HelloApplication() {
    }

    @Override
    public void start(Stage stage) throws IOException {
        SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
        SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
        stage.setMaximized(true);

//        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());


        ScrollPane sp = new ScrollPane();
        VBox vBox = new VBox();

        Scene scene = new Scene(sp, SCREEN_WIDTH, SCREEN_HEIGHT);
//
//        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());

        for (String operation_name: OPERATIONS_LIST) {
            VBox right_panel_template = new VBox();
            Button paste_matrix = new Button("PASTE");



           // right_panel_template.getChildren().add();
            if (operation_name.equals("FIND DETERMINANT")){}
            if (operation_name.equals("SOLVE THE EQUATION")){}

        }
         /*
            Button back_to_main_menu_button = new Button("BACK");
            back_to_main_menu_button.setPrefSize(SCREEN_WIDTH,90);
            back_to_main_menu_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    stage.setTitle(STAGE_TITTLE);
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
                    stage.setTitle(operation_name);
                    stage.setScene(new_scene);
                }
            });
            vBox.getChildren().add(main_screen_button);
        }*/
        sp.setContent(vBox);
        stage.setTitle(STAGE_TITTLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}