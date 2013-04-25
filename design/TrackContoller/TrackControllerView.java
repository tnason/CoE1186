/*
* Author(s): Cameron Dashti
* Updated: 25 – 4 – 2013
* Purpose: This file sets up the gui for the track controller. It allows
*           the user to view all of the block under each contoller and preform
*          various test meachanisms on the track.
*/

package TLTTC;

import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.concurrent.*;
import java.lang.reflect.*;

@SuppressWarnings("serial")  
public class TrackControllerView extends javax.swing.JFrame 
{

    // Controller holds all of the controllers. Each controller in the table has a table of blocks 
    // that it monitors. 
    private Hashtable<Integer, Hashtable<Integer, Block>> controller;        

    // Get the model of all of the tables so they can be updated.  
    private DefaultTableModel tcListModel, tcPropModel, controllerInfoModel; 

    private int   selectedController = -1;   // Which number controller is selected in thegui
    private int   userSelectedBlock  = -1;   // Which number block is selected in the gui
    private Block currentBlock       = null; // Reference to the selected block

    private Object   plcObject = null;       // Holds reference to dynamically loaded PLC class
    private Class<?> plcClass  = null;       // Reference to plc class loaded by user
    private boolean  plcLoaded = false;        

    public TrackControllerView(Hashtable<Integer, Hashtable<Integer, Block>> allControllers) 
    {
        initComponents(); // Auto generated code to set up gui

        // Once the gui is built, get the model of the tables so they can be changed when action.
        tcListModel = (DefaultTableModel) tcList.getModel();       
        tcPropModel = (DefaultTableModel) tcProp.getModel();
        controllerInfoModel = (DefaultTableModel) controllerInfo.getModel();

        controller = allControllers;  // Gets the table of contorllers from the main track contoller class.
        
        // Loop over all track controllers and add them to the track controller list.
        for(int tcNum : controller.keySet())
        {
            tcListModel.addRow(new Object [] {"Track Controller " + tcNum});
        }
    }

    @SuppressWarnings("unchecked")                        
    private void initComponents() {
        closeTC = new javax.swing.JButton();
        curLine = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tcList = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tcProp = new javax.swing.JTable();
        enableCrossing = new javax.swing.JButton();
        toggleSwitch = new javax.swing.JButton();
        putMaintenance = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        trainInfo = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        controllerInfo = new javax.swing.JTable();
        loadPLC = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cameron Dashti - Track Controller");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        closeTC.setText("Close");
        closeTC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeTC(evt);
            }
        });

        curLine.setText("Red Line");
        curLine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeLine(evt);
            }
        });

        tcList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Available Track Controllers"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tcList.setColumnSelectionAllowed(true);
        tcList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tcList.getTableHeader().setReorderingAllowed(false);
        tcList.setUpdateSelectionOnSort(false);
        tcList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tcSelected(evt);
            }
        });
        jScrollPane1.setViewportView(tcList);
        tcList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tcList.getColumnModel().getColumn(0).setResizable(false);
        tcList.getAccessibleContext().setAccessibleName("tcList");
        tcList.getAccessibleContext().setAccessibleParent(this);

        jScrollPane3.setOpaque(false);

        tcProp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Block Number", "Next Block", "Occupied", "Maintenance", "Crossing"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tcProp.setOpaque(false);
        tcProp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tcProp.getTableHeader().setReorderingAllowed(false);
        tcProp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                blockSelected(evt);
            }
        });
        jScrollPane3.setViewportView(tcProp);
        tcProp.getColumnModel().getColumn(0).setResizable(false);
        tcProp.getColumnModel().getColumn(1).setResizable(false);
        tcProp.getColumnModel().getColumn(2).setResizable(false);
        tcProp.getColumnModel().getColumn(3).setResizable(false);
        tcProp.getColumnModel().getColumn(4).setResizable(false);
        tcProp.getAccessibleContext().setAccessibleName("tcProp");
        tcProp.getAccessibleContext().setAccessibleParent(this);

        enableCrossing.setText("Enable Crossing");
        enableCrossing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enableCrossing(evt);
            }
        });

        toggleSwitch.setText("Toggle Switch");
        toggleSwitch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toggleSwitchClicked(evt);
            }
        });

        putMaintenance.setText("Maintenance");
        putMaintenance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                putMaintenanceClicked(evt);
            }
        });

        jScrollPane5.setOpaque(false);

        trainInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Train Number", "Current Block", "Authority", "Speed"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        trainInfo.setOpaque(false);
        trainInfo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        trainInfo.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(trainInfo);
        trainInfo.getColumnModel().getColumn(0).setResizable(false);
        trainInfo.getColumnModel().getColumn(1).setResizable(false);
        trainInfo.getColumnModel().getColumn(2).setResizable(false);
        trainInfo.getColumnModel().getColumn(2).setHeaderValue("Authority");
        trainInfo.getColumnModel().getColumn(3).setResizable(false);
        trainInfo.getColumnModel().getColumn(3).setHeaderValue("Speed");

        jScrollPane6.setOpaque(false);

        controllerInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Number of Blocks", "Active Trains"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        controllerInfo.setOpaque(false);
        controllerInfo.setRowSelectionAllowed(false);
        controllerInfo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        controllerInfo.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(controllerInfo);
        controllerInfo.getColumnModel().getColumn(0).setResizable(false);
        controllerInfo.getColumnModel().getColumn(1).setResizable(false);

        loadPLC.setText("Load PLC");
        loadPLC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadProg(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loadPLC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(curLine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(closeTC))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(toggleSwitch)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(enableCrossing)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(putMaintenance))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(putMaintenance)
                            .addComponent(enableCrossing)
                            .addComponent(toggleSwitch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(curLine)
                    .addComponent(closeTC)
                    .addComponent(loadPLC))
                .addGap(20, 20, 20))
        );

        closeTC.getAccessibleContext().setAccessibleName("closeTC");
        curLine.getAccessibleContext().setAccessibleName("curLine");
        loadPLC.getAccessibleContext().setAccessibleName("loadPLC");

        enableCrossing.setEnabled(false);
        putMaintenance.setEnabled(false);
        toggleSwitch.setEnabled(false);

        pack();
    }                     

    // Returns a reference to the PLC object for the track controller to use it.
    public Object getPLC ()
    {
        return plcObject;
    }

     // Returns a reference to the PLC class for the track controller to get all methods.
    public Class<?> getPlcClass()
    {
        return plcClass;
    }

    // Retruns a boolean whether or not the plc progam is loaded.
    public boolean PLCLoaded()
    {
        return plcLoaded;
    }
                
    private void changeLine(java.awt.event.MouseEvent evt) 
    {
        
    }

    // On close, set the gui to be invisible
    private void closeTC(java.awt.event.MouseEvent evt) 
    {
        this.setVisible(false);
    }

    // a user selects a track controller
    private void tcSelected(java.awt.event.MouseEvent evt) 
    {             
        int numTrains = 0;  // Number of valid trains

        enableCrossing.setEnabled(false); // disable all of the buttons
        putMaintenance.setEnabled(false);
        toggleSwitch.setEnabled(false);

        // Get the string value of which controller selected and parse its numeric value.
        String tcString = (String) tcList.getValueAt(tcList.getSelectedRow(), 0);
        selectedController = Integer.parseInt(tcString.split(" ")[2]);

        for(Block curBlock : controller.get(selectedController).values())
        {
            if(curBlock.isOccupiedNoMaintenance())
            {
                numTrains++;
            }
        }

        // Remove all of the old controller information
        while(controllerInfoModel.getRowCount() > 0)
        {
            controllerInfoModel.removeRow(0);
        }

        // Add new contoller statistics
        controllerInfoModel.addRow(new Object [] { controller.get(selectedController).size(),
                                                   "num Trains " + numTrains});
        refresh();  // refresh the rest of the tables
    } 

    // The user selectes a block from the gui
    private void blockSelected(java.awt.event.MouseEvent evt) 
    {                               
        // Get the block number selected and get the block reference
        int choosenBlockNum = (int) tcProp.getValueAt(tcProp.getSelectedRow(), 0);
        currentBlock = controller.get(selectedController).get(choosenBlockNum);

        userSelectedBlock = choosenBlockNum; // save block number globally

        // If the selected block is not occupied, it can be put into maintenance
        if(!currentBlock.isOccupiedNoMaintenance())
        {
            putMaintenance.setEnabled(true);      
        }
        else
        {
            putMaintenance.setEnabled(false);
        }

        // If the user selects a crossing, allow them to enable the crossing.
        if(currentBlock.isCrossing() && !currentBlock.getCrossing())
        {
            enableCrossing.setEnabled(true);
        }
        else
        {
            enableCrossing.setEnabled(false);
        }

        /*   switches.*/
        if(currentBlock.getStartNode() != null && currentBlock.getStartNode().getNodeType() == constData.NodeType.Switch)
        {
            toggleSwitch.setEnabled(true);
        }

        if(currentBlock.getStopNode() != null && currentBlock.getStopNode().getNodeType() == constData.NodeType.Switch)
        {
            toggleSwitch.setEnabled(true);
        }
    }                                                    

    private void enableCrossing(java.awt.event.MouseEvent evt) 
    {               
        // Check that the current block is a crossing                 
       if(currentBlock.isCrossing() && enableCrossing.isEnabled())
       {
            // Create a thread scheduler to disable crossing.
            ScheduledThreadPoolExecutor delayReset = new ScheduledThreadPoolExecutor(1);

            currentBlock.setCrossing(true); // Enable the block's crossing

            // Schecdule a new job taht disables the crossing after 30 seconds.
            delayReset.schedule(new Runnable() 
                              {
                                public void run()
                                {
                                    currentBlock.setCrossing(false);
                                }
                            }, 30, TimeUnit.SECONDS);

       }
    }                               

    // If the use elects to toggle maintenance of a block
    private void putMaintenanceClicked(java.awt.event.MouseEvent evt) 
    {    
      if(putMaintenance.isEnabled())
        {
            // toggle the stat of the maintenance of the block, update the table.
            currentBlock.setMaintenance(!currentBlock.getMaintenance());
            tcProp.setValueAt((Object) currentBlock.isOccupied(), tcProp.getSelectedRow(), 2);
            tcProp.setValueAt((Object) currentBlock.getMaintenance(), tcProp.getSelectedRow(), 3);
        }  
    }                                      

    private void toggleSwitchClicked(java.awt.event.MouseEvent evt) 
    {                                     
    
        if(toggleSwitch.isEnabled())
        {

          if(currentBlock.getStartNode() != null && currentBlock.getStartNode().getNodeType() == constData.NodeType.Switch)
          {
             ((SwitchNode)currentBlock.getStartNode()).setSwitchState(Math.abs(((SwitchNode)currentBlock.getStartNode()).getSwitchState() - 1));
          }  

          if(currentBlock.getStopNode() != null && currentBlock.getStopNode().getNodeType() == constData.NodeType.Switch)
          {
            ((SwitchNode)currentBlock.getStopNode()).setSwitchState(Math.abs(((SwitchNode)currentBlock.getStopNode()).getSwitchState() - 1));
          }
        }
            
    }                                      

    // User is able to load the PLC class file to the track controller
    private void loadProg(java.awt.event.MouseEvent evt) 
    {                          
        String className = "";

        try
        {   
            JFileChooser choosePLC = new JFileChooser();        // open file chooser to user
            int returnVal = choosePLC.showOpenDialog(null);     // Get the users option

            if(returnVal != JFileChooser.APPROVE_OPTION)        // if user canceled, return
            {
                return;
            }

            // get the name of the class from file chooser
            className = choosePLC.getSelectedFile().getName(); 

            // Load the class from the package.
            plcClass = Class.forName("TLTTC."+className.split("\\.")[0]);
                                                   
            // create a new instance of the PLC                                                                           
            plcObject = plcClass.newInstance();  

            // check that the loaded class is indeed a PLC.
            boolean isPLC = (boolean) plcClass.getMethod("verifyPLC").invoke(plcObject);

            // If invalid class file, display error
            if(!isPLC)
            {
                JOptionPane.showMessageDialog(null, "This is not a valid PLC!");
                return;
            }
        } 
        catch (ClassNotFoundException cnfe)
        {
            // if the class is not found, display error. Return
            JOptionPane.showMessageDialog(null, className + " is not a valid class file!");
            return;
        }
        catch (Exception e)
        {
            // If any other exception occurs display it to the user.
            JOptionPane.showMessageDialog(null, "This is not a valid PLC!\n"+ e.toString());
            return;
        }
        
        plcLoaded = true;               // if reached this point, PLC is loaded.
        loadPLC.setEnabled(false);      // disable PLC load button.
    }       

    public void refresh()
    {
        while(tcPropModel.getRowCount() > 0)        // remove all rows fro the controller properties
        {
            tcPropModel.removeRow(0);
        }

        if(selectedController == -1)               // no controller selected, return
        {
            return;
        }

        // loop over all blocks in the current controller
        for(Block b : controller.get(selectedController).values())
        {
            // determine if block is a crossing
            String crossing = (b.isCrossing()) ? (""+ b.getCrossing()) : "-"; 

            try
            {
                // Try to add the block, assuming there is a next block
                tcPropModel.addRow(new Object [] { b.getID(), 
                                                   b.getNextBlock(b.getStartNode()).getID(),
                                                   b.isOccupied(),
                                                   b.getMaintenance(),
                                                   crossing
                                                   });
            }
            catch(Exception e)
            {
                // if no next block, the block points to the yard
                tcPropModel.addRow(new Object [] { b.getID(), 
                                                   "yard",
                                                   b.isOccupied(),
                                                   b.getMaintenance(),
                                                   crossing
                                                   });
            }
        }

        // disable all buttons until next block is selected.
        enableCrossing.setEnabled(false);
        putMaintenance.setEnabled(false);
        toggleSwitch.setEnabled(false);
    }

    private javax.swing.JButton closeTC;
    private javax.swing.JButton curLine;
    private javax.swing.JButton enableCrossing;
    private javax.swing.JButton putMaintenance;
    private javax.swing.JButton toggleSwitch;
    private javax.swing.JButton loadPLC;

    private javax.swing.JTable controllerInfo;
    private javax.swing.JTable tcList;
    private javax.swing.JTable tcProp;
    private javax.swing.JTable trainInfo;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
}    