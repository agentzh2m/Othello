package ui;

import com.sun.tools.internal.ws.processor.model.Model;
import logic.BoardLogic;
import logic.InitLogic;
import logic.entity.Board;
import logic.entity.Cell;
import logic.entity.Coin;

import javax.swing.*;

/**
 * Created by karn806 on 9/18/17.
 */
public class Controller extends JFrame {

    BoardLogic boardLogic = new BoardLogic();
    InitLogic initLogic = new InitLogic();
    Board board = initLogic.generateBoard();

    ViewBoard viewBoard = new ViewBoard();

    JPanel p = viewBoard.p;
    JPanel menuBar = viewBoard.menuBar;
    JPanel fullPanel = viewBoard.fullPanel;
    CoinButton buttons[] = viewBoard.buttons;
    JButton resetBtn = viewBoard.resetBtn;

    ImageIcon B = viewBoard.B;
    ImageIcon W = viewBoard.W;


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

        int dimension = board.getDIM();

        menuBar.add(resetBtn);

        int ix = 0;
        int iy = 0;

        for (int i = 1; i <= dimension; i++) {
            buttons[i] = new CoinButton();
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
