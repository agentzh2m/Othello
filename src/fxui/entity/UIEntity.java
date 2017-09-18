package fxui.entity;

import javafx.scene.Node;
import logic.BoardLogic;

public abstract class UIEntity {

    private BoardLogic boardLogic = new BoardLogic();
    public final double OPACITY = 0.25;

    public abstract Node createEntity();
}
