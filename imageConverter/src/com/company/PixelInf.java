package com.company;

public class PixelInf {
    private int x;
    private int y;
    private int rgb;
    private int red;
    private int green;
    private int blue;

    public PixelInf(int x, int y, int rgb) {
        this.x = x;
        this.y = y;
        this.rgb = rgb;
        this.red = (rgb & 0x00ff0000) >> 16;
        this.green = (rgb & 0x0000ff00) >> 8;
        this.blue = rgb & 0x000000ff;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
