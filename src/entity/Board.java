package entity;

/**
 * Created by karn806 on 9/11/17.
 */
public class Board {

    private final int NROWS = 8;
    private final int NCOLS = 8;

    private Cell[][] board = new Cell[NROWS][NCOLS];

    public int getNROWS() {
        return NROWS;
    }

    public int getNCOLS() {
        return NCOLS;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Cell getCell(int x, int y){
        return board[y][x];
    }


}
