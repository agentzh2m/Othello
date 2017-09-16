package test;

import logic.entity.Board;
import logic.entity.PlayerStatus;
import logic.BoardLogic;
import logic.InitLogic;

public class MainDebug {

    public static void main(String[] args) {
        BoardLogic boardLogic = new BoardLogic();
        InitLogic initLogic = new InitLogic();

        Board board = initLogic.generateBoard();
        System.out.println(board);

        System.out.println(PlayerStatus.getInstance().getTurn());

        boardLogic.placeCoin(board, board.getCell(5,3));
        boardLogic.placeCoin(board, board.getCell(5,2));
        System.out.println(board);
    }
}
