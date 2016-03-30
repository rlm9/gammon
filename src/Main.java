import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.SimpleTimeZone;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Ruari on 24/03/2016.
 */
    public class Main {
        public static final Logger log=Logger.getLogger(Main.class.getName());
        public static FileHandler fh;
        public static void main(String[] args){
          //  Player player_1=setPlayer(Statas.WHITE);
            Player player_1=null;
            Player player_2=null;
            System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
                @Override public void write(int b) {}
            }) {
                @Override public void flush() {}
                @Override public void close() {}
                @Override public void write(int b) {}
                @Override public void write(byte[] b) {}
                @Override public void write(byte[] buf, int off, int len) {}
                @Override public void print(boolean b) {}
                @Override public void print(char c) {}
                @Override public void print(int i) {}
                @Override public void print(long l) {}
                @Override public void print(float f) {}
                @Override public void print(double d) {}
                @Override public void print(char[] s) {}
                @Override public void print(String s) {}
                @Override public void print(Object obj) {}
                @Override public void println() {}
                @Override public void println(boolean x) {}
                @Override public void println(char x) {}
                @Override public void println(int x) {}
                @Override public void println(long x) {}
                @Override public void println(float x) {}
                @Override public void println(double x) {}
                @Override public void println(char[] x) {}
                @Override public void println(String x) {}
                @Override public void println(Object x) {}
                @Override public java.io.PrintStream printf(String format, Object... args) { return this; }
                @Override public java.io.PrintStream printf(java.util.Locale l, String format, Object... args) { return this; }
                @Override public java.io.PrintStream format(String format, Object... args) { return this; }
                @Override public java.io.PrintStream format(java.util.Locale l, String format, Object... args) { return this; }
                @Override public java.io.PrintStream append(CharSequence csq) { return this; }
                @Override public java.io.PrintStream append(CharSequence csq, int start, int end) { return this; }
                @Override public java.io.PrintStream append(char c) { return this; }
            });
            int redwins=0,whitewinss=0;
            for(int i=0;i<1000;i++){
                player_1 = new AiBob(Statas.RED);
                player_2 = new AiAnd(Statas.WHITE);
                Player curentPLayer;
                if(i%2==0){
                    curentPLayer = player_2;
                }else {
                    curentPLayer = player_1;
                }

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
                    if(curentPLayer.colour==Statas.RED){
                        redwins++;
                    }else {
                        whitewinss++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                    System.out.println("wtf");
                }
            }
            try {
                fh = new FileHandler("text.txt",true);
            }
            catch (Exception e){
                System.out.println("file prob");
            }
           // log.addHandler(cs);

            //System.out.println(redwins+ "    r w    "+whitewinss);

            log.addHandler(fh);
             SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            log.info(redwins+ " "+player_1.getName()+ " "+player_1.colour.getText()+" vs " +player_2.getName()+ " "+player_2.colour.getText()+" " +whitewinss+"\n");

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
