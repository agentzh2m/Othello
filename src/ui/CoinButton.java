package ui;

import logic.BoardLogic;
import logic.entity.Board;
import logic.entity.Cell;
import logic.entity.Coin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by karn806 on 9/18/17.
 */
public class CoinButton extends JButton implements ActionListener {

    BoardLogic boardLogic = new BoardLogic();
    Controller controller;
    Board board;

    public CoinButton(Board board) {
        this.board = board;
    }


    int x;
    int y;


    public int getIX() {
        return x;
    }

    public int getIY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void refresh() {
        controller.refresh(board);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//            System.out.println(x + " , " + y);
        Cell cell = board.getCell(x, y);
        if (boardLogic.isValidMove(board, cell)) {
            boardLogic.placeCoin(board, cell);
            System.out.println(board);
            refresh();
        } else {
            setIcon(null);
        }
    }
}
