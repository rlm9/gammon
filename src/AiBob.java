import java.util.ArrayList;

/**
 * Created by Ruari on 30/03/2016.
 */
public class AiBob extends AiPlayer {
    public String name="Bob";
    public String getName(){return name;}
    public AiBob(Statas colour){
        super(colour);
    }
    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls, Triangle[] bord) {
        int max=0;
        int total=0;
        Move best=null;
        dissRolls(rolls);
        dissPossMoves(posMoves);
        for(Move move:posMoves){
            total=0;

            total+=movePriorty(move,300,200,100);
            total-=disFrom(getEndIndex(colour.getopp()),move.start);
            total+=disFrom(getEndIndex(colour.getopp()),move.end);
            if(total>max){
                best=move;
                max=total;
            }

        }
        rolls.remove(new Integer(best.roll));
        return best;
    }
}
