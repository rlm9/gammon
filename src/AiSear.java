import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ruari on 30/03/2016.
 */
public class AiSear extends AiSteve {
    public String name="Sear";
    public String getName(){return name;}
    public AiSear(Statas colour){
        super(colour);
    }
//    public boolean doMove(Triangle[] bord)throws Exception {
//
//        ArrayList<Move> possMoves=null;
//        int roll1 = roll(), roll2 = roll();
//        Move move = null;
//        ArrayList<Integer> rolls = roll(roll1, roll2);
//        possMoves=getPossMove(bord,rolls);
//        move = evaluate(possMoves, rolls, bord);
//        if (move != null) {
//            move.execute(bord);
//            Main.display(bord);
////                move = null;
//        }
//
//        if (bord[getEndIndex(colour)].getPieces() == 15) {
//            return false;
//        }
//        return true;
//    }
    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls, Triangle[] bord) {
        int max=0;
        int total=0;
        Move best=null;
        ArrayList<Move> bestMoves=new ArrayList<>();
        for(Move move:posMoves) {

            total+=evaluateValue(move,bord);
            //Triangle[] newBord=bord.clone();
            Triangle[] newBord= Arrays.copyOf(bord,bord.length);
            move.execute(newBord);
            rolls.remove(new Integer(move.roll));
            if(!rolls.isEmpty()) {
                Move followMove = evaluate(getPossMove(newBord, rolls), rolls, newBord);
                if(followMove!=null) {
                    total += evaluateValue(followMove, newBord);
                }
            }
            rolls.add((move.roll));

            if(total>max) {
//                bestMoves.clear();
//                bestMoves.add(move);
                best=move;
                max = total;
            }

        }
        rolls.remove(new Integer(best.roll));
        return best;
    }
//    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls, Triangle[] bord) {
//        int max=0;
//        int total=0;
//        Move best=null;
//        ArrayList<Move> bestMoves=new ArrayList<>();
//        for(Move move:posMoves){
//            total=evaluateValue(move,bord);
//            Triangle[] newBord=bord.clone();
//            move.execute(newBord);
//            rolls.remove(new Integer(move.roll));
//            for(Move folowMove:getPossMove(newBord,rolls)){
//                total+=evaluateValue(folowMove,bord);
//            }
//            if(total>max) {
////
//                best=move;
//                max = total;
//            }
//
//        }
////        best=rndBest(bestMoves);
//        rolls.remove(new Integer(best.roll));
//        return best;
//    }
}
