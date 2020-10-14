package sample;

import javafx.scene.image.Image;

public class Character {

    private double x;
    private double y;
    private double width;
    private double height;
    private double speed;
    private String img = "https://icon2.cleanpng.com/20180320/ypq/kisspng-professor-pac-man-arcade-game-single-player-video-png-pacman-background-transparent-hd-5ab14378df8977.3194325715215665849156.jpg";

    public Character(double x, double y, double width, double height, double speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public Image getImg() {
        return new Image(this.img);
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
