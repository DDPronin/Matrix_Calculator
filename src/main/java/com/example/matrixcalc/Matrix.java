package com.example.matrixcalc;

import java.util.*;


public class Matrix {
    private int rows; // количество строк матрицы
    private int columns; // количество столбцов матрицы
    private double[][] values; // двумерный массив, содержащий значения элементов матрицы

    // Конструктор, создающий нулевую(!) матрицу размером rows x columns
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.values = new double[rows][columns];
    }

    // Конструктор, создающий матрицу по переданному двумерному массиву
    public Matrix(double[][] values) {
        this.values = values;
        this.rows = values.length;
        this.columns = values[0].length;
    }

    // Возращает количество строк матрицы
    public int getRowsQuantity() {
        return rows;
    }

    // Возращает количество столбцов матрицы
    public int getColumnsQuantity() {
        return columns;
    }

    // Возращает значение элемента матрицы по адресу (строка, столбец)
    public double getElementValue(int rowIndex, int columnIndex) {
        if ((0 <= rowIndex) & (rowIndex <= rows - 1) & (0 <= columnIndex) & (columnIndex <= columns - 1)) {
            return values[rowIndex][columnIndex];
        } else {
            throw new RuntimeException("Element's index is out of Matrix's rows or columns range!");
        }
    }

    // Меняет значение элемента матрицы по адресу (строка, столбец)
    public void setElementValue(int rowIndex, int columnIndex, double newValue) {
        if ((0 <= rowIndex) & (rowIndex <= rows) & (0 <= columnIndex) & (columnIndex <= columns)) {
            values[rowIndex][columnIndex] = newValue;
        } else {
            throw new RuntimeException("Element's index is out of Matrix's rows or columns range!");
        }
    }

    // Возвращает копию матрицы после транспонирования. Исходная матрица остается неизменной
    public static Matrix getTransposedMatrix(Matrix matrix) {
        Matrix transposedMatrix = new Matrix(matrix.columns, matrix.rows);
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                transposedMatrix.values[j][i] = matrix.values[i][j];
            }
        }
        return transposedMatrix;
    }

    // Транспонирует исходную матрицу
    public void transpose() {
        double[][] transposedValues = new double[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposedValues[j][i] = values[i][j];
            }
        }
        int oldRows = rows;
        rows = columns;
        columns = oldRows;
        values = transposedValues;

    }

    // Возвращает строку матрицы по индексу в виде массива
    public double[] getRow(int rowIndex) {
        double[] row = new double[columns];
        if ((0 <= rowIndex) & (rowIndex <= rows - 1)) {
            for (int i = 0; i < columns; i++) {
                row[i] = values[rowIndex][i];
            }
            return row;
        } else {
            throw new RuntimeException("Element's index is out of Matrix's rows range!");
        }
    }

    // Меняет строку матрицы по индексу на введенный массив.
    public void setRow(int rowIndex, double[] newRowValues) {
        if ((0 <= rowIndex) & (rowIndex <= rows - 1)) {
            if (newRowValues.length == columns) {
                for (int i = 0; i < columns; i++) {
                    values[rowIndex][i] = newRowValues[i];
                }
            } else {
                throw new RuntimeException("The length of array-row of values is out of Matrix's columns range!");
            }
        } else {
            throw new RuntimeException("Row's index is out of Matrix's rows range!");
        }
    }

    // Возвращает столбец матрицы по индексу в виде массива
    public double[] getColumn(int columnIndex) {
        double[] column = new double[rows];
        if ((0 <= columnIndex) & (columnIndex <= columns - 1)) {
            for (int i = 0; i < rows; i++) {
                column[i] = values[i][columnIndex];
            }
            return column;
        } else {
            throw new RuntimeException("Element's index is out of Matrix's columns range!");
        }
    }

    // Меняет столбец матрицы по индексу на введенный массив.
    public void setColumn(int columnIndex, double[] columnValues) {
        if ((0 <= columnIndex) & (columnIndex <= columns - 1)) {
            if (columnValues.length == columns) {
                for (int i = 0; i < rows; i++) {
                    values[i][columnIndex] = columnValues[i];
                }
            } else {
                throw new RuntimeException("The length of array-column of values is out of Matrix's rows range!");
            }
        } else {
            throw new RuntimeException("Column's index is out of Matrix's rows range!");
        }
    }

    // Возвращает содержимое матрицы в виде двумерного массива
    public double[][] getValues() {
        return values;
    }


    // Вывод матрицы
    public String toString() {
        String displayMatrix = "{ ";
        for (int i = 0; i < values.length; ++i) {
            for (int j = 0; j < values[0].length; ++j) {
                displayMatrix += Double.toString(values[i][j]) + ' ';
            }
            displayMatrix += "}\n{ ";
        }
        return displayMatrix.substring(0, displayMatrix.length() - 3);
    }

    // Сложение матриц (с изменением исходной)
    public void addMatrix(Matrix toAdd) {
        if ((rows == toAdd.getRowsQuantity()) & (columns == toAdd.getColumnsQuantity())) {
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    values[i][j] += toAdd.getElementValue(i, j);
                }
            }
        } else {
            throw new RuntimeException("These matrices cannot be added");
        }
    }

    // Вычитание матриц (с изменением исходной)
    public void diffMatrix(Matrix toDiff) {
        if ((rows == toDiff.getRowsQuantity()) & (columns == toDiff.getColumnsQuantity())) {
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    values[i][j] -= toDiff.getElementValue(i, j);
                }
            }
        } else {
            throw new RuntimeException("Matrix difference cannot be found");
        }
    }

    // Умножение матрицы на число (с изменением исходной)
    public void multiplyMatrixByNum(double num) {
        for (int i = 0; i < columns; ++i) {
            for (int j = 0; j < rows; ++j) {
                values[i][j] *= num;
            }
        }
    }

    // Умножение строки матрицы (по индексу) на число (с изменением исходной)
    public void multiplyMatrixRowByNum(int ind, double num) {
        for (int i = 0; i < columns; ++i) {
            values[ind][i] *= num;
        }
    }

    // Умножение матриц (с получением новой)
    public Matrix multiplyMatrix(Matrix toMultiply) {
        int rowsFirst = rows;
        int columnsFirst = columns;
        int rowsSecond = toMultiply.getRowsQuantity();
        int columnsSecond = toMultiply.getColumnsQuantity();
        if (columnsFirst == rowsSecond) {
            Matrix newMatrix = new Matrix(rowsFirst, columnsSecond);
            for (int i = 0; i < rowsFirst; ++i) {
                for (int j = 0; j < columnsSecond; ++j) {
                    for (int k = 0; k < rowsSecond; ++k) {
                        newMatrix.setElementValue(i, j, newMatrix.getElementValue(i, j) +
                                values[i][k] * toMultiply.getElementValue(k, j));
                    }
                }
            }
            return newMatrix;
        } else {
            throw new RuntimeException("These matrices cannot be multiplied");
        }
    }


    // Возвращает определитель матрицы
    public static double getDeterminant(Matrix matrix) {
        int size = matrix.getRowsQuantity();
        double[][] values = matrix.getValues();
        int mul = 1;
        if (values.length != values[0].length) {
            throw new RuntimeException("Non-square matrix has no determinant!");
        }
        if (size == 1) {
            return values[0][0];
        }
        while (size > 2) {
            double[][] M = new double[size - 1][size - 1];
            int next_index = 1;
            while (values[0][0] == 0) {
                if (values[next_index][0] > 0) {
                    for (int k = 0; k < size; k++) {
                        double temp = values[0][k];
                        values[0][k] = values[next_index][k];
                        values[next_index][k] = temp;
                    }
                    mul = (int) (mul * Math.pow((-1),
                            (next_index)));
                }
            }
            double p = values[0][0];
            mul = (int) (mul * Math.pow(1 / p, size - 2));
            for (int i = 1; i < size; i++) {
                for (int j = 1; j < size; j++) {
                    M[i - 1][j - 1] = values[0][0] * values[i][j] - values[i][0] * values[0][j];
                }
            }
            for (int i = 0; i < (size - 1); i++) {
                for (int j = 0; j < (size - 1); j++) {
                    values[i][j] = M[i][j];
                }
            }
            size--;
        }
        return mul * (values[0][0] * values[1][1] - values[0][1] * values[1][0]);
    }

    // Обратная матрица
    public static Matrix inverseMatrix(Matrix matrix) {

        if (Math.abs(Matrix.getDeterminant(matrix)) <= 1e-10) {
            throw new RuntimeException("That matrix has no inversed one!");
        }
        double[][] A = matrix.getValues();
        double temp;
        int N = A.length;
        double[][] E = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                E[i][j] = 0f;

                if (i == j)
                    E[i][j] = 1f;
            }
        for (int k = 0; k < N; k++) {
            temp = A[k][k];
            for (int j = 0; j < N; j++) {
                A[k][j] /= temp;
                E[k][j] /= temp;
            }
            for (int i = k + 1; i < N; i++) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
        for (int k = N - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = E[i][j];
        return new Matrix(A);
    }

/*
    // Обратная матрица
    public static Matrix inverseMatrix(Matrix matrix) {
        if (Math.abs(Matrix.getDeterminant(matrix)) <= 1e-10) {
            throw new RuntimeException("That matrix has no inversed one!");
        }
        double[][] A = matrix.getValues();
        double temp;
        int N = A.length;
        double[][] E = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                E[i][j] = 0f;
                if (i == j)
                    E[i][j] = 1f;
            }
        for (int k = 0; k < N; k++) {
            temp = A[k][k];
            for (int j = 0; j < N; j++) {
                A[k][j] /= temp;
                E[k][j] /= temp;
            }
            for (int i = k + 1; i < N; i++) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
        for (int k = N - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = E[i][j];
        return new Matrix(A);
    }
*/


    // Решение СЛАУ (Передаем кол-во строк, столбцов, далее матрицу коэффициентов и массив свободных членов)
    public static double[] Gauss(Matrix A, double[] freeNums) {
        int n = A.getRowsQuantity();
        int m = A.getColumnsQuantity();
        int size = n;
        for (int p = 0; p < size; ++p) {
            int max = p;
            for (int i = p + 1; i < size; ++i) {
                if (Math.abs(A.getElementValue(i, p)) > Math.abs(A.getElementValue(max, p))) {
                    max = i;
                }
            }
            double[] temp = A.getRow(p);
            A.setRow(p, A.getRow(max));
            A.setRow(max, temp);
            double t = freeNums[p];
            freeNums[p] = freeNums[max];
            freeNums[max] = t;
            if (Math.abs(A.getElementValue(p, p)) <= 1e-10) {
                System.out.println("No solutions");
            }
            for (int i = p + 1; i < size; ++i) {
                double alpha = A.getElementValue(i, p) / A.getElementValue(p, p);
                freeNums[i] -= alpha * freeNums[p];
                for (int j = p; j < size; ++j) {
                    A.setElementValue(i, j, A.getElementValue(i, j) - alpha * A.getElementValue(p, j));
                }
            }
        }
        double[] solutions = new double[size];
        for (int i = size - 1; i >= 0; --i) {
            double sum = 0.0;
            for (int j = i + 1; j < size; j++) {
                sum += A.getElementValue(i, j) * solutions[j];
            }
            solutions[i] = (freeNums[i] - sum) / A.getElementValue(i, i);
        }
        if (n < m) {
            System.out.println("Infinite quantity of solutions");
        }
        return solutions;
    }


    // Сравнение матриц
    public static boolean isEquals(Matrix matrix1, Matrix matrix2) {
        if ((matrix1.getValues().length == matrix2.getValues().length) &
                (matrix1.getValues()[0].length == matrix2.getValues()[0].length)) {
            for (int i = 0; i < matrix1.getValues().length; ++i) {
                for (int j = 0; j < matrix1.getValues()[0].length; ++j) {
                    if (matrix1.getValues()[i][j] != matrix2.getValues()[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    // Сравнение массивов
    public static boolean isEquals(double[] array1, double[] array2) {
        if (array1.length == array2.length) {
            for (int i = 0; i < array1.length; ++i) {
                if (array1[i] != array2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}