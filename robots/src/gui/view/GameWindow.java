package gui.view;

import gui.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JInternalFrame
{
    public GameWindow(ViewModel viewModel)
    {
        super("Игровое поле", true, true, true, true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(viewModel, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
