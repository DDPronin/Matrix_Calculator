package com.example.matrixcalc;

import java.util.*;

class Matrix {
    private int rows; // количество строк матрицы
    private int columns; // количество столбцов матрицы
    private double[][] values; // двумерный массив, содержащий значения элементов матрицы

    // Конструктор, создающий нулевую(!) матрицу размером rows x columns
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.values = new double[rows][columns];
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
        if ((0 <= columnIndex) & (columnIndex <= rows - 1)) {
            for (int i = 0; i < columns; i++) {
                column[i] = values[i][columnIndex];
            }
            return column;
        } else {
            throw new RuntimeException("Element's index is out of Matrix's columns range!");
        }
    }

    // Меняет столбец матрицы по индексу на введенный массив.
    public void setColumn(int columnIndex, double[] columnValues) {
        if ((0 <= columnIndex) & (columnIndex <= rows - 1)) {
            if (columnValues.length == columns) {
                for (int i = 0; i < columns; i++) {
                    values[i][columnIndex] = columnValues[i];
                }
            } else {
                throw new RuntimeException("The length of array-column of values is out of Matrix's rows range!");
            }
        } else {
            throw new RuntimeException("Column's index is out of Matrix's rows range!");
        }
    }

    //================================================================================================================//
    // Вывод матрицы
    public String printMatrix() {
        String displayMatrix = "{ ";
        for (int i = 0; i < columns; ++i) {
            for (int j = 0; j < rows; ++j) {
                displayMatrix += values[i][j] + ' ';
            }
            displayMatrix += "}\n{ ";
        }
        return displayMatrix;
    }

    // Сложение матриц (с изменением исходной)
    public void addMatrix(Matrix toAdd) {
        if ((rows == toAdd.getRowsQuantity()) & (columns == toAdd.getColumnsQuantity())) {
            for (int i = 0; i < columns; ++i) {
                for (int j = 0; j < rows; ++j) {
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
            for (int i = 0; i < columns; ++i) {
                for (int j = 0; j < rows; ++j) {
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
        int columnsQuantitySecondMatrix = toMultiply.getRowsQuantity();
        if (columns == columnsQuantitySecondMatrix) {
            Matrix newMatrix = new Matrix(rows, columnsQuantitySecondMatrix);
            for (int i = 0; i < rows; ++i) {
                for (int u = 0; u < columnsQuantitySecondMatrix; ++u) {
                    for (int j = 0; j < columns; ++j) {
                        newMatrix.setElementValue(i, u, values[i][j] * getElementValue(j, u));
                    }
                }
            }
            return newMatrix;
        } else {
            throw new RuntimeException("These matrices cannot be multiplied");
        }
    }

    // Детерминант матрицы
    public double getDeterminant(double[][] values) {
        double det = 0;
        return det; // В функцию передается двумерный массив, детерминант считается рекурсивно
    }

    // Поиск минора для детерминанта
    private double[][] getMinor(int row, int column) {
        int minorLength = rows - 1;
        double[][] minor = new double[minorLength][minorLength];
        int dI = 0;
        int dJ = 0;
        for (int i = 0; i <= minorLength; i++) {
            dJ = 0;
            for (int j = 0; j <= minorLength; j++) {
                if (i == row) {
                    dI = 1;
                } else {
                    if (j == column) {
                        dJ = 1;
                    } else {
                        minor[i - dI][j - dJ] = values[i][j];
                    }
                }
            }
        }
        return minor;
    }

    // Решение СЛАУ (Передаем кол-во строк, столбцов, матрицу коэффициентов и массив свободных членов)
    public double[] Gauss(int n, int m, Matrix A, double[] freeNums) {
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
    //================================================================================================================//
}