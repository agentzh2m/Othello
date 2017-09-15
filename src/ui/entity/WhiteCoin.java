package ui.entity;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.entity.Board;

public class WhiteCoin extends UIEntity {

    @Override
    public Node createEntity() {
        Circle whiteCircle = new Circle(0,0,25, Color.WHITE);
        whiteCircle.setStroke(Color.BLACK);
        return whiteCircle;
    }
}
