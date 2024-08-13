class Cannon extends Piece {
    public Cannon(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "resources/symbols/redcannon.jpg" : "resources/symbols/blackcannon.jpg");
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {

        if (newX != x && newY != y) return false;
        if (newX == x) {
            int start = Math.min(y, newY) + 1;
            int end = Math.max(y, newY);
            int count = 0;
            for (int i = start; i < end; i++) {
                if (board.getPiece(x, i) != null) count++;
            }
            if (count > 1) return false;
            if (count == 1) return board.getPiece(newX, newY) != null && board.getPiece(newX, newY).isRed() != isRed;
            return board.getPiece(newX, newY) == null;
        } else {
            int start = Math.min(x, newX) + 1;
            int end = Math.max(x, newX);
            int count = 0;
            for (int i = start; i < end; i++) {
                if (board.getPiece(i, y) != null) count++;
            }
            if (count > 1) return false;
            if (count == 1) return board.getPiece(newX, newY) != null && board.getPiece(newX, newY).isRed() != isRed;
            return board.getPiece(newX, newY) == null;
        }
    }
}