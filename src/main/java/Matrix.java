// Структура индектов элементов матрицы:
//    (0,0 0,1)
//    (1,0 1,1)

public class Matrix {
    private int rows; // количество строк матрицы
    private int columns; // количество столбцов матрицы
    private float[][] values; // двумерный массив, содержащий значения элементов матрицы

    // Конструктор, создающий нулевую(!) матрицу размером rows x columns
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.values = new float[rows][columns];
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
    public float getElementValue(int rowIndex, int columnIndex) {
        if ((0 <= rowIndex) & (rowIndex <= rows-1) & (0 <= columnIndex) & (columnIndex <= columns-1)) {
            return values[rowIndex][columnIndex];
        } else {
            throw new RuntimeException("Element index out of Matrix rows or columns range!");
        }
    }

    // Меняет значение элемента матрицы по адресу (строка, столбец)
    public void setElementValue(int rowIndex, int columnIndex, float newValue) {
        if ((0 <= rowIndex) & (rowIndex <= rows) & (0 <= columnIndex) & (columnIndex <= columns)) {
            values[rowIndex][columnIndex] = newValue;
        } else {
            throw new RuntimeException("Element index out of Matrix rows or columns range!");
        }
    }

    // Возвращает копию матрицы после транспонирования. Исходная матрица остается неизменной
    public static Matrix getTransposedMatrix(Matrix matrix) {
        Matrix transposedMatrix = new Matrix(matrix.columns, matrix.rows);
        for (int i = 0; i < matrix.rows; i++){
            for (int j = 0; j < matrix.columns; j++) {
                transposedMatrix.values[j][i] = matrix.values[i][j];
            }
        }
        return transposedMatrix;
    }

    // Транспонирует исходную матрицу
    public void transpose() {
        float[][] transposedValues = new float[columns][rows];
        for (int i = 0; i < rows; i++){
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
    public float[] getRow(int rowIndex) {
        float[] row = new float[columns];
        if ((0 <= rowIndex) & (rowIndex <= rows-1)) {
            for (int i = 0; i < columns; i++) {
                row[i] = values[rowIndex][i];
            }
            return row;
        } else {
            throw new RuntimeException("Element index out of Matrix rows range!");
        }
    }

    // Меняет строку матрицы по индексу на введенный массив.
    public void setRow(int rowIndex, float[] newRowValues) {
        if ((0 <= rowIndex) & (rowIndex <= rows-1)) {
            if (newRowValues.length == columns) {
                for (int i = 0; i < columns; i++) {
                    values[rowIndex][i] = newRowValues[i];
                }
            } else {
                throw new RuntimeException("Length of row values array is out of Matrix columns range!");
            }
        } else {
            throw new RuntimeException("Row index is out of Matrix rows range!");
        }
    }

    // Возвращает столбец матрицы по индексу в виде массива
    public float[] getColumn(int columnIndex) {
        float[] column = new float[rows];
        if ((0 <= columnIndex) & (columnIndex <= rows-1)) {
            for (int i = 0; i < columns; i++) {
                column[i] = values[i][columnIndex];
            }
            return column;
        } else {
            throw new RuntimeException("Element index out of Matrix columns range!");
        }
    }

    // Меняет столбец матрицы по индексу на введенный массив.
    public void setColumn(int columnIndex, float[] columnValues) {
        if ((0 <= columnIndex) & (columnIndex <= rows-1)) {
            if (columnValues.length == columns) {
                for (int i = 0; i < columns; i++) {
                    values[i][columnIndex] = columnValues[i];
                }
            } else {
                throw new RuntimeException("Length of column values array is out of Matrix raws range!");
            }
        } else {
            throw new RuntimeException("Column index is out of Matrix rows range!");
        }
    }
}
