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
      //  this.colour=colour;
      //  this.valid=false;

      /////  this.roll=roll;
    }
    public boolean validateMove(ArrayList<Move> posMoves, ArrayList<Integer> rolls){
        for(Move move:posMoves){
            if(move.start==start&&move.end==end){
            }
        }

//        if(bord[start].getStatas()!=colour){
//            return false;
//        }
//        int diff= ((end-start)*colour.getDir());
//        if(!rolls.contains(diff)){
//            return false;
//        }
//        rolls.remove(diff);
//        if(!(bord[end].getStatas()==colour||bord[end].getStatas()==Statas.EMPTY)){
//            if(bord[end].getPieces()!=1){
//                return false;
//            }else {
//
//            }
//        }else {
//
//        }
//        return true;
            return true;
    }
    public void execute(Triangle[] bord){
        Triangle triangle=bord[start];
        triangle.remove();
        if(triangle.getPieces()==0){
            triangle.setStatus(Statas.EMPTY);
        }

    }
    public void undo(Triangle[] bord){
        Triangle triangle=bord[start];
        triangle.add();
        triangle.setStatus(colour);


    }
    public int getEdgeIndex(){
        return (int)(1+(11.5*(colour.getDir()+1)));
    }

}
