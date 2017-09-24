package ui;

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

        p.setSize(550, 550);
        p.setLayout(new GridLayout(8, 8));
        setSize(650, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controller.initializeBoardUI();

        setVisible(true);

    }
}
