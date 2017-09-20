package logic.entity;

public class PlayerStatus {
    private static PlayerStatus ourInstance = new PlayerStatus();

    public static PlayerStatus getInstance() {
        return ourInstance;
    }

    /**
     * If true player A turn if false player B turn
     */
    private Coin turn = Coin.WHITE;

    public void switchTurn() {
        if (turn.equals(Coin.BLACK)) turn = Coin.WHITE;
        else turn = Coin.BLACK;
    }

    public Coin getTurn() {
        return turn;
    }

    private PlayerStatus() {
    }
}
