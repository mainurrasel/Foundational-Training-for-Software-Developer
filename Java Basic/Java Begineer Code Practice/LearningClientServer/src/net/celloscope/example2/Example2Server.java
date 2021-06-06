package net.celloscope.example2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Example2Server {
    public static void main(String[] args) {
        int port = 8888;

        BufferedReader reader = null;
        OutputStream writer = null;
        try {
            System.out.println("Trying to start server on port: "+ port);
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on " +port);
            Socket socket = serverSocket.accept();
            System.out.println("Connection from "+ socket.getInetAddress().toString());
            System.out.println("Connection from port "+ socket.getPort());
            System.out.println("Connected to local IP "+ socket.getInetAddress());
            System.out.println("Connected to local port" + socket.getLocalPort());
            //Thread.sleep(10000);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer= socket.getOutputStream();


            int charLength = 0;
            System.out.println("Number of character sent: "+charLength);

            int characterValue = -1;
            int counter = 0;
            while (true){
                ++counter;
                characterValue = reader.read();
                if( counter == charLength) break;
                System.out.println("The "+ counter +" character as integer: "+ characterValue);
                System.out.println("The "+ counter +" character :            : "+String.valueOf((char)characterValue));
            }

            writer.write(66);
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
