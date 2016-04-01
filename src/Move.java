import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by Ruari on 24/03/2016.
 */
public class Move {
    public int start;
    public int end;
    public Statas colour;
    public int type;//should be replaced with enum, 1 is take, 2 notaml,3 is bearing off
    public boolean valid;
    public int roll;

    public Move(int start, int end){
        this.start=start;
        this.end=end;

    }
//default method executed by all kinds of moves
    public void execute(Triangle[] bord){
        Triangle triangle=bord[start];
        triangle.remove();
        if(triangle.getPieces()==0){
            triangle.setStatus(Statas.EMPTY);
        }

    }
    //code foor unfinsihed sear AI
    public void undo(Triangle[] bord){
        Triangle triangle=bord[start];
        triangle.add();
        triangle.setStatus(colour);


    }
    //Returns 1 or 24 depending on colour
    public int getEdgeIndex(){
        return (int)(1+(11.5*(colour.getDir()+1)));
    }

}
