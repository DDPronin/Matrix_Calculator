package com.example.matrixcalc;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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

//        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());

        for (String operation_name : OPERATIONS_LIST) {

            int right_panel_width = SCREEN_WIDTH / 3;
            int MANUAL_MATRIX_WIDTH = right_panel_width;
            int MANUAL_MATRIX_HEIGHT = 100;

            Label info_label = new Label();
            info_label.setFont(new Font(15));
            info_label.setPrefSize(right_panel_width, 50);


            BorderPane right_panel_root_border_pane = new BorderPane();

            HBox hBox_main_in_new_screen = new HBox();

            int DEFAULT_INPUT_SIZE_ROWS = 3;
            int DEFAULT_INPUT_SIZE_COLUMNS = 3;
            GridPane input_matrix_gridPane_left_panel = new GridPane();
            int SINGLE_BUTTON_WIDTH = (SCREEN_WIDTH - right_panel_width) / DEFAULT_INPUT_SIZE_COLUMNS;
            int SINGLE_BUTTON_HEIGHT = (SCREEN_HEIGHT - 70) / DEFAULT_INPUT_SIZE_ROWS;
            generateInputMatrix(input_matrix_gridPane_left_panel, DEFAULT_INPUT_SIZE_COLUMNS, DEFAULT_INPUT_SIZE_ROWS, right_panel_width, info_label);


            VBox right_panel_template = new VBox();
            Button paste_matrix_button = new Button("PASTE");


            paste_matrix_button.setPrefSize(right_panel_width, 40);
            right_panel_template.getChildren().add(paste_matrix_button);

            HBox matrix_size_panel_hbox = new HBox();

            Label input_size_text_label = new Label(" Input matrix \n   size:");
            input_size_text_label.setFont(new Font(15));
            input_size_text_label.setPrefSize(right_panel_width / 3, 40);
            matrix_size_panel_hbox.getChildren().add(input_size_text_label);

            TextField input_size_text_field = new TextField(Integer.toString(DEFAULT_INPUT_SIZE_ROWS));
            input_size_text_field.setPrefSize(right_panel_width / 3, 40);
            matrix_size_panel_hbox.getChildren().add(input_size_text_field);

            Button confirm_size_button = new Button("CONFIRM \n   SIZE");
            confirm_size_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int INPUT_SIZE_COLUMNS = Integer.parseInt(input_size_text_field.getText());
                    int INPUT_SIZE_ROWS = INPUT_SIZE_COLUMNS;
                    generateInputMatrix(input_matrix_gridPane_left_panel, INPUT_SIZE_COLUMNS, INPUT_SIZE_ROWS, right_panel_width, info_label);
                }
            });
            confirm_size_button.setPrefSize(right_panel_width / 3, 40);
            matrix_size_panel_hbox.getChildren().add(confirm_size_button);

            HBox matrix_manual_input_hbox = new HBox();
            GridPane index_and_num_info_gridPane = new GridPane();//VBox index_info_vbox = new VBox();
            HBox matrix_manual_input_confirmation_hbox = new HBox();

            Label index_text_label = new Label("    Index");
            index_text_label.setPrefSize(MANUAL_MATRIX_WIDTH / 3, MANUAL_MATRIX_HEIGHT / 2);
            Label index_info_label = new Label("0 0");
            index_info_label.setFont(new Font(20));
            index_info_label.setPrefSize(MANUAL_MATRIX_WIDTH / 3, MANUAL_MATRIX_HEIGHT / 2);
            index_and_num_info_gridPane.add(index_text_label, 0, 0);//matrix_manual_input_hbox.getChildren().add(index_info_label);
            index_and_num_info_gridPane.add(index_info_label, 1, 0);//matrix_manual_input_hbox.getChildren().add(index_text_label);

            Label num_text_label = new Label("Matrix Element(num):");
            num_text_label.setPrefSize(MANUAL_MATRIX_WIDTH / 3, MANUAL_MATRIX_HEIGHT / 2);
            TextField element_matrix_input_textField = new TextField("");
            element_matrix_input_textField.setPrefSize(MANUAL_MATRIX_WIDTH / 3, MANUAL_MATRIX_HEIGHT / 2);
            index_and_num_info_gridPane.add(num_text_label, 0, 1);//index_info_vbox.getChildren().add(num_text_label);
            index_and_num_info_gridPane.add(element_matrix_input_textField, 1, 1);//index_info_vbox.getChildren().add(element_matrix_input_textField);

            Button next_element_matrix_input_button = new Button("CONFIRM\n (NEXT EL)");
            next_element_matrix_input_button.setPrefSize(MANUAL_MATRIX_WIDTH / 6, MANUAL_MATRIX_HEIGHT);
            Button previous_element_matrix_input_button = new Button("PREVIOUS");
            previous_element_matrix_input_button.setPrefSize(MANUAL_MATRIX_WIDTH / 6, MANUAL_MATRIX_HEIGHT);
            matrix_manual_input_confirmation_hbox.getChildren().add(next_element_matrix_input_button);
            matrix_manual_input_confirmation_hbox.getChildren().add(previous_element_matrix_input_button);//
            index_and_num_info_gridPane.snapSpaceX(50);
            matrix_manual_input_hbox.getChildren().add(index_and_num_info_gridPane);
            right_panel_template.getChildren().add(matrix_manual_input_hbox);
            matrix_manual_input_hbox.getChildren().add(matrix_manual_input_confirmation_hbox);

            right_panel_template.getChildren().add(matrix_size_panel_hbox);

            Button calculate_button = new Button("CALCULATE!");
            calculate_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Matrix matrix_from_right_grid = getMatrixFromGrid(input_matrix_gridPane_left_panel);
                    info_label.setText("   Состояние: " +"det = " + Matrix.getDeterminant(matrix_from_right_grid));
                }
            });
            calculate_button.setPrefSize(right_panel_width, 80);
            right_panel_template.getChildren().add(calculate_button);


            Button back_to_main_menu_button = new Button("BACK\n ");
            back_to_main_menu_button.setFont(new Font(25));
            back_to_main_menu_button.setPrefSize(right_panel_width, 200);
            back_to_main_menu_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    stage.setTitle(STAGE_TITTLE);
                    stage.setScene(scene);

                }
            });
            right_panel_template.getChildren().add(back_to_main_menu_button);

            right_panel_template.setSpacing(10);


            right_panel_root_border_pane.setTop(right_panel_template);
            right_panel_root_border_pane.setCenter(info_label);
            right_panel_root_border_pane.setBottom(back_to_main_menu_button);


            hBox_main_in_new_screen.getChildren().add(right_panel_root_border_pane);//hBox_main_in_new_screen.getChildren().add(right_panel_template);
            hBox_main_in_new_screen.getChildren().add(input_matrix_gridPane_left_panel);


            Scene new_scene = new Scene(hBox_main_in_new_screen, SCREEN_WIDTH, SCREEN_HEIGHT);


            // right_panel_template.getChildren().add();
            if (operation_name.equals("FIND DETERMINANT")) {

            }
            if (operation_name.equals("SOLVE THE EQUATION")) {

            }


            Button main_screen_button = new Button(operation_name);
            main_screen_button.setPrefSize(SCREEN_WIDTH - 20, 80);

            main_screen_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    stage.setTitle(operation_name);
                    stage.setScene(new_scene);
                }
            });
            vBox.getChildren().add(main_screen_button);


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

    public void generateInputMatrix(GridPane gridPane, int rows, int columns, int right_panel_width, Label info_label_copy) {
        gridPane.getChildren().clear();
        if ((rows > 10) | (columns > 10)) {
            info_label_copy.setText("   Состояние: матрица слишком большая,\n   придется ввести элементы вручную");
            return;
        }
        info_label_copy.setText("   Состояние:");
        int SINGLE_BUTTON_WIDTH = (SCREEN_WIDTH - right_panel_width) / columns;
        int SINGLE_BUTTON_HEIGHT = (SCREEN_HEIGHT - 70) / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                TextField input_matrix_textField = new TextField();
                input_matrix_textField.setFont(new Font(100/rows/columns +15));
                input_matrix_textField.setPrefSize(SINGLE_BUTTON_WIDTH, SINGLE_BUTTON_HEIGHT);
                gridPane.add(input_matrix_textField, j, i);
            }
        }
    }

    public Matrix getMatrixFromGrid(GridPane gridPane) {
        Matrix matrix = new Matrix(gridPane.getColumnCount(), gridPane.getRowCount());
        int i = 0;
        int j = 0;
        System.out.println(gridPane.getColumnCount()+" "+ gridPane.getRowCount());
        for (Node textField : gridPane.getChildren()) {
            int num = Integer.parseInt(((TextField) textField).getText());
            System.out.println(num + " " + j+" "+i);
            matrix.setElementValue(j,i, num);
            i++;
            if (i%gridPane.getColumnCount() ==0){i=0;j++;}
        }
        //System.out.println(matrix.toString());
        return matrix;
    }

    public static void main(String[] args) {
        launch();
    }
}