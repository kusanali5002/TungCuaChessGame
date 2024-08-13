class Advisor extends Piece {
    public Advisor(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "resources/symbols/redadvisor.jpg" : "resources/symbols/blackadvisor.jpg");
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {

        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if (dx != 1 || dy != 1) return false;
        if (isRed) {
            if (newX < 7 || newX > 9 || newY < 3 || newY > 5) return false;
        } else {
            if (newX < 0 || newX > 2 || newY < 3 || newY > 5) return false;
        }
        return board.getPiece(newX, newY) == null || board.getPiece(newX, newY).isRed() != isRed;
    }
}