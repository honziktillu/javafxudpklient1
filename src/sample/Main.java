package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        Server server = new Server();
        server.start();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        server.setMsg(characters.get(0).getX() + ";" + characters.get(0).getY());
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 10);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        primaryStage.setOnCloseRequest(event -> {
            System.exit(-1);
        });

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
