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
    private ServerSocket socketIn;
    public OnlinePlayer(Statas colour) {

        super(colour);

        final String port = "50230";
        try {
            final String hostname = InetAddress.getLocalHost().getHostName();
        }catch(UnknownHostException ex){
            System.out.println(ex);
        }

        String[] poss = {"client", "server"};
        String type = "";
        type = Main.getString("Are you the client or the server?", poss, "Please enter a valid option");
        if(type.equals("client")){
            static void startup()
            {
                // create listening socket
                try {
                    socketIn = new ServerSocket(Numbers.chatPort);
                    socketIn.setSoTimeout(Numbers.soTimeout);
                }
                catch (IOException e) {
                    error("server failed! " + e.getClass().getName());
                    System.exit(-1);
                }
                // local input
                InetAddress h;+
                String s = "(unknown)";
                try {
                    h = InetAddress.getLocalHost();
                    s = h.getCanonicalHostName();
                    report("host: " + h.getByName(s));
                }
                catch (UnknownHostException e) {
                    s = "(unknown)";
                    error("startup(): cannot get hostname!");
                }
                report("port: " + socketIn.getLocalPort());
                report("ready ...");

                _state = ChatState.idle;
            }

        }else if(type.equals("server")){

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
