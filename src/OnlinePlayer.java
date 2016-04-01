/**
 * Created by Ruari on 24/03/2016.
 */

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlinePlayer extends Player {
    final String bye = "bye";
    final String win = "you-win; bye";
    final String hello = "hello";
    final String busy = "busy";
    final String newgame = "New-game";
    final String reject = "reject";
    final String ready = "ready";
    final String pass = "pass";
    final int port = 50230;
    private ArrayList<Move> movesIn = new ArrayList<>();
    private ArrayList<Integer> rollsIn = new ArrayList<>();
    private ServerSocket socketServer;
    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;

    public OnlinePlayer(Statas colour) {

        super(colour);
        Boolean loop=true;
        String hostName;
        try {
            final String hostname = InetAddress.getLocalHost().getHostName();
            System.out.println(hostname);
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        }

        String type = "";
        while (loop) {
            hostName = getString("enter the host you wish to connect to( or wait to act as a server)", null, "Please Enter valid input");

            try {
                if (hostName.equals("wait")) {
                    setServer();
                } else {
                    seClinet(hostName);
                }
                loop=false;
            } catch (java.net.SocketException e) {
                // e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (java.io.IOException e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getString(String mess, String[] poss, String errMess) {
        Boolean error = true;
        String userIn = "";
        Scanner scan = new Scanner(System.in);
        while (error) {//loops until a valid number is entered
            try {
                System.out.println(mess);

                userIn = scan.nextLine();
                if (userIn.equals("exit")) {
                    System.exit(1);
                }

                error = false;
            } catch (java.util.InputMismatchException e) {
                System.out.println(e);
                scan.nextLine();//this makes scanner read next input again, as without it would keep returning the first numberdf
            }

        }
        return userIn;
    }

    public int roll() {
        return -1;
    }

    public Move getMove() {
        Move move = null;
        try {
            move = movesIn.get(0);
            movesIn.remove(0);
            return move;
        } catch (Exception e) {

        }
        return move;
    }


    public void getMessageIn() {
        String in = scanner.nextLine();
        try {
            Move move;
           // in = scanner.nextLine();
        //   String in = "4-3:(-1|-1),(-2|-890890);";
            if (in.equals(bye)) {
                System.out.println("Connection has been closed, exiting program");
                socket.close();
                System.exit(1);
            } else if (in.equals(win)) {
                System.out.println("Connection thinks that they won (incorrectly)");
                socket.close();
                System.exit(1);

            }
            String[] parts = in.split("[:]");
            String[] rolls=parts[0].split("[-]");
            int roll1 = Integer.parseInt(rolls[0]);
            int roll2 = Integer.parseInt(rolls[1]);

            if (!(roll1 <= 6 && roll1 >= 1 && roll2 <= 6 && roll2 >= 1)) {
                throw new Exception("Roll out of bounds");
            }

            rollsIn = super.roll(roll1, roll2);
            parts[1]=parts[1].replace(";", "");
            parts=parts[1].split("[,]");
            for(String each:parts){
                each=each.replaceAll("[()]", "");
                String[] moves=each.split("\\|");
                move = new Move(Integer.parseInt(moves[0]), Integer.parseInt(moves[1]));
                movesIn.add(move);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("test the problem");
            writer.println(bye);
            System.exit(1);
        }

    }

    public ArrayList<Integer> roll(int roll1, int roll2) {
        if(Main.mess !=null){
            sendMessage();
        }
        getMessageIn();
        return rollsIn;
    }

    public void sendMessage(){
        try{
            writer.println(Main.mess);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void seClinet(String hostName) throws java.io.IOException {
        this.colour = Statas.RED;
        socket = new Socket(hostName, port);
        scanner = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);
        BufferedInputStream marker = new BufferedInputStream(socket.getInputStream());
        scanner = new Scanner(marker);

        writer.println(hello);
       // System.out.println("out" + hello);
        String in = scanner.nextLine();
        if (!in.equals(hello)) {
            throw new IOException("Server did not adhere to protocol");
        }
        //System.out.println("in" + hello);
        writer.println(newgame);
       // System.out.println("out" + newgame);
        in = scanner.nextLine();
        if (!in.equals(ready)) {
            throw new IOException("Server did not adhere to protocol");
        }
        //System.out.println("in" + ready);
        if (((int) (1 + (Math.random() * 2)) % 2) == 0) {
            System.out.println("Server Wins First Turn");
          //  System.out.println(" out pass");
            writer.println(pass);
            firstPlayer=true;
        } else {
            System.out.println("You win first Turn");
            firstPlayer=false;
           // writer.println("3-3");
          //  System.out.println(" out3-3:");

        }
    }

    public void setServer() throws java.io.IOException {
        this.colour = Statas.WHITE;
        socketServer = new ServerSocket(port);
        socket = socketServer.accept();
        BufferedInputStream marker = new BufferedInputStream(socket.getInputStream());
        scanner = new Scanner(marker);
        writer = new PrintWriter(socket.getOutputStream(), true);
        String in = scanner.nextLine();
        if (!in.equals(hello)) {
            throw new IOException("Client did not adhere to protocol");
        }
        writer.println(hello);
        in = scanner.nextLine();
        if (!in.equals(newgame)) {
            throw new IOException("Client did not adhere to protocol");
        }
        writer.println(ready);
        marker.mark(1);
        int test = marker.read();
       // System.out.println(test);
        if (test == 112) {//asco code for p, start of pass protocol
            System.out.println("will be pass");
            marker.reset();
            in=scanner.nextLine();
            System.out.println(in);
            if(in.equals(pass)){
                System.out.println("You Win First Turn");
                firstPlayer=false;
            }else {
                throw new IOException("Not move or pass command");
            }
        } else {
            //System.out.println("will be move");
            marker.reset();
            System.out.println("Client wins First Turn");
            firstPlayer = true;
        }
    }
    public void won(){
        sendMessage();
    }

}
