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
            Color turn = PlayerStatus.getInstance().getTurn();
            //flip all the flippable
            cell.setCoin(new Coin(turn));
            List<Cell> flippableCells = cellLogic.getAllIntersect(board, cell);
            for(Cell fCells: flippableCells){
                Cell currentCell = board.getCell(fCells.getX(), fCells.getY());
                currentCell.getCoin().flip();
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
        for (int i = 0; i < board.getNROWS(); i++) {
            for (int j = 0; j < board.getNCOLS(); j++) {
                if (board.getCell(j,i).getCoin() == null) blankCells++;
            }
        }
        return blankCells == 0;
    }

    /**
     * check the winner when the game ends
     * @param board
     * @return the color that is the winner
     */
    public Color whoWin(Board board){
        Pair<Integer, Integer> pair = calculateScore(board);
        int whiteCells = pair.get_1();
        int blackCells = pair.get_2();
        return whiteCells > blackCells ? Color.WHITE : Color.BLACK;
    }

    public Pair<Integer, Integer> calculateScore(Board board){
        int whiteCells = 0;
        int blackCells = 0;
        for (int i = 0; i < board.getNROWS(); i++) {
            for (int j = 0; j < board.getNCOLS(); j++) {
                if (board.getCell(j,i).getCoin() != null){
                    if (board.getCell(j,i).getCoin().getColor().equals(Color.WHITE)) whiteCells++;
                    else blackCells++;
                }
            }
        }
        return new Pair<>(whiteCells, blackCells);
    }
}
