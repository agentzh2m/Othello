package logic;

import entity.*;

import java.util.List;

public class BoardLogic {
    CellLogic cellLogic = new CellLogic();
    /**
     * check if that cell can be placed with a coin
     * @param cell
     * @return
     */
    public boolean isValidMove(Board board, Cell cell){
        return cellLogic.isValidMove(board, cell);
    }

    public void placeCoin(Board board, Cell cell){
        if(isValidMove(board, cell)){
            Color turn = PlayerStatus.getInstance().getTurn();
            //flip all the flippable
            cell.setCoin(new Coin(turn));
            List<Cell> flippableCells = cellLogic.getAllIntersect(board, cell);
            for(Cell fCells: flippableCells){
                Cell currentCell = board.getCell(fCells.getX(), fCells.getY());
                currentCell.getCoin().flip();
                board.setCell(fCells.getX(), fCells.getY(), currentCell);
                System.out.println();
            }
            PlayerStatus.getInstance().switchTurn();
            System.out.println("coin is place");
        }
    }
}
