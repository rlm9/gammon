/**
 * Created by Ruari on 26/03/2016.
 */
public class BearMove extends NormalMove {
//    public BearMove(int start,int end,Statas colour,int roll){
//        super(start,end);
//        this.colour=colour;
//        this.roll = roll;
//    }

    //Functionally the same to normal, different subclass for AI evaluation

    public BearMove(int start, int end, Statas colour,int roll){
        super(start,end,colour,roll);
    }
}
