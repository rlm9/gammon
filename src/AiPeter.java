import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;

/**
 * Created by Ruari on 29/03/2016.
 */
public class AiPeter extends Player {
    public AiPeter(Statas colour){
        super(colour);
    }
    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls, Triangle[] bord) {
        Move bestMove = null;
        int count = 0;
        int evaluation = 0;
        int bigggest = 0;
        for (Move move : posMoves) {
            evaluation = 0;
            count++;
            evaluation += count*7;
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

            if (bord[move.start].getPieces() > 1) {
                evaluation += 40;
            }
            if (evaluation >= bigggest) {
                bigggest = evaluation;
                bestMove = move;
            }

        }

        return bestMove;
    }

}
