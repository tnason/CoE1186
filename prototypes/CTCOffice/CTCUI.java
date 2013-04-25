/**
 *
 * @author seanmoore
 */
 
package TLTTC;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.concurrent.*;
import java.lang.reflect.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("unchecked")
public class CTCUI extends javax.swing.JFrame {

    /**
     * Creates new form CTCUI
     */
     @SuppressWarnings("rawtypes")
    private CTCController _controller;
    private boolean _FixedBlockIsActive = true;
    private TrainTableDataModel _dataModel;
    private Integer selectedRouteRow;
    private boolean routeRowIsSelected = false;
    private DefaultListModel _routeModel;
    private String _currentSelectedLine = "green";
    private int _count = 0;
    
     
    public CTCUI(CTCController controller) {
        _controller = controller;
        _dataModel = new TrainTableDataModel();
        _routeModel = new DefaultListModel();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(_dataModel);
        jTable1.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent event)
            {
                tableSelectionListener(event);
            }
        });
        jTable1.setRowSelectionAllowed(true);
        jTable1.setColumnSelectionAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jList1.setModel(_routeModel);
        jList1.getSelectionModel().addListSelectionListener( new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent event)
            {
                listSelectionListener(event);
            };
        });
        jScrollPane2.setViewportView(jList1);

        jButton1.setText("Dispatch Train");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Emergency Closing");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Maintenance Closing");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Selected Line is: Green");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Train #:");

        jLabel2.setText("Speed");

        jLabel3.setText("Authority");

        jLabel4.setText("Schedule");

        jTextField1.setText("0.0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton5.setText("Set");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField2.setText("0");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton6.setText("Set");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField3.setText("Next Station...");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton7.setText("Update Schedule");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Update Route");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextField4.setText("Train 0");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(0, 66, Short.MAX_VALUE))
                                    .addComponent(jTextField4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // jButton2 = Scheduled Closing
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        // this should pop up some sort of window to select blocks for closing
        // TODO: Implement & perform some sort of scheduling mechanism
        ArrayList<Integer> bIDs = new ArrayList<Integer>();
        bIDs.add(0);
        _controller.closeTrackSections(bIDs);
    }

    // jButton1 = Dispatch Train
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        _controller.dispatchTrain(_currentSelectedLine);
    }

    // jButton3 = Emergency Closing
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // this should pop up some sort of window to select blocks for closing
        // TODO: Implement
        ArrayList<Integer> bIDs = new ArrayList<Integer>();
        bIDs.add(0);
        _controller.closeTrackSections(bIDs);
    }//GEN-LAST:event_jButton2ActionPerformed

    // jButton4 = Toggle train Line
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        if (_currentSelectedLine == "green")
        {
            _currentSelectedLine = "red";
            jButton4.setText("Selected line is: red.");
        }
        else
        {
            _currentSelectedLine = "green";
            jButton4.setText("Selected line is: green.");
        }
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    // jButton5 = setSpeed
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        // grab the value of the "speed" textfield, check it as it's converted to a Double, and if the speed is valid, pass along that info to the controller to handle
        String speedText = jTextField1.getText();
        Integer trainID = getCurrentTrainSelection();
        if (trainID != null)
        {
            try
            {
                Double speed = Double.parseDouble(speedText);
                _controller.setSpeedForTrain(trainID, speed);
            }
            catch(Exception e)
            {
                // right now, we'll fail silently. Could clear the field or reset it.
            }
        }
    }

    // jButton6 = set authority
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt)
    {
        String authorityText = jTextField2.getText();
        Integer trainID = getCurrentTrainSelection();
        if (trainID != null)
        {
            if (_FixedBlockIsActive)
            {
                try
                {
                    Integer fixedAuthority = Integer.parseInt(authorityText);
                    _controller.setAuthorityForTrain(trainID, fixedAuthority);
                }
                catch(Exception e)
                {

                }
            }
            else
            {
                try
                {
                    Double movingAuthority = Double.parseDouble(authorityText);
                    _controller.setAuthorityForTrain(trainID, movingAuthority);
                }
                catch(Exception e)
                {
                    
                }
            }
        }
    }

    // jButton7 = Update Schedule
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    // jButton8 - Update Route
     @SuppressWarnings("cast")
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        // first check if the input is valid - should either be Route XX or XX
        // TODO - actually implement above, since I forgot to add the text field
        Integer blockID = 0;
        int index;
        // now, check if anything is selected

        if (routeRowIsSelected)
        {
            index = selectedRouteRow;
            _routeModel.insertElementAt((String) "Block " + blockID, index);
        }
        else // otherwise, set it to the length of the list
        {
            _routeModel.addElement((String) "Block " + blockID);
        }
        
        // finally, clear the text
        // which, again, I'll actually need to implement when I add the field...
    }
    
    private void tableSelectionListener(javax.swing.event.ListSelectionEvent event)
    {
        // grab the row data and set it to the detail views
        int row = event.getFirstIndex();
        jTextField4.setText((String) _dataModel.getValueAt(row, 0)); // setting the train 
        jTextField1.setText( "" + (Double) _dataModel.getValueAt(row, 3)); // setting the speed
        try 
        {
            jTextField2.setText( "" + (Integer) _dataModel.getValueAt(row, 4)); // setting authority
        }
        catch (Exception e)
        {
            jTextField2.setText( "" + (Double) _dataModel.getValueAt(row, 4)); // setting authority
        }
        jTextField3.setText((String) _dataModel.getValueAt(row, 5)); // setting station
        
        Integer tID = getCurrentTrainSelection((String) _dataModel.getValueAt(row, 0));
        // strip the route list and rebuild it with the current selection
        try
        {
            _routeModel.removeAllElements();
            ArrayList<Integer> route = _controller.getRouteListingForTrain(tID);
            if (route != null)
            {
                for (Integer block : route)
                {
                    _routeModel.addElement((String) "Block " + block);
                }
            }
        }
        catch (Exception e)
        {
            // fail silently
        }
    }

    private void listSelectionListener(javax.swing.event.ListSelectionEvent event)
    {
        selectedRouteRow = event.getFirstIndex();
        routeRowIsSelected = true;
    }

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CTCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CTCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CTCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CTCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        // java.awt.EventQueue.invokeLater(new Runnable() {
//             public void run() {
//                 new CTCUI().setVisible(true);
//             }
//         });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
    
    // helper methods for private use
    private Integer getCurrentTrainSelection()
    {
        String trainText = jTextField4.getText();
        String[] splitText = trainText.split(" ");
        try
        {
            return Integer.parseInt(splitText[1]);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    private Integer getCurrentTrainSelection(String selection)
    {
        try
        {
            String[] splitText = selection.split(" ");
            return Integer.parseInt(splitText[1]);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    private ArrayList<Object> parseValuesFromTrainModel( TrainViewModel train)
    {
        ArrayList<Object> vals = new ArrayList<Object>();
        vals.add("Train " + train.getTrainID()); // train ID comes first
        vals.add(train.getLine());
        vals.add(train.getCurrentBlock());
        vals.add(train.getSpeed());
        if (_FixedBlockIsActive)
        {
            vals.add(train.getFixedBlockAuthority());
        }
        else
        {
            vals.add(train.getMovingBlockAuthority());
        }
        vals.add(train.getNextStation());
        vals.add(train.getScheduleStatus());
        
        System.out.print(vals);
        
        return vals;
    }
    
    // access methods for Controller updating
    public void setDataModelForTable( TrainViewModel train )
    {
        // check if it exists, first
        boolean trainExists = false;
        int   row = _count;
        for (int i = 0; i < _dataModel.getRowCount(); i++)
        {
            if (getCurrentTrainSelection((String) _dataModel.getValueAt(i, 0)) == train.getTrainID() )
            {
                // it exists!
                trainExists = true;
                row = i;
            }
        }
        
        if (trainExists)
        {
            _dataModel.setValuesForRow(parseValuesFromTrainModel(train), row);
        }
        else
        {   
            _dataModel.setValuesForRow(parseValuesFromTrainModel(train), row);
            _count++;
        }
    }
    
    public String getCurrentLine ()
    {
        return _currentSelectedLine;
    }
}

class TrainTableDataModel extends AbstractTableModel
{
    private String [] columnNames = new String[] { "Train #", "Line", "Location", "Speed", "Authority", "Next Station", "Schedule" };
    private Object [][] data = new Object [10][7];
    
    public int getColumnCount()
    {
        return columnNames.length;
    }
    
    public int getRowCount()
    {
        if (data != null)
        {
            return data.length;
        }
        else
        {
            return 0;
        }
        
    }
    
    public String getColumnName(int col)
    {
        return columnNames[col];
    }
    
    // This method will be used to iterate through a selected row and populate the detail
    public Object getValueAt(int row, int col)
    {   
        if ( row < getRowCount() && col < getColumnCount() && row >= 0 && col >= 0 )
        {
            return data[row][col];
        }
        else return null;
    }
    
    public boolean isCellEditable(int row, int col)
    {
        return true;
    }
    
    // use this to update models that already exist
    public void setValueAt(Object val, int row, int col )
    {
        if ( row < getRowCount() && col < getColumnCount() )
        {
            data[row][col] = val;
            fireTableCellUpdated(row, col);
        }
        else
        {
            
        }
    }
    
    // use this to update an entire row at once
    public void setValuesForRow(ArrayList<Object> vals, int row)
    {
        // perform a check to ensure lengths are equals
        if (vals.size() == getColumnCount())
        {
            for (int i = 0; i < vals.size(); i++)
            {
                setValueAt(vals.get(i), row, i);
            }
        }
        else 
        {
            System.out.println("NOT SAME SIZE");
        }
    } 
}

