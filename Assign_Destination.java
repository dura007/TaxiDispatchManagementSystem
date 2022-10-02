package Taxi;

import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class Assign_Destination extends javax.swing.JInternalFrame {

    Connection conn = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    int i_no = 0;

    public Assign_Destination() {
        initComponents();

        //txt_DateAssigned.setMaxSelectableDate(new java.util.Date());
     //   txt_DateAssigned.setMaxSelectableDate(new java.util.Date());

        try {

            conn = DatabaseConnection.connectToDb();
            st = (Statement) conn.createStatement();
            UpdateIssuedTable();
            UpdateAvailableTable();
            setRouteCombo();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void UpdateIssuedTable() {
        try {
            String clrd = "Unassigned";
            String sql = "select no_blade as 'Number Blade' from vehicle WHERE route='" + clrd + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table_issued.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            Logger.getLogger(Assign_Destination.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void UpdateAvailableTable() {
        try {
            String clrd = "Unassigned";
            String sql = "select no_blade AS 'Number Blade',route as Destination from vehicle WHERE route!='" + clrd + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table_available.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            Logger.getLogger(Assign_Destination.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 private void setRouteCombo(){
         try {
            String sql1 = "select * from routes";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            while (rs.next()) {
                
                cmb_condition.addItem(rs.getString("Route"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_issued = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_vehicleNo = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txt_owner = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_BladeNo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_make = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txt_driver = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        cmb_condition = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        txt_DateAssigned = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_available = new javax.swing.JTable();

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
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Assign Destination", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Modern No. 20", 1, 18))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jPanel14.setBackground(new java.awt.Color(239, 239, 247));
        jPanel14.setName("jPanel14"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/crossroad-plain-32.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(137, 170, 181));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)), "Please Fill In Details Below", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Monotype Corsiva", 1, 18))); // NOI18N
        jPanel12.setName("jPanel12"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(239, 239, 247));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "UnAssigned Vehicles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        table_issued.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_issued.setName("table_issued"); // NOI18N
        table_issued.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_issuedMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_issued);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(137, 170, 181));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel5.setText("Vehicle No.:");
        jLabel5.setName("jLabel5"); // NOI18N

        txt_vehicleNo.setBackground(new java.awt.Color(239, 239, 247));
        txt_vehicleNo.setName("txt_vehicleNo"); // NOI18N
        txt_vehicleNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_vehicleNoKeyReleased(evt);
            }
        });

        jLabel30.setText("Owner:");
        jLabel30.setName("jLabel30"); // NOI18N

        txt_owner.setEditable(false);
        txt_owner.setName("txt_owner"); // NOI18N

        jLabel8.setText("Blade No.");
        jLabel8.setName("jLabel8"); // NOI18N

        txt_BladeNo.setEditable(false);
        txt_BladeNo.setName("txt_BladeNo"); // NOI18N

        jLabel9.setText("Make:");
        jLabel9.setName("jLabel9"); // NOI18N

        txt_make.setEditable(false);
        txt_make.setName("txt_make"); // NOI18N

        jLabel31.setText("Driver:");
        jLabel31.setName("jLabel31"); // NOI18N

        txt_driver.setEditable(false);
        txt_driver.setName("txt_driver"); // NOI18N

        jLabel57.setText("Destination:");
        jLabel57.setName("jLabel57"); // NOI18N

        cmb_condition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----------" }));
        cmb_condition.setName("cmb_condition"); // NOI18N

        jLabel53.setText("Date Assigned:");
        jLabel53.setName("jLabel53"); // NOI18N

        txt_DateAssigned.setBackground(new java.awt.Color(255, 255, 255));
        txt_DateAssigned.setForeground(new java.awt.Color(255, 51, 51));
        txt_DateAssigned.setAutoscrolls(true);
        txt_DateAssigned.setDateFormatString("yyyy-MM-dd");
        txt_DateAssigned.setName("txt_DateAssigned"); // NOI18N
        txt_DateAssigned.setOpaque(false);

        jButton2.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        jButton2.setText("Assign");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5)
                            .addComponent(jLabel31)
                            .addComponent(jLabel57))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel30)
                                .addComponent(jLabel8)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txt_driver, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel53))
                                .addComponent(cmb_condition, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_make, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_vehicleNo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_DateAssigned, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_BladeNo, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_owner, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jButton2)
                        .addGap(29, 29, 29)
                        .addComponent(jButton5)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_vehicleNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_BladeNo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_owner, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_make, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel30)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txt_driver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(cmb_condition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_DateAssigned, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(239, 239, 247));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Assigned Vehicles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        table_available.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_available.setName("table_available"); // NOI18N
        table_available.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_availableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_available);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date2 = ((JTextField) txt_DateAssigned.getDateEditor().getUiComponent()).getText();
        if (txt_BladeNo.getText().isEmpty() || txt_owner.getText().isEmpty() || txt_driver.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please ensure every field has the needed data!!");
        } else if (txt_DateAssigned.getDate().after(Taxi_Main.txt_dated.getDate())) {
            JOptionPane.showMessageDialog(null, "Error in Date Selected, No Future Dates Allowed!!", "Future Dates", JOptionPane.ERROR_MESSAGE);

        } else if(jButton2.getText().equals("Assign")){
            try {
                String stat="Available";
                String clrd="Yes";

                String sql = "UPDATE vehicle SET route='"+cmb_condition.getSelectedItem()+"' WHERE no_blade='"+txt_BladeNo.getText()+"'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                
                
                
                JOptionPane.showMessageDialog(null, "Route Successfully Assigned");
                UpdateAvailableTable();
                UpdateIssuedTable();

                txt_vehicleNo.setText("");
                txt_BladeNo.setText("");
                txt_make.setText("");
                txt_owner.setText("");
                txt_driver.setText("");

                cmb_condition.setSelectedIndex(0);

            } catch (SQLException ex) {
                Logger.getLogger(Assign_Destination.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
             try {
                String stat="Unassigned";
                String clrd="Yes";

                String sql = "UPDATE vehicle SET route='"+stat+"',Available_spc=(SELECT no_pass) WHERE no_blade='"+txt_BladeNo.getText()+"'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                
                
                
                JOptionPane.showMessageDialog(null, "Route Successfully UnAssigned");
                UpdateAvailableTable();
                UpdateIssuedTable();

                txt_vehicleNo.setText("");
                txt_BladeNo.setText("");
                txt_make.setText("");
                txt_owner.setText("");
                txt_driver.setText("");

                cmb_condition.setSelectedIndex(0);

            } catch (SQLException ex) {
                Logger.getLogger(Assign_Destination.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        txt_BladeNo.setText(null);
        txt_make.setText(null);
        txt_owner.setText(null);
        txt_driver.setText(null);
        txt_DateAssigned.setDate(null);
        
        cmb_condition.setSelectedIndex(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void table_issuedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_issuedMouseClicked
     
        try {
            int r = table_issued.getSelectedRow();
            int c = table_issued.getSelectedColumn();
            Object s = table_issued.getValueAt(r, 0);
            String NameVal = s.toString();
            String sql = "select * from vehicle where no_blade='" + NameVal + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            txt_vehicleNo.setText("" + rs.getString("Vehicle_No"));
            txt_driver.setText("" + rs.getString("driver"));
            txt_owner.setText("" + rs.getString("owner"));
            txt_BladeNo.setText(rs.getString("no_blade"));
            txt_make.setText(rs.getString("make"));
            jButton2.setText("Assign");
            jButton2.setForeground(Color.green);
            cmb_condition.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(Assign_Destination.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_table_issuedMouseClicked

    private void txt_vehicleNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vehicleNoKeyReleased
          try {
           
            String NameVal = txt_vehicleNo.getText();
            String sql = "select * from vehicle where Vehicle_No='" + NameVal + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            txt_vehicleNo.setText("" + rs.getString("Vehicle_No"));
            txt_driver.setText("" + rs.getString("driver"));
            txt_owner.setText("" + rs.getString("owner"));
            txt_BladeNo.setText(rs.getString("no_blade"));
            txt_make.setText(rs.getString("make"));
        } catch (SQLException ex) {
            Logger.getLogger(Assign_Destination.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_vehicleNoKeyReleased

    private void table_availableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_availableMouseClicked
     try {
            int r = table_available.getSelectedRow();
            int c = table_available.getSelectedColumn();
            Object s = table_available.getValueAt(r, 0);
            String NameVal = s.toString();
            String sql = "select * from vehicle where no_blade='" + NameVal + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            txt_vehicleNo.setText("" + rs.getString("Vehicle_No"));
            txt_driver.setText("" + rs.getString("driver"));
            txt_owner.setText("" + rs.getString("owner"));
            txt_BladeNo.setText(rs.getString("no_blade"));
            txt_make.setText(rs.getString("make"));
            jButton2.setText("UnAssign");
            jButton2.setForeground(Color.red);
            cmb_condition.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(Assign_Destination.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_table_availableMouseClicked


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
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable table_available;
    private javax.swing.JTable table_issued;
    private javax.swing.JTextField txt_BladeNo;
    private com.toedter.calendar.JDateChooser txt_DateAssigned;
    private javax.swing.JTextField txt_driver;
    private javax.swing.JTextField txt_make;
    private javax.swing.JTextField txt_owner;
    private javax.swing.JTextField txt_vehicleNo;
    // End of variables declaration//GEN-END:variables

}
