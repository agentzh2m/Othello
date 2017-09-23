package ui;

import logic.entity.Cell;
import logic.entity.Coin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by karn806 on 9/18/17.
 */
public class CoinButton extends JButton implements ActionListener {

    Controller controller;

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
        for (int i = 1; i <= 64; i++) {
            int rx = controller.buttons[i].getIX();
            int ry = controller.buttons[i].getIY();
//                System.out.println(rx + " " + ry);
            Cell cell = controller.board.getCell(rx, ry);
            if (cell.getCoin() != null) {
                if (cell.getCoin() == Coin.BLACK) {
                    controller.buttons[i].setIcon(controller.B);
                } else if (cell.getCoin() == Coin.WHITE) {
                    controller.buttons[i].setIcon(controller.W);
                }
            } else {
                controller.buttons[i].setIcon(null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//            System.out.println(x + " , " + y);
        Cell cell = controller.board.getCell(x, y);
        if (controller.boardLogic.isValidMove(controller.board, cell)) {
            controller.boardLogic.placeCoin(controller.board, cell);
            System.out.println(controller.board);
            refresh();
        } else {
            setIcon(null);
        }
    }
}
