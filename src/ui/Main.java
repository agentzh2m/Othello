package ui;

import entity.Board;
import entity.Cell;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.BoardLogic;
import logic.InitLogic;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    InitLogic initLogic = new InitLogic();
    Board board = initLogic.generateBoard();

    int nCol = board.getNCOLS();
    int nRow = board.getNROWS();
    int dimension = nCol*nRow;

    JPanel p = new JPanel();
    CoinButton buttons[] = new CoinButton[dimension+1];


    public static void main(String args[]){
        new Main();
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

    public Main(){
        super("Othello");
        setSize(550,550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(nRow,nCol));

        int x = 0;
        int y = 0;

        for(int i = 1; i <= dimension; i++){
            buttons[i] = new CoinButton();
            buttons[i].setX(x);
            buttons[i].setY(y);
            p.add(buttons[i]);

            Cell cell = new Cell();
            board.setCell(x,y,cell);

            x = newRow(i,x);
            y = newCol(i,y);

        }
        add(p);

        setVisible(true);
    }
}
