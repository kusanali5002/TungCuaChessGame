class Rook extends Piece {

    public Rook(int x, int y, boolean isRed){
        super(x, y, isRed, isRed ? "resources/symbols/redrook.jpg" : "resources/symbols/blackrook.jpg");
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        if (newX != x && newY != y) return false;
        if (newX == x) {
            int start = Math.min(y, newY) + 1;
            int end = Math.max(y, newY);
            for (int i = start; i < end; i++) {
                if (board.getPiece(x, i) != null) return false;
            }
        } else {
            int start = Math.min(x, newX) + 1;
            int end = Math.max(x, newX);
            for (int i = start; i < end; i++) {
                if (board.getPiece(i, y) != null) return false;
            }
        }
        return board.getPiece(newX, newY) == null || board.getPiece(newX, newY).isRed() != isRed;
    }
}