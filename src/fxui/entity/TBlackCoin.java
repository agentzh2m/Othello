package fxui.entity;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TBlackCoin extends UIEntity{
    @Override
    public Node createEntity() {
        Circle blackCircle = new Circle(0,0,25, Color.BLACK);
        blackCircle.setOpacity(OPACITY);
        return blackCircle;
    }
}
