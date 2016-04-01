/**
 * Created by Ruari on 24/03/2016.
 */

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlinePlayer extends Player {
    final String bye = "bye";
    final String win= "you-win; bye";
    final String hello = "hello";
    final String busy = "busy";
    final String newgame = "New-game";
    final String reject = "reject";
    final String ready = "ready";
    final String pass = "pass";
    final int port = 50230;
    private ServerSocket socketServer;
    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;
    public OnlinePlayer(Statas colour) {
        super(colour);
        String hostName;
        try {
            final String hostname = InetAddress.getLocalHost().getHostName();
            System.out.println(hostname);
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        }

        String[] poss = {"client", "server"};
        String type = "";
        //type = Main.getString("Are you the client or the server?", poss, "Please enter a valid option");
        hostName = getString("enter the host you wish to connect to", null, "Please Enter valid input");
        try {
            if (hostName.equals("wait")) {
                setServer();
            } else {
                seClinet(hostName);
            }

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
                error=false;
            } catch (java.util.InputMismatchException e) {
                System.out.println(e);
                scan.nextLine();//this makes scanner read next input again, as without it would keep returning the first number
            }

        }
        return userIn;
    }
    public int roll(){
return 3;
    }
    public void getMessage(){
        String in = scanner.nextLine();
        System.out.println(in);

    }
    public void seClinet(String hostName)throws java.io.IOException{
        this.colour=Statas.RED;
        socket = new Socket(hostName, port);
        scanner = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(),true);
        BufferedInputStream marker=new BufferedInputStream(socket.getInputStream());
        scanner=new Scanner(marker);

        writer.println(hello);

        String in =scanner.nextLine();
        if(!in.equals(hello)){
            throw new IOException("Server did not adhere to protocol");
        }
        writer.println(newgame);
        in=scanner.nextLine();
        if(!in.equals(ready)){
            throw new IOException("Server did not adhere to protocol");
        }
        if(((int)(1+(Math.random()*2))%2)==0){
            System.out.println("Server Wins First Turn");
            writer.println(pass);
        }else {
            System.out.println("You win first Turn");
        }

        marker.mark(1);
        int test=marker.read();
        System.out.println(test);
        marker.reset();
        in = scanner.nextLine();
        System.out.println(in);
        if(in.equals(hello)){
            System.out.println("suces 1");
        }
        System.out.println("in");
        writer.println(hello);
    }
    public void setServer()throws java.io.IOException {
        this.colour=Statas.WHITE;
        socketServer = new ServerSocket(port);
        socket = socketServer.accept();
        BufferedInputStream marker=new BufferedInputStream(socket.getInputStream());
        scanner=new Scanner(marker);
        writer = new PrintWriter(socket.getOutputStream(),true);
        String in = scanner.nextLine();
        if(!in.equals(hello)){
            throw new IOException("Client did not adhere to protocol");
        }
        writer.println(hello);
        in = scanner.nextLine();
        if(!in.equals(newgame)){
            throw new IOException("Client did not adhere to protocol");
        }
        writer.println(ready);
        marker.mark(1);
        int test=marker.read();
        if(test==1){
            System.out.println("will be pass");
        }
        System.out.println(scanner.nextLine());

        System.out.println(test);
        marker.reset();
        in = scanner.nextLine();
        System.out.println(in);







        System.out.println("");
    }
}
