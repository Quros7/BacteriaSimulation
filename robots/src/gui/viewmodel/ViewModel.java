package gui.viewmodel;

import gui.model.*;
import gui.view.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ViewModel extends JPanel {
    private final World world;
    private final View view;
    private final int gameWindowWidth;
    private final int gameWindowHeight;

    private static Timer initTimer()
    {
        Timer timer = new Timer("events generator", true);
        return timer;
    }
    private final Timer m_timer = initTimer();

    public ViewModel(int gw_width, int gw_height, World world, View view) {
        this.gameWindowWidth = gw_width;
        this.gameWindowHeight = gw_height;

        this.world = world;
        this.view = view;

        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                updateLogic();
                onRedrawEvent();
            }
        }, 1000, 1000);

        setDoubleBuffered(true);
        //onRedrawEvent();
    }

    public void updateLogic() {
        world.updateWorld();
    }
    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        view.paint(g);
    }

    public int getGameWindowWidth() {return gameWindowWidth;}
    public int getGameWindowHeight() {return gameWindowHeight;}
}
