package ui;

import entity.Board;
import logic.BoardLogic;
import logic.InitLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by karn806 on 9/17/17.
 */
public class CoinButton extends JButton implements ActionListener {
    ImageIcon B,W;

    int x;
    int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    byte value=0;
      /*
      0:nothing
      1: black
      2: white
      */

    public CoinButton(){
        B = new ImageIcon(this.getClass().getResource("/images/b.png"));
        W = new ImageIcon(this.getClass().getResource("/images/w.png"));
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(x + " , " + y);
        value++;
        value%=3;
        switch(value){
            case 0:
                setIcon(null);
                break;
            case 1:
                setIcon(W);
                break;
            case 2:
                setIcon(B);
                break;
        }
    }
}
