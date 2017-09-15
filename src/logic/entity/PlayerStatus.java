package logic.entity;

public class PlayerStatus {
    private static PlayerStatus ourInstance = new PlayerStatus();

    public static PlayerStatus getInstance() {
        return ourInstance;
    }

    /**
     * If true player A turn if false player B turn
     */
    private Color turn = Color.WHITE;

    public void switchTurn() {
        if (turn.equals(Color.BLACK)) turn = Color.WHITE;
        else turn = Color.BLACK;
    }

    public Color getTurn() {
        return turn;
    }

    private PlayerStatus() {
    }
}
