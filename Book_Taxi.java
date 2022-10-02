package Taxi;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Element;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class Book_Taxi extends javax.swing.JInternalFrame {

    private Vector<Vector<String>> data;
    private Vector<Vector<String>> data1;
    private Vector<String> header;
    int tot = 0;

    Connection conn = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    int i_no = 0;
    int c_no = 000;

    public Book_Taxi() {
        initComponents();

//        txt_DateReceived.setMaxSelectableDate(new java.util.Date());
//        txt_receivedBy.setText(CAMS_Main.user.getText());
        try {

            conn = DatabaseConnection.connectToDb();
            st = (Statement) conn.createStatement();
            IncCodeNo();
            setRouteCombo();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void validateQnty() {
        int qnty, avalQnty;

        qnty = Integer.parseInt(no_people.getText());
        avalQnty = Integer.parseInt(available_space.getText());
        if ((Pattern.matches("^[\\p{L} .'-]+$", no_people.getText()))) {
            JOptionPane.showMessageDialog(null, "Please enter a valid quantity without characters", "User Input Error Message", JOptionPane.ERROR_MESSAGE);
            no_people.setText("");
        } else if (qnty > avalQnty) {
            JOptionPane.showMessageDialog(null, "Please enter a valid quantity can not exceed available capacity", "User Input Error Message", JOptionPane.ERROR_MESSAGE);
            no_people.setText("");
        }
    }

    private void IncCodeNo() {
        try {
            String stat = "Active";
            String sql = "select max(ticket_no) from booking";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            if (rs != null) {
                c_no = rs.getInt(1);
                c_no++;
            } else {
                c_no = 1;
            }

            ticket_no.setText("" + c_no);
            ticket_no.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(Book_Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setRouteCombo() {
        try {
            String sql1 = "select * from routes";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            while (rs.next()) {

                jComboBox1.addItem(rs.getString("Route"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void UpdateTable() {
        try {

            String sql = "select vehicle_no AS 'Vehicle No', no_blade AS 'Reg No.',no_pass AS Capacity,available_spc AS 'Available' from VEHICLE where Route='" + jComboBox1.getSelectedItem() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            sc.setModel(DbUtils.resultSetToTableModel(rs));

            String sql1 = "select * from routes where Route='" + jComboBox1.getSelectedItem() + "'";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            rs.next();
            //asset_no.setText(rs.getString("Code"));
            fare_phead.setText(rs.getString("Fare"));
            fare_phead.setEditable(false);
        } catch (Exception ex) {
            Logger.getLogger(Book_Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    private void generateReport() {
        try {
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document(PageSize.A4);

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Receipt.pdf"));

            doc.open();
            Paragraph p;
            p = new Paragraph("Form S13                                                                                     No." + ticket_no.getText() + "\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);

            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("create-ticket-icon.png");

            img.scaleAbsolute(70, 70);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);

            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = ((JTextField) Taxi_Main.txt_dated.getDateEditor().getUiComponent()).getText();
            // Paragraph p;
            p = new Paragraph("WASAA SAFARI COMPANY", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);
            p = new Paragraph("Experience the Best Services in Town", FontFactory.getFont(FontFactory.TIMES_BOLD, 18));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            //doc.add(p);
            p = new Paragraph("BOOKING RECEIPT VOUCHER \n P.O. Box 4,80403, Kwale", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);
            p = new Paragraph(((JTextField) Taxi_Main.txt_dated.getDateEditor().getUiComponent()).getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            doc.add(p);

            //doc.addTitle(head);
            p = new com.itextpdf.text.Paragraph("BOOKING RECEIPT VOUCHER", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.RED));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(new com.itextpdf.text.Paragraph("=========================================================================="));

            p = new Paragraph("Ticket No.:  " + ticket_no.getText() + "   Vehicle Reg No.: "+no_blade.getText()+"     Destination:  "+jComboBox1.getSelectedItem()+"     \nDate:  " + date1 + "\n\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            doc.add(p);

            p = new Paragraph("SN               Qnty              Price             Ksh   \n", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            doc.add(p);
            p = new Paragraph("1                    "+no_people.getText()+"                   "+fare_phead.getText()+"                   "+total.getText()+"   \n", FontFactory.getFont(FontFactory.TIMES, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            doc.add(p);

         
            p = new Paragraph("You were serve by #"+Taxi_Main.user.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            doc.add(p);

            JOptionPane.showMessageDialog(null, "Receipt Saved");
            openReceipt();

            doc.close();
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void openReceipt() {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "Receipt.pdf");
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
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sc = new javax.swing.JTable();
        add = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        ticket_no = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        vehicle_no = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        no_blade = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        available_space = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        fare_phead = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        no_people = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        sub_tot = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel71 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();

        jButton1.setText("jButton1");
        jButton1.setName("jButton1"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        setBackground(new java.awt.Color(0, 0, 0));
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setName("jPanel3"); // NOI18N

        jPanel8.setBackground(new java.awt.Color(230, 230, 251));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Book Taxi", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Modern No. 20", 1, 18))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jPanel14.setBackground(new java.awt.Color(239, 239, 247));
        jPanel14.setName("jPanel14"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/create-ticket-icon.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(137, 170, 181));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)), "Please Fill In Details Below", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Monotype Corsiva", 1, 18))); // NOI18N
        jPanel15.setName("jPanel15"); // NOI18N

        jPanel16.setBackground(new java.awt.Color(239, 239, 247));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 2), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel16.setName("jPanel16"); // NOI18N

        jPanel21.setName("jPanel21"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        sc.setModel(new javax.swing.table.DefaultTableModel(data,header));
        sc.setName("sc"); // NOI18N
        sc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scMouseClicked(evt);
            }
        });
        new JScrollPane(sc,jScrollPane1.VERTICAL_SCROLLBAR_ALWAYS,jScrollPane1.HORIZONTAL_SCROLLBAR_ALWAYS);
        sc.setAutoResizeMode(sc.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(sc);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add.setFont(new java.awt.Font("SimHei", 0, 24)); // NOI18N
        add.setText("ADD");
        add.setName("add"); // NOI18N
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel68.setText("TOTAL:");
        jLabel68.setName("jLabel68"); // NOI18N

        total.setBackground(new java.awt.Color(255, 204, 255));
        total.setName("total"); // NOI18N

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel69.setText("Ticket No.:");
        jLabel69.setName("jLabel69"); // NOI18N

        ticket_no.setEditable(false);
        ticket_no.setBackground(new java.awt.Color(255, 255, 204));
        ticket_no.setName("ticket_no"); // NOI18N
        ticket_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticket_noActionPerformed(evt);
            }
        });

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel22.setName("jPanel22"); // NOI18N

        vehicle_no.setEditable(false);
        vehicle_no.setBackground(new java.awt.Color(255, 255, 204));
        vehicle_no.setName("vehicle_no"); // NOI18N

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel73.setText("Vehicle No.:");
        jLabel73.setName("jLabel73"); // NOI18N

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("No. Plate:");
        jLabel74.setName("jLabel74"); // NOI18N

        no_blade.setEditable(false);
        no_blade.setBackground(new java.awt.Color(255, 255, 204));
        no_blade.setName("no_blade"); // NOI18N

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("Available Cpty:");
        jLabel75.setName("jLabel75"); // NOI18N

        available_space.setEditable(false);
        available_space.setBackground(new java.awt.Color(255, 255, 204));
        available_space.setName("available_space"); // NOI18N

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("Fare/Head:");
        jLabel76.setName("jLabel76"); // NOI18N

        fare_phead.setEditable(false);
        fare_phead.setBackground(new java.awt.Color(255, 255, 204));
        fare_phead.setName("fare_phead"); // NOI18N

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("Heads:");
        jLabel77.setName("jLabel77"); // NOI18N

        no_people.setBackground(new java.awt.Color(255, 255, 204));
        no_people.setName("no_people"); // NOI18N
        no_people.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                no_peopleKeyReleased(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("SUB-TOTAL:");
        jLabel78.setName("jLabel78"); // NOI18N

        sub_tot.setBackground(new java.awt.Color(255, 255, 204));
        sub_tot.setName("sub_tot"); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel73))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(vehicle_no, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(no_blade))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(available_space)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fare_phead)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(no_people))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sub_tot)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel74)
                        .addComponent(jLabel73))
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(available_space, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(no_blade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(vehicle_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel76)
                                .addComponent(jLabel77)
                                .addComponent(jLabel78)
                                .addGroup(jPanel22Layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fare_phead, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(no_people, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sub_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(1, 1, 1))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel70.setText("Phone No.:");
        jLabel70.setName("jLabel70"); // NOI18N

        phone.setBackground(new java.awt.Color(255, 255, 204));
        phone.setName("phone"); // NOI18N
        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-SELEC DESTINATION-" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel71.setText("Name:");
        jLabel71.setName("jLabel71"); // NOI18N

        txt_name.setBackground(new java.awt.Color(255, 255, 204));
        txt_name.setName("txt_name"); // NOI18N
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, 337, Short.MAX_VALUE))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ticket_no, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel71)
                        .addGap(18, 18, 18)
                        .addComponent(txt_name))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel68)
                                .addGap(18, 18, 18)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ticket_no, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 8, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scMouseClicked

        try {

            int r = sc.getSelectedRow();
            int c = sc.getSelectedColumn();
            Object s = sc.getValueAt(r, 0);
            String NameVal = s.toString();
            String sql = "select * from vehicle where Vehicle_No='" + NameVal + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            vehicle_no.setText("" + rs.getString("Vehicle_No"));
            no_blade.setText("" + rs.getString("no_blade"));
            available_space.setText("" + rs.getString("available_spc"));
            // asset_name.setText("" + rs.getString("Asset_Name"));

            conn = DatabaseConnection.connectToDb();
            st = conn.createStatement();
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }//GEN-LAST:event_scMouseClicked

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
if(jComboBox1.getSelectedIndex()==0||phone.getText().isEmpty()||txt_name.getText().isEmpty()||no_people.getText().isEmpty()){
    JOptionPane.showMessageDialog(null, "Ensure every field has the needed data");
}else{
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = ((JTextField) Taxi_Main.txt_dated.getDateEditor().getUiComponent()).getText();
            
            int qnty, avalQnty;
            qnty = Integer.parseInt(no_people.getText());
            avalQnty = Integer.parseInt(available_space.getText());

            avalQnty = avalQnty - qnty;

            String stat = "Active";

            pst = conn.prepareStatement("insert into booking values(?,?,?,?,?,?,?,?,?)");

            pst.setInt(1, Integer.parseInt(ticket_no.getText()));
            pst.setString(2, phone.getText());
            pst.setString(3, txt_name.getText());
            pst.setString(4, vehicle_no.getText());
            pst.setString(5, no_blade.getText());
            pst.setString(6, fare_phead.getText());
            pst.setString(7, no_people.getText());
            pst.setString(8, total.getText());
            pst.setString(9, date1);
            pst.execute();

            String stati = "Issued";
            String sql1 = "UPDATE vehicle SET available_spc='" + avalQnty + "' WHERE Vehicle_No='" + vehicle_no.getText() + "'";
            pst = conn.prepareStatement(sql1);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Success!");
            generateReport();

            UpdateTable();
            IncCodeNo();

            vehicle_no.setText("");
            no_blade.setText("");
            phone.setText("");
            available_space.setText("");
            txt_name.setText("");
            no_people.setText("");
            sub_tot.setText("");
            total.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Error = " + ex);
        }
}
    }//GEN-LAST:event_addActionPerformed

    private void ticket_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ticket_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ticket_noActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        UpdateTable();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void no_peopleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_peopleKeyReleased
        validateQnty();
        int price, qnty, tot, avalQnty;
        price = Integer.parseInt(fare_phead.getText());
        qnty = Integer.parseInt(no_people.getText());
        avalQnty = Integer.parseInt(available_space.getText());
        tot = qnty * price;
        avalQnty = avalQnty - qnty;
        sub_tot.setText(String.format("%s", tot));
        total.setText(String.format("%s", tot));
        //available_space.setText(String.format("%s", avalQnty));

    }//GEN-LAST:event_no_peopleKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTextField available_space;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField fare_phead;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField no_blade;
    private javax.swing.JTextField no_people;
    private javax.swing.JTextField phone;
    private javax.swing.JTable sc;
    private javax.swing.JTextField sub_tot;
    private javax.swing.JTextField ticket_no;
    private javax.swing.JTextField total;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField vehicle_no;
    // End of variables declaration//GEN-END:variables

}
