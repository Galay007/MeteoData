public abstract class ChessPiece {
    private final String color;
    private Boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public Boolean getCheck() {
        return check;
    }
    public void setCheck(Boolean check) {
        this.check = false;
    }
    public String getColor() {
        return color;
    }

    abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    abstract String  getSymbol();

}
