package com.pchang.misc;

public class RotatingMatrix {

	static int rowSize = 3;
	static int colSize = 4;

	public static void main(String[] args) {

		// int[][] matrix = new int[rowSize][colSize]; // 2 row, 3 columns e.g. 1 2 3 & 4 5 6
		// matrix[0][0] = 1;
		// matrix[0][1] = 2;
		// matrix[0][2] = 3;
		// matrix[0][3] = 4;
		//
		// matrix[1][0] = 4;
		// matrix[1][1] = 5;
		// matrix[1][2] = 6;
		// matrix[1][3] = 8;
		//
		// matrix[2][0] = 9;
		// matrix[2][1] = 10;
		// matrix[2][2] = 11;
		// matrix[2][3] = 12;
		//
		// matrix[3][0] = 13;
		// matrix[3][1] = 14;
		// matrix[3][2] = 15;
		// matrix[3][3] = 16;

		// int m[][] = {
		// { 1 * 1, 1 * 2, 1 * 3, 1 * 4 },
		// { 2 * 2, 2 * 2, 2 * 3, 2 * 4 },
		// { 3 * 1, 3 * 2, 3 * 3, 3 * 4 },
		// { 4 * 1, 4 * 2, 4 * 3, 4 * 4 },
		// };

		int m[][] = {
				{ 1, 2, 3, 4 },
				{ 5, 6, 7, 8 },
				{ 9, 10, 11, 12 },
				// { 13, 14, 15, 16 },
		};

		printArray(m);

		// rotateMatrixInplace_90(m);
		// rotate_matrix_cw_90(m);
		rotateMatrix_180(m);
	}

	public static void rotate_matrix_cw_90(int[][] m) {

		int i, j = 0;
		int n = m.length;
		printArray(m);
		// first mirror the matrix along the diagnal line.
		for (i = 0; i < n; i++) {
			for (j = i + 1; j < n; j++)
			{
				int tmp = m[i][j];
				m[i][j] = m[j][i];
				m[j][i] = tmp;
			}
		}
		printArray(m);
		// mirror the matrix horizontally.
		for (i = 0; i < n / 2; i++) {
			for (j = 0; j < n; j++)
			{
				int tmp = m[j][i];
				m[j][i] = m[j][n - i - 1];
				m[j][n - i - 1] = tmp;
			}
		}
		printArray(m);
	}

	public static void rotateMatrixInplace_90(int[][] matrix) {
		// rotate n*n matrix by 90 degrees in place?
		// step 1: reverse the columns i.e. 1 2 3 => 3 2 1
		// THIS ASSUMES A SQUARE MATRIX!!!!!!!!!!!!!!!!!!

		for (int i = 0; i < rowSize; i++) {
			System.out.println(" new row: " + 1);
			for (int j = 0; j < colSize / 2; j++) {
				int temp = matrix[i][j];
				System.out.printf("... first element: [ %d , %d] = %d%n", i, j, temp);
				int endIndex = (colSize - 1) - j;
				System.out.printf("... end element: [ %d , %d] = %d%n", i, endIndex, matrix[i][endIndex]);
				
				if (endIndex == j) break;
				// swap
				matrix[i][j] = matrix[i][endIndex];
				matrix[i][endIndex] = temp;
			}
		}
		System.out.println("\nafter reversering:");
		printArray(matrix);

		// Step 2 rotate ...by just manipulating the index
		System.out.println("\nRotate:");
		for (int i = 0; i < colSize; i++) {
			for (int j = 0; j < rowSize; j++) {
				int temp = matrix[j][i];
				System.out.printf("[ %d , %d] = %d%n", i, j, temp);
			}
		}

	}

	public static void rotateMatrix_180(int[][] matrix) {
		// rotate m*n matrix by 180 degrees - top to bottom, right to left, i.e. swap( m[first row, first cell], m[last row, last cell])

		for (int i = 0; i < rowSize / 2; i++) {
			System.out.println(" new row: " + 1);
			for (int j = 0; j < colSize; j++) {
				int bRowIndex = rowSize - 1;
				int bColIndex = (colSize - 1) - j;
				int topCell = matrix[i][j];
				int botCell = matrix[bRowIndex][bColIndex];

				System.out.printf("... first element: [ %d , %d] = %d%n", i, j, topCell);
				System.out.printf("... end element: [ %d , %d] = %d%n", bRowIndex, bColIndex, botCell);

				if (bColIndex == j) break;
				// swap
				matrix[i][j] = botCell;
				matrix[bRowIndex][bColIndex] = topCell;
			}
		}
		// check for odd number of rows
		int oddRow = rowSize % 2;
		if (oddRow != 0) {
			oddRow = oddRow + rowSize / 2;
			// flip this row
			for (int i = 0; i < colSize / 2; i++) {
				int temp = matrix[oddRow][i];
				int endIndex = colSize - 1;
				matrix[oddRow][i] = matrix[oddRow][endIndex];
				matrix[oddRow][endIndex] = temp;
			}
		}

		System.out.println("\nafter reversering:");
		printArray(matrix);

	}


	public static void printArray(int[][] matrix) {
		System.out.println("\n");
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				int temp = matrix[i][j];
				System.out.printf("[ %d , %d] = %d%n", i, j, temp);
			}
		}

	}

}
