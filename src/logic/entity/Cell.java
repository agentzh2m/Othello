package logic.entity;

/**
 * Created by karn806 on 9/11/17.
 */
public class Cell {

    /**
     * cell coordinate
     */
    private int x;
    private int y;

    private Coin coin = Coin.BLANK;

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void flip() {
        if (coin == Coin.BLACK) coin = Coin.WHITE;
        else coin = Coin.BLACK;
    }

    @Override
    public String toString() {
        String coordinate = String.format("(%d, %d)", x, y);
        if(coin == Coin.BLANK) return " ";
        else if(coin == Coin.WHITE) return "W";
        return "B";
    }
}
