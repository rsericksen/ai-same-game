package samegame;

import jaima.search.SearchAction;
import jaima.search.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardState extends jaima.search.State {

	public char[] colors;

	private static final char EMPTY = 'E';
	private static final char EDGE = 'I';

	/**
	 * Board state represented as a grid of chars
	 * 
	 * row0col0 row0col1 row0col2... row1col0 row1col1 row1col2... row2col0
	 * row2col1 row2col2... . . . . . . . . .
	 * 
	 * ex: g g b r r b b b g
	 * 
	 * @param fromString
	 */
	public BoardState(String fromString) {
		super(fromString);

		colors = fromString.toCharArray();
	}

	@Override
	public String toString() {
		return new String(colors);
	}

	@Override
	public String prettyPrint() {
		String board = "";
		for (int i = 0; i < toString().length(); i++) {
			board += toString().charAt(i) + " ";
			if ((i + 1) % Math.sqrt(toString().length()) == 0) {
				board += '\n';
			}
		}

		return board;
	}

	@Override
	public BoardState clone() {
		return new BoardState(toString());
	}

	public boolean isEmpty() {
		for (int color : colors) {
			if (color != 0) {
				return false;
			}
		}

		return true;
	}

	public Map<SearchAction, State> successors() {
		Map<SearchAction, State> moves = new HashMap<SearchAction, State>();

		List<ColorGroup> groups = new ArrayList<ColorGroup>();

		for (int i = 0; i < colors.length; i++) {
			groups.add(new ColorGroup(i));
		}

		ColorGroup thisGroup;
		ColorGroup leftGroup;
		ColorGroup upGroup;
		int row;
		int column;
		for (int i = 0; i < groups.size(); i++) {
			thisGroup = groups.get(i);

			row = getRow(i);
			column = getColumn(i);

			// check if up exists
			if (row > 0) {
				upGroup = groups.get(getLocation(row - 1, column));
				if (upGroup.color == thisGroup.color) {
					upGroup.merge(thisGroup);
				}
			}

			// check if left exists
			if (column > 0) {
				leftGroup = groups.get(getLocation(row, column - 1));
				if (!leftGroup.locations.contains(thisGroup.locations.get(0))
						&& leftGroup.color == thisGroup.color) {
					leftGroup.merge(thisGroup);
				}
			}
		}

		for (ColorGroup group : groups) {
			if (group.isGroup()) {
				Remove action = new Remove(this, group);
				BoardState state = action.getTo();
				moves.put(action, state);
			}
		}

		return moves;
	}

	public BoardState remove(ColorGroup group) {
		BoardState newState = this.clone();

		// replace array locations in group with empty character
		for (int i = 0; i < group.locations.size(); i++) {
			newState.colors[group.locations.get(i)] = EMPTY;
		}

		// if square is empty and square above it is not, swap the two
		int row;
		int column;
		char up;
		for (int j = 0; j < Math.sqrt(newState.colors.length); j++) {
			for (int i = 0; i < newState.colors.length; i++) {
				if (newState.colors[i] == EMPTY) {
					row = newState.getRow(i);
					column = newState.getColumn(i);

					if (row > 0) {
						up = newState.getColor(row - 1, column);
						if (up != EMPTY && up != EDGE) {
							int upLocation = newState.getLocation(row - 1,
									column);
							newState.colors[upLocation] = EMPTY;
							newState.colors[i] = up;
						}
					}
				}
			}
		}

		return newState;
	}

	public class ColorGroup {
		public List<Integer> locations;
		public char color;

		public ColorGroup(int initialLocation) {
			locations = new ArrayList<Integer>();
			locations.add(initialLocation);
			color = colors[initialLocation];
		}

		public boolean isGroup() {
			return locations.size() > 1;
		}

		public void merge(ColorGroup group) {
			locations.addAll(group.locations);
		}
	}

	/**
	 * 
	 * @param row
	 * @param column
	 * @return an integer value that corresponds to the color at specified row
	 *         and column
	 */
	public char getColor(int row, int column) {
		return colors[getLocation(row, column)];
	}

	public int getLocation(int row, int column) {
		return (int) (column + (Math.sqrt(colors.length) * row));
	}

	/**
	 * @param n
	 * @return row of colors[n]
	 */
	public int getRow(int n) {
		return (int) (n / Math.sqrt(colors.length));
	}

	/**
	 * 
	 * @param n
	 * @return column of colors[n]
	 */
	public int getColumn(int n) {
		return (int) (n % Math.sqrt(colors.length));
	}

}
