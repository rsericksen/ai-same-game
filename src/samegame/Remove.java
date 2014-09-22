package samegame;

public class Remove extends jaima.search.SearchAction {

	public final BoardState from;
	public final BoardState to;

	public Remove(BoardState from, BoardState to) {
		this.from = from;
		this.to = to;
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
		return to;
	}

}
