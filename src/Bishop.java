public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine > 7 || toLine < 0 || toColumn > 7 || toColumn < 0 || (chessBoard.board[toLine][toColumn] != null
                && chessBoard.board[toLine][toColumn].getColor().equals(chessBoard.nowPlayerColor()))) {
            return false;
        }
        if (Math.abs(toColumn - column) == Math.abs(toLine - line)) {
            if (Math.abs(toColumn - column) == 1) {
                return true;
            } else if (toColumn - column > 1 && toLine - line > 1) {
                return northEast(chessBoard, line, column, toLine, toColumn);
            } else if (toColumn - column < 1 && toLine - line > 1) {
                return northWest(chessBoard, line, column, toLine, toColumn);
            } else if (toColumn - column < 1 && toLine - line < 1) {
                return southWest(chessBoard, line, column, toLine, toColumn);
            } else if (toColumn - column > 1 && toLine - line < 1) {
                return southEast(chessBoard, line, column, toLine, toColumn);
            }
            return false;
        }
        return false;
    }

    private Boolean northEast(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int i = line + 1;
        int j = column + 1;
        while (i <= toLine - 1 && j <= toColumn - 1) {
            if (chessBoard.board[i][j] != null) return false;
            i++;
            j++;
        }
        return true;
    }

    private Boolean northWest(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int i = line + 1;
        int j = column - 1;
        while (i <= toLine - 1 && j >= toColumn + 1) {
            if (chessBoard.board[i][j] != null) return false;
            i++;
            j--;
        }
        return true;
    }

    private Boolean southWest(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int i = line - 1;
        int j = column - 1;
        while (i >= toLine + 1 && j >= toColumn + 1) {
            if (chessBoard.board[i][j] != null) return false;
            i--;
            j--;
        }
        return true;
    }

    private Boolean southEast(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int i = line - 1;
        int j = column + 1;
        while (i >= toLine + 1 && j <= toColumn - 1) {
            if (chessBoard.board[i][j] != null) return false;
            i--;
            j++;
        }
        return true;
    }

    @Override
    String getSymbol() {
        return "B";
    }
}
