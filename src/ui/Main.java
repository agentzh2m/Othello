package ui;


import logic.entity.*;
import logic.BoardLogic;
import logic.InitLogic;
import logic.entity.Coin;

import javax.swing.*;
import java.awt.*;


public class Main extends JFrame {

    public static void main(String args[]) {
        new Main();
    }

    public Main() {
        super("Othello");

        JPanel p = new JPanel();
        JPanel menuBar = new JPanel();
        JPanel fullPanel = new JPanel();
        JButton resetBtn = new JButton();
        CoinButton[] buttons = new CoinButton[8 * 8 + 1];
        Controller controller = new Controller(p, menuBar, fullPanel, resetBtn, buttons);

        setSize(650, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controller.initializeBoardUI();

        setVisible(true);

    }
}
