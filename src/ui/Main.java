package ui;

import entity.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.BoardLogic;
import logic.InitLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame{

    BoardLogic boardLogic = new BoardLogic();
    InitLogic initLogic = new InitLogic();
    private Board board = initLogic.generateBoard();
    private PlayerStatus playerStatus;

    int nCol = board.getNCOLS();
    int nRow = board.getNROWS();
    int dimension = nCol*nRow;

    JPanel p = new JPanel();
    CoinButton buttons[] = new CoinButton[dimension+1];

    public class CoinButton extends JButton implements ActionListener{
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

        public void refresh() {
            for (int i = 1; i <= 64; i++) {

            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println(x + " , " + y);
            Cell cell = board.getCell(x,y);
            if (boardLogic.isValidMove(board, cell)){
//                cell.getCoin().flip();
                boardLogic.placeCoin(board, cell);
//                if (playerStatus.getTurn().equals(entity.Color.WHITE)){
//                    setIcon(W);
//                } else {
//                    setIcon(B);
//                }
            } else {
                setIcon(null);
            }
//            value++;
//            value%=3;
//            switch(value){
//                case 0:
//                    setIcon(null);
//                    break;
//                case 1:
//                    setIcon(W);
//                    break;
//                case 2:
//                    setIcon(B);
//                    break;
//            }
        }
    }


    public static void main(String args[]){
        new Main();
    }

    public boolean checkColor(Cell cell){
        if (cell.getCoin().getColor().equals(entity.Color.BLACK)){
            return true;
        } else if (cell.getCoin().getColor().equals(entity.Color.WHITE)) {
            return false;
        }
        return false;
    }

    public int newRow(int i, int x){
        if (i!=1 && i%8==0){
            x++;
        }
        return x;
    }

    public int newCol(int i, int y){
        if (i==1){
            y = 1;
        }
        else if (y!=0 && y%7==0){
            y = 0;
        } else {
            y++;
        }
        return y;
    }

    public void initializeBoardUI() {
        ImageIcon B = new ImageIcon(this.getClass().getResource("/images/b.png"));
        ImageIcon W = new ImageIcon(this.getClass().getResource("/images/w.png"));

        int x = 0;
        int y = 0;

        for(int i = 1; i <= dimension; i++){
            buttons[i] = new CoinButton();
            buttons[i].setX(x);
            buttons[i].setY(y);
            Cell cell = board.getCell(x,y);
            if (cell.getCoin()!=null){
                // true - black
                // false - white
                if (checkColor(cell)){
                    buttons[i].setIcon(B);
                } else {
                    buttons[i].setIcon(W);
                }
            }
            p.add(buttons[i]);
            x = newRow(i,x);
            y = newCol(i,y);

        }
        add(p);
    }

    public Main(){
        super("Othello");
        setSize(550,550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(nRow,nCol));

        initializeBoardUI();

        setVisible(true);
    }
}
