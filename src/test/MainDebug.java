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

        System.out.println(boardLogic.isValidMove(board, board.getCell(2, 4)));
        boardLogic.placeCoin(board, board.getCell(2,4));
        System.out.println(board);
        boardLogic.placeCoin(board, board.getCell(4,5));
        System.out.println(board);
        System.out.println(PlayerStatus.getInstance().getTurn());
        boardLogic.placeCoin(board, board.getCell(5,2));
        System.out.println(board);
        boardLogic.placeCoin(board, board.getCell(1,4));
        System.out.println(board);
        boardLogic.placeCoin(board, board.getCell(4,6));
        System.out.println(board);
        boardLogic.placeCoin(board, board.getCell(6,1));
        System.out.println(board);
    }
}
