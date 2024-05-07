package gui.view;

import gui.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JInternalFrame {
    private final int gameWindowWidth; // размеры окна игры
    private final int gameWindowHeight;
    public GameWindow(ViewModel viewModel, Dimension screenSize)
    {
        super("Игровое поле", false, true, true, true);
        this.gameWindowWidth = viewModel.getGameWindowWidth();
        this.gameWindowHeight = viewModel.getGameWindowHeight();
        setLocation((screenSize.width-gameWindowWidth)/2,(screenSize.height-gameWindowHeight)/2);
        setMinimumSize(new Dimension(gameWindowWidth, gameWindowHeight));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(gameWindowWidth, gameWindowHeight));
        panel.add(viewModel, BorderLayout.CENTER);
        panel.setVisible(true);
        getContentPane().add(panel);

        pack();
    }
}
