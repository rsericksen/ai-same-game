package samegame;

import samegame.BoardState.ColorGroup;

public class Remove extends jaima.search.SearchAction {

	public final BoardState from;
	public ColorGroup group;

	public Remove(BoardState from, ColorGroup group) {
		this.from = from;
		this.group = group;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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

		// replace array locations in group with empty character
		for (int i = 0; i < group.locations.size(); i++) {
			newState.colors[group.locations.get(i)] = BoardState.EMPTY;
		}

		// if square is empty and square above it is not, swap the two
		int row;
		int column;
		char up;
		char left;
		for (int j = 0; j < Math.sqrt(newState.colors.length); j++) {
			for (int i = 0; i < newState.colors.length; i++) {
				if (newState.colors[i] == BoardState.EMPTY) {
					row = newState.getRow(i);
					column = newState.getColumn(i);

					if (row > 0) {
						up = newState.getColor(row - 1, column);
						if (up != BoardState.EMPTY && up != BoardState.EDGE) {
							int upLocation = newState.getLocation(row - 1,
									column);
							newState.colors[upLocation] = BoardState.EMPTY;
							newState.colors[i] = up;
						}
					}
					
					if(column > 0) {
						left = newState.getColor(row, column - 1);
						if(left != BoardState.EDGE && left != BoardState.EMPTY){
							int leftLocation = newState.getLocation(row, column - 1);
							newState.colors[leftLocation] = BoardState.EMPTY;
							newState.colors[i] = left;
						}
					}
				}
			}
		}

		return newState;
	}

}
