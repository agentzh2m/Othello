package ui;

import entity.Board;
import entity.PlayerStatus;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.BoardLogic;
import logic.InitLogic;

import java.awt.event.MouseEvent;

public class Controller {
    @FXML
    private Text turnText;

    @FXML
    private AnchorPane gameBoard;

    @FXML
    public void initialize() {
        GridPane gridPane = new GridPane();
        InitLogic initLogic = new InitLogic();
        BoardLogic boardLogic = new BoardLogic();
        Board board = initLogic.generateBoard();
        for (int i = 0; i < board.getNROWS(); i++) {
            for (int j = 0; j < board.getNCOLS(); j++) {
                Rectangle rectangle = new Rectangle(50, 50, Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                Circle whiteCircle = new Circle(0,0,25, Color.WHITE);
                whiteCircle.setStroke(Color.BLACK);
                Circle blackCircle = new Circle(0,0,25, Color.BLACK);
                blackCircle.setStroke(Color.RED);
                final int fi = i;
                final int fj = j;
                rectangle.setOnMouseEntered(event -> {
                    if(boardLogic.isValidMove(board, board.getCell(fj,fi))){
                        gridPane.getChildren().remove(rectangle);
                        Circle tempCircle = new Circle(0,0,25);
                        tempCircle.setOnMouseExited(event1 -> {
                            gridPane.getChildren().remove(tempCircle);
                            gridPane.add(rectangle, fj, fi);
                            gridPane.setGridLinesVisible(true);
                        });
                        tempCircle.setOpacity(0.25);
                        if (PlayerStatus.getInstance().getTurn().equals(entity.Color.WHITE)) {
                            tempCircle.setFill(Color.WHITE);
                            tempCircle.setStroke(Color.BLACK);
                        }
                        else{
                            tempCircle.setFill(Color.BLACK);
                            tempCircle.setStroke(Color.RED);
                        }
                        gridPane.add(tempCircle, fj, fi);
                    }

                });
                if (board.getCell(i,j).getCoin() == null) gridPane.add(rectangle, j, i);
                else if (board.getCell(i,j).getCoin().getColor().equals(entity.Color.WHITE)) gridPane.add(blackCircle, j, i);
                else gridPane.add(whiteCircle, j, i);
            }
        }
        gameBoard.getChildren().add(gridPane);
        turnText.setText("Turn: " + PlayerStatus.getInstance().getTurn());
    }

}
