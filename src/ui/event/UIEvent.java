package ui.event;

import javafx.event.Event;
import logic.BoardLogic;

public abstract class UIEvent {
    BoardLogic boardLogic = new BoardLogic();
    public abstract void getEvent(Event event);
}
