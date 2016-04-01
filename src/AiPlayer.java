import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ruari on 23/03/2016.
 */
public class AiPlayer extends Player {
    public String name="player";
    public String getName(){return name;}
    public AiPlayer(Statas colour) {
        super(colour);
    }

    //Default AI class, with methods used by some subclasses


    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls, Triangle[] bord) {
        int evaluation = 0, bigggest = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        Move bestMove = null;
        for (Move move : posMoves) {
            evaluation = 0;
           evaluation+=movePriorty(move,300,200,100);
            if (evaluation > bigggest) {
                bigggest = evaluation;
                bestMoves.clear();
                bestMoves.add(move);
                bestMove = move;
//            }else if(evaluation==bigggest){
//                bestMoves.add(move);
            }
            Random random = new Random();
            int rnd = random.nextInt(bestMoves.size());
            // bestMove=bestMoves.get(rnd);

        }
        return bestMove;
    }

    public int movePriorty(Move move, int w1, int w2, int w3) {

        if (move instanceof BearMove) {
            return w1;
        } else if (move instanceof TakeMove) {
            return w2;
        } else {
            return w3;
        }

    }
    public Move rndBest(ArrayList<Move> bestMoves){
        Random random= new Random();
        int rnd = random.nextInt(bestMoves.size());
        return bestMoves.get(rnd);

    }
    public int disFrom(int endpoint, int startPoint){
        return Math.abs(startPoint-endpoint);

    }
//    public int checkTotal(int max, int total){
//        if(max>total){
//            max=total;
//        }
//    }

}
