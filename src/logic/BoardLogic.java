package logic;

import logic.entity.*;

import java.util.List;

public class BoardLogic {
    private CellLogic cellLogic = new CellLogic();
    /**
     * check if that cell can be placed with a coin
     * @param cell
     * @return true if the move is valid false otherwise
     */
    public boolean isValidMove(Board board, Cell cell){
        return cellLogic.isValidMove(board, cell);
    }

    public void placeCoin(Board board, Cell cell){
        if(isValidMove(board, cell)){
            Coin turn = PlayerStatus.getInstance().getTurn();
            //flip all the flippable
            cell.setCoin(turn);
            List<Cell> flippableCells = cellLogic.getAllIntersect(board, cell);
            for(Cell fCells: flippableCells){
                Cell currentCell = board.getCell(fCells.getX(), fCells.getY());
                currentCell.flip();
                board.setCell(fCells.getX(), fCells.getY(), currentCell);
                System.out.println();
            }
            PlayerStatus.getInstance().switchTurn();
            System.out.println("coin is place");
        }
    }

    /**
     * Check whether the game has ended or not
     * @param board
     * @return true if the game is already over false if it is not over yet
     */
    public boolean isGameOver(Board board){
        int blankCells = 0;
        int isValid = 0;
        for (int i = 0; i < board.getDIM(); i++) {
            for (int j = 0; j < board.getDIM(); j++) {
                if (board.getCell(j,i).getCoin() == Coin.BLANK) blankCells++;
                if (isValidMove(board,board.getCell(j,i))) isValid++;
            }
        }
        System.out.println("is valid = "+isValid);
        return blankCells == 0 | isValid == 0;
    }

    /**
     * check the winner when the game ends
     * @param board
     * @return the color that is the winner
     */
    public Coin whoWin(Board board){
        Pair<Integer, Integer> pair = calculateScore(board);
        int whiteCells = pair.get_1();
        int blackCells = pair.get_2();
        return whiteCells > blackCells ? Coin.WHITE : Coin.BLACK;
    }

    /**
     * find the score in the board
     * @param board
     * @return a Pair between white and black coins
     */
    public Pair<Integer, Integer> calculateScore(Board board){
        int whiteCells = 0;
        int blackCells = 0;
        for (int i = 0; i < board.getDIM(); i++) {
            for (int j = 0; j < board.getDIM(); j++) {
                if (board.getCell(j,i).getCoin() != Coin.BLANK){
                    if (board.getCell(j,i).getCoin() == Coin.WHITE) whiteCells++;
                    else blackCells++;
                }
            }
        }
        return new Pair<>(whiteCells, blackCells);
    }
}
