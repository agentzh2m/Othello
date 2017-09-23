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

        Controller controller = new Controller();
        JPanel p = controller.p;

        setSize(650, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setSize(550, 550);
        p.setLayout(new GridLayout(8, 8));

        controller.initializeBoardUI();

        setVisible(true);

    }
}
