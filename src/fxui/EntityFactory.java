package fxui;

import javafx.scene.layout.GridPane;
import fxui.entity.*;

import java.util.HashMap;
import java.util.Map;

public class EntityFactory {
    GridPane gridPane = new GridPane();
    public UIEntity getEnity(String entityType){
        Map<String, UIEntity> entities = new HashMap<String, UIEntity>(){{
           put("empty-cell", new EmptyCell());
           put("black-coin", new BlackCoin());
           put("white-coin", new WhiteCoin());
           put("t-white-coin", new TWhiteCoin());
           put("t-black-coin", new TBlackCoin());
        }};
        return entities.get(entityType);
    }
}
