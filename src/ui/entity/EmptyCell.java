package ui.entity;

import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.entity.Board;
import logic.entity.PlayerStatus;

public class EmptyCell extends UIEntity {

    private Rectangle rectangle;

    @Override
    public Node createEntity() {
        rectangle = new Rectangle(50, 50, Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        return rectangle;
    }

}
