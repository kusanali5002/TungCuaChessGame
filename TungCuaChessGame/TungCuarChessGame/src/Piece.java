abstract class Piece {
    protected int x, y;
    protected boolean isRed;
    protected String imagePath;

    public Piece(int x, int y, boolean isRed, String imagePath){
        this.x = x;
        this.y = y;
        this.isRed = isRed;
        this.imagePath = imagePath;
    }

    public boolean isRed(){
        return isRed;
    }

    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract boolean isValidMove(int newX, int newY, Board board);


    public String getImagePath(){
        return imagePath;
    }
}