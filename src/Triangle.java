/**
 * Created by Ruari on 01/03/2016.
 */
public class Triangle {
    private int pieces;
    private boolean isWhite;
    private Statas status ;
    public boolean isWhite() {
        return isWhite;
    }

    public void setIsWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }
    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }
    public String getStats(){
        if(status==Statas.WHITE){
            return "W";
        }else if(status==Statas.RED){
            return "R";
        }else return "O";
    }
    public Statas getStatas(){
        return status;
    }
    public void remove(){
        pieces--;
    }
    public void add(){
        pieces++;
    }
    public void setStatus(Statas status){
        this.status=status;
    }

    public Triangle(int start){
        switch (start){

            case 1:
                pieces=2;
                status=Statas.WHITE;
                break;

            case 6:
                pieces=5;
                status=Statas.RED;
                break;
            case 8:
                pieces=3;
                status=Statas.RED;
                break;
            case 12:
                pieces=5;
                status=Statas.WHITE;
                break;
            case 13:
                pieces=5;
                status=Statas.RED;
                break;
            case 17:
                pieces=3;
                status=Statas.WHITE;
                break;
            case 19:
                pieces=5;
                status=Statas.WHITE;
                break;

            case 24:
                pieces=2;
                status=Statas.RED;
                break;
            default:
                pieces=0;
                status=Statas.EMPTY;
                break;

        }
    }

}
