package logic;

import logic.entity.Board;
import logic.entity.Cell;
import logic.entity.Coin;

public class InitLogic {
    private final int CENTER_X = 3;
    private final int CENTER_Y = 3;
    public Board generateBoard(){
        Board board = new Board();
        for(int i = 0; i < board.getDIM(); i++){
            for (int j = 0; j < board.getDIM(); j++) {
                board.setCell(i,j, new Cell());
            }
        }
        board.getCell(CENTER_X, CENTER_Y).setCoin(Coin.WHITE);
        board.getCell(CENTER_X+1, CENTER_Y).setCoin(Coin.BLACK);
        board.getCell(CENTER_X, CENTER_Y+1).setCoin(Coin.BLACK);
        board.getCell(CENTER_X+1, CENTER_Y+1).setCoin(Coin.WHITE);
        return board;
    }

}
