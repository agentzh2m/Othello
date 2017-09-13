package logic;

import entity.Board;
import entity.Cell;
import entity.Coin;
import entity.Color;

public class BoardLogic {
    CellLogic cellLogic = new CellLogic();
    /**
     * check if that cell can be placed with a coin
     * @param cell
     * @return
     */
    boolean isValidMove(Board board, Cell cell){
        return cellLogic.isValidMove(board, cell);
    }

    void placeCoin(Board board, Cell cell, Color turn){
        if(isValidMove(board, cell)){
            //flip all the flippable
            cell.setCoin(new Coin(turn));
            cellLogic.getAllIntersect(board, cell);
        }
    }
}
