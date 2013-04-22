package TLTTC;
import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

//@SuppressWarnings("serial")
public class CTCOfficeView extends JFrame{
    
    private JPanel dispatchPanel;
    private JPanel speedPanel;
    private JPanel authorityPanel;
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
       
        initComponents();
         setTitle("CTC Office - Sean Moore");
        setSize(400,400);
        setLocation(10,10);
        setVisible(true);
    }
    
    private void initComponents() {
        
        
        
        commandPanel = new JPanel(new GridLayout(3,2));
        
        setSpeedButton = new JButton("Set Speed");
        setSpeedField = new JTextField("10");

        setSpeedButton.addActionListener(new CTCOfficeViewListener());
        commandPanel.add(setSpeedField);
        commandPanel.add(setSpeedButton);
        
        setAuthorityButton = new JButton("Set Authority");
        setAuthorityField = new JTextField("1");
        //setAuthorityButton.setSize(20,20);
        setAuthorityButton.addActionListener(new CTCOfficeViewListener());
        commandPanel.add(setAuthorityField);
        commandPanel.add(setAuthorityButton);

        
        dispatchButton = new JButton("Dispatch Train");
        //dispatchButton.setSize(20,40);
        dispatchButton.addActionListener(new CTCOfficeViewListener());
        commandPanel.add(dispatchButton);
        add(commandPanel);
        System.out.println("GUI for CTC Office init");
    }
    
    private class CTCOfficeViewListener implements ActionListener {
    
        String text;
        public void actionPerformed(ActionEvent e) {
            Component event = (Component) e.getSource();
        
            if (event == dispatchButton) {
                _office.dispatchTrain();
            }
            else if (event == setSpeedButton) {
                text = setSpeedField.getText();

                Double speed = Double.parseDouble(text);
                _office.setSpeed(0, speed); //NOT CORRECT

            }
            
            else if (event == setAuthorityButton) {
                text = setAuthorityField.getText();
                
                Integer authority = Integer.parseInt(text);
                _office.setFixedAuthority(0, authority);
                
            }
        }
    }
}

