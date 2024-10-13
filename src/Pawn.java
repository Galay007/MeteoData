public class Pawn extends ChessPiece {


    public Pawn(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine > 7 || toLine < 0 || toColumn > 7 || toColumn < 0 || (chessBoard.board[toLine][toColumn] != null
                && chessBoard.board[toLine][toColumn].getColor().equals(chessBoard.nowPlayerColor()))) {
            return false;
        }
        if (chessBoard.nowPlayerColor().equals("White")) {
            return whiteCheck(chessBoard, line, column, toLine, toColumn);
        } else return blackCheck(chessBoard, line, column, toLine, toColumn);
    }

    @Override
    String getSymbol() {
        return "P";
    }

    private Boolean whiteCheck(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (chessBoard.board[toLine][toColumn] == null) {
            if (line == 1) {
                return line + 2 == toLine && column == toColumn;
            } else return line + 1 == toLine && column == toColumn;
        } else return column + 1 == toColumn && line + 1 == toLine;
    }

    private Boolean blackCheck(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (chessBoard.board[toLine][toColumn] == null) {
            if (line == 6) {
                return line - 2 == toLine && column == toColumn;
            } else return line - 1 == toLine && column == toColumn;
        } else return column - 1 == toColumn && line - 1 == toLine;
    }
}
