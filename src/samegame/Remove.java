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
		return from.remove(group);
	}

}
