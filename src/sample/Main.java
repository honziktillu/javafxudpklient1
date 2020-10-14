package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    public static ArrayList<Character> characters = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        root.requestFocus();

        DatagramSocket ds1 = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {

                    @Override
                    public void run() {
                        try {
                            byte buf[] = null;
                            String coordinates = "0;" + characters.get(0).getX() + ";" + characters.get(0).getY();
                            buf = coordinates.getBytes();
                            DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 1234);
                            ds1.send(DpSend);
                        } catch (IOException e) {

                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 10);
                return null;


            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        DatagramSocket ds = new DatagramSocket(1235);
        Task<Void> task2 = new Task<Void>() {
            @Override
            protected Void call() throws Exception {


                Timer timer2 = new Timer();
                TimerTask task2 = new TimerTask() {

                    @Override
                    public void run() {
                        try {
                            byte[] receive = new byte[65535];
                            DatagramPacket DpReceive = null;
                            DpReceive = new DatagramPacket(receive, receive.length);
                            ds.receive(DpReceive);
                            String coordinates = data(receive).toString();
                            String[] converted = coordinates.split(";");
                            if (converted[0].equals("1")) {
                                characters.get(1).setX(Double.parseDouble(converted[1]));
                                characters.get(1).setY(Double.parseDouble(converted[2]));
                            }
                        } catch (IOException e) {

                        }
                    }
                };
                timer2.scheduleAtFixedRate(task2, 0, 10);
                return null;


            }
        };
        Thread thread2 = new Thread(task2);
        thread2.setDaemon(true);
        thread2.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
