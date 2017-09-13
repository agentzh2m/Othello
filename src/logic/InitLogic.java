package logic;

import entity.Board;
import entity.Coin;
import entity.Color;

public class InitLogic {
    private final int CENTER_X = 4;
    private final int CENTER_Y = 4;
    public Board generateBoard(){
        Board board = new Board();
        board.getCell(CENTER_X, CENTER_Y).setCoin(new Coin(Color.WHITE));
        board.getCell(CENTER_X+1, CENTER_Y).setCoin(new Coin(Color.BLACK));
        board.getCell(CENTER_X, CENTER_Y+1).setCoin(new Coin(Color.BLACK));
        board.getCell(CENTER_X+1, CENTER_Y+1).setCoin(new Coin(Color.WHITE));
        return board;
    }
}
