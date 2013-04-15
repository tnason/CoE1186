package TLTTC;

import javax.swing.*;

@SuppressWarnings("serial")  
public class TrainControllerGUI extends javax.swing.JFrame {
  private int trainID;
  private double velocity;
  private TrainController tc;
  private TrainControllerModule module;
  private JButton jButton1;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JTextField jTextField1;
  
  public TrainControllerGUI(int id, TrainController t, TrainControllerModule m) {
    initComponents();
    trainID = id;
    module = m;
    tc = t;
    jLabel4.setText("Train " + id);
  }
  
  private void initComponents() {
    jButton1 = new javax.swing.JButton();
    jTextField1 = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    
    jButton1.setText("Enter");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });
    
    jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    jTextField1.setText("0");
    jTextField1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1ActionPerformed(evt);
      }
    });
    
    jLabel1.setText("Train Operator Velocity");
    jLabel1.setToolTipText("");
    
    jLabel2.setText("m/s");
    
    jLabel3.setText("No commands sent.");
    
    jLabel4.setText("Train x");
    
    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
                              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                                    .addComponent(jLabel4)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(jLabel1))
                                                        .addGroup(layout.createSequentialGroup()
                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                            .addComponent(jLabel2)
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                            .addComponent(jButton1))
                                                                                .addComponent(jLabel3))
                                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                            .addContainerGap())
                             );
    layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                          .addContainerGap()
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                      .addComponent(jLabel1)
                                                      .addComponent(jLabel4))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                      .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addComponent(jButton1)
                                                      .addComponent(jLabel2))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addComponent(jLabel3)
                                          .addContainerGap())
                           );
    
    pack();
  }
  
  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    velocity = Long.parseLong(jTextField1.getText());
    if (velocity < 0){
      velocity = 0;
    }
    jLabel3.setText("Sent velocity of " + velocity + " m/s.");
    tc.trainOperatorVelocity = velocity;
    module.sendPower();
  }
  
  private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    
  }
  
  public void closeGUI(){
    setVisible(false);
    dispose();
  }
  
  public void openGUI(){
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(TrainControllerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(TrainControllerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(TrainControllerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(TrainControllerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    
    final TrainControllerGUI gui = this;
    java.awt.EventQueue.invokeLater(new Runnable() {
      
      public void run() {
        gui.setVisible(true);
      }
    });
  }
}
