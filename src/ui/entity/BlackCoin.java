package ui.entity;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.entity.Board;

public class BlackCoin extends UIEntity {
    @Override
    public Node createEntity() {
        Circle blackCircle = new Circle(0,0,25, Color.BLACK);
        blackCircle.setStroke(Color.RED);
        return blackCircle;
    }

}
