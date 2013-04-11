package TLTTC;
import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;


public class CTCOfficeView extends JFrame{
    
    private JPanel commandPanel;
    private JButton dispatchButton;
    private JButton setSpeedButton;
    private JButton setAuthorityButton;
    private JTextField setAuthorityField;
    private JTextField setSpeedField;
    private CTCOffice _office;
    //private TableView trainTableView;
    
    
    public CTCOfficeView (CTCOffice office) {
        this._office = office;
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
        dispatchButton.addActionListener(new CTCOfficeViewListener());
        commandPanel.add(dispatchButton);
        setSpeedButton = new JButton("Set Speed");
        setSpeedButton.setSize(20,40);
        setSpeedButton.addActionListener(new CTCOfficeViewListener());
        commandPanel.add(setSpeedButton);
        setAuthorityButton = new JButton("Set Authority");
        setAuthorityButton.setSize(20,40);
        setAuthorityButton.addActionListener(new CTCOfficeViewListener());
        commandPanel.add(setAuthorityButton);
        add(commandPanel);
    }
    
    private class CTCOfficeViewListener implements ActionListener {
    
        public void actionPerformed(ActionEvent e) {
            Component event = (Component) e.getSource();
        
            if (event == dispatchButton) {
                _office.dispatchTrain();
            }
            else if (event == setSpeedButton) {
            
            }
            
            else if (event == setAuthorityButton) {
                
            }
        }
    }
}

