import java.util.ArrayList;

/**
 * Created by Ruari on 30/03/2016.
 */
public class AiAnd extends AiPlayer {
    public String name="And";
    public String getName(){return name;}
    public AiAnd(Statas colour){
        super(colour);
    }
    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls, Triangle[] bord) {
        int max=0;
        int total=0;
        Move best=null;

//Standard type priority and closer to start of board
        for(Move move:posMoves){
            total=0;

            total+=movePriorty(move,300,200,100);
            total+=disFrom(getEndIndex(colour.getopp()),move.start);

            if(total>max){
                best=move;
                max=total;
            }

        }
        rolls.remove(new Integer(best.roll));
        System.out.println("And is moving " + best.start + " to " + best.end);
        return best;
    }
}
