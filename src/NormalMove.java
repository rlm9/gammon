/**
 * Created by Ruari on 26/03/2016.
 */
public class NormalMove extends Move {
    public NormalMove(int start, int end,Statas colour,int roll){
        super(start,end);
        this.colour=colour;
        this.roll=roll;
    }
    //Finished a standard move after super

    public void execute(Triangle[] bord){
        super.execute(bord);
        Triangle triangle=bord[end];
        triangle.add();
        triangle.setStatus(colour);

    }
    public void  undo(Triangle[] bord){
        super.undo(bord);
        Triangle triangle=bord[end];
        triangle.remove();
        if(triangle.getPieces()==0){
            triangle.setStatus(Statas.EMPTY);

        }
    }

}

