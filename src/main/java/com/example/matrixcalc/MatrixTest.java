package com.example.matrixcalc;

import org.junit.Test;

import java.util.Arrays;

//import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    Matrix matrix1 = new Matrix(new double[][]{{42}});

    Matrix matrix2 = new Matrix(new double[][]{{1, 0},{0, 1}}); // Единичная
    Matrix matrix3 = new Matrix(new double[][]{{1, 2},{2, 4}}); // Нулевой определитель
    Matrix matrix4 = new Matrix(new double[][]{{1, 2},{3, 4}}); // Произвольная

    Matrix matrix5 = new Matrix(new double[][]{{1, 0, 0},{0, 1, 0}, {0, 0, 1}}); // Единичная
    Matrix matrix6 = new Matrix(new double[][]{{1, 2, 3},{2, 4, 6}, {1, 1, 1}}); // Нулевой определитель
    Matrix matrix7 = new Matrix(new double[][]{{1, 2, 3},{4, 5, 6}, {7, 8, 9}}); // Произвольная

    Matrix matrix8 = new Matrix(new double[][]{{1, 2, 3},{4, 5, 6}}); // Не квадратная

    Matrix matrix9 = new Matrix(new double[][]{{0, 0},{0, 0}}); // Квадратная нульматрица



    @Test
    public void getElementValueTest() {
        System.out.println("Get Element Value Test:");

        System.out.println(matrix1.getElementValue(0,0) == 42);

        System.out.println(matrix2.getElementValue(0,0) == 1 &
                matrix2.getElementValue(0,1) == 0 &
                matrix2.getElementValue(1,0) == 0 &
                matrix2.getElementValue(1,1) == 1);

        System.out.println(matrix8.getElementValue(0,0) == 1 &
                matrix8.getElementValue(0,1) == 2 &
                matrix8.getElementValue(0,2) == 3 &
                matrix8.getElementValue(1,0) == 4 &
                matrix8.getElementValue(1,1) == 5 &
                matrix8.getElementValue(1,2) == 6);

    }

    @Test
    public void transposeTest() {
        System.out.println("Transpose Test:");

        Matrix matrix1Transposed = matrix1;
        matrix1.transpose();

        Matrix matrix4Transposed = new Matrix(new double[][]{{1, 3}, {2, 4}});
        matrix4.transpose();

        Matrix matrix7Transposed = new Matrix(new double[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}});
        matrix7.transpose();

        Matrix matrix8Transposed = new Matrix(new double[][]{{1, 4}, {2, 5}, {3, 6}});
        matrix8.transpose();

        System.out.println(Matrix.isEquals(matrix1, matrix1Transposed));
        System.out.println(Matrix.isEquals(matrix4, matrix4Transposed));
        System.out.println(Matrix.isEquals(matrix7, matrix7Transposed));
        System.out.println(Matrix.isEquals(matrix8, matrix8Transposed));

        Matrix matrix7DoubleTransposed = matrix7;
        matrix7.transpose();
        matrix7.transpose();
        System.out.println(Matrix.isEquals(matrix7DoubleTransposed, matrix7));

        matrix1.transpose();
        matrix4.transpose();
        matrix7.transpose();
        matrix8.transpose();
    }

    @Test
    public void getRowTest() {
        System.out.println("Get Row Test:");

        System.out.println(Matrix.isEquals(matrix8.getRow(0), new double[]{1, 2, 3}));
        System.out.println(Matrix.isEquals(matrix8.getRow(1), new double[]{4, 5, 6}));
    }

    @Test
    public void getColumnTest() {
        System.out.println("Get Column Test:");
        System.out.println(Matrix.isEquals(matrix8.getColumn(0), new double[]{1, 4}));
        System.out.println(Matrix.isEquals(matrix8.getColumn(1), new double[]{2, 5}));
        System.out.println(Matrix.isEquals(matrix8.getColumn(2), new double[]{3, 6}));

    }

    @Test
    public void addMatrixTest() {
        System.out.println("Add Matrix Test:");
        Matrix add = matrix3;
        add.addMatrix(matrix4);
        System.out.println(Matrix.isEquals(add, new Matrix(new double[][]{{2, 4}, {5, 8}})));

        add = matrix8;
        add.addMatrix(matrix8);
        System.out.println(Matrix.isEquals(add,  new Matrix(new double[][]{{2, 4, 6}, {8, 10, 12}})));

        add = matrix1;
        add.addMatrix(matrix1);
        System.out.println(Matrix.isEquals(add,  new Matrix(new double[][]{{84}})));
    }

    @Test
    public void diffMatrixTest() {
        System.out.println("Diff Matrix Test:");
        Matrix diff = matrix3;
        diff.diffMatrix(matrix4);
        System.out.println(Matrix.isEquals(diff,  new Matrix(new double[][]{{0, 0}, {-1, 0}})));

        diff = matrix8;
        diff.diffMatrix(matrix8);
        System.out.println(Matrix.isEquals(diff,  new Matrix(new double[][]{{0, 0, 0}, {0, 0, 0}})));


    }

    @Test
    public void multiplyMatrixTest() {
        System.out.println("Multiply Matrix Test:");
        Matrix multiply = matrix1;
        System.out.println(Matrix.isEquals(multiply.multiplyMatrix(matrix1),
                new Matrix(new double[][]{{42*42}})));

        multiply = matrix5;
        System.out.println(Matrix.isEquals(multiply.multiplyMatrix(matrix7),
                new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));

        Matrix multiply1 = new Matrix(matrix8.getValues());
        Matrix multiply2 = new Matrix(matrix8.getValues());
        multiply2.transpose();
        System.out.println(Matrix.isEquals(multiply1.multiplyMatrix(multiply2),
                new Matrix(new double[][]{{14, 32}, {32, 77}})));
        matrix8.transpose();



    }

    @Test
    public void getDeterminantTest() {
        System.out.println("Get Determinant Test:");

        System.out.println(Matrix.getDeterminant(matrix1) == 42);
        System.out.println(Matrix.getDeterminant(matrix2) == 1);
        System.out.println(Matrix.getDeterminant(matrix4) == -2);
        System.out.println(Matrix.getDeterminant(matrix6) == 0);
    }


    @Test
    public void inverseMatrixTest() {
        System.out.println("Inverse Matrix Test:");
        System.out.println(Matrix.isEquals(Matrix.inverseMatrix(matrix1), matrix1));
        System.out.println(Matrix.isEquals(Matrix.inverseMatrix(matrix4),
                new Matrix(new double[][]{{-2, 1}, {1.5, -0.5}})));
        System.out.println(Matrix.isEquals(Matrix.inverseMatrix(Matrix.inverseMatrix(matrix4)), matrix4));


    }

    @Test
    public void GaussTest() {
        System.out.println("Gauss Test:");
        System.out.println(Matrix.isEquals(Matrix.Gauss(matrix2, new double[]{1, 2}), new double[]{1, 2}));
        System.out.println(Matrix.isEquals(Matrix.Gauss(matrix4, new double[]{1, 2}), new double[]{0, 0.5}));
    }
}