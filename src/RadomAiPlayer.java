import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ruari on 29/03/2016.
 */
public class RadomAiPlayer extends Player {
    public RadomAiPlayer(Statas colour){
        super(colour);
    }
    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls,Triangle[] bord){
        Random random= new Random();
        int rnd = random.nextInt(posMoves.size());
        Move  move=posMoves.get(rnd);
        dissRolls(rolls);
        dissPossMoves(posMoves);
        System.out.println("ai steve is moving the "+move.start+ " piece to "+ move.end);
        rolls.remove(new Integer(move.roll));
        return posMoves.get(rnd);


    }
}
