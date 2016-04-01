import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SimpleTimeZone;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Ruari on 24/03/2016.
 */
    public class Main {
    public static String mess;

    //gets player

    public static void main(String[] args) {
        String [] options1={"ai", "user", "online"};
        //Sets colour and priority either hardcoded or changed by an online player
        Player player_1 = setPlayer(1,Statas.RED,options1);
        String[] options2={"ai", "user"};
        Player player_2 = setPlayer(2, player_1.colour.getopp(),options2);

        Player curentPLayer=null;
        if(player_1.firstPlayer){
            curentPLayer=player_2;
        }else {
            curentPLayer=player_1;
        }

        boolean game = true;
        Triangle[] board = setBoard();
        display(board);


        //plays until a player reports finished
            try {
                while (game) {
                    if (curentPLayer == player_1) {
                        curentPLayer = player_2;
                    } else curentPLayer = player_1;
                    game = curentPLayer.doMove(board);
                }
                System.out.println("congrats, " + curentPLayer.colour.getText() + " is the winner");
                if (curentPLayer == player_1) {
                    curentPLayer = player_2;
                } else curentPLayer = player_1;
                if(curentPLayer instanceof OnlinePlayer){
                    ((OnlinePlayer) curentPLayer).won();
                }

            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
                System.out.println("An unforeseen problem has occurred");
            }



    }

    //Sets up the board
    public static Triangle[] setBoard() {
        Triangle[] bord = new Triangle[26];
        for (int i = 0; i <= 25; i++) {
            bord[i] = new Triangle(i);
        }
        return bord;
    }

    //Allows user to select type of player
    public static Player setPlayer(int num,Statas colour,String[] types) {
        String type;
        type = getString("What is Player " + num + "?", types,"Please Enter a Valid type");
        if(type.equals("user")){
            return new UserPlayer(colour);
        }else if (type.equals("online")){
            return new OnlinePlayer(colour);
        }else if(type.equals("ai")){
            String aiType = "";
            String[] ai = {"and", "bob", "noah", "steve", "thewhale"};
            aiType = getString("Which AI is Player " + num + "?" , ai, "Please enter a valid AI");
            if(aiType.equals("And")){
                return new AiAnd(colour);
            }else if(aiType.equals("bob")){
                return new AiBob(colour);
            }else if(aiType.equals("noah")){
                return new AiNoah(colour);
            }else if(aiType.equals("steve")){
                return new AiSteve(colour);
            }else if(aiType.equals("thewhale")){
                return new AiTheWhale(colour);
            }
        }

        return new UserPlayer(colour);
    }

    //Used for validating input
    public static String getString(String mess, String[] poss,String errMess){
        Boolean error=true;
        String userIn = "";
        Scanner scan = new Scanner(System.in);
        while(error) {//loops until a valid number is entered
            try {
                System.out.println(mess);
                for(String pos:poss){
                    System.out.print(pos + " ");
                }
                System.out.println();
                userIn= scan.nextLine();
                if(userIn.equals("exit")){
                    System.exit(1);
                }
                for(String pos:poss){
                    if(pos.equals(userIn.toLowerCase())){
                        error=false;
                    }
                }

            }catch (java.util.InputMismatchException e){
                System.out.println(e);
                scan.nextLine();//this makes scanner read next input again, as without it would keep returning the first number
            }

        }
        return userIn.toLowerCase();
    }

//Class for displaying the board
    public static void display(Triangle[] board) {

        final String borderString = "l";
        //Prints the border of the board
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        int pieces;
        String colour;

        //Draws top border
        for (int i = 0; i <= 49; i++) {
            System.out.print("_");
        }
        System.out.println();
        //Begins to draw the actual board, have to print backwards
        for (int i = 13; i < 25; i++) {
            pieces = board[i].getPieces();
            colour = board[i].getStats();
            if (i == 13) {
                System.out.print("|");
            }
            System.out.print(" " + i + " ");
            if (i == 24) {
                System.out.print("|");
            }
        }

        int[] arrayCopy = new int[board.length];
        //Creates a new array which tells us how many pieces are on which place
        for (int i = 1; i < board.length; i++) {
            arrayCopy[i] = board[i].getPieces();
        }
        //Find max value of counters on a single place
        int max = 0;
        for (int i = 1; i < arrayCopy.length-1; i++) {
            if (arrayCopy[i] > max) {
                max = arrayCopy[i];
            }
        }
        System.out.println();
        //Draws the top half of the board
        for (int io = 0; io < max; io++) {
            System.out.print("|");
            for (int i = 13; i < 25; i++) {
                if (arrayCopy[i] > 0) {
                    if (board[i].getStatas() == Statas.RED) {
                        System.out.print(" R  ");
                    } else if (board[i].getStatas() == Statas.WHITE) {
                        System.out.print(" W  ");
                    } else {
                    }
                    arrayCopy[i]--;
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println("|");
        }
        for (int i = 0; i <= 49; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int io = 0; io < max; io++) {
            for (int i = 12; i > 0; i--) {
                if (i == 12) {
                    System.out.print("|");
                }
                if (arrayCopy[i] > 0 && (max - arrayCopy[i] == io)) {
                    if (board[i].getStatas() == Statas.RED) {
                        System.out.print(" R  ");
                    } else if (board[i].getStatas() == Statas.WHITE) {
                        System.out.print(" W  ");
                    }
                    arrayCopy[i]--;
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println("|");
        }
        System.out.print("|");
        for (int i = 12; i > 0; i--) {
            if (i < 10) {
                System.out.print(" " + i + "  ");
            } else {
                System.out.print(" " + i + " ");
            }

        }
        System.out.print("|");
        System.out.println();
        for (int i = 0; i <= 49; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}

