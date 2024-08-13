class King extends Piece {

    public King(int x, int y, boolean isRed){
        super(x, y, isRed, isRed ? "resources/symbols/redking.jpg" : "resources/symbols/blackking.jpg");
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board){
        if (newX < 0 || newX > 9 || newY < 0 || newY > 8) return false;
        if (isRed) {
            if (newX < 7 || newX > 9 || newY < 3 || newY > 5) return false;
        }
        else {
            if (newX < 0 || newX > 2 || newY < 3 || newY > 5) return false;
        }
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        return dx + dy == 1;
    }
}