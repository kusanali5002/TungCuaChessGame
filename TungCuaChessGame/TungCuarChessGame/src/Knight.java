class Knight extends Piece {
    public Knight(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "resources/symbols/redknight.jpg" : "resources/symbols/blackknight.jpg");
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);


        if (dx * dy != 2) return false;

        if (dx == 2) {
            int blockX = (newX + x) / 2;
            if (board.getPiece(blockX, y) != null) return false;
        } else if (dy == 2) {
            int blockY = (newY + y) / 2;
            if (board.getPiece(x, blockY) != null) return false;
        }

        Piece targetPiece = board.getPiece(newX, newY);
        return targetPiece == null || targetPiece.isRed() != isRed;
    }
}