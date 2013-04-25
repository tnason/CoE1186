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

    private Hashtable<Integer, Hashtable<Integer, Block>> controller;
    private DefaultTableModel tcListModel, tcPropModel, controllerInfoModel;

    private int   selectedController = -1;
    private int   userSelectedBlock  = -1;
    private Block currentBlock       = null;

    private Object   plcObject = null;
    private Class<?> plcClass  = null;
    private boolean  plcLoaded = false;

    public TrackControllerView(Hashtable<Integer, Hashtable<Integer, Block>> allControllers) 
    {
        initComponents();

        tcListModel = (DefaultTableModel) tcList.getModel();
        tcPropModel = (DefaultTableModel) tcProp.getModel();
        controllerInfoModel = (DefaultTableModel) controllerInfo.getModel();

        controller = allControllers;
        
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

    public Object getPLC ()
    {
        return plcObject;
    }

    public Class<?> getPlcClass()
    {
        return plcClass;
    }

    public boolean PLCLoaded()
    {
        return plcLoaded;
    }
                
    private void changeLine(java.awt.event.MouseEvent evt) 
    {
        
    }

    private void closeTC(java.awt.event.MouseEvent evt) 
    {
        this.setVisible(false);
    }

    private void tcSelected(java.awt.event.MouseEvent evt) 
    {             
        enableCrossing.setEnabled(false);
        putMaintenance.setEnabled(false);
        toggleSwitch.setEnabled(false);

        String tcString = (String) tcList.getValueAt(tcList.getSelectedRow(), 0);
        selectedController = Integer.parseInt(tcString.split(" ")[2]);

        while(controllerInfoModel.getRowCount() > 0)
        {
            controllerInfoModel.removeRow(0);
        }

        controllerInfoModel.addRow(new Object [] { controller.get(selectedController).size(),
                                                   "num Trains 0"});
        refresh();
    } 

    private void blockSelected(java.awt.event.MouseEvent evt) 
    {                               
        int choosenBlockNum = (int) tcProp.getValueAt(tcProp.getSelectedRow(), 0);

        currentBlock = controller.get(selectedController).get(choosenBlockNum);

        userSelectedBlock = choosenBlockNum;

        putMaintenance.setEnabled(true);

        if(currentBlock.isCrossing() && !currentBlock.getCrossing())
        {
            enableCrossing.setEnabled(true);
        }
        else
        {
            enableCrossing.setEnabled(false);
        }

        /* Something about switches.
        if(currentBlock.isCrossing())
        {
            enableCrossing.setEnabled(true);
        }*/
    }                                                    

    private void enableCrossing(java.awt.event.MouseEvent evt) 
    {                                
       if(currentBlock.isCrossing() && enableCrossing.isEnabled())
       {
            ScheduledThreadPoolExecutor delayReset = new ScheduledThreadPoolExecutor(1);

            currentBlock.setCrossing(true);

            delayReset.schedule(new Runnable() 
                              {
                                public void run()
                                {
                                    currentBlock.setCrossing(false);
                                }
                            }, 30, TimeUnit.SECONDS);

       }
    }                               

    private void putMaintenanceClicked(java.awt.event.MouseEvent evt) 
    {    
      if(putMaintenance.isEnabled())
        {
            currentBlock.setMaintenance(!currentBlock.getMaintenance());
            tcProp.setValueAt((Object) currentBlock.isOccupied(), tcProp.getSelectedRow(), 2);
            tcProp.setValueAt((Object) currentBlock.getMaintenance(), tcProp.getSelectedRow(), 3);
        }  
    }                                      

    private void toggleSwitchClicked(java.awt.event.MouseEvent evt) 
    {                                     
        // TODO add your handling code here:
        if(toggleSwitch.isEnabled())
        {
            System.out.println("HERE switch");
        }
            
    }                                      

    private void loadProg(java.awt.event.MouseEvent evt) 
    {                          
        // TODO add your handling code here:
       String className = "";
        try
        {   
            JFileChooser choosePLC = new JFileChooser();
            int returnVal = choosePLC.showOpenDialog(null);

            if(returnVal != JFileChooser.APPROVE_OPTION)
            {
                return;
            }

            className = choosePLC.getSelectedFile().getName();

            plcClass = Class.forName("TLTTC."+className.split("\\.")[0]);
                                                                                       
            plcObject = plcClass.newInstance();  

            boolean isPLC = (boolean) plcClass.getMethod("verifyPLC").invoke(plcObject);

            if(!isPLC)
            {
                JOptionPane.showMessageDialog(null, "This is not a valid PLC!");
                return;
            }
        } 
        catch (ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(null, className + " is not a valid class file!");
            return;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "This is not a valid PLC!\n"+ e.toString());
            return;
        }
        
        plcLoaded = true;
        loadPLC.setEnabled(false);

        for(Method m : plcClass.getMethods())
            System.out.println(m);
    }       

    public void refresh()
    {
        while(tcPropModel.getRowCount() > 0)
        {
            tcPropModel.removeRow(0);
        }

        if(selectedController == -1)
        {
            return;
        }

        for(Block b : controller.get(selectedController).values())
        {
            String crossing = (b.isCrossing()) ? (""+ b.getCrossing()) : "-";
            try
            {
                tcPropModel.addRow(new Object [] { b.getID(), 
                                                   b.getNextBlock(b.getStartNode()).getID(),
                                                   b.isOccupied(),
                                                   b.getMaintenance(),
                                                   crossing
                                                   });
            }
            catch(Exception e)
            {
                tcPropModel.addRow(new Object [] { b.getID(), 
                                                   "yard",
                                                   b.isOccupied(),
                                                   b.getMaintenance(),
                                                   crossing
                                                   });
            }
        }

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