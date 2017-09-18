package fxui.entity;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TWhiteCoin extends UIEntity {
    @Override
    public Node createEntity() {
        Circle whiteCircle = new Circle(0,0,25, Color.WHITE);
        whiteCircle.setStroke(Color.BLACK);
        whiteCircle.setOpacity(OPACITY);
        return whiteCircle;
    }
}
