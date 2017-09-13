package entity;

/**
 * Created by karn806 on 9/11/17.
 */

public class Coin {
    Color color;

    public Coin(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void flip(){
        if (color.equals(Color.BLACK)) color = Color.WHITE;
        else color = Color.BLACK;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
