package ai.nqueens;

public class NQueen {

	private int N = 8;
	private int X[] = new int[N + 1];

	public NQueen() {
		for (int i = 1; i <= N; i++)
			X[i] = 0;
	}

	private void init() {
		for (int i = 1; i <= N; i++)
			X[i] = 0;

	}

	public boolean placeQueens(int i) {
		if (i == N + 1) {
			for (int a = 1; a <= N; a++)
				System.out.print(" " + X[a] + "   ");
			System.out.println();

			return true;
		}

		for (int j = 1; j <= N; j++) {
			X[i] = j;
			boolean flag = checkConsTraints(N, i);
			if (flag) {
				boolean succsess = placeQueens(i + 1);
				if (succsess)
					return true;

			}

		}
		return false;

	}

	public boolean placeQueensAllSolution(int i) {
		//System.out.println("here"+i);
		if (i == N + 1) {
			for (int a = 1; a <= N; a++)
				System.out.print(" " + X[a] + "   ");
			System.out.println();
			//init();
			return true;
		}

		for (int j = 1; j <= N; j++) {
			X[i] = j;
			boolean flag = checkConsTraints_1(i, j);
			if (flag) {
				boolean succsess = placeQueensAllSolution(i + 1);
				// if(succsess)
				// return true;

			}
			//System.out.println("flag"+flag);

		}
		return false;

	}

	private boolean checkConsTraints(int N, int r) {
		for (int i = 1; i <= r-1; i++) {
			if (X[i] == X[r] || X[r] - (r - 1) == X[i] || (X[r] + (r - 1) == X[i])) {
				return false;
			}
		}
		return true;
	}

	private boolean checkConsTraints_1(int queen, int row_pos) {
		for (int i = 1; i <=queen-1; i++) {
			if (X[i] == row_pos || (row_pos - (queen - i) == X[i]) ||( row_pos + (queen - i) == X[i]  )) {
				return false;
			}
		}
		return true;
	}
}
