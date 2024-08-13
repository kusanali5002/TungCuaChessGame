import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {
    private final int tileSize = 64;
    private final int offsetX = 20;
    private final int offsetY = 20;
    private Board board;
    private int selectedX = -1;
    private int selectedY = -1;
    private boolean isRedTurn = true;
    private final MusicPlayer soundPlayer = new MusicPlayer();
    private boolean dragging = false;
    private int dragX = -1;
    private int dragY = -1;

    public BoardPanel() {
        board = new Board();
        setPreferredSize(new Dimension(tileSize * 9 + offsetX * 2, tileSize * 10 + offsetY * 2));
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = (e.getY() - offsetY) / tileSize;
                int y = (e.getX() - offsetX) / tileSize;
                if (x < 0 || x >= 10 || y < 0 || y >= 9) return;
                if (board.getPiece(x, y) != null && board.getPiece(x, y).isRed() == isRedTurn) {
                    selectedX = x;
                    selectedY = y;
                    dragging = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (dragging) {
                    int x = (e.getY() - offsetY) / tileSize;
                    int y = (e.getX() - offsetX) / tileSize;
                    if (x >= 0 && x < 10 && y >= 0 && y < 9) {
                        if (board.getPiece(x, y) == null || board.getPiece(x, y).isRed() != isRedTurn) {
                            Piece piece = board.getPiece(selectedX, selectedY);
                            if (piece.isValidMove(x, y, board)) {
                                if (board.getPiece(x, y) != null) {
                                    if (isRedTurn) {
                                        soundPlayer.playSound("resources/sounds/red_capture.wav", false);
                                    } else {
                                        soundPlayer.playSound("resources/sounds/black_capture.wav", false);
                                    }
                                } else {
                                    soundPlayer.playSound("resources/sounds/move.wav", false);
                                }
                                board.movePiece(selectedX, selectedY, x, y);
                                if (board.isGameOver()) {
                                    String message = board.isRedWin() ? "Red wins!" : "Black wins!";
                                    JOptionPane.showMessageDialog(BoardPanel.this, message);
                                    int response = JOptionPane.showOptionDialog(BoardPanel.this, "Game Over. Start a new game?", "Game Over",
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"New Game", "Exit"}, "New Game");
                                    if (response == JOptionPane.YES_OPTION) {
                                        board = new Board();
                                    } else {
                                        System.exit(0);
                                    }
                                } else {
                                    isRedTurn = !isRedTurn;
                                    if (!isRedTurn) {
                                        board.makeRandomMove(false);
                                        isRedTurn = true;
                                    }
                                }
                            }
                        }
                    }
                    selectedX = -1;
                    selectedY = -1;
                    dragging = false;
                    repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    dragX = e.getX();
                    dragY = e.getY();
                    repaint();
                }
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);
        if (dragging && selectedX != -1 && selectedY != -1) {
            drawDraggedPiece(g);
        }
    }

    private void drawBoard(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < 10; i++) {
            g.drawLine(offsetX, offsetY + i * tileSize, offsetX + 8 * tileSize, offsetY + i * tileSize);
        }
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 8) {
                g.drawLine(offsetX + i * tileSize, offsetY, offsetX + i * tileSize, offsetY + 9 * tileSize);
            } else {
                g.drawLine(offsetX + i * tileSize, offsetY, offsetX + i * tileSize, offsetY + 4 * tileSize);
                g.drawLine(offsetX + i * tileSize, offsetY + 5 * tileSize, offsetX + i * tileSize, offsetY + 9 * tileSize);
            }
        }

        g.drawLine(offsetX + 3 * tileSize, offsetY, offsetX + 5 * tileSize, offsetY + 2 * tileSize);
        g.drawLine(offsetX + 5 * tileSize, offsetY, offsetX + 3 * tileSize, offsetY + 2 * tileSize);
        g.drawLine(offsetX + 3 * tileSize, offsetY + 7 * tileSize, offsetX + 5 * tileSize, offsetY + 9 * tileSize);
        g.drawLine(offsetX + 5 * tileSize, offsetY + 7 * tileSize, offsetX + 3 * tileSize, offsetY + 9 * tileSize);
    }

    private void drawPieces(Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                Piece piece = board.getPiece(i, j);
                if (piece != null && !(dragging && i == selectedX && j == selectedY)) {
                    Image img = new ImageIcon(piece.getImagePath()).getImage();
                    g.drawImage(img, offsetX + j * tileSize - tileSize / 2, offsetY + i * tileSize - tileSize / 2, tileSize, tileSize, this);
                }
            }
        }
    }

    private void drawDraggedPiece(Graphics g) {
        Piece piece = board.getPiece(selectedX, selectedY);
        if (piece != null) {
            Image img = new ImageIcon(piece.getImagePath()).getImage();
            g.drawImage(img, dragX - tileSize / 2, dragY - tileSize / 2, tileSize, tileSize, this);
        }
    }
}