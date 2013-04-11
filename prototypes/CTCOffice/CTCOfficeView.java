package TLTTC;
import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import javax.swing.*;


public class CTCOfficeView extends JFrame{
    
    private JPanel commandPanel;
    private JButton dispatchButton;
    private JButton setSpeedButton;
    private JButton setAuthorityButton;
    private JTextField setAuthorityField;
    private JTextField setSpeedField;
    
    
    public CTCOfficeView () {
        setTitle("CTC Office - Sean Moore");
        setSize(400,400);
        setLocation(10,10);
        setVisible(true);
        initComponents();
    }
    
    private void initComponents() {
        
        commandPanel = new JPanel(new GridLayout(3,1));
        commandPanel.setSize(60,40);
        dispatchButton = new JButton("Dispatch Train");
        dispatchButton.setSize(20,40);
        commandPanel.add(dispatchButton);
        setSpeedButton = new JButton("Set Speed");
        setSpeedButton.setSize(20,40);
        commandPanel.add(setSpeedButton);
        setAuthorityButton = new JButton("Set Authority");
        setAuthorityButton.setSize(20,40);
        commandPanel.add(setAuthorityButton);
        add(commandPanel);
    }
}