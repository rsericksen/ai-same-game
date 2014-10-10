package samegame;

public class Remove extends jaima.search.SearchAction {

	public final BoardState from;
	public final int entryClicked;

	public Remove(BoardState from, int entryClicked) {
		this.from = from;
		this.entryClicked = entryClicked;
	}

	@Override
	public String toString() {
		return "Clicked row " + from.getRow(entryClicked) + " column "
				+ from.getColumn(entryClicked);
	}

	@Override
	public double cost() {
		return 1;
	}

	public BoardState getFrom() {
		return from;
	}

	public BoardState getTo() {
		BoardState newState = from.clone();

		// mark entries empty
		markEmpty(entryClicked, newState);

		sort(newState);

		return newState;
	}

	/**
	 * marks entry EMPTY if it has neighbors of the same color, recursively does
	 * the same to neighbors until finished
	 * 
	 * @param entryClicked
	 */
	private void markEmpty(int entryClicked, BoardState state) {
		int row = state.getRow(entryClicked);
		int column = state.getColumn(entryClicked);
		char color = state.colors[entryClicked];

		state.colors[entryClicked] = BoardState.EMPTY;

		if (row > 0) {
			char up = state.getColor(row - 1, column);
			if (up == color) {
				markEmpty(state.getLocation(row - 1, column), state);
			}
		}

		if (row < Math.sqrt(state.colors.length) - 1) {
			char down = state.getColor(row + 1, column);
			if (down == color) {
				markEmpty(state.getLocation(row + 1, column), state);
			}
		}

		if (column > 0) {
			char left = state.getColor(row, column - 1);
			if (left == color) {
				markEmpty(state.getLocation(row, column - 1), state);
			}
		}

		if (column < Math.sqrt(state.colors.length) - 1) {
			char right = state.getColor(row, column + 1);
			if (right == color) {
				markEmpty(state.getLocation(row, column + 1), state);
			}
		}
	}

	private void sort(BoardState state) {
		int row;
		int column;
		char up;
		char right;

		for (int j = 0; j < state.colors.length; j++) {
			// if square is empty and square above it is not, swap the two
			for (int i = 0; i < state.colors.length; i++) {
				row = state.getRow(i);
				column = state.getColumn(i);
				if (state.colors[i] == BoardState.EMPTY && row > 0) {
					up = state.getColor(row - 1, column);
					if (up != BoardState.EMPTY) {
						char swap = up;
						state.colors[state.getLocation(row - 1, column)] = state.colors[i];
						state.colors[i] = swap;
					}
				}
			}
		}

		for (int j = 0; j < state.colors.length; j++) {
			// if square is in an empty column and its neighbor to the right is
			// not
			// empty, swap the two
			for (int i = 0; i < state.colors.length; i++) {
				row = state.getRow(i);
				column = state.getColumn(i);
				if (column < Math.sqrt(state.colors.length) - 1
						&& state.isColumnEmpty(column)) {
					right = state.getColor(row, column + 1);
					if (right != BoardState.EMPTY) {
						char swap = right;
						state.colors[state.getLocation(row, column + 1)] = state.colors[i];
						state.colors[i] = swap;
					}
				}
			}
		}
	}

}
