package net.celloscope.example8;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Example8Server {
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


            int characterValue = reader.read();
            if(characterValue == -1){
                System.out.println("Nothing to read");
                return;
            }

            String line = "";
            line = reader.readLine();

            if(line == null){
                System.out.println("Nothing to read");
                return;
            }
            StringTokenizer st;
            while (line != null){
                System.out.println("Received: "+line);
                st = new StringTokenizer(line,"<DL>");

                if(st.countTokens() ==2);{
                    String key = st.nextToken();
                    String value = st.nextToken();
                    System.out.println("key: "+key);
                    System.out.println("value: "+ value);
                }
            }

            System.out.println("End of transmission");

            writer.write(66);
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
