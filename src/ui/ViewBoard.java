package ui;

import javax.swing.*;

/**
 * Created by karn806 on 9/18/17.
 */
public class ViewBoard extends JFrame {


    int dim = 8;

//    public ViewBoard(int dim) {
//        this.dim = dim;
//    }

    JPanel p = new JPanel();

    JPanel menuBar = new JPanel();
    JPanel fullPanel = new JPanel();
    CoinButton buttons[] = new CoinButton[dim * dim + 1];
    JButton resetBtn = new JButton("Reset");

    ImageIcon B = new ImageIcon(this.getClass().getResource("b.png"));
    ImageIcon W = new ImageIcon(this.getClass().getResource("w.png"));
}
