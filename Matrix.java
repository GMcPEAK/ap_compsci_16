/**
 * Matrix 
 * 
 * @author Grady McPeak
 * @version 2/24/27
 */
public class Matrix {
    double [][] matrix; //the matrix, represented as a 2-d array
    int horiz; //number of rows in matrix
    int vert; //number of columns in matrix
    
    /**
     * Constructor method.
     * @param x - the amount of rows in the matrix
     * @param y - the amount of columns in the matrix
     */
    public Matrix (int x, int y) {
        matrix = new double [x][y];
        horiz = x;
        vert = y;
    }
    
    /**
     * Allows user to change the elements in the matrix.
     * @param x - the x-coordinate of the location you want to put the value in the matrix
     * @param y - the y-coordinate
     * @param v - the value you want to insert in your matrix
     */
    public void set (int x, int y, double v) throws MatrixException {
        if (x >= horiz || y >= vert || x < 0 || y < 0) {
           throw new MatrixException ("Your matrix is too small to insert this value!"); 
        } else {
            matrix[x][y] = v;
        }
    }
    
    /**
     * Returns what number is stored in the coordinates sent in
     * @param x - the x-coordinate of the value being returned
     * @param y - the y-coordinate
     * @return matrix[x][y] - the value stored in the coordinates requested
     */
    public double get (int x, int y) throws MatrixException {
        if (x >= horiz || y >= vert || x < 0 || y < 0) {
           throw new MatrixException ("Your matrix is too small! There is no value to return!"); 
        } else {
            return matrix[x][y];
        }
    }
    
    
    /**
     * Adds together any two matrices.
     * @param a - one of the matrices to be added together
     * @param b - the other matrix
     * @return c - the new, added-together matrix
     */
    public static Matrix add (Matrix a, Matrix b) throws MatrixException {
        if (a == null || b == null) {
            throw new MatrixException ("One of your matrices is equal to null!");
        } else if (a.horiz != b.horiz || a.vert != b.vert) {
            throw new MatrixException ("Your matrices are not the same size!");
        } else {
            Matrix c = new Matrix (a.horiz, a.vert);
            for (int i = 0; i < c.horiz; i++) {
                for (int j = 0; j < c.vert; j++) {
                    c.matrix[i][j] = a.matrix[i][j] + b.matrix[i][j];
                }
            }
            return c;
        }
    }
    
    /**
     * Subtracts any two matrices.
     * @param a - one of the matrices to be added together, the minuend
     * @param b - the other matrix, the subtrahend
     * @return c - the new matrix, which is the difference of the two parameters
     */
    public static Matrix sub (Matrix a, Matrix b) throws MatrixException {
        if (a == null || b == null) {
            throw new MatrixException ("One of your matrices is equal to null!");
        } else if (a.horiz != b.horiz || a.vert != b.vert) {
            throw new MatrixException ("Your matrices are not the same size!");
        } else {
            Matrix c = new Matrix (a.horiz, a.vert);
            for (int i = 0; i < c.horiz; i++) {
                for (int j = 0; j < c.vert; j++) {
                    c.matrix[i][j] = a.matrix[i][j] - b.matrix[i][j];
                }
            }
            return c;
        }
    }
    
    /**
     * Multiplies a matrix by a numerical input.
     * @param a - the matrix to be multiplied 
     * @param b - the integer to multiply the matrix by
     * @return c - the product of a and b
     */
    public static Matrix mult (Matrix a, double b) throws MatrixException {
        if (a == null) {
            throw new MatrixException ("Your matrix is equal to null!");
        } else {
            Matrix c = new Matrix (a.horiz, a.vert);
            for (int i = 0; i < c.horiz; i++) {
                for (int j = 0; j < c.vert; j++) {
                    c.matrix[i][j] = a.matrix[i][j] * b;
                }
            }
            return c;
        }
    }
    
    /**
     * Multiplies a matrix by another matrix.
     * @param a - the matrix to be multiplied 
     * @param b - the other matrix to be multiplied
     * @return c - the product of a and b
     */
    public static Matrix mult (Matrix a, Matrix b) throws MatrixException {
        if (a == null || b == null) {
            throw new MatrixException ("One of your matrices is equal to null!");
        } else if (a.horiz != b.vert || a.vert != b.horiz) {
            throw new MatrixException ("Your matrices are not the correct sizes for multiplication!");
        } else {
            int i = 0;
            int j = 0;
            
            if (a.horiz < a.vert) {
                i = a.horiz;
                j = b.vert;
            } else {
                i = a.vert;
                j = b.horiz;
            }
            
            Matrix c = new Matrix (i, j);
            
            for (int x = 0; x < c.horiz; x++) {
                for (int y = 0; y < c.vert; y++) {
                    double ans = 0.0;
                    if (i == a.horiz) {
                        for (int p = 0; p < a.horiz; p++) {
                           ans = ans + (a.matrix[x][p]*b.matrix[p][y]); 
                        }
                    } else {
                        for (int p = 0; p < a.vert; p++) {
                           ans = ans + (a.matrix[x][p]*b.matrix[p][y]); 
                        }
                    }
                    c.matrix[x][y] = ans;
                }
            }
            
            return c;
        }
    }
    
    /**
     * Performs a transposition on a given matrix
     * @param a - the matrix to be transposed
     * @return b - the new, transposed matrix
     */
    public static Matrix transpose (Matrix a) throws MatrixException {
        if (a == null) {
            throw new MatrixException ("Your input is equal to null!");
        } else {
            Matrix b = new Matrix (a.vert, a.horiz);
        
            for (int x = 0; x < b.horiz; x++) {
                for (int y = 0; y < b.vert; y++) {
                    b.matrix[x][y] = a.matrix[y][x];
                }
            }
        
            return b;
        }
    }
    
    /**
    * MatrixException
    * This exception is thrown for any error associated with the
    * methods in the Matrix class.
    *
    * @author Mr. Kramer
    * @version 10/3/2010
    */

    public static class MatrixException extends Exception{
        public MatrixException() { 
            super(); 
        }

        public MatrixException (String s) {       
            super (s); 
        }
    }
}
