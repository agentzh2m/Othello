package ui;

import logic.entity.Cell;
import logic.entity.Color;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by karn806 on 9/18/17.
 */
public class CoinButton extends JButton implements ActionListener {

    private Main main;
    ImageIcon B, W;

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

    public CoinButton(Main main) {
        this.main = main;
        B = new ImageIcon(this.getClass().getResource("b.png"));
        W = new ImageIcon(this.getClass().getResource("w.png"));
        this.addActionListener(this);
    }

    public void refresh() {
        for (int i = 1; i <= 64; i++) {
            int rx = main.buttons[i].getIX();
            int ry = main.buttons[i].getIY();
//                System.out.println(rx + " " + ry);
            Cell cell = main.board.getCell(rx, ry);
            if (cell.getCoin() != null) {
                if (cell.getCoin().getColor().equals(Color.BLACK)) {
                    main.buttons[i].setIcon(B);
                } else if (cell.getCoin().getColor().equals(Color.WHITE)) {
                    main.buttons[i].setIcon(W);
                }
            } else {
                main.buttons[i].setIcon(null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//            System.out.println(x + " , " + y);
        Cell cell = main.board.getCell(x, y);
        if (main.boardLogic.isValidMove(main.board, cell)) {
            main.boardLogic.placeCoin(main.board, cell);
            System.out.println(main.board);
            refresh();
        } else {
            setIcon(null);
        }
    }
}
