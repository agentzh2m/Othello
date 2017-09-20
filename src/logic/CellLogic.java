package logic;

import logic.entity.*;

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
        Coin currentTurn = PlayerStatus.getInstance().getTurn();
        Coin coin = board.getCell(x, y).getCoin();
        if(coin == null){
            flippableCell.clear();
            return true;
        }else if (!coin.getColor().equals(currentTurn)){
            flippableCell.add(board.getCell(x,y));
            return false;
        }else{
            resultCell.addAll(flippableCell);
            flippableCell.clear();
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
        for (int i = cell.getX()+1; i < board.getDIM(); i++) {
            if(matchingLogic(resultCell, flippableCell, i, cell.getY(), board)) break;
        }
        //checking left
        for (int i = cell.getX()-1; i >= 0; i--) {
            if(matchingLogic(resultCell, flippableCell, i, cell.getY(), board)) break;
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
        for (int i = cell.getY()+1; i < board.getDIM(); i++) {
            if(matchingLogic(resultCell, flippableCell, cell.getX(), i, board)) break;
        }
        //checking down
        for (int i = cell.getY()-1; i >= 0; i--) {
            if(matchingLogic(resultCell, flippableCell, cell.getX(), i, board)) break;
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
        int curX = cell.getX() - 1;
        int curY = cell.getY() - 1;
        while (curX >= 0 && curY >= 0) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board)) break;
            curX--;
            curY--;
        }
//        System.out.println("left up" + resultCell);
        //check diag right down
        curX = cell.getX() + 1;
        curY = cell.getY() + 1;
        while (curX < board.getDIM() && curY < board.getDIM()) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board)) break;
            curX++;
            curY++;
        }
//        System.out.println("right down" + resultCell);
        //checking diag left down
        curX = cell.getX() - 1;
        curY = cell.getY() + 1;
        while (curX >= 0 && curY < board.getDIM()) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board)) break;
            curX--;
            curY++;
        }
//        System.out.println("left down" + resultCell);
        //checking diag right up
        curX = cell.getX() + 1;
        curY = cell.getY() - 1;
        while (curX < board.getDIM() && curY >= 0) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board)) break;
            curX++;
            curY--;
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
