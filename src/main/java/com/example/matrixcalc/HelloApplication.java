package com.example.matrixcalc;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    int SCREEN_HEIGHT; // Screen.getPrimary().getBounds().getWidth();
    int SCREEN_WIDTH; //= Screen.getPrimary().getBounds().getHeight();

    final String STAGE_TITTLE = "MATRIX";
    final String[] OPERATIONS_LIST = new String[]{"FIND DETERMINANT", "SOLVE THE EQUATION", "GET INVERSE MATRIX", "MULTIPLY", "SUM", "SUBTRACT"};


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


            GridPane right_panel_main_gridPane = new GridPane();

            BorderPane right_panel_root_border_pane = new BorderPane();

            HBox hBox_main_in_new_screen = new HBox();

            int DEFAULT_INPUT_SIZE_ROWS = 3;
            int DEFAULT_INPUT_SIZE_COLUMNS = 3;
            GridPane input_matrix_gridPane_right_panel = new GridPane();
            int SINGLE_BUTTON_WIDTH = (SCREEN_WIDTH - right_panel_width) / DEFAULT_INPUT_SIZE_COLUMNS;
            int SINGLE_BUTTON_HEIGHT = (SCREEN_HEIGHT - 70) / DEFAULT_INPUT_SIZE_ROWS;
            generateInputMatrix(input_matrix_gridPane_right_panel, DEFAULT_INPUT_SIZE_COLUMNS, DEFAULT_INPUT_SIZE_ROWS, SCREEN_WIDTH-right_panel_width, SCREEN_HEIGHT-70, info_label);


            VBox right_panel_template = new VBox();



            Button calculate_button = new Button("CALCULATE!");
            calculate_button.setPrefSize(right_panel_width, 80);
            right_panel_template.getChildren().add(calculate_button);
            // Button paste_matrix_button = new Button("PASTE");
            // paste_matrix_button.setPrefSize(right_panel_width, 40);
            // right_panel_template.getChildren().add(paste_matrix_button);

            HBox matrix_size_panel_hbox = new HBox();

            Label input_size_text_label = new Label(" Input matrix \n   size:");
            input_size_text_label.setFont(new Font(15));
            input_size_text_label.setPrefSize(right_panel_width / 3, 40);
            matrix_size_panel_hbox.getChildren().add(input_size_text_label);

            TextField input_size_text_field = new TextField(Integer.toString(DEFAULT_INPUT_SIZE_ROWS));
            input_size_text_field.setPrefSize(right_panel_width / 3, 40);
            matrix_size_panel_hbox.getChildren().add(input_size_text_field);

            Button confirm_size_button = new Button("CONFIRM \n   SIZE");
            matrix_size_panel_hbox.getChildren().add(confirm_size_button);

            right_panel_template.getChildren().add(matrix_size_panel_hbox);




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
            hBox_main_in_new_screen.getChildren().add(right_panel_main_gridPane);


            Scene new_scene = new Scene(hBox_main_in_new_screen, SCREEN_WIDTH, SCREEN_HEIGHT);
            confirm_size_button.setPrefSize(right_panel_width / 3, 40);
            right_panel_main_gridPane.add(input_matrix_gridPane_right_panel, 0, 0);

            if (operation_name.equals("FIND DETERMINANT")) {
                confirm_size_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int INPUT_SIZE_COLUMNS = Integer.parseInt(input_size_text_field.getText());
                        int INPUT_SIZE_ROWS = INPUT_SIZE_COLUMNS;
                        generateInputMatrix(input_matrix_gridPane_right_panel, INPUT_SIZE_COLUMNS, INPUT_SIZE_ROWS, SCREEN_WIDTH - right_panel_width, SCREEN_HEIGHT -70, info_label);
                    }
                });

                calculate_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        Matrix matrix_from_right_grid = getMatrixFromGrid(input_matrix_gridPane_right_panel);
                        System.out.println(matrix_from_right_grid.toString());
                        System.out.println(Matrix.getDeterminant(matrix_from_right_grid));
                        info_label.setText("" + "   ОТВЕТ: det = " + Matrix.getDeterminant(matrix_from_right_grid));
                    }
                });


            }

            if (operation_name.equals("GET INVERSE MATRIX")) {




                confirm_size_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int INPUT_SIZE_COLUMNS = Integer.parseInt(input_size_text_field.getText());
                        int INPUT_SIZE_ROWS = INPUT_SIZE_COLUMNS;
                        generateInputMatrix(input_matrix_gridPane_right_panel, INPUT_SIZE_COLUMNS, INPUT_SIZE_ROWS, SCREEN_WIDTH - right_panel_width, SCREEN_HEIGHT - 70 , info_label);
                    }
                });

                GridPane result_of_inverse_gridPane = new GridPane();
                right_panel_template.getChildren().add(result_of_inverse_gridPane);
                calculate_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        result_of_inverse_gridPane.getChildren().clear();
                        Matrix matrix_from_right_grid = getMatrixFromGrid(input_matrix_gridPane_right_panel);
                        Matrix inversed_matrix = null;
                        boolean isInversedMatrixExist = true;
                        try {
                            inversed_matrix = Matrix.inverseMatrix(matrix_from_right_grid);
                        } catch (Exception e) {
                            //e.printStackTrace();
                            isInversedMatrixExist = false;
                            info_label.setText("   Состояние: не существует обратной матрицы");
                        }
                        if (isInversedMatrixExist) {
                            System.out.println(inversed_matrix.toString());
                            for (int i = 0; i < matrix_from_right_grid.getRowsQuantity(); i++) {
                                for (int j = 0; j < matrix_from_right_grid.getColumnsQuantity(); j++) {
                                    Label output_matrix_label = new Label(String.format("%.3f", inversed_matrix.getElementValue(i, j)));
                                    output_matrix_label.setPrefSize(right_panel_width / inversed_matrix.getColumnsQuantity(), 20);
                                    output_matrix_label.setFont(new Font(25));
                                    result_of_inverse_gridPane.add(output_matrix_label, j, i);

                                }
                            }
                            info_label.setText("   Состояние: ответ найден, расположен выше");

                        }
                    }
                });


            }
            if (operation_name.equals("MULTIPLY")) {
                TextField colomns_textField = new TextField("3");
                matrix_size_panel_hbox.getChildren().add(colomns_textField);
                input_size_text_field.setPrefSize(right_panel_width / 6, 40);
                colomns_textField.setPrefSize(right_panel_width/6,40);

                int currentMatrix_index = 1;
                Button change_matrix_button = new Button("current matrix is 1");

                change_matrix_button.setFont(new Font(20));
                change_matrix_button.setPrefSize(right_panel_width,40);
                right_panel_template.getChildren().add(change_matrix_button);
                Matrix matrix1 = null;
                Matrix matrix2 = null;
                GridPane matrix_2_gridPane = new GridPane();
                right_panel_main_gridPane.add(matrix_2_gridPane,1,1);
                generateInputMatrix(input_matrix_gridPane_right_panel,3,3,MANUAL_MATRIX_WIDTH, SCREEN_HEIGHT/2 -35, info_label);
                generateInputMatrix(matrix_2_gridPane,3,3,MANUAL_MATRIX_WIDTH, SCREEN_HEIGHT/2 -35,info_label);
                confirm_size_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int col = Integer.parseInt(colomns_textField.getText());
                        int row = Integer.parseInt(input_size_text_field.getText());
                        generateInputMatrix(input_matrix_gridPane_right_panel,row,col,MANUAL_MATRIX_WIDTH, MANUAL_MATRIX_HEIGHT,info_label);
                       // changeGridPaneElementsAmount;
                    }
                });



                change_matrix_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        if (change_matrix_button.getText().equals("current matrix is 1")){
                            change_matrix_button.setText("current matrix is 2");
                        }
                        else {
                            change_matrix_button.setText("current matrix is 1");
                        }

                    }
                });






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
        sp.setContent(vBox);
        stage.setTitle(STAGE_TITTLE);
        stage.setScene(scene);
        stage.show();
    }

    public void changeSizeGridPane(Pane gridPane_PARENT, int row_in_main_grid, int column_in_main_grid, int maxWidth, int maxHeight, int rows, int columns) {

        GridPane gridPane = new GridPane();
        for (Node node : gridPane_PARENT.getChildren()) {
            if ((GridPane.getRowIndex(node) == row_in_main_grid) && (GridPane.getColumnIndex(node) == column_in_main_grid)) {
                gridPane = (GridPane) node;// (GridPane) node;
            }

        }
        for (Node node : gridPane.getChildren()) {
            node.resize(maxWidth / columns, maxHeight / rows);
        }

    }

    public void generateInputMatrix(GridPane gridPane, int rows, int columns, int gridWidth, int gridHeight, Label info_label_copy) {
        gridPane.getChildren().clear();
        if ((rows > 30) | (columns > 30)) {
            info_label_copy.setText("   Состояние: матрица слишком большая,\n   не могу отобразить");
            return;
        }
        info_label_copy.setText("   Состояние:");
        int SINGLE_BUTTON_WIDTH = gridWidth / columns;
        int SINGLE_BUTTON_HEIGHT = gridHeight / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                TextField input_matrix_textField = new TextField();
                input_matrix_textField.setFont(new Font(100 / rows / columns + 15));
                input_matrix_textField.setPrefSize(SINGLE_BUTTON_WIDTH, SINGLE_BUTTON_HEIGHT);
                gridPane.add(input_matrix_textField, j, i);
            }
        }
    }

    public Matrix getMatrixFromGrid(GridPane gridPane) {
        Matrix matrix = new Matrix(gridPane.getColumnCount(), gridPane.getRowCount());
        int i = 0;
        int j = 0;
        //    System.out.println(gridPane.getColumnCount()+" "+ gridPane.getRowCount());
        for (Node textField : gridPane.getChildren()) {
            double num = 0.0;
            try {
                num = Double.parseDouble(((TextField) textField).getText());
            } catch (Exception e) {
                ((TextField)textField).setText("0");
            }
            // System.out.println(num + " " + j+" "+i);
            matrix.setElementValue(j, i, num);
            i++;
            if (i % gridPane.getColumnCount() == 0) {
                i = 0;
                j++;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        launch();
    }
}