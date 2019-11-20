package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;

import java.util.Objects;

public class Triangle extends Figure {

    private Point2D point1;
    private Point2D point2;
    private Point2D point3;

    public Triangle(Point2D point1, Point2D point2, Point2D point3, Color color) throws ColorException {

        super(color);
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public Triangle(Point2D point1, Point2D point2, Point2D point3, String color) throws ColorException {

        this(point1, point2, point3, Color.colorFromString(color));
    }

    public Point2D getPoint1() {

        return point1;
    }

    public Point2D getPoint2() {

        return point2;
    }

    public Point2D getPoint3() {

        return point3;
    }

    public void setPoint1(Point2D point) {

        point1 = point;
    }

    public void setPoint2(Point2D point) {

        point2 = point;
    }

    public void setPoint3(Point2D point) {

        point3 = point;
    }

    public double getSide12() {

        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
    }

    public double getSide13() {

        return Math.sqrt(Math.pow(point3.getX() - point1.getX(), 2) + Math.pow(point3.getY() - point1.getY(), 2));
    }

    public double getSide23() {

        return Math.sqrt(Math.pow(point3.getX() - point2.getX(), 2) + Math.pow(point3.getY() - point2.getY(), 2));
    }

    public void moveRel(int dx, int dy) {

        point1.setX(point1.getX() + dx);
        point1.setY(point1.getY() + dy);
        point2.setX(point2.getX() + dx);
        point2.setY(point2.getY() + dy);
        point3.setX(point3.getX() + dx);
        point3.setY(point3.getY() + dy);
    }

    public double getArea() {

        double semiPerimeter = 0.5 * getPerimeter();
        return Math.sqrt(semiPerimeter * (semiPerimeter - getSide12()) * (semiPerimeter - getSide13()) * (semiPerimeter - getSide23()));
    }

    public double getPerimeter() {

        return getSide12() + getSide13() + getSide23();
    }

    public boolean isInside(int x, int y) {

        int a = (point1.getX() - x) * (point2.getY() - point1.getY()) - (point2.getX() - point1.getX()) * (point1.getY() - y);
        int b = (point2.getX() - x) * (point3.getY() - point2.getY()) - (point3.getX() - point2.getX()) * (point2.getY() - y);
        int c = (point3.getX() - x) * (point1.getY() - point3.getY()) - (point1.getX() - point3.getX()) * (point3.getY() - y);
        return a >= 0 && b >= 0 && c >= 0 || a <= 0 && b <= 0 && c <= 0;
    }

    public boolean isInside(Point2D point) {

        return isInside(point.getX(), point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return getColor() == triangle.getColor() &&
                Objects.equals(point1, triangle.point1) &&
                Objects.equals(point2, triangle.point2) &&
                Objects.equals(point3, triangle.point3);
    }

    @Override
    public int hashCode() {

        return Objects.hash(point1, point2, point3, getColor());
    }
}
