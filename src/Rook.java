public class Rook extends ChessPiece {
    public Rook(String color) {
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
            } else return checkHorizontalMove(chessBoard, line, column, toColumn);
        } else if (toColumn == column) {
            if (Math.abs(toLine - line) == 1) {
                return true;
            } else return checkVerticalMove(chessBoard, column, line, toLine);
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "R";
    }

    private Boolean checkHorizontalMove(ChessBoard chessBoard, int line, int column, int toColumn) {
        if (toColumn - column > 0) {
            for (int j = column + 1; j <= toColumn - 1; j++) {
                if (chessBoard.board[line][j] != null) return false;
            }
        } else {
            for (int j = column - 1; j >= toColumn + 1; j--) {
                if (chessBoard.board[line][j] != null) return false;
            }
        }
        return true;
    }

    private Boolean checkVerticalMove(ChessBoard chessBoard, int column, int line, int toLine) {
        if (toLine - line < 0) {
            for (int i = line + 1; i <= toLine - 1; i++) {
                if (chessBoard.board[i][column] != null) return false;
            }
        } else {
            for (int i = line - 1; i >= toLine + 1; i--) {
                if (chessBoard.board[i][column] != null) return false;
            }
        }
        return true;
    }
}
