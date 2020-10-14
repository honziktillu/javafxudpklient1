package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public Pane pane;
    public Canvas canvas;
    public GraphicsContext gc;
    public Character player;
    public Character enemy;
    public ArrayList<String> vstup = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        player = new Character(100, 100, 100, 100, 5);
        enemy = new Character(600, 100, 100, 100, 5);
        Main.characters.add(player);
        Main.characters.add(enemy);
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.setFill(Paint.valueOf("WHITE"));
                movement();
                collisionDetection();
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Paint.valueOf("RED"));
                gc.fillRect(Main.characters.get(0).getX(), Main.characters.get(0).getY(), Main.characters.get(0).getWidth(), Main.characters.get(0).getHeight());
                gc.setFill(Paint.valueOf("BLUE"));
                gc.fillRect(Main.characters.get(1).getX(), Main.characters.get(1).getY(), Main.characters.get(1).getWidth(), Main.characters.get(1).getHeight());
            }
        };
        gameLoop.start();

    }

    public void collisionDetection() {
        if (
                player.getX() < enemy.getX() + enemy.getWidth() &&
                player.getX() + player.getWidth() > enemy.getX() &&
                player.getY() < enemy.getY() + enemy.getHeight() &&
                player.getY() + player.getHeight() > enemy.getY()
        ) {
            System.out.println("Collision detected");
        }
    }

    public void movement() {
        if (vstup.contains("W")) {
            Main.characters.get(0).setY(Main.characters.get(0).getY() - Main.characters.get(0).getSpeed());
        }
        if (vstup.contains("S")) {
            Main.characters.get(0).setY(Main.characters.get(0).getY() + Main.characters.get(0).getSpeed());
        }
        if (vstup.contains("A")) {
            Main.characters.get(0).setX(Main.characters.get(0).getX() - Main.characters.get(0).getSpeed());
        }
        if (vstup.contains("D")) {
            Main.characters.get(0).setX(Main.characters.get(0).getX() + Main.characters.get(0).getSpeed());
        }
    }


    public void movementStart(KeyEvent keyEvent) {
        String code = keyEvent.getCode().toString();
        if (!vstup.contains(code)) {
            vstup.add(code);
        }
    }

    public void movementEnd(KeyEvent keyEvent) {
        String code = keyEvent.getCode().toString();
        vstup.remove(code);
    }
}
