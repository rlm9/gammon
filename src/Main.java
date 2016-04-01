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
    public static final Logger log = Logger.getLogger(Main.class.getName());
    public static FileHandler fh;

    public static void main(String[] args) {
        //  Player player_1=setPlayer(Statas.WHITE);dfde
        //Player online=new OnlinePlayer(Statas.RED);
        String [] options1={"ai", "user", "online"};
        Player player_1 = setPlayer(1,Statas.RED,options1);
        String[] options2={"ai", "user"};
        Player player_2 = setPlayer(2, player_1.colour.getopp(),options2);
//            Player player_2= new AiSteve(Statas.RED);
//            Player player_1= new UserPlayer(Statas.WHITE);
//        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
//            @Override
//            public void write(int b) {

//            }
//        }) {
//            @Override
//            public void flush() {
//            }
//
//            @Override
//            public void close() {
//            }
//
//            @Override
//            public void write(int b) {
//            }
//
//            @Override
//            public void write(byte[] b) {
//            }
//
//            @Override
//            public void write(byte[] buf, int off, int len) {
//            }
//
//            @Override
//            public void print(boolean b) {
//            }
//
//            @Override
//            public void print(char c) {
//            }
//
//            @Override
//            public void print(int i) {
//            }
//
//            @Override
//            public void print(long l) {
//            }
//
//            @Override
//            public void print(float f) {
//            }
//
//            @Override
//            public void print(double d) {
//            }
//
//            @Override
//            public void print(char[] s) {
//            }
//
//            @Override
//            public void print(String s) {
//            }
//
//            @Override
//            public void print(Object obj) {
//            }
//
//            @Override
//            public void println() {
//            }
//
//            @Override
//            public void println(boolean x) {
//            }
//
//            @Override
//            public void println(char x) {
//            }
//
//            @Override
//            public void println(int x) {
//            }
//
//            @Override
//            public void println(long x) {
//            }
//
//            @Override
//            public void println(float x) {
//            }
//
//            @Override
//            public void println(double x) {
//            }
//
//            @Override
//            public void println(char[] x) {
//            }
//
//            @Override
//            public void println(String x) {
//            }
//
//            @Override
//            public void println(Object x) {
//            }
//
//            @Override
//            public java.io.PrintStream printf(String format, Object... args) {
//                return this;
//            }
//
//            @Override
//            public java.io.PrintStream printf(java.util.Locale l, String format, Object... args) {
//                return this;
//            }
//
//            @Override
//            public java.io.PrintStream format(String format, Object... args) {
//                return this;
//            }
//
//            @Override
//            public java.io.PrintStream format(java.util.Locale l, String format, Object... args) {
//                return this;
//            }
//
//            @Override
//            public java.io.PrintStream append(CharSequence csq) {
//                return this;
//            }
//
//            @Override
//            public java.io.PrintStream append(CharSequence csq, int start, int end) {
//                return this;
//            }
//
//            @Override
//            public java.io.PrintStream append(char c) {
//                return this;
//            }
//        });
//        int redwins = 0, whitewinss = 0;
//        for (int i = 0; i < 10000; i++) {
//            player_2 = new AiSear(Statas.WHITE);
//            player_1 = new UserPlayer(Statas.RED);
            Player curentPLayer=player_1;
//            if (i % 2 == 0) {
//                curentPLayer = player_1;
//            } else {
//                curentPLayer = player_2;
//            }
            boolean game = true;
            Triangle[] board = setBoard();
            display(board);

            try {
                while (game) {
                    if (curentPLayer == player_1) {
                        curentPLayer = player_2;
                    } else curentPLayer = player_1;
                    game = curentPLayer.doMove(board);
                }
                System.out.println("congrats, " + curentPLayer.colour.getText() + " is the winner");
                if (curentPLayer.colour == Statas.RED) {
                //    redwins++;
                } else {
                //    whitewinss++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
                System.out.println("wtf");
            }

        try {
            fh = new FileHandler("text.txt", true);
        } catch (Exception e) {
            System.out.println("file prob");
        }
        // log.addHandler(cs);

        //System.out.println(redwins+ "    r w    "+whitewinss);

//        log.addHandler(fh);
//        SimpleFormatter sf = new SimpleFormatter();
//        SimpleFormatter sf = new SimpleFormatter();
//        fh.setFormatter(sf);
      //  log.info(redwins + " red " + player_1.getName() + " " + player_1.getClass().getSimpleName() + " vs white " + player_2.getName() + " " + player_2.getClass().getSimpleName() + " " + whitewinss + "\n");

    }

    public static Triangle[] setBoard() {
        Triangle[] bord = new Triangle[26];
        for (int i = 0; i <= 25; i++) {
            bord[i] = new Triangle(i);
        }
        return bord;
    }

    public static Player setPlayer(int num,Statas colour,String[] types) {
        String type;
        type = getString("What is Player " + num + "?\n AI, User or Online?", types,"Please Enter a Valid type");
        if(type.equals("user")){
            return new UserPlayer(colour);
        }else if (type.equals("online")){
            return new OnlinePlayer(colour);
        }else if(type.equals("ai")){
            String aiType = "";
            String[] ai = {"and", "bob", "noah", "peter", "sear", "steve", "thewhale"};
            aiType = getString("Which AI is Player " + num + "? And, Bob, Noah, Peter, Sear, Steve or TheWhale?" , ai, "Please enter a valid AI");
            if(aiType.equals("And")){
                return new AiAnd(colour);
            }else if(aiType.equals("bob")){
                return new AiBob(colour);
            }else if(aiType.equals("noah")){
                return new AiNoah(colour);
            }else if(aiType.equals("peter")){
                return new AiPeter(colour);
            }else if(aiType.equals("sear")){
                return new AiSear(colour);
            }else if(aiType.equals("steve")){
                return new AiSteve(colour);
            }else if(aiType.equals("thewhale")){
                return new AiTheWhale(colour);
            }
        }

        return new UserPlayer(colour);//in the actual code this would obviously ask for the type of player required, could be done via the gui if we wana fo that
    }

    public static String getString(String mess, String[] poss,String errMess){
        Boolean error=true;
        String userIn = "";
        Scanner scan = new Scanner(System.in);
        while(error) {//loops until a valid number is entered
            try {
                System.out.println(mess);

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
//                       System.out.println("arr" + arrayCopy[i] + "io" + io + "max" + max);
                        System.out.print(" R  ");

                    } else if (board[i].getStatas() == Statas.WHITE) {
//                       System.out.println("arr" + arrayCopy[i] + "io" + io + "max" + max);
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

//            int pieces;
//            String colour;
//            for(int i0 = 0;i0<4;i0++){
//                System.out.println("");
//            }
//            System.out.println(bord[0].getPieces()+" bore off for red team ");
//            for(int i = 0;i<26;i++){
//                pieces = bord[i].getPieces();
//                colour=bord[i].getStats();
//                System.out.print("                                                "+i+"-->");
//                for(int i2=0;i2<pieces;i2++){
//                    System.out.print(colour);
//                }
//                System.out.print("   "+bord[i].getPieces());
//                System.out.println();
//            }
//            System.out.println(bord[25].getPieces()+" bore off for white team ");
//            System.out.println();
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

