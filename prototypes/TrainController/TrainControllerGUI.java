import *;

+@SuppressWarnings("serial") 
public class TrainControllerGUI extends JFrame {
  TrainControllerModule module;


    public TrainControllerGUI(TrainControllerModule mod) {
        module = mod;
		initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        trainContDropdown = new javax.swing.JComboBox();
        doorControlPanel = new javax.swing.JPanel();
        doorControlButton = new javax.swing.JButton();
        lightControlPanel = new javax.swing.JPanel();
        lightControlButton1 = new javax.swing.JButton();
        lightControlButton2 = new javax.swing.JButton();
        internalLightsLabel = new javax.swing.JLabel();
        externalLightsLabel = new javax.swing.JLabel();
        temperatureControlPanel = new javax.swing.JPanel();
        tempSetpointSetButton = new javax.swing.JButton();
        currentTempText = new javax.swing.JTextField();
        currentTempLabel = new javax.swing.JLabel();
        degreesSymbolText1 = new javax.swing.JLabel();
        tempSetpointText = new javax.swing.JTextField();
        tempSetpointLabel = new javax.swing.JLabel();
        degreesSymbolText2 = new javax.swing.JLabel();
        powerControlPanel = new javax.swing.JPanel();
        authorityLabel = new javax.swing.JLabel();
        authorityText = new javax.swing.JTextField();
        velocitySetter = new javax.swing.JSpinner();
        accelerateButton = new javax.swing.JButton();
        brakeButton = new javax.swing.JButton();
        metersText = new javax.swing.JLabel();
        mphText2 = new javax.swing.JLabel();
        emergencyBrakeButton = new javax.swing.JButton();
        velocityLabel = new javax.swing.JLabel();
        velocityText = new javax.swing.JTextField();
        mphText = new javax.swing.JLabel();
        gpsPanel = new javax.swing.JPanel();
        gpsConnectButton = new javax.swing.JButton();
        nextStationPanel = new javax.swing.JPanel();
        nextStationAnnounceButton2 = new javax.swing.JButton();
        nextStationText2 = new javax.swing.JTextField();
        faultsPanel = new javax.swing.JPanel();
        engineFailureText = new javax.swing.JTextField();
        pickupFailureText = new javax.swing.JTextField();
        brakeFailureText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        powerTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        trainContDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainContDropdownActionPerformed(evt);
            }
        });

        doorControlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Door Control"));

        doorControlButton.setText("Open Doors");

        javax.swing.GroupLayout doorControlPanelLayout = new javax.swing.GroupLayout(doorControlPanel);
        doorControlPanel.setLayout(doorControlPanelLayout);
        doorControlPanelLayout.setHorizontalGroup(
            doorControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, doorControlPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(doorControlButton)
                .addGap(27, 27, 27))
        );
        doorControlPanelLayout.setVerticalGroup(
            doorControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doorControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(doorControlButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lightControlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Light Control"));

        lightControlButton1.setText("Turn On");

        lightControlButton2.setText("Turn On");

        internalLightsLabel.setText("Internal Lights");

        externalLightsLabel.setText("External Lights");

        javax.swing.GroupLayout lightControlPanelLayout = new javax.swing.GroupLayout(lightControlPanel);
        lightControlPanel.setLayout(lightControlPanelLayout);
        lightControlPanelLayout.setHorizontalGroup(
            lightControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lightControlPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(lightControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lightControlButton2)
                    .addComponent(externalLightsLabel)
                    .addComponent(internalLightsLabel)
                    .addComponent(lightControlButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lightControlPanelLayout.setVerticalGroup(
            lightControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lightControlPanelLayout.createSequentialGroup()
                .addComponent(internalLightsLabel)
                .addGap(9, 9, 9)
                .addComponent(lightControlButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(externalLightsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lightControlButton2)
                .addContainerGap())
        );

        temperatureControlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Temperature Control"));

        tempSetpointSetButton.setText("Set");

        currentTempText.setEditable(false);

        currentTempLabel.setText("Temperature");

        degreesSymbolText1.setText("째F");

        tempSetpointLabel.setText("Setpoint");

        degreesSymbolText2.setText("째F");

        javax.swing.GroupLayout temperatureControlPanelLayout = new javax.swing.GroupLayout(temperatureControlPanel);
        temperatureControlPanel.setLayout(temperatureControlPanelLayout);
        temperatureControlPanelLayout.setHorizontalGroup(
            temperatureControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(temperatureControlPanelLayout.createSequentialGroup()
                .addGroup(temperatureControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentTempLabel)
                    .addGroup(temperatureControlPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(currentTempText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(degreesSymbolText2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(temperatureControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(temperatureControlPanelLayout.createSequentialGroup()
                        .addComponent(tempSetpointSetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, temperatureControlPanelLayout.createSequentialGroup()
                        .addGroup(temperatureControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(temperatureControlPanelLayout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addComponent(tempSetpointLabel))
                            .addComponent(tempSetpointText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(degreesSymbolText1)
                        .addGap(8, 8, 8))))
        );
        temperatureControlPanelLayout.setVerticalGroup(
            temperatureControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(temperatureControlPanelLayout.createSequentialGroup()
                .addGroup(temperatureControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentTempLabel)
                    .addComponent(tempSetpointLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(temperatureControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentTempText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tempSetpointText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degreesSymbolText1)
                    .addComponent(degreesSymbolText2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempSetpointSetButton))
        );

        powerControlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Power Control"));

        authorityLabel.setText("Authority");

        authorityText.setEditable(false);

        velocitySetter.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), Double.valueOf(0.0d), null, Double.valueOf(1.0d)));

        accelerateButton.setText("Accelerate");

        brakeButton.setText("Brake");

        metersText.setText("m");

        mphText2.setText("mph");

        emergencyBrakeButton.setText("E-Brake");

        velocityLabel.setText("Velocity");

        velocityText.setEditable(false);

        mphText.setText("mph");

        javax.swing.GroupLayout powerControlPanelLayout = new javax.swing.GroupLayout(powerControlPanel);
        powerControlPanel.setLayout(powerControlPanelLayout);
        powerControlPanelLayout.setHorizontalGroup(
            powerControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, powerControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(velocityText, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(powerControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(powerControlPanelLayout.createSequentialGroup()
                        .addGroup(powerControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(velocityLabel)
                            .addComponent(mphText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(velocitySetter, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mphText2))
                    .addGroup(powerControlPanelLayout.createSequentialGroup()
                        .addGroup(powerControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(powerControlPanelLayout.createSequentialGroup()
                                .addComponent(authorityText, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(metersText))
                            .addComponent(authorityLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(powerControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(accelerateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(brakeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emergencyBrakeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );
        powerControlPanelLayout.setVerticalGroup(
            powerControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(powerControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(powerControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(powerControlPanelLayout.createSequentialGroup()
                        .addComponent(accelerateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brakeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emergencyBrakeButton))
                    .addGroup(powerControlPanelLayout.createSequentialGroup()
                        .addComponent(velocityLabel)
                        .addGap(3, 3, 3)
                        .addGroup(powerControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(velocityText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mphText)
                            .addComponent(velocitySetter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mphText2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(authorityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(powerControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(authorityText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metersText))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gpsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("GPS"));

        gpsConnectButton.setText("Connect");

        javax.swing.GroupLayout gpsPanelLayout = new javax.swing.GroupLayout(gpsPanel);
        gpsPanel.setLayout(gpsPanelLayout);
        gpsPanelLayout.setHorizontalGroup(
            gpsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gpsPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(gpsConnectButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gpsPanelLayout.setVerticalGroup(
            gpsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gpsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gpsConnectButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nextStationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Next Station"));

        nextStationAnnounceButton2.setText("Announce");

        nextStationText2.setEditable(false);

        javax.swing.GroupLayout nextStationPanelLayout = new javax.swing.GroupLayout(nextStationPanel);
        nextStationPanel.setLayout(nextStationPanelLayout);
        nextStationPanelLayout.setHorizontalGroup(
            nextStationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nextStationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nextStationText2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nextStationAnnounceButton2)
                .addContainerGap())
        );
        nextStationPanelLayout.setVerticalGroup(
            nextStationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nextStationPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(nextStationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextStationAnnounceButton2)
                    .addComponent(nextStationText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        faultsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Faults"));

        engineFailureText.setEditable(false);
        engineFailureText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        engineFailureText.setText("Train Engine Failure");

        pickupFailureText.setEditable(false);
        pickupFailureText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pickupFailureText.setText("Signal Pickup Failure");

        brakeFailureText.setEditable(false);
        brakeFailureText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        brakeFailureText.setText("Brake Failiure");

        javax.swing.GroupLayout faultsPanelLayout = new javax.swing.GroupLayout(faultsPanel);
        faultsPanel.setLayout(faultsPanelLayout);
        faultsPanelLayout.setHorizontalGroup(
            faultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, faultsPanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(faultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pickupFailureText)
                    .addComponent(brakeFailureText)
                    .addComponent(engineFailureText, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        faultsPanelLayout.setVerticalGroup(
            faultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(faultsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(engineFailureText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pickupFailureText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(brakeFailureText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        powerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Time", "Velocity (mph)", "Setpoint (mph)", "Power (W)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        powerTable.setName("");
        jScrollPane1.setViewportView(powerTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(doorControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trainContDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(temperatureControlPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lightControlPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nextStationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(powerControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(faultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gpsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(trainContDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(doorControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gpsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(nextStationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(powerControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(faultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lightControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(temperatureControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 113, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trainContDropdownActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

	
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrainControllerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrainControllerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrainControllerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrainControllerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		final TrainControllerGUI g = this;
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                g.setVisible(true);
            }
        });
    }

    private javax.swing.JButton accelerateButton;
    private javax.swing.JLabel authorityLabel;
    private javax.swing.JTextField authorityText;
    private javax.swing.JButton brakeButton;
    private javax.swing.JTextField brakeFailureText;
    private javax.swing.JLabel currentTempLabel;
    private javax.swing.JTextField currentTempText;
    private javax.swing.JLabel degreesSymbolText1;
    private javax.swing.JLabel degreesSymbolText2;
    private javax.swing.JButton doorControlButton;
    private javax.swing.JPanel doorControlPanel;
    private javax.swing.JButton emergencyBrakeButton;
    private javax.swing.JTextField engineFailureText;
    private javax.swing.JLabel externalLightsLabel;
    private javax.swing.JPanel faultsPanel;
    private javax.swing.JButton gpsConnectButton;
    private javax.swing.JPanel gpsPanel;
    private javax.swing.JLabel internalLightsLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lightControlButton1;
    private javax.swing.JButton lightControlButton2;
    private javax.swing.JPanel lightControlPanel;
    private javax.swing.JLabel metersText;
    private javax.swing.JLabel mphText;
    private javax.swing.JLabel mphText2;
    private javax.swing.JButton nextStationAnnounceButton2;
    private javax.swing.JPanel nextStationPanel;
    private javax.swing.JTextField nextStationText2;
    private javax.swing.JTextField pickupFailureText;
    private javax.swing.JPanel powerControlPanel;
    private javax.swing.JTable powerTable;
    private javax.swing.JLabel tempSetpointLabel;
    private javax.swing.JButton tempSetpointSetButton;
    private javax.swing.JTextField tempSetpointText;
    private javax.swing.JPanel temperatureControlPanel;
    private javax.swing.JComboBox trainContDropdown;
    private javax.swing.JLabel velocityLabel;
    private javax.swing.JSpinner velocitySetter;
    private javax.swing.JTextField velocityText;
}
