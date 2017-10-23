package edu.cn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by 周太宇 on 2017/10/9.
 */
@Component
public class Circle {
    @Value("${radius}")
    private int radius;
    @Autowired
    private Point point;

    public Circle() {
    }

    public Circle(int radius, Point point) {
        this.radius = radius;
        this.point = point;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void draw(){
        System.out.print("The circle raduis is "+ getRadius()+" with("+point.getX()+","+point.getY()+")" );
    }
}
