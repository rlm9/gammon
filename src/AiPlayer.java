import java.util.ArrayList;

/**
 * Created by Ruari on 23/03/2016.
 */
public class AiPlayer extends Player {
    public AiPlayer(Statas colour) {
        super(colour);
    }

    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls,Triangle[] bord) {
        int evaluation=0,bigggest=0;
        Move bestMove=null;
        for(Move move:posMoves){
            evaluation=0;
            if(move instanceof BearMove){
                evaluation+=300;
            }else if(move instanceof TakeMove){
                evaluation+=200;
            }else {
                evaluation+=100;
            }
            if(evaluation>bigggest){
                bigggest=evaluation;
                bestMove=move;
            }
        }

        return bestMove;
    }
}
