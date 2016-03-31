import java.util.ArrayList;

/**
 * Created by Ruari on 30/03/2016.
 */
public class AiSteve extends AiPlayer {
    public String name="Steve";
    public String getName(){return name;}
    public AiSteve(Statas colour){
        super(colour);
    }
    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls, Triangle[] bord) {
        int max=0;
        int total=0;
        Move best=null;
        ArrayList<Move> bestMoves=new ArrayList<>();
        for(Move move:posMoves){
            total=evaluateValue(move,bord);
            if(total>max) {
//                bestMoves.clear();
//                bestMoves.add(move);
                best=move;
                max = total;
            }
//            } else if(total==max){
//                bestMoves.add(move);
//            }

        }
//        best=rndBest(bestMoves);
        rolls.remove(new Integer(best.roll));
        return best;
    }
    public int evaluateValue(Move move,Triangle[] bord){
        int total=0;

        if (move instanceof BearMove) {
            total+= 300;
        } else if (move instanceof TakeMove) {
            total += 200;
        } else {
            if (bord[move.end].getPieces() == 1) {
                total += 40;
            }
//            if (bord[move.start].getPieces() - 1 != 1) {
//                total += 40;
//            }
            total += 100;
        }
        if (bord[move.start].getPieces() - 1 != 1) {
            total += 40;
        }
        total-=disFrom(getEndIndex(colour.getopp()),move.start);
        return total;
    }

}

