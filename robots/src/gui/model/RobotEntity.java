package gui.model;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
// TODO: как разделить GameVisualizer, на какие классы и что в них должно быть?

public class RobotEntity extends JPanel implements Entity {
    Point2D.Double robotPosition = new Point2D.Double(100, 100);
    Point targetPosition = new Point(150, 100);
    private volatile double m_robotDirection = 0;
    private static final double maxVelocity = 0.1;
    private static final double maxAngularVelocity = 0.001;
    public RobotEntity() {

    }
    public void update()
    {
        double distance = distance(targetPosition.x, targetPosition.y,
                robotPosition.x, robotPosition.y);
        if (distance < 0.5)
        {
            return;
        }
        double velocity = maxVelocity;
        double angleToTarget = angleTo(robotPosition.x, robotPosition.y, targetPosition.x, targetPosition.y);
        double angularVelocity = 0;
        if (angleToTarget > m_robotDirection)
        {
            angularVelocity = maxAngularVelocity;
        }
        if (angleToTarget < m_robotDirection)
        {
            angularVelocity = -maxAngularVelocity;
        }

        moveRobot(velocity, angularVelocity, 10);
    }
    public Point2D.Double getRobotPosition() {
        return robotPosition;
    }
    public Point getTargetPosition() {
        return targetPosition;
    }
    public double getRobotDirection() {
        return m_robotDirection;
    }
    public void setTargetPosition(Point p)
    {
        targetPosition.x = p.x;
        targetPosition.y = p.y;
    }
    private static double distance(double x1, double y1, double x2, double y2)
    {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }
    private static double angleTo(double fromX, double fromY, double toX, double toY)
    {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }
    private static double asNormalizedRadians(double angle)
    {
        while (angle < 0)
        {
            angle += 2*Math.PI;
        }
        while (angle >= 2*Math.PI)
        {
            angle -= 2*Math.PI;
        }
        return angle;
    }
    private static double applyLimits(double value, double min, double max)
    {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }
    private void moveRobot(double velocity, double angularVelocity, double duration)
    {
        velocity = applyLimits(velocity, 0, maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
        double newX = robotPosition.x + velocity / angularVelocity *
                (Math.sin(m_robotDirection  + angularVelocity * duration) -
                        Math.sin(m_robotDirection));
        if (!Double.isFinite(newX))
        {
            newX = robotPosition.x + velocity * duration * Math.cos(m_robotDirection);
        }
        double newY = robotPosition.y - velocity / angularVelocity *
                (Math.cos(m_robotDirection  + angularVelocity * duration) -
                        Math.cos(m_robotDirection));
        if (!Double.isFinite(newY))
        {
            newY = robotPosition.y + velocity * duration * Math.sin(m_robotDirection);
        }
        robotPosition.x = newX;
        robotPosition.y = newY;
        double newDirection = asNormalizedRadians(m_robotDirection + angularVelocity * duration);
        m_robotDirection = newDirection;
    }
}
