import javax.swing.*;

public class TungCuaChess {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Kusanali's Chinese Chess Game");
        BoardPanel boardPanel = new BoardPanel();
        frame.add(boardPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}