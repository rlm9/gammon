import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CORBA.REBIND;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Ruari on 23/03/2016.
 */
public abstract class Player {
    public Move move;
    public Statas colour;
    public Triangle[] bord;
    public Player(Statas colour){
        this.colour=colour;
    }
    public int boreOff;

    public boolean doMove(Triangle[] bord)throws Exception{

        ArrayList<Move> possMoves;
        int roll1=roll(),roll2=roll();
        Move move=null;
        ArrayList<Integer> rolls = roll(roll1,roll2);

        int size=rolls.size();
        for(int i =0;i<size;i++) {
            possMoves = getPossMove(bord, rolls);
            while (move == null && !possMoves.isEmpty()) {
                move = evaluate(possMoves, rolls,bord);
            }
            if (move != null) {
                move.execute(bord);
                Main.display(bord);
                move = null;
            }
        }
        if(bord[getEndIndex(colour)].getPieces()==15){
            return false;
        }
        return true;
    }
    public void dissPossMoves(ArrayList<Move> moves){
        for(Move move:moves){
            System.out.println(move.start + " to "+ move.end);
        }
    }

    public void dissRolls(ArrayList<Integer> rolls){
        for(Integer roll:rolls){
            System.out.println(roll);
        }
    }
    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls,Triangle[] bord){
        Move move =getMove();
        for(Move possMove:posMoves){
            if(move.start==possMove.start&&move.end==possMove.end){
                move=possMove;
                rolls.remove(new Integer(move.roll));
                move.valid=true;
                return move;
            }
        }
        return null;
    }
    public Move getMove(){return null;}

    public ArrayList<Move> getPossMove(Triangle[] bord,ArrayList<Integer> rolls){
        Move move;
        int end;
        ArrayList<Move> posMove= new ArrayList<>();
        Set<Integer> uniqueRolls=new HashSet<Integer>(rolls);

        for(int start=1;start<25;start++){
            if(bord[start].getStatas()==colour){
                for(Integer roll: uniqueRolls) {

                    end = (start) + (colour.getDir() * roll);
                    move=checkRule(start,end,roll,bord);
                    if(move!=null){
                        posMove.add(move);
                    }
                }
            }
        }
        if(posMove.isEmpty()){
            System.out.println("There were no Valid moves for "+ colour.getText());
        }
        return posMove;
    }
    public Move checkRule(int start,int end,int roll,Triangle[] bord){
        boolean allHomeBord=true;
        if(end>=0&&end<=25) {
            if (bord[end].getStatas() == colour.getopp()) {
                if (bord[end].getPieces() == 1) {
                    return new TakeMove(start, end, colour, roll);
                } else {
                    return null;
                }
            } else {
                if (end == getEndIndex(colour)) {
                    for (int i = getEndIndex(colour.getopp()) + colour.getDir(), count = 0; count < 18; count++, i += colour.getDir()) {
                      //  System.out.println(count+" "+i);
                        if (bord[i].getStatas() == colour) {
                          //  System.out.println(count+"  "+i);
                            allHomeBord = false;
                            break;
                        }
                    }
                    if (allHomeBord) {
                        return new BearMove(start, end, colour, roll);
                    } else {
                        return null;
                    }
                } else {
                    return new NormalMove(start, end, colour, roll);
                }
            }
        }else {
            return null;
        }

    }

    public ArrayList<Integer> roll(int roll1, int roll2){
        ArrayList<Integer> rolls=new ArrayList<>();
        if(roll1==roll2){
            for(int i=0;i<4;i++){
                rolls.add(roll1);
            }
        }else {
            rolls.add(roll1);
            rolls.add(roll2);
        }

        return rolls;
    }

    public static int roll(){
        return (int)((Math.random()*6)+1);
    }
    public int getEndIndex(Statas colour){
        return (int)(12.5*(colour.getDir()+1));
    }





}
