import java.util.ArrayList;
import java.util.SimpleTimeZone;

/**
 * Created by Ruari on 24/03/2016.
 */
    public class Main {

        public static void main(String[] args){
          //  Player player_1=setPlayer(Statas.WHITE);
            Player player_1=new RadomAiPlayer(Statas.WHITE);
            Player player_2=new RadomAiPlayer(Statas.RED);
            Player curentPLayer=player_2;
            boolean game=true;
            Triangle[] board=setBoard();
            display(board);

            try {
                while (game) {
                    if (curentPLayer == player_1) {
                        curentPLayer = player_2;
                    } else curentPLayer = player_1;
                    game=curentPLayer.doMove(board);
                }
                System.out.println("congrats, " + curentPLayer.colour.getText() + " is the winner");
            }catch (Exception e ){
                e.printStackTrace();
                e.getMessage();
                System.out.println("wtf");
            }

        }
        public static Triangle[] setBoard(){
            Triangle[] bord=new Triangle[26];
            for(int i=0;i<=25;i++) {
                bord[i] = new Triangle(i);
            }
            return bord;
        }

        public static Player setPlayer(Statas colour){

            return new UserPlayer(colour);//in the actual code this would obviously ask for the type of player required, could be done via the gui if we wana fo that
        }
        public static void display(Triangle[] bord){
            int pieces;
            String colour;
            for(int i0 = 0;i0<4;i0++){
                System.out.println("");
            }
            System.out.println(bord[0].getPieces()+" bore off for red team ");
            for(int i = 0;i<26;i++){
                pieces = bord[i].getPieces();
                colour=bord[i].getStats();
                System.out.print("                                                "+i+"-->");
                for(int i2=0;i2<pieces;i2++){
                    System.out.print(colour);
                }
                System.out.print("   "+bord[i].getPieces());
                System.out.println();
            }
            System.out.println(bord[25].getPieces()+" bore off for white team ");
            System.out.println();
//            for(int i = 12;i>=0;i--){
//                pieces=bord[i].getPieces();
//                if(bord[i].isWhite()){
//                    colour="w";
//                }else {colour="R";}
//
//                for(int i2=0;i<=pieces;i++){
//                    System.out.println(colour);
//                }
//                for(int i3=pieces;i3<=12;i++){
//                    System.out.println("O");
//                }
//
//            }
        }
    }
