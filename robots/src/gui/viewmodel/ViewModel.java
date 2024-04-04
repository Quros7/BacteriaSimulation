package gui.viewmodel;

import gui.model.Entity;
import gui.model.Model;
import gui.model.RobotEntity;
import gui.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ViewModel extends JPanel {
    private final Model model;
    RobotEntity robot = new RobotEntity();
    private Map<Class<?>, EntityRenderer<?>> renderes = Map.of(
            RobotEntity.class, new RobotRenderer()
    );
    private static Timer initTimer()
    {
        Timer timer = new Timer("events generator", true);
        return timer;
    }
    private final Timer m_timer = initTimer();

    public ViewModel() {
        model = new Model(robot);

        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                updateLogic();
            }
        }, 0, 10);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                robot.setTargetPosition(e.getPoint());
            }
        });
        setDoubleBuffered(true);
    }
    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }

    public void updateLogic() {
        model.updateModel();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        List<Entity> entities = model.getEntities();
        for (Entity entity : entities) {
            EntityRenderer<Entity> entityRenderer = (EntityRenderer<Entity>)renderes.get(entity.getClass());
            entityRenderer.render(entity, g);
        }
    }
}
