package TLTTC;

import java.util.*;
import javax.swing.table.*;
import java.util.concurrent.*;

@SuppressWarnings("serial")  
public class TrackControllerView extends javax.swing.JFrame 
{

    private Hashtable<Integer, Hashtable<Integer, Block>> controller;
    private DefaultTableModel tcListModel, tcPropModel, controllerInfoModel;

    private int selectedController = -1;
    private int userSelectedBlock  = -1;
    private Block currentBlock     = null;

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
    private void initComponents() 
    {
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        
        closeTC = new javax.swing.JButton();
        curLine = new javax.swing.JButton();
        enableCrossing = new javax.swing.JButton();
        toggleSwitch = new javax.swing.JButton();
        putMaintenance = new javax.swing.JButton();

    
        tcList = new javax.swing.JTable();
        tcProp = new javax.swing.JTable();
        trainInfo = new javax.swing.JTable();
        controllerInfo = new javax.swing.JTable();

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
                "Block Number", "Track State", "Next Block"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
        tcProp.getColumnModel().getColumn(0).setHeaderValue("Block Number");
        tcProp.getColumnModel().getColumn(1).setResizable(false);
        tcProp.getColumnModel().getColumn(2).setResizable(false);
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
                    .addComponent(closeTC))
                .addGap(20, 20, 20))
        );

        closeTC.getAccessibleContext().setAccessibleName("closeTC");
        curLine.getAccessibleContext().setAccessibleName("curLine");

        enableCrossing.setEnabled(false);
        putMaintenance.setEnabled(false);
        toggleSwitch.setEnabled(false);

        pack();
    }                
                  
    private void changeLine(java.awt.event.MouseEvent evt) 
    {
        
    }

    private void closeTC(java.awt.event.MouseEvent evt) 
    {
        this.dispose();
    }

    private void tcSelected(java.awt.event.MouseEvent evt) 
    {             
        String tcString = (String) tcList.getValueAt(tcList.getSelectedRow(), 0);
        selectedController = Integer.parseInt(tcString.split(" ")[2]);

        while(tcPropModel.getRowCount() > 0)
        {
            tcPropModel.removeRow(0);
        }

        while(controllerInfoModel.getRowCount() > 0)
        {
            controllerInfoModel.removeRow(0);
        }
            
        for(Block b : controller.get(selectedController).values())
        {
            try
            {
                tcPropModel.addRow(new Object [] { b.getID(), 
                                                   b.isOccupied(),
                                                   b.getNextBlock(b.getStartNode()).getID()});
            }
            catch(Exception e)
            {
                tcPropModel.addRow(new Object [] { b.getID(), 
                                                   b.isOccupied(),
                                                   "none"});
            }
        }

        controllerInfoModel.addRow(new Object [] { controller.get(selectedController).size(),
                                                   "num Trains 0"});
    }                                                  

    private void enableCrossing(java.awt.event.MouseEvent evt) {                                
       //put block in maintenance
       ScheduledThreadPoolExecutor delayReset = new ScheduledThreadPoolExecutor(1);

       System.out.println("HERE start");

       delayReset.schedule(new Runnable() 
                           {
                                public void run()
                                {
                                    System.out.println("HERE done");
                                    //take block out of maintenance
                                }
                            }, 30, TimeUnit.SECONDS);
    }                               

    private void putMaintenanceClicked(java.awt.event.MouseEvent evt) {    

        System.out.println("HERE " + currentBlock.getID()+ " " + userSelectedBlock + " " + selectedController);                                   
        if(!currentBlock.isOccupied())
        {
            currentBlock.setMaintenance(true);
            tcProp.setValueAt((Object) currentBlock.isOccupied(), tcProp.getSelectedRow(), 1);
            putMaintenance.setEnabled(false);   
        }
    }                                      

    private void toggleSwitchClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        if(toggleSwitch.isEnabled())
        System.out.println("HERE switch");
    }                                    

    private void blockSelected(java.awt.event.MouseEvent evt) {                               
        int choosenBlockNum = (int) tcProp.getValueAt(tcProp.getSelectedRow(), 0);

        currentBlock = controller.get(selectedController).get(choosenBlockNum);

        userSelectedBlock = choosenBlockNum;

        System.out.println("HERE " + currentBlock.getID()+ " " + userSelectedBlock + " " + selectedController + " "+currentBlock.isOccupied());                                   
        
        if(currentBlock.isCrossing())
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

        if(!currentBlock.isOccupied())
        {
            putMaintenance.setEnabled(true);
        }
        else
        {
            putMaintenance.setEnabled(false);
        }
    }     

    private javax.swing.JButton closeTC;
    private javax.swing.JButton curLine;
    private javax.swing.JButton enableCrossing;
    private javax.swing.JButton putMaintenance;
    private javax.swing.JButton toggleSwitch;

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
    
