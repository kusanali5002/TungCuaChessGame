class Pawn extends Piece {
    public Pawn(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "resources/symbols/redpawn.jpg" : "resources/symbols/blackpawn.jpg");
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if (dx + dy != 1) return false;
        if (isRed) {
            if (newX > x) return false;
            if (x >= 5 && newY != y) return false;
        } else {
            if (newX < x) return false;
            if (x <= 4 && newY != y) return false;
        }
        return board.getPiece(newX, newY) == null || board.getPiece(newX, newY).isRed() != isRed;
    }
}