package samegame;

import jaima.search.SearchAction;
import jaima.search.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardState extends jaima.search.State {

	public char[] colors;

	public static final char EMPTY = 'E';
	public static final char EDGE = 'I';

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
		for (char thisChar : colors) {
			if (thisChar != EMPTY) {
				return false;
			}
		}

		return true;
	}

	public Map<SearchAction, State> successors() {
		Map<SearchAction, State> moves = new HashMap<SearchAction, State>();
		char color;
		Remove remove;
		BoardState newState;
		for (int i = 0; i < colors.length; i++) {
			if(isClickable(i)){
				remove = new Remove(this, i);
				newState = remove.getTo();
				
				if(!moves.values().contains(newState)){
					moves.put(remove, newState);
				}
			}
		}

		return moves;
	}

	public boolean isClickable(int entry) {
		char color = colors[entry];
		int row = getRow(entry);
		int column = getColumn(entry);

		return color == EMPTY ? false : (row > 0 && getColor(row - 1, column) == color)
				|| (row < Math.sqrt(colors.length) - 1 && getColor(row + 1, column) == color)
				|| (column > 0 && getColor(row, column - 1) == color)
				|| (column < Math.sqrt(colors.length) - 1 && getColor(row, column + 1) == color);
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

	public boolean isColumnEmpty(int column) {
		int row = 0;
		char color;
		while (row < Math.sqrt(colors.length)) {
			color = getColor(row++, column);
			if (color != EMPTY) {
				return false;
			}
		}

		return true;
	}
	
	public int numEmpty(){
		int i = 0;
		for(char color : colors){
			if(color == EMPTY){
				i++;
			}
		}
		
		return i;
	}

}
