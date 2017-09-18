package fxui;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import logic.Pair;
import logic.entity.Board;
import logic.entity.PlayerStatus;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.BoardLogic;
import logic.InitLogic;

public class Controller {
    @FXML
    private Text turnText;

    @FXML
    private AnchorPane gameBoard;

    @FXML
    private Text scoreText;

    @FXML
    public void initialize() {
        GridPane gridPane = new GridPane();
        InitLogic initLogic = new InitLogic();
        BoardLogic boardLogic = new BoardLogic();
        Board board = initLogic.generateBoard();
        EntityFactory entityFactory = new EntityFactory();
        draw(board, boardLogic, entityFactory, gridPane);
    }

    private void draw(Board board, BoardLogic boardLogic, EntityFactory entityFactory, GridPane gridPane){
        System.out.println(board);
        for (int i = 0; i < board.getNROWS(); i++) {
            for (int j = 0; j < board.getNCOLS(); j++) {
                Rectangle rectangle = (Rectangle) entityFactory.getEnity("empty-cell").createEntity();
                Circle whiteCircle = (Circle) entityFactory.getEnity("white-coin").createEntity();
                Circle blackCircle = (Circle) entityFactory.getEnity("black-coin").createEntity();
                final int fi = i;
                final int fj = j;
                rectangle.setOnMouseEntered(event -> {
                    if(boardLogic.isValidMove(board, board.getCell(fj,fi))){
                        gridPane.getChildren().remove(rectangle);
                        Circle tempCircle = new Circle(0,0,25);
                        tempCircle.setOpacity(0.25);
                        if (PlayerStatus.getInstance().getTurn().equals(logic.entity.Color.WHITE)) {
                            tempCircle.setFill(Color.WHITE);
                            tempCircle.setStroke(Color.BLACK);
                        }
                        else{
                            tempCircle.setFill(Color.BLACK);
                            tempCircle.setStroke(Color.RED);
                        }
                        tempCircle.setOnMouseClicked(event1 -> {
                            boardLogic.placeCoin(board, board.getCell(fj, fi));
                            gameBoard.getChildren().remove(gridPane);
                            GridPane newGridPane = new GridPane();
                            if (boardLogic.isGameOver(board)){
                                logic.entity.Color winner = boardLogic.whoWin(board);
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "The Game has ended with " + winner + " winning do" +
                                                " you want to restart the game?",
                                        ButtonType.YES, ButtonType.NO);
                                alert.showAndWait();
                                if (alert.getResult() == ButtonType.YES) initialize();
                                else System.exit(0);
                            }else {
                                Pair<Integer, Integer> score = boardLogic.calculateScore(board);
                                scoreText.setText(String.format("White: %d | Black: %d", score.get_1(), score.get_2()));
                                draw(board, boardLogic, entityFactory, newGridPane);
                            }
                        });
                        tempCircle.setOnMouseExited(event1 -> {
                            gridPane.getChildren().remove(tempCircle);
                            Task<Void> task = new Task<Void>(){
                                @Override
                                protected Void call() throws Exception {
                                    try {
                                        Thread.sleep(250);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    return null;
                                }
                            };
                            task.setOnSucceeded(e -> {
                                gridPane.add(rectangle, fj, fi);
                            });
                            new Thread(task).start();
                        });
                        gridPane.add(tempCircle, fj, fi);
                    }

                });
                if (board.getCell(j,i).getCoin() == null) gridPane.add(rectangle, j, i);
                else if (board.getCell(j,i).getCoin().getColor().equals(logic.entity.Color.BLACK)) gridPane.add(blackCircle, j, i);
                else gridPane.add(whiteCircle, j, i);
            }
        }
        gameBoard.getChildren().add(gridPane);
        turnText.setText("Turn: " + PlayerStatus.getInstance().getTurn());

    }

}
