package Taxi;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Register_Taxi extends javax.swing.JInternalFrame {

    Connection conn = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    int i_no = 0;

    public Register_Taxi() {
        initComponents();

        txt_DateReceived.setMaxSelectableDate(new java.util.Date());

        try {

            conn = DatabaseConnection.connectToDb();
            st = (Statement) conn.createStatement();
            IncVehicleNo();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void IncVehicleNo() {
        try {
            String sql = "select max(vehicle_no) from vehicle";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            if (rs != null) {
                i_no = Integer.parseInt(rs.getString("max(vehicle_no)"));
                i_no++;
            } else {
                i_no = 1;
            }

            txt_vehicleNo.setText("" + i_no);
            txt_vehicleNo.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(Register_Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_vehicleNo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_Owner = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_make = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txt_regNo = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txt_Driver = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txt_DateReceived = new com.toedter.calendar.JDateChooser();
        jLabel54 = new javax.swing.JLabel();
        txt_noPass = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        cmb_condition = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setText("jButton1");
        jButton1.setName("jButton1"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        setBackground(new java.awt.Color(0, 0, 0));
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setName("jPanel3"); // NOI18N

        jPanel8.setBackground(new java.awt.Color(239, 239, 247));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Taxi", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Modern No. 20", 1, 18))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jPanel12.setBackground(new java.awt.Color(137, 170, 181));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)), "Please Fill In Details Below", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Monotype Corsiva", 1, 18))); // NOI18N
        jPanel12.setName("jPanel12"); // NOI18N

        jLabel5.setText("Vehicle No.:");
        jLabel5.setName("jLabel5"); // NOI18N

        txt_vehicleNo.setName("txt_vehicleNo"); // NOI18N

        jLabel8.setText("Owner Name:");
        jLabel8.setName("jLabel8"); // NOI18N

        txt_Owner.setName("txt_Owner"); // NOI18N

        jLabel9.setText("Make:");
        jLabel9.setName("jLabel9"); // NOI18N

        txt_make.setName("txt_make"); // NOI18N

        jLabel30.setText("No. Plate/Reg No.");
        jLabel30.setName("jLabel30"); // NOI18N

        txt_regNo.setName("txt_regNo"); // NOI18N
        txt_regNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_regNoActionPerformed(evt);
            }
        });
        txt_regNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_regNoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_regNoKeyTyped(evt);
            }
        });

        jLabel31.setText("Driver Name:");
        jLabel31.setName("jLabel31"); // NOI18N

        txt_Driver.setName("txt_Driver"); // NOI18N

        jLabel53.setText("Date Registered:");
        jLabel53.setName("jLabel53"); // NOI18N

        txt_DateReceived.setBackground(new java.awt.Color(255, 255, 255));
        txt_DateReceived.setForeground(new java.awt.Color(255, 51, 51));
        txt_DateReceived.setAutoscrolls(true);
        txt_DateReceived.setDateFormatString("yyyy-MM-dd");
        txt_DateReceived.setName("txt_DateReceived"); // NOI18N
        txt_DateReceived.setOpaque(false);

        jLabel54.setText("No. of Pass:");
        jLabel54.setName("jLabel54"); // NOI18N

        txt_noPass.setName("txt_noPass"); // NOI18N
        txt_noPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_noPassActionPerformed(evt);
            }
        });
        txt_noPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_noPassKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_noPassKeyTyped(evt);
            }
        });

        jLabel57.setText("Status/Condition:");
        jLabel57.setName("jLabel57"); // NOI18N

        cmb_condition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----------", "New Good", "New Worn/Broken", "Old Good", "Old Worn/Broken" }));
        cmb_condition.setName("cmb_condition"); // NOI18N

        jLabel58.setText("Description:");
        jLabel58.setName("jLabel58"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        jButton2.setText("Save");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        jButton5.setText("Cancel");
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel57)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(cmb_condition, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel53))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(txt_Owner, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel31)))
                                .addGap(20, 20, 20))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(txt_vehicleNo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(txt_regNo, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel9)
                                .addGap(75, 75, 75))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_Driver, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_DateReceived, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel58))
                                .addGap(17, 17, 17)))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addComponent(txt_make)
                                .addGap(64, 64, 64))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_noPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(37, Short.MAX_VALUE))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_vehicleNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_make, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_regNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30)))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(txt_Driver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel54)
                        .addComponent(txt_noPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(txt_Owner, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton5))
                        .addGap(85, 85, 85))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel53)
                                .addComponent(cmb_condition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel57))
                            .addComponent(txt_DateReceived, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Taxis-Kenya-Nairobi.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date2 = ((JTextField) txt_DateReceived.getDateEditor().getUiComponent()).getText();
        if (txt_Owner.getText().isEmpty() || txt_regNo.getText().isEmpty() || txt_Driver.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please ensure every field has the needed data!!");
        } else if (txt_DateReceived.getDate().after(Taxi_Main.txt_dated.getDate())) {
            JOptionPane.showMessageDialog(null, "Error in Date Selected, No Future Dates Allowed!!", "Future Dates", JOptionPane.ERROR_MESSAGE);

        } else {
            try {

                String sql = "INSERT INTO vehicle (vehicle_no,no_blade,make,owner,driver,no_pass,available_spc,date_registered,v_condition) VALUES(?,?,?,?,?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, txt_vehicleNo.getText());
                pst.setString(2, txt_regNo.getText());
                pst.setString(3, txt_make.getText());
                pst.setString(4, txt_Owner.getText());
                pst.setString(5, txt_Driver.getText());
                pst.setString(6, txt_noPass.getText());
                pst.setString(7, txt_noPass.getText());
                pst.setString(8, date2);
                pst.setString(9, cmb_condition.getSelectedItem().toString());

                pst.execute();
                JOptionPane.showMessageDialog(null, "Saved");

                txt_vehicleNo.setText("");
                txt_Owner.setText("");
                jTextArea1.setText("");
                txt_make.setText("");
                txt_regNo.setText("");
                txt_Driver.setText("");
                txt_noPass.setText("");

                cmb_condition.setSelectedIndex(0);
                IncVehicleNo();
            } catch (SQLException ex) {
                Logger.getLogger(Register_Taxi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        txt_Owner.setText(null);
        txt_make.setText(null);
        txt_regNo.setText(null);
        txt_Driver.setText(null);
        txt_DateReceived.setDate(null);
        txt_noPass.setText(null);
        cmb_condition.setSelectedIndex(0);
        jTextArea1.setText(null);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_regNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_regNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_regNoActionPerformed

    private void txt_regNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_regNoKeyReleased

// Declare variables
        String str;
        Pattern checkPattern;
        Matcher match;
        boolean check;

        // Declare pattern of special characters
        checkPattern = Pattern.compile("[^a-zA-Z0-9]");
        // Declare matcher to match with String
        match = checkPattern.matcher(txt_regNo.getText());
        // Use find( ) function to check
        check = match.find();
        // Check whether String contains any special characters or not and display
        // accordingly
        if (check) {
            System.out.println("Given String contains special character!");
            JOptionPane.showMessageDialog(null, "Please enter a valid Number Plate", "User Input Error Message", JOptionPane.ERROR_MESSAGE);
            txt_regNo.setText("");

        } else {
            System.out.println("Given String doesn't contain any special characters");
        }

    }//GEN-LAST:event_txt_regNoKeyReleased

    private void txt_noPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_noPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noPassActionPerformed

    private void txt_noPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_noPassKeyReleased

    }//GEN-LAST:event_txt_noPassKeyReleased

    private void txt_regNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_regNoKeyTyped

    }//GEN-LAST:event_txt_regNoKeyTyped

    private void txt_noPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_noPassKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txt_noPass.setEditable(false);
            JOptionPane.showMessageDialog(null, "Only numbers allowed");
            txt_noPass.setEditable(true);
        } else {
            txt_noPass.setEditable(true);
        }
    }//GEN-LAST:event_txt_noPassKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmb_condition;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private com.toedter.calendar.JDateChooser txt_DateReceived;
    private javax.swing.JTextField txt_Driver;
    private javax.swing.JTextField txt_Owner;
    private javax.swing.JTextField txt_make;
    private javax.swing.JTextField txt_noPass;
    private javax.swing.JTextField txt_regNo;
    private javax.swing.JTextField txt_vehicleNo;
    // End of variables declaration//GEN-END:variables

}
