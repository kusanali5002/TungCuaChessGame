class Bishop extends Piece {
    public Bishop(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "resources/symbols/redbishop.jpg" : "resources/symbols/blackbishop.jpg");
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);

        if (dx != 2 || dy != 2) return false;

        int blockX = (newX + x) / 2;
        int blockY = (newY + y) / 2;
        if (board.getPiece(blockX, blockY) != null) return false;

        if (isRed) {
            if (newX < 5) return false;
        } else {
            if (newX > 4) return false;
        }

        Piece targetPiece = board.getPiece(newX, newY);
        return targetPiece == null || targetPiece.isRed() != isRed;
    }
}