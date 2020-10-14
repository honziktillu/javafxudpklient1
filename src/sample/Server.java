package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];
    private String msg;

    public Server() throws SocketException {
        socket = new DatagramSocket(1234);
    }

    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(),0, packet.getLength());
                String[] conv = received.split(";");
                Main.characters.get(1).setX(Double.parseDouble(conv[0]));
                Main.characters.get(1).setY(Double.parseDouble(conv[1]));
                buf = msg.getBytes();
                socket.send(new DatagramPacket(buf, buf.length, address, port));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
