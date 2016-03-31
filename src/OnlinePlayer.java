/**
 * Created by Ruari on 24/03/2016.
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class OnlinePlayer extends Player {
    final String bye = "bye";
    final String hello = "hello";
    final String busy = "busy";
    final String newgame = "newgame";
    final String reject = "reject";
    final String ready = "ready";
    private ServerSocket socketServer;
    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;
    public OnlinePlayer(Statas colour) {
        super(colour);
        String hostName;
        final int port = 50230;
        try {
            final String hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        }

        String[] poss = {"client", "server"};
        String type = "";
        type = Main.getString("Are you the client or the server?", poss, "Please enter a valid option");
        hostName = getString("enter the host you wish to connect to", null, "Please Enter valid input");
        try {
            if (type.equals("server")) {

                // create listening socket
                this.colour=Statas.WHITE;
                socketServer = new ServerSocket(port);
                socketServer.setSoTimeout(300000);
                socket = socketServer.accept();

                System.out.println("Connection Timeout");
                System.exit(-1);

            } else if (type.equals("client")) {
                this.colour=Statas.RED;
                socket = new Socket(hostName, port);

            }
            scanner = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream());
        }catch (java.net.SocketException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }catch (java.io.IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
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
//                for(String pos:poss){
//                    if(pos.equals(userIn.toLowerCase())){
//                        error=false;
//                    }
//                }

            } catch (java.util.InputMismatchException e) {
                System.out.println(e);
                scan.nextLine();//this makes scanner read next input again, as without it would keep returning the first number
            }

        }


        return userIn;
    }
}
