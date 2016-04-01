/**
 * Created by Ruari on 26/03/2016.
 */
public class TakeMove extends Move{
    public TakeMove(int start, int end,Statas colour,int roll){
        super(start,end);
        this.colour=colour;
        this.roll=roll;
    }
    public void execute(Triangle[] bord){
       super.execute(bord);
        Triangle triangle=bord[end];
        triangle.setStatus(colour);
       // triangle=bord[(int)(12.5*(colour.getDir()+1))];
        int bordEdge= getEdgeIndex();//gets the index of the starting point for each
        for(int i= bordEdge;;i-=colour.getDir()) {
            if(bord[i].getStatas()!=colour){
                bord[i].add();
                bord[i].setStatus(colour.getopp());
                break;
            }
        }

    }
    public void undo(Triangle[] bord){
        super.execute(bord);
        Triangle triangle=bord[end];
        triangle.setStatus(colour.getopp());
        int bordEdge= getEdgeIndex();//gets the index of the starting point for each
        for(int i= bordEdge;;i-=colour.getDir()) {
            if(bord[i].getStatas()!=colour){
                bord[i].remove();
               if(bord[i].getPieces()==0){
                   bord[i].setStatus(Statas.EMPTY);
               }
                break;
            }
        }
    }

}