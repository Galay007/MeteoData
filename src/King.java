public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine > 7 || toLine < 0 || toColumn > 7 || toColumn < 0 || (chessBoard.board[toLine][toColumn] != null
                && chessBoard.board[toLine][toColumn].getColor().equals(chessBoard.nowPlayerColor()))) {
            return false;
        }
        if (toLine == line) {
            if (Math.abs(toColumn - column) == 1) {
                return true;
            }
        } else if (toColumn == column) {
            if (Math.abs(toLine - line) == 1) {
                return true;
            }
        }
        if (Math.abs(toColumn - column) == 1 && Math.abs(toLine - line) == 1) {
            return true;
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "K";
    }

    public Boolean isUnderAttack() {
        return false;
    }
}
