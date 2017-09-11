package logic;

import java.util.List;

/**
 * Created by karn806 on 9/11/17.
 */
public class Cell {

    /**
     * cell coordinate
     */
    int x;
    int y;

    Coin coin;

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    /**
     * check if the coin to be placed on cell is valid
     * @return
     */
    boolean checkValidMove(){

        return true;
    }

    /**
     * get all valid horizontal flippable cells
     * @return
     */
    List<Cell> getHorizontal(){
        return null;
    }

    /**
     * get all valid vertical flippable cells
     * @return
     */
    List<Cell> getVertical(){
        return null;
    }

    /**
     * get all valid diagonal flippable cells
     * @return
     */
    List<Cell> getDiag(){
        return null;
    }

    /**
     * concat all valid cells (hori, ver, diag)
     * @return
     */
    List<Cell> getAllIntersect(){
        return null;
    }
}
