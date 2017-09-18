package ui;

import entity.*;
import logic.BoardLogic;
import logic.InitLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame{

    BoardLogic boardLogic = new BoardLogic();
    InitLogic initLogic = new InitLogic();
    Board board = initLogic.generateBoard();

    JPanel p = new JPanel();
    JPanel menuBar = new JPanel();
    JPanel fullPanel = new JPanel();
    CoinButton buttons[] = new CoinButton[65];
    JButton resetBtn = new JButton("Reset");

    public class CoinButton extends JButton implements ActionListener{

        ImageIcon B,W;

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

        public CoinButton(){
            B = new ImageIcon(this.getClass().getResource("/images/b.png"));
            W = new ImageIcon(this.getClass().getResource("/images/w.png"));
            this.addActionListener(this);
        }

        public void refresh() {
            for (int i = 1; i <= 64; i++) {
                int rx = buttons[i].getIX();
                int ry = buttons[i].getIY();
//                System.out.println(rx + " " + ry);
                Cell cell = board.getCell(rx,ry);
                if (cell.getCoin()!=null){
                    if (cell.getCoin().getColor().equals(entity.Color.BLACK)){
                        buttons[i].setIcon(B);
                    } else if (cell.getCoin().getColor().equals(entity.Color.WHITE)) {
                        buttons[i].setIcon(W);
                    }
                } else {
                    buttons[i].setIcon(null);
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

//            System.out.println(x + " , " + y);
            Cell cell = board.getCell(x,y);
            if (boardLogic.isValidMove(board, cell)){
                boardLogic.placeCoin(board, cell);
                System.out.println(board);
                refresh();
            } else {
                setIcon(null);
            }
        }
    }

    ///////////////////////


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

    public void initializeBoardUI(Board board) {

        int nCol = board.getNCOLS();
        int nRow = board.getNROWS();
        int dimension = nCol*nRow;


        menuBar.add(resetBtn);

        ImageIcon B = new ImageIcon(this.getClass().getResource("/images/b.png"));
        ImageIcon W = new ImageIcon(this.getClass().getResource("/images/w.png"));

        int ix = 0;
        int iy = 0;

        for(int i = 1; i <= dimension; i++){
            buttons[i] = new CoinButton();
            buttons[i].setX(iy);
            buttons[i].setY(ix);
            Cell cell = board.getCell(ix,iy);
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
            ix = newRow(i,ix);
            iy = newCol(i,iy);

        }
//        getContentPane().add(p);
        fullPanel.add(menuBar);
        fullPanel.add(p);
        add(fullPanel);
    }

    public Main(){
        super("Othello");
        setSize(650,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setSize(550,550);
        p.setLayout(new GridLayout(8,8));

        initializeBoardUI(board);

        setVisible(true);
    }
}
