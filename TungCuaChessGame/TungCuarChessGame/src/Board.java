import java.util.Random;

class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[10][9];
        initializePieces();
    }

    private void initializePieces() {

        board[9][4] = new King(9, 4, true);
        board[9][0] = new Rook(9, 0, true);
        board[9][8] = new Rook(9, 8, true);
        board[9][1] = new Knight(9, 1, true);
        board[9][7] = new Knight(9, 7, true);
        board[9][2] = new Bishop(9, 2, true);
        board[9][6] = new Bishop(9, 6, true);
        board[9][3] = new Advisor(9, 3, true);
        board[9][5] = new Advisor(9, 5, true);
        board[7][1] = new Cannon(7, 1, true);
        board[7][7] = new Cannon(7, 7, true);
        for (int i = 0; i < 9; i += 2) {
            board[6][i] = new Pawn(6, i, true);
        }

        board[0][4] = new King(0, 4, false);
        board[0][0] = new Rook(0, 0, false);
        board[0][8] = new Rook(0, 8, false);
        board[0][1] = new Knight(0, 1, false);
        board[0][7] = new Knight(0, 7, false);
        board[0][2] = new Bishop(0, 2, false);
        board[0][6] = new Bishop(0, 6, false);
        board[0][3] = new Advisor(0, 3, false);
        board[0][5] = new Advisor(0, 5, false);
        board[2][1] = new Cannon(2, 1, false);
        board[2][7] = new Cannon(2, 7, false);
        for (int i = 0; i < 9; i += 2) {
            board[3][i] = new Pawn(3, i, false);
        }
    }

    public Piece getPiece(int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 9) return null;
        return board[x][y];
    }

    public void movePiece(int oldX, int oldY, int newX, int newY) {
        Piece piece = board[oldX][oldY];
        board[newX][newY] = piece;
        board[oldX][oldY] = null;
        piece.move(newX, newY);
    }

    public boolean isGameOver() {
        boolean redKing = false;
        boolean blackKing = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                Piece piece = board[i][j];
                if (piece instanceof King) {
                    if (piece.isRed()) {
                        redKing = true;
                    } else {
                        blackKing = true;
                    }
                }
            }
        }
        return !redKing || !blackKing;
    }

    public boolean isRedWin() {
        return !isGameOver() || getKing(true) != null;
    }

    public Piece getKing(boolean isRed) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                Piece piece = board[i][j];
                if (piece instanceof King && piece.isRed() == isRed) {
                    return piece;
                }
            }
        }
        return null;
    }

    public void makeRandomMove(boolean isRed) {
        Random random = new Random();
        while (true) {
            int oldX = random.nextInt(10);
            int oldY = random.nextInt(9);
            Piece piece = getPiece(oldX, oldY);
            if (piece != null && piece.isRed() == isRed) {
                int newX = random.nextInt(10);
                int newY = random.nextInt(9);
                if (piece.isValidMove(newX, newY, this)) {
                    movePiece(oldX, oldY, newX, newY);
                    return;
                }
            }
        }
    }
}