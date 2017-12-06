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
     * Check for the border cell.
     * @param board
     * @param x
     * @param y
     * @param pos
     * @return false if there is no blank cell next to the next coin
     */
    private boolean checkBorder(Board board, int x, int y, int pos){
        int dim = board.getDIM();
        if (x != 0 || x != dim || y != 0 || y != dim){
            return false;
        }
        switch (pos) {
            // hori right
            case 1 :
                if (board.getCell(x+1,y).getCoin() == Coin.BLANK) {
                    return true;
                }
            // hori left
            case 2 :
                if (board.getCell(x-1,y).getCoin() == Coin.BLANK){
                    return true;
                }
            // ver up
            case 3 :
                if (board.getCell(x,y-1).getCoin() == Coin.BLANK){
                    return true;
                }
            // ver down
            case 4 :
                if (board.getCell(x,y+1).getCoin() == Coin.BLANK){
                    return true;
                }
            // diag left up
            case 5 :
                if (board.getCell(x-1,y-1).getCoin() == Coin.BLANK){
                    return true;
                }
            // diag right down
            case 6 :
                if (board.getCell(x+1,y+1).getCoin() == Coin.BLANK){
                    return true;
                }
            // diag left down
            case 7 :
                if (board.getCell(x-1,y+1).getCoin() == Coin.BLANK){
                    return true;
                }
            // diag right up
            case 8 :
                if (board.getCell(x+1,y-1).getCoin() == Coin.BLANK){
                    return true;
                }
        }
        return false;
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
    private boolean matchingLogic(List<Cell> resultCell, List<Cell> flippableCell, int x, int y, Board board, int pos){
        //same color
        Coin currentTurn = PlayerStatus.getInstance().getTurn();
        Coin coin = board.getCell(x, y).getCoin();
        if (coin == Coin.BLANK){
            System.out.println("1");
            flippableCell.clear();
            return true;
        } else if (coin != currentTurn){
            System.out.println("2");
            flippableCell.add(board.getCell(x,y));
            return false;
        } else if (coin == currentTurn){
            System.out.println("5");
            resultCell.addAll(flippableCell);
            flippableCell.clear();
            return true;
        }
        else if (!checkBorder(board, x, y, pos)){
            System.out.println("3");
            flippableCell.clear();
            return true;
        } else {
            System.out.println("4");
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
        List<Cell> resultCell = new ArrayList<>();
        List<Cell> flippableCell = new ArrayList<>();
        //checking right
        for (int i = cell.getX()+1; i < board.getDIM(); i++) {
            if(matchingLogic(resultCell, flippableCell, i, cell.getY(), board, 1)) break;
        }
        //checking left
        for (int i = cell.getX()-1; i >= 0; i--) {
            if(matchingLogic(resultCell, flippableCell, i, cell.getY(), board, 2)) break;
        }
//        System.out.println("hor-resultcell: "+resultCell);
//        System.out.println("hor-flip: "+flippableCell);
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
            if(matchingLogic(resultCell, flippableCell, cell.getX(), i, board, 3)) break;
        }
        //checking down
        for (int i = cell.getY()-1; i >= 0; i--) {
            if(matchingLogic(resultCell, flippableCell, cell.getX(), i, board, 4)) break;
        }
//        System.out.println("ver-resultcell: "+resultCell);
//        System.out.println("ver-flip: "+flippableCell);
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
            if (matchingLogic(resultCell, flippableCell, curX, curY, board, 5)) break;
            curX--;
            curY--;
        }
//        System.out.println("left up" + resultCell);
        //check diag right down
        curX = cell.getX() + 1;
        curY = cell.getY() + 1;
        while (curX < board.getDIM() && curY < board.getDIM()) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board, 6)) break;
            curX++;
            curY++;
        }
//        System.out.println("right down" + resultCell);
        //checking diag left down
        curX = cell.getX() - 1;
        curY = cell.getY() + 1;
        while (curX >= 0 && curY < board.getDIM()) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board,7)) break;
            curX--;
            curY++;
        }
//        System.out.println("left down" + resultCell);
        //checking diag right up
        curX = cell.getX() + 1;
        curY = cell.getY() - 1;
        while (curX < board.getDIM() && curY >= 0) {
            if (matchingLogic(resultCell, flippableCell, curX, curY, board,8)) break;
            curX++;
            curY--;
        }
//        System.out.println("diag-resultcell: "+resultCell);
//        System.out.println("diag-flip: "+flippableCell);
//        System.out.println("---------");
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