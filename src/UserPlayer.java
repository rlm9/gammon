import java.util.*;

/**
 * Created by Ruari on 24/03/2016.
 */
public class UserPlayer extends Player {
    public UserPlayer(Statas colour){
        super(colour);
    }
    public void dissPossMoves(ArrayList<Move> moves){
        System.out.println("you could make the following moves");
        for(Move move:moves){
            System.out.print(move.start + " to "+ move.end+" ");
            System.out.println(move.getClass().getSimpleName());
        }
    }

    public void dissRolls(ArrayList<Integer> rolls){
        System.out.println("Avalible Rolls for "+colour.getText()+":");
        for(Integer roll:rolls){
            System.out.println(roll);
        }
    }
    public Move evaluate(ArrayList<Move> posMoves, ArrayList<Integer> rolls,Triangle[] bord){
//        dissRolls(rolls);
//        dissPossMoves(posMoves);
        Move move =super.evaluate(posMoves,rolls,bord);
        if(move==null){
            System.out.println("Invalid Move Please Enter a valid Move");
        }
        return move;
    }

    public Move getMove(){
        String messErr ="Please enter a valid number";

        int start=getInt("Enter the starting point",messErr);
        int end=getInt("Etner the postion you wish to move to",messErr);
        Move move=new Move(start,end);

        return move;
    }
    public static int getInt(String mess,String messErr){
        Boolean error=true;
        int userIn=0;
        Scanner scan = new Scanner(System.in);
        while(error) {//loops until a valid number is entered
            try {

                System.out.println(mess);
                userIn= scan.nextInt();
                error=false;

            }catch (java.util.InputMismatchException e){
                System.out.println(messErr);
                scan.nextLine();//this makes scanner read next input again, as without it would keep returning the first number
            }

        }
        return userIn;
    }
}