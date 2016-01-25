package matricesAssignment;

import java.util.ArrayList;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	//sup dude
	
	//crack cocaine

	// Constructor with data for new matrix (automatically determines
	// dimensions)
	public Matrix(int data[][]) {
		numRows = data.length; // d.length is the number of 1D arrays in the 2D
								// array
		if (numRows == 0) {
			numColumns = 0;
		} else {
			numColumns = data[0].length; // d[0] is the first 1D array
		}
		this.data = new int[numRows][numColumns]; // create a new matrix to hold
													// the data
		// copy the data over
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	@Override
	// instruct the compiler that we do indeed intend for this method to
	// override the superclass' (Object) version
	public boolean equals(Object other) {
		if (!(other instanceof Matrix)) { // make sure the Object we're
											// comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix) other; // if the above was not true, we know
										// it's safe to treat 'o' as a Matrix

		// returns true if the Matrix strings are identical indicating that they
		// contain the same values, rows, and columns. Returns false if
		// otherwise
		return this.toString().equals(other.toString());
	}

	@Override
	// instruct the compiler that we do indeed intend for this method to
	// override the superclass' (Object) version
	public String toString() {
		String result = "";
		for (int i = 0; i < numRows; i++) {
			// adds each number from each row to a string
			for (int n = 0; n < numColumns; n++) {
				result += data[i][n] + " ";
				// when the loop reaches the end of the row, adds a newline
				// character
				if (n == (numColumns - 1)) {
					result += "\n";
				}
			}
		}

		return result;
	}

	public Matrix times(Matrix matrix) {
		// returns null if the Matrices are incompatible for multiplication
		if (this.numColumns != matrix.numRows) {
			return null;
		}
		// creates a 2D array that will be used as the parameter for creating
		// the Matrix of the result.
		// the result must contain the same number of rows as the first Matrix.
		int[][] parameter = new int[this.numRows][];
		for (int i = 0; i < numRows; i++) {
			// creates an array that will be used to fill in each row of the 2D
			// parameter array
			// must be of the same length as the number of columns in the second
			// Matrix
			int[] arr = new int[matrix.numColumns];
			for (int n = 0; n < matrix.numColumns; n++) {
				// computes a value for each spot in each row by moving across a
				// row of the left matrix, and down a column of the right
				// matrix, multiplying each element together, and summing them
				// all up
				int value = 0;
				for (int k = 0; k < matrix.numRows; k++) {
					value += data[i][k] * matrix.data[k][n];
				}
				//adds the value to the row array
				arr[n] = value;
			}
			//adds the row array to the 2D array
			parameter[i] = arr;
		}
		return new Matrix(parameter);
	}

	public Matrix plus(Matrix matrix) {
		// checks whether or not this Matrix and the parameter Matrix are
		// compatible for addition.
		// they must have the same number of rows and columns
		if (matrix.numRows != this.numRows
				|| matrix.numColumns != this.numColumns) {
			return null;
		}
		// creates a 2D array that will be used as a parameter for the resulting
		// matrix
		// the array contained within this 2D array contains the same number
		// of elements as this matrix and the parameter matrix
		int[][] parameter = new int[matrix.numRows][];
		for (int i = 0; i < numRows; i++) {
			int[] arr = new int[numColumns];
			for (int n = 0; n < numColumns; n++) {
				// computes each value in each row by adding together the
				// corresponding values from each matrix
				arr[n] = (data[i][n] + matrix.data[i][n]);

			}
			//adds the row to the 2D array
			parameter[i] = arr;
		}

		return new Matrix(parameter);
	}
}
