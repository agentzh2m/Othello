package logic.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by karn806 on 9/11/17.
 */
public class Board {

    private final int DIM = 8;

    private Cell[][] board = new Cell[DIM][DIM];

    public int getDIM() {
        return DIM;
    }

//    public Cell[][] getBoard() {
//        return board;
//    }

    public Cell getCell(int x, int y){
        return board[y][x];
    }

    public void setCell(int x, int y, Cell cell){
        cell.setX(x);
        cell.setY(y);
        board[y][x] = cell;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < DIM; i++) {
            nums.add(i);
        }
        stringBuilder.append(" " + nums);
        stringBuilder.append("\n");
        int j = 0;
        for(Cell[] row: board){
            stringBuilder.append(j + Arrays.toString(row) + "\n");
            j++;
        }
        return stringBuilder.toString();
    }
}
