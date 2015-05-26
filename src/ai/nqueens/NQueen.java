package ai.nqueens;

import java.io.EOFException;
import java.text.BreakIterator;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit.BoldAction;

public class NQueen {

	private int N = 8;
	private int X[] = new int[N + 1];
	int c = 0;
	int count=0;

	public NQueen() {
	}

	public void nQueenUisngSimpleBackTrack()
	{
		String input=JOptionPane.showInputDialog("Please Enter N");
		N=Integer.parseInt(input);
		System.out.println("Printing Solutions using Simple BackTrack:");
		placeQueensAllSolution(1);
	}
	public void nQueenUisngFTAndMRV()
	{
		String input=JOptionPane.showInputDialog("Please Enter N");
		N=Integer.parseInt(input);
		System.out.println("Printing Solutions using FT and MRV:");
		placeQueensFCMRV(new int[N][N]);
		
		
		
	}
	public boolean placeQueensAllSolution(int row) {
		// System.out.println("here"+i);
		if (row == N + 1) {
			count++;
			System.out.print(""+count+") ");
			for (int a = 1; a <= N; a++)
				System.out.print(" " + X[a] + "   ");
			System.out.println();
			// init();
			return true;
		}

		for (int j = 1; j <= N; j++) {
			X[row] = j;
			boolean flag = checkConsTraints(row, j);
			if (flag) {
				//System.out.println("row"+ row);
				//printvars();
				boolean succsess = placeQueensAllSolution(row + 1);
				/*if (succsess)
					return true;
*/
			}
			// System.out.println("flag"+flag);

		}
		return false;

	}

	private void printvars() {
		for (int a = 1; a <= N; a++)
			System.out.print(" " + X[a] + "   ");
		System.out.println();
	}

	private boolean checkConsTraints(int queen, int row_pos) {
		for (int i = 1; i <= queen - 1; i++) {
			if (X[i] == row_pos || (row_pos - (queen - i) == X[i]) || (row_pos + (queen - i) == X[i])) {
				return false;
			}
		}
		return true;
	}

	public boolean placeQueensFCMRV(int[][] blocks) {
		if (!isRowAvailable(blocks)) {
			count++;
			System.out.print(""+count+") ");
			for (int a = 1; a <= N; a++)
				System.out.print(" " + X[a] + "   ");
			System.out.println();

			return true;
		}

		int row = selectRowMRV(blocks);
		//System.out.println("new row:"+(row+1));
		for (int i = 0; i < N; i++)
			if (blocks[row][i] != 2 && blocks[row][i] != 1) {
				X[row + 1] = i + 1;

				boolean flag = forwardChecking(row, i,blocks);
				if (flag) {
					//printBlocks(blocks);
					// break;
					//System.out.println("row:"+(row));
					//printBlocks(blocks);
					//printvars();
					int [][]cBlocks=copyBlocks(blocks);
					cBlocks=addNewInvalisPositions(row, i, cBlocks);
					boolean succ=placeQueensFCMRV(cBlocks);
					/*if (succ)
						return true;*/
				}
			}
		return false;

	}

	private int[][] copyBlocks(int [][] blocks) {
		int a[][]=new int[N][N];
		for (int i = 0; i < N; i++)
			for(int j=0;j<N;j++)
				a[i][j]=blocks[i][j];
		return a;

	}
	private boolean isRowAvailable(int [][] blocks) {
		for (int i = 0; i < N; i++) {
			boolean blankRow = true;
			for (int j = 0; j < N; j++)
				if (blocks[i][j] == 1)
					blankRow = false;
			if (blankRow)
				return true;
		}
		return false;
	}

	private void printBlocks(int [][] blocks) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(" " + blocks[i][j] + "  ");
			System.out.println();
		}

	}

	private int selectRowMRV(int [][] blocks) {
		int min = N + 1;
		int row_num = 0;
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < N; j++)
				if (blocks[i][j] != 2) {
					count++;
				}
			if (count < min && isRowValid(i,blocks)) {
				row_num = i;
				min = count;
			}
		}

		return row_num;

	}

	private boolean isRowValid(int row,int [][] blocks) {
		for (int i = 0; i < N; i++)
			if (blocks[row][i] == 1)
				return false;
		return true;
	}
	

	private boolean forwardChecking(int row, int column,int [][] blocks) {
		//System.out.println("row:" + row + "column " + column);
		int count = 0;
		/*if(isRowValid(row, blocks))
		{
			
		}
		else
			return false;*/
		for (int i = row; i < N; i++) {
			if (blocks[i][column] == 1)
				return false;
			if (column - count >= 0 && blocks[i][column - count] == 1)
				return false;
			if (column + count < N && blocks[i][column + count] == 1)
				return false;
			count++;

		}
		return true;
	}
	private int [][] addNewInvalisPositions(int row, int column,int [][] blocks) {
		//System.out.println("row:" + row + "column " + column);
		int count = 0;	
		blocks[row][column] = 1;

		for (int i = row; i < N; i++) {
			if (blocks[i][column] != 1)
				blocks[i][column] = 2;
			if (column - count >= 0 && blocks[i][column - count] != 1)
				blocks[i][column - count] = 2;
			if (column + count < N && blocks[i][column + count] != 1)
				blocks[i][column + count] = 2;
			count++;

		}
		return blocks;
	}
}
