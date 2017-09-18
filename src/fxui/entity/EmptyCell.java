package fxui.entity;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EmptyCell extends UIEntity {

    private Rectangle rectangle;

    @Override
    public Node createEntity() {
        rectangle = new Rectangle(50, 50, Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        return rectangle;
    }

}
