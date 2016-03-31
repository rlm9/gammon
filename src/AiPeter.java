import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ruari on 29/03/2016.
 */
public class AiPeter extends Player {
    public String name="peter";
    public AiPeter(Statas colour){
        super(colour);
    }
    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls, Triangle[] bord) {
        Move bestMove = null;
        ArrayList<Move> bestMoves=new ArrayList<>();
        int count = 0;
        int evaluation = 0;
        int bigggest = 0;
        for (Move move : posMoves) {
            evaluation = 0;
//            count+=colour.getDir();
//            evaluation += Math.abs(count);
            if (move instanceof BearMove) {
                evaluation += 300;
            } else if (move instanceof TakeMove) {
                evaluation += 200;
            } else {
                evaluation += 100;
                if (bord[move.end].getPieces() == 1) {
                    evaluation += 40;
                }
            }
            if (bord[move.start].getPieces() == 1) {
                evaluation += 40;
            }

            if (evaluation >  bigggest) {
                bigggest = evaluation;
                bestMoves.clear();
                bestMoves.add(move);

            }else if(evaluation==bigggest){
                bestMoves.add(move);
            }

        }

        Random random= new Random();
        int rnd = random.nextInt(bestMoves.size());
        bestMove=bestMoves.get(rnd);
        rolls.remove(new Integer(bestMove.roll));
        return bestMove;
    }

}
