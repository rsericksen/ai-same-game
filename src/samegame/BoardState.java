package samegame;

import jaima.search.SearchAction;
import jaima.search.State;

import java.util.Map;

public class BoardState extends jaima.search.State {

	public int[] colors;

	public BoardState(String fromString) {
		super(fromString);
		
		colors = new int[fromString.length()];
		
		for(int i = 0; i < fromString.length(); i++){
			colors[i] = fromString.charAt(i);
		}
	}

	@Override
	public String toString() {
		String s = "";
		for(int color : colors){
			s += color;
		}
		
		return s;
	}
	
	@Override
	public jaima.search.State clone() {
		return new BoardState(toString());
	}
	
	public boolean isEmpty(){
		for(int color : colors){
			if(color != 0){
				return false;
			}
		}
		
		return true;
	}
	
	public Map<SearchAction, State> successors(){
		return null;
		
	}
	
	/**
	 * 
	 * @param n
	 * @return true if colors[n] has adjacent boxes of the same color
	 */
	public boolean isRemovable(int row, int column){
		int color = getColor(row, column);
		
		// color of -1 means it is an edge piece
		int leftColor = column > 0 ? getColor(row, column - 1) : -1;
		int rightColor = column < Math.sqrt(colors.length) ? getColor(row, column + 1) : -1;
		int upColor = row > 0 ? getColor(row - 1, column) : -1;
		int downColor = row < Math.sqrt(colors.length) ? getColor(row + 1, column) : -1;
		
		return color == leftColor || color == rightColor || color == upColor || color == downColor;
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return an integer value that corresponds to the color at specified row and column
	 */
	public int getColor(int row, int column){
		int i = (int) (column + (Math.sqrt(colors.length) * row));
		
		return colors[i];
	}
	
	/**
	 * @param n
	 * @return row of colors[n]
	 */
	public int getRow(int n){
		return (int) (n / Math.sqrt(colors.length));
	}
	
	/**
	 * 
	 * @param n
	 * @return column of colors[n]
	 */
	public int getColumn(int n){
		return (int) (n % Math.sqrt(colors.length));
	}

	

}
