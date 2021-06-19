package top.littlefogcat.demolist.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
    public static void main(String[] args) throws IOException {
        int port = 17427;
        Server server = new Server(port);
        server.start();

        Socket client = new Socket("127.0.0.1", server.port);
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        out.writeUTF("hello!");
        DataInputStream in = new DataInputStream(client.getInputStream());
        System.out.println(in.readUTF());
        for (; ; ) ;
    }

    private static class Server extends Thread {
        private ServerSocket socket;
        private int port;

        public Server(int p) {
            this.port = p;
            while (port < 65536) {
                try {
                    socket = new ServerSocket(port);
                    break;
                } catch (IOException e) {
                    System.out.println("port " + port + " is invalid.");
                    port++;
                }
            }
            System.out.println("socket create port = " + port);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Socket conn = socket.accept();

                    DataInputStream in = new DataInputStream(conn.getInputStream());
                    String msg = in.readUTF();
                    System.out.println("Accepted message: " + msg);

                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeUTF("Greetings!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
