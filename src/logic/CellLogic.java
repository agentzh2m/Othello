package logic;

import entity.Board;
import entity.Cell;
import entity.Color;
import entity.PlayerStatus;

import java.util.ArrayList;
import java.util.List;

public class CellLogic {

    /**
     * check if the coin to be placed on cell is valid
     * @return true is valid false if not valid
     */
    public boolean isValidMove(Board board, Cell cell){

        return !(getHorizontal(board, cell).isEmpty()
                && getVertical(board, cell).isEmpty()
                && getDiag(board, cell).isEmpty());
    }

    /**
     * the logic share by all method
     * @param resultCell
     * @param flippableCell
     * @param x
     * @param y
     * @param board
     * @return true when the logic is resolve false is to keep running the logic
     */
    private boolean matchingLogic(List<Cell> resultCell, List<Cell> flippableCell, int x, int y, Board board){
        //same color
        Color currentTurn = PlayerStatus.getInstance().getTurn();
        Color color = board.getCell(x, y).getCoin().getColor();
        if(color == null){
            resultCell.clear();
            return true;
        }else if (!color.equals(currentTurn)){
            flippableCell.add(board.getCell(x,y));
            return false;
        }else{
            resultCell.addAll(flippableCell);
            return true;
        }
    }

    /**
     * get all valid horizontal flippable cells
     * @return all flippable cells
     */
    List<Cell> getHorizontal(Board board, Cell cell){
        //checking right
        List<Cell> resultCell = new ArrayList<>();
        List<Cell> flippableCell = new ArrayList<>();
        for (int i = cell.getX(); i < board.getNCOLS(); i++) {
            if (matchingLogic(resultCell, flippableCell, cell.getX(), i, board)) break;
        }
        //checking left
        for (int i = cell.getX(); i >= 0; i--) {
            if(matchingLogic(resultCell, flippableCell, cell.getX(), i, board)) break;
        }
        return resultCell;
    }

    /**
     * get all valid vertical flippable cells
     * @return
     */
    List<Cell> getVertical(Board board, Cell cell){
        List<Cell> resultCell = new ArrayList<>();
        List<Cell> flippableCell = new ArrayList<>();
        //checking up
        for (int i = cell.getY(); i < board.getNROWS(); i++) {
            if(matchingLogic(resultCell, flippableCell, i, cell.getY(), board)) break;
        }
        //checking down
        for (int i = cell.getY(); i >= 0; i--) {
            if(matchingLogic(resultCell, flippableCell, i, cell.getY(), board)) break;
        }
        return resultCell;
    }

    /**
     * get all valid diagonal flippable cells
     * @return
     */
    List<Cell> getDiag(Board board, Cell cell){
        List<Cell> resultCell = new ArrayList<>();
        List<Cell> flippableCell = new ArrayList<>();
        //checking diag left up
        int curX = cell.getX();
        int curY = cell.getY();
        while (curX >= 0 && curY < board.getNROWS()) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board)) break;
            curX--;
            curY++;
        }
        //check diag right down
        curX = cell.getX();
        curY = cell.getY();
        while (curX < board.getNCOLS() && curY >= 0) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board)) break;
            curX++;
            curY--;
        }
        //checking diag left down
        curX = cell.getX();
        curY = cell.getY();
        while (curX >= 0 && curY >= 0) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board)) break;
            curX--;
            curY--;
        }
        //checking diag right up
        curX = cell.getX();
        curY = cell.getY();
        while (curX < board.getNCOLS() && curY < board.getNROWS()) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board)) break;
            curX++;
            curY++;
        }

        return resultCell;
    }

    /**
     * concat all valid cells (hori, ver, diag)
     * @return
     */
    List<Cell> getAllIntersect(Board board, Cell cell){
        List<Cell> allCells = new ArrayList<>();
        allCells.addAll(getHorizontal(board, cell));
        allCells.addAll(getVertical(board, cell));
        allCells.addAll(getDiag(board, cell));
        return allCells;
    }
}
