package com.cn;

/**
 * Created by 周太宇 on 2017/10/9.
 */
public class Circle {
    private int redius;
    private Point point;

    public Circle() {
    }

    public Circle(int redius, Point point) {
        this.redius = redius;
        this.point = point;
    }

    public int getRedius() {
        return redius;
    }

    public void setRedius(int redius) {
        this.redius = redius;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void draw(){
        System.out.print("The circle reduis is "+ getRedius()+" with("+point.getX()+","+point.getY()+")" );
    }
}
