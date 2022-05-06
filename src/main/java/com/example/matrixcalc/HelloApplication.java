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
            int MANUAL_MATRIX_WIDTH = SCREEN_WIDTH - right_panel_width;
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
            generateInputMatrix(input_matrix_gridPane_right_panel, DEFAULT_INPUT_SIZE_COLUMNS, DEFAULT_INPUT_SIZE_ROWS, SCREEN_WIDTH - right_panel_width, SCREEN_HEIGHT - 70, info_label);


            VBox right_panel_template = new VBox();


            Button calculate_button = new Button("CALCULATE!");
            calculate_button.setPrefSize(right_panel_width, 80);
            right_panel_template.getChildren().add(calculate_button);

            HBox matrix_size_panel_hbox = new HBox();

            Label input_size_text_label = new Label(" Input matrix \n   size:");
            input_size_text_label.setFont(new Font(15));
            input_size_text_label.setPrefSize(right_panel_width / 3, 40);
            matrix_size_panel_hbox.getChildren().add(input_size_text_label);

            TextField input_size_text_field = new TextField(Integer.toString(DEFAULT_INPUT_SIZE_ROWS));
            input_size_text_field.setPrefSize(right_panel_width / 3, 40);
            Button confirm_size_button = new Button("CONFIRM \n   SIZE");
            matrix_size_panel_hbox.getChildren().add(confirm_size_button);
            matrix_size_panel_hbox.getChildren().add(input_size_text_field);

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

            GridPane result_of_operation = new GridPane();
            right_panel_template.getChildren().add(result_of_operation);
            if (operation_name.equals("FIND DETERMINANT")) {
                confirm_size_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        //int INPUT_SIZE_COLUMNS = 1;
                        try {
                            int INPUT_SIZE_COLUMNS = Integer.parseInt(input_size_text_field.getText());

                            if (INPUT_SIZE_COLUMNS < 1) {
                                INPUT_SIZE_COLUMNS = 1;
                                input_size_text_field.setText("1");
                            }
                            int INPUT_SIZE_ROWS = INPUT_SIZE_COLUMNS;
                            generateInputMatrix(input_matrix_gridPane_right_panel, INPUT_SIZE_COLUMNS, INPUT_SIZE_ROWS, SCREEN_WIDTH - right_panel_width, SCREEN_HEIGHT - 70, info_label);
                        } catch (Exception e) {

                        }
                    }
                });

                calculate_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        Matrix matrix_from_right_grid = getMatrixFromGrid(input_matrix_gridPane_right_panel);
                        // System.out.println(matrix_from_right_grid.toString());
                        //System.out.println(Matrix.getDeterminant(matrix_from_right_grid));
                        info_label.setText("" + "   Answer: det = " + Matrix.getDeterminant(matrix_from_right_grid));
                    }
                });


            }
            if (operation_name.equals("SOLVE THE EQUATION")) {


                //input_matrix_gridPane_right_panel.getChildren().clear();
                GridPane x_matrix_gridPane = new GridPane();
                GridPane B_matrix_gridPane = new GridPane();


                right_panel_main_gridPane.add(x_matrix_gridPane, 1, 0);
                right_panel_main_gridPane.add(B_matrix_gridPane, 2, 0);

                Label text_A_matrix = new Label("A");
                text_A_matrix.setFont(new Font(40));
                Label text_B_matrix = new Label("B");
                text_B_matrix.setFont(new Font(40));
                Label text_X_matrix = new Label("*  X = ");
                text_X_matrix.setFont(new Font(40));

                BorderPane text_A_matrix_borderPane = new BorderPane();
                BorderPane text_B_matrix_borderPane = new BorderPane();
                BorderPane text_X_matrix_borderPane = new BorderPane();

                text_A_matrix_borderPane.setCenter(text_A_matrix);
                text_B_matrix_borderPane.setCenter(text_B_matrix);
                text_X_matrix_borderPane.setCenter(text_X_matrix);

                right_panel_main_gridPane.add(text_A_matrix_borderPane, 0, 1);
                right_panel_main_gridPane.add(text_B_matrix_borderPane, 2, 1);
                right_panel_main_gridPane.add(text_X_matrix_borderPane, 1, 1);

                right_panel_main_gridPane.setHgap(20);
                right_panel_main_gridPane.setVgap(20);

                generateInputMatrix(input_matrix_gridPane_right_panel, 3, 3, MANUAL_MATRIX_WIDTH / 3, SCREEN_HEIGHT / 2, info_label);
                generateInputMatrix(B_matrix_gridPane, 3, 1, MANUAL_MATRIX_WIDTH / 3, SCREEN_HEIGHT / 2, info_label);
                for (int i = 0; i < 3; i++) {
                    Label x_label = new Label("x" + (i + 1));
                    x_label.setFont(new Font(20));
                    x_label.setPrefSize(MANUAL_MATRIX_WIDTH / 20, SCREEN_HEIGHT / 2 / 3);
                    x_matrix_gridPane.add(x_label, 0, i);
                }
                //Matrix x_matrix = new Matrix(3,1);
                confirm_size_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int rows = Integer.parseInt(input_size_text_field.getText());

                        generateInputMatrix(input_matrix_gridPane_right_panel, rows, rows, MANUAL_MATRIX_WIDTH / 3, SCREEN_HEIGHT / 2, info_label);
                        generateInputMatrix(B_matrix_gridPane, rows, 1, MANUAL_MATRIX_WIDTH / 3, SCREEN_HEIGHT / 2, info_label);
                        if (rows < 8) {
                            x_matrix_gridPane.getChildren().clear();
                            for (int i = 0; i < rows; i++) {
                                Label x_label = new Label("x" + (i + 1));
                                x_label.setFont(new Font(20));
                                x_label.setPrefSize(MANUAL_MATRIX_WIDTH / 15, SCREEN_HEIGHT / 2 / rows);
                                x_matrix_gridPane.add(x_label, 0, i);
                            }
                        }
//                        generateInputMatrix(input_matrix_gridPane_right_panel,);
                    }
                });
                calculate_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        Matrix B_matrix = getMatrixFromGrid(B_matrix_gridPane);
                        B_matrix.transpose();
                        Matrix A_matrix = getMatrixFromGrid(input_matrix_gridPane_right_panel);
                        A_matrix.transpose();
                        System.out.println(A_matrix.toString());
                        System.out.println(B_matrix.toString());

                        Matrix x_matrix = Matrix.Gauss(A_matrix, B_matrix);
                        x_matrix.transpose();
                        generateMatrixFromMatrix(x_matrix_gridPane, x_matrix, MANUAL_MATRIX_WIDTH / 5, SCREEN_HEIGHT / 2 , info_label);
                        System.out.println(x_matrix);
                    }
                });

            }
            if (operation_name.equals("GET INVERSE MATRIX")) {


                confirm_size_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int INPUT_SIZE_COLUMNS = Integer.parseInt(input_size_text_field.getText());
                        int INPUT_SIZE_ROWS = INPUT_SIZE_COLUMNS;
                        generateInputMatrix(input_matrix_gridPane_right_panel, INPUT_SIZE_COLUMNS, INPUT_SIZE_ROWS, SCREEN_WIDTH - right_panel_width, SCREEN_HEIGHT - 70, info_label);
                    }
                });


                calculate_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        result_of_operation.getChildren().clear();
                        Matrix matrix_from_right_grid = getMatrixFromGrid(input_matrix_gridPane_right_panel);
                        Matrix inversed_matrix = null;
                        boolean isInversedMatrixExist = true;
                        try {
                            inversed_matrix = Matrix.inverseMatrix(matrix_from_right_grid);
                        } catch (Exception e) {
                            //e.printStackTrace();
                            isInversedMatrixExist = false;
                            info_label.setText("   Status: inversed matrix don't exist");
                        }
                        if (isInversedMatrixExist) {
                            generateMatrixFromMatrix(result_of_operation, inversed_matrix, right_panel_width, 100, info_label);
                            info_label.setText("   Status: answer is found");

                        }
                    }
                });
            }

            if ((operation_name.equals("SUBTRACT")) || (operation_name.equals("SUM"))) {

                TextField colomns_2_textField = new TextField("3");
                matrix_size_panel_hbox.getChildren().add(colomns_2_textField);
                input_size_text_field.setPrefSize(right_panel_width / 6, 40);
                colomns_2_textField.setPrefSize(right_panel_width / 6, 40);
                int currentMatrix_index = 1;
                Matrix matrix1 = null;
                Matrix matrix2 = null;
                GridPane matrix_2_gridPane = new GridPane();
                right_panel_main_gridPane.add(matrix_2_gridPane, 1, 1);
                generateInputMatrix(input_matrix_gridPane_right_panel, 3, 3, MANUAL_MATRIX_WIDTH / 2, SCREEN_HEIGHT / 2 - 35, info_label);
                generateInputMatrix(matrix_2_gridPane, 3, 3, MANUAL_MATRIX_WIDTH / 2, SCREEN_HEIGHT / 2 - 35, info_label);
                confirm_size_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        // int col2 = Integer.parseInt(colomns_3_textField.getText());
                        int col = Integer.parseInt(colomns_2_textField.getText());
                        int row = Integer.parseInt(input_size_text_field.getText());
                        generateInputMatrix(input_matrix_gridPane_right_panel, row, col, MANUAL_MATRIX_WIDTH / 2, SCREEN_HEIGHT / 2 - 35, info_label);
                        generateInputMatrix(matrix_2_gridPane, row, col, MANUAL_MATRIX_WIDTH / 2, SCREEN_HEIGHT / 2 - 35, info_label);
                    }
                });


                calculate_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        //     int col2 = matrix_2_gridPane.getColumnCount();//Integer.parseInt(colomns_3_textField.getText());
                        int col = input_matrix_gridPane_right_panel.getColumnCount();//Integer.parseInt(colomns_2_textField.getText());
                        int row = input_matrix_gridPane_right_panel.getRowCount();//Integer.parseInt(input_size_text_field.getText());

                        result_of_operation.getChildren().clear();
                        Matrix matrix_A = getMatrixFromGrid(input_matrix_gridPane_right_panel);
                        //System.out.println("\n");
                        Matrix matrix_B = getMatrixFromGrid(matrix_2_gridPane);
                        if (operation_name.equals("SUM"))
                            matrix_A.addMatrix(matrix_B);//matrix_B.multiplyMatrix(matrix_A);
                        else matrix_A.diffMatrix(matrix_B);
                        Matrix matrix_result = matrix_A;
                        System.out.println(matrix_result.toString());


                        generateMatrixFromMatrix(result_of_operation, matrix_result, right_panel_width, 100, info_label);
                        info_label.setText("   Status: calculated");

                    }
                });
            }


            if (operation_name.equals("MULTIPLY")) {

                TextField colomns_2_textField = new TextField("3");
                matrix_size_panel_hbox.getChildren().add(colomns_2_textField);
                input_size_text_field.setPrefSize(right_panel_width / 12, 40);
                colomns_2_textField.setPrefSize(right_panel_width / 12, 40);

                TextField colomns_3_textField = new TextField("3");
                matrix_size_panel_hbox.getChildren().add(colomns_3_textField);
                colomns_3_textField.setPrefSize(right_panel_width / 12, 40);


                int currentMatrix_index = 1;

                Matrix matrix1 = null;
                Matrix matrix2 = null;
                GridPane matrix_2_gridPane = new GridPane();
                right_panel_main_gridPane.add(matrix_2_gridPane, 1, 1);
                generateInputMatrix(input_matrix_gridPane_right_panel, 3, 3, MANUAL_MATRIX_WIDTH / 2, SCREEN_HEIGHT / 2 - 35, info_label);
                generateInputMatrix(matrix_2_gridPane, 3, 3, MANUAL_MATRIX_WIDTH / 2, SCREEN_HEIGHT / 2 - 35, info_label);
                confirm_size_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int col2 = Integer.parseInt(colomns_3_textField.getText());
                        int col = Integer.parseInt(colomns_2_textField.getText());
                        int row = Integer.parseInt(input_size_text_field.getText());
                        generateInputMatrix(input_matrix_gridPane_right_panel, row, col, MANUAL_MATRIX_WIDTH / 2, SCREEN_HEIGHT / 2 - 35, info_label);
                        generateInputMatrix(matrix_2_gridPane, col, col2, MANUAL_MATRIX_WIDTH / 2, SCREEN_HEIGHT / 2 - 35, info_label);
                    }
                });


                calculate_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int col2 = matrix_2_gridPane.getColumnCount();//Integer.parseInt(colomns_3_textField.getText());
                        int col = input_matrix_gridPane_right_panel.getColumnCount();//Integer.parseInt(colomns_2_textField.getText());
                        int row = input_matrix_gridPane_right_panel.getRowCount();//Integer.parseInt(input_size_text_field.getText());

                        result_of_operation.getChildren().clear();
                        Matrix matrix_A = getMatrixFromGrid(input_matrix_gridPane_right_panel);
                        System.out.println("\n");
                        Matrix matrix_B = getMatrixFromGrid(matrix_2_gridPane);

                        Matrix matrix_result = matrix_B.multiplyMatrix(matrix_A);
                        System.out.println(matrix_result.toString());


                        generateMatrixFromMatrix(result_of_operation, matrix_result, right_panel_width, 100, info_label);
                        info_label.setText("   Status: calculated");

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

    public void generateMatrixFromMatrix(GridPane gridPane, Matrix matrix, int gridWidth, int gridHeight, Label info_label_copy) {
        gridPane.getChildren().clear();
        info_label_copy.setText("   Status:");
        int columns = matrix.getRowsQuantity();
        int rows = matrix.getColumnsQuantity();
        int SINGLE_BUTTON_WIDTH = gridWidth / columns;
        int SINGLE_BUTTON_HEIGHT = gridHeight / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Label output_matrix_Label = new Label(Double.toString(matrix.getElementValue(j, i)));
                output_matrix_Label.setFont(new Font(SINGLE_BUTTON_HEIGHT / 4));
                output_matrix_Label.setPrefSize(SINGLE_BUTTON_WIDTH, SINGLE_BUTTON_HEIGHT);
                gridPane.add(output_matrix_Label, j, i);
            }
        }
    }

    public void generateInputMatrix(GridPane gridPane, int rows, int columns, int gridWidth, int gridHeight, Label info_label_copy) {

        if ((rows > 7) | (columns > 7)) {
            info_label_copy.setText("   Status: it's too big");
            return;
        }
        gridPane.getChildren().clear();
        info_label_copy.setText("   Status:");
        int SINGLE_BUTTON_WIDTH = gridWidth / columns;
        int SINGLE_BUTTON_HEIGHT = gridHeight / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                TextField input_matrix_textField = new TextField();
                input_matrix_textField.setFont(new Font(SINGLE_BUTTON_HEIGHT / 4));
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
                ((TextField) textField).setText("0");
            }
            // System.out.println(num + " " + j+" "+i);
            matrix.setElementValue(i, j, num);
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