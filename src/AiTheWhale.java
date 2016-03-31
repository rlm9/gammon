import java.util.ArrayList;

/**
 * Created by Ruari on 30/03/2016.
 */
public class AiTheWhale extends AiPlayer {
    public String name="TheWhale";
    public String getName(){return name;}
    public AiTheWhale(Statas colour){
        super(colour);
    }
    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls, Triangle[] bord) {
        int max=0;
        int total=0;
        Move best=null;
        ArrayList<Move> bestMoves=new ArrayList<>();
        for(Move move:posMoves){
            total=0;

            total+=movePriorty(move,300,200,100);
            total+=disFrom(getEndIndex(colour.getopp()),move.end);
            total+=disFrom(getEndIndex(colour.getopp()),move.start);

            if(total>max){
                bestMoves.clear();
                bestMoves.add(move);
                max=total;
            } else if(total==max){
                bestMoves.add(move);
            }

        }
        best=rndBest(bestMoves);
        rolls.remove(new Integer(best.roll));
        return best;
    }
}
