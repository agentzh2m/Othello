package ui;

import com.sun.tools.internal.ws.processor.model.Model;
import fxui.EntityFactory;
import javafx.scene.layout.GridPane;
import logic.BoardLogic;
import logic.InitLogic;
import logic.entity.Board;
import logic.entity.Cell;
import logic.entity.Coin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by karn806 on 9/18/17.
 */
public class Controller extends JFrame{

    private final JPanel p;
    private final JPanel menuBar;
    private final JPanel fullPanel;
    private final CoinButton[] buttons;
    private final JButton resetBtn;
    //    BoardLogic boardLogic = new BoardLogic();
//    InitLogic initLogic = new InitLogic();
//    Board board = initLogic.generateBoard();
//
    ViewBoard viewBoard = new ViewBoard();
//    JButton resetBtn = viewBoard.resetBtn;
//
//    JPanel p;
//
//    JPanel menuBar = viewBoard.menuBar;
//    JPanel fullPanel = viewBoard.fullPanel;
//    CoinButton buttons[] = viewBoard.buttons;
//    JButton resetBtn = viewBoard.resetBtn;
//
//    ImageIcon B = viewBoard.B;
//    ImageIcon W = viewBoard.W;

    ImageIcon B = new ImageIcon(this.getClass().getResource("b.png"));
    ImageIcon W = new ImageIcon(this.getClass().getResource("w.png"));

    public Controller(JPanel p, JPanel menuBar, JPanel fullPanel, JButton resetBtn, CoinButton[] buttons) {
        this.p = p;
        this.menuBar = menuBar;
        this.fullPanel = fullPanel;
        this.resetBtn = resetBtn;
        this.buttons = buttons;

    }


    public boolean checkColor(Cell cell) {
        if (cell.getCoin() == Coin.BLACK) {
            return true;
        } else if (cell.getCoin() == Coin.WHITE) {
            return false;
        }
        return false;
    }

    public int newRow(int i, int x) {
        if (i != 1 && i % 8 == 0) {
            x++;
        }
        return x;
    }

    public int newCol(int i, int y) {
        if (i == 1) {
            y = 1;
        } else if (y != 0 && y % 7 == 0) {
            y = 0;
        } else {
            y++;
        }
        return y;
    }

    public void initializeBoardUI() {

        InitLogic initLogic = new InitLogic();
        Board board = initLogic.generateBoard();

        menuBar.add(resetBtn);


        draw(board);

    }

    public void refresh(Board board, CoinButton[] buttons) {
        for (int i = 1; i <= 64; i++) {
            int rx = buttons[i].getIX();
            int ry = buttons[i].getIY();
//                System.out.println(rx + " " + ry);
            Cell cell = board.getCell(rx, ry);
            if (cell.getCoin() != null) {
                if (cell.getCoin() == Coin.BLACK) {
                    buttons[i].setIcon(B);
                } else if (cell.getCoin() == Coin.WHITE) {
                    buttons[i].setIcon(W);
                }
            } else {
                buttons[i].setIcon(null);
            }
        }
    }

    public void draw(Board board){

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeBoardUI();
            }
        });

        int ix = 0;
        int iy = 0;

        for (int i = 1; i <= 64; i++) {
            buttons[i] = new CoinButton(board);
            buttons[i].setX(iy);
            buttons[i].setY(ix);
            Cell cell = board.getCell(ix, iy);
            if (cell.getCoin() != Coin.BLANK) {
                // true - black
                // false - white
                if (checkColor(cell)) {
                    buttons[i].setIcon(B);
                } else {
                    buttons[i].setIcon(W);
                }
            }
            p.add(buttons[i]);
            ix = newRow(i, ix);
            iy = newCol(i, iy);

        }
//        getContentPane().add(p);
        fullPanel.add(menuBar);
        fullPanel.add(p);
        add(fullPanel);
    }

}
