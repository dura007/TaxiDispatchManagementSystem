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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class Reports extends javax.swing.JInternalFrame {

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

    public Reports() {
        initComponents();

//        txt_DateReceived.setMaxSelectableDate(new java.util.Date());
//        txt_receivedBy.setText(CAMS_Main.user.getText());
        try {

            conn = DatabaseConnection.connectToDb();
            st = (Statement) conn.createStatement();
            setVehicleCombo();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setVehicleCombo() {
        try {
            String sql1 = "select DISTINCT blade_no from booking";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            while (rs.next()) {

                jComboBox1.addItem(rs.getString("blade_no"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void generateReport1() {

        try {
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document(PageSize.A4);

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Report.pdf"));

            doc.open();

            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("report128.png");

            img.scaleAbsolute(70, 70);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);

            Paragraph p;
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = ((JTextField) Taxi_Main.txt_dated.getDateEditor().getUiComponent()).getText();
            // Paragraph p;
            p = new Paragraph("WASAA SAFARI COMPANY ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);
            p = new Paragraph("Experience The Best Services in Town", FontFactory.getFont(FontFactory.TIMES_BOLD, 18));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);
            p = new Paragraph("P.O. Box 4,80403, Kwale", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);
            p = new Paragraph(((JTextField) Taxi_Main.txt_dated.getDateEditor().getUiComponent()).getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            doc.add(p);

            //doc.addTitle(head);
            p = new com.itextpdf.text.Paragraph("BILLING RECORDS", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.RED));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(new com.itextpdf.text.Paragraph("=========================================================================="));

            p = new Paragraph("Department.....III................     Branch....Store..............     Unit....NETWORKS...........\n\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            //doc.add(p);

            p = new Paragraph("Being Bills Received Between Date: " + ((JTextField) date_from_Report.getDateEditor().getUiComponent()).getText() + "       To: " + ((JTextField) date_to_Report.getDateEditor().getUiComponent()).getText() + "\n\n", FontFactory.getFont(FontFactory.COURIER, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);
            //table.setWidths(new int[]{3, 6, 3});
            PdfPCell hd = new PdfPCell(new com.itextpdf.text.Paragraph("Bill Details"));
            hd.setColspan(8);
            hd.setHorizontalAlignment(Element.ALIGN_CENTER);
            hd.setBackgroundColor(BaseColor.BLUE);
            hd.setFixedHeight(20);
            table.addCell(hd);

            table.addCell("TICKET No");
            table.addCell("DATE");
            table.addCell("PHONE NO.");
            table.addCell("NAME");
            table.addCell("V. REG. NO.");
            table.addCell("PRICE/H");
            table.addCell("QNTY");
            table.addCell("Total");

            String date2 = ((JTextField) date_from_Report.getDateEditor().getUiComponent()).getText();
            String date3 = ((JTextField) date_to_Report.getDateEditor().getUiComponent()).getText();

            String sql1 = "select * from BOOKING where Date_booked between '" + date2 + "' AND '" + date3 + "'";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            while (rs.next()) {

                java.util.Date tarehe;
                tarehe = rs.getDate("Date_booked");

                table.addCell(rs.getString("ticket_no"));
                table.addCell(tarehe.toString());
                table.addCell(rs.getString("phone_no"));
                table.addCell(rs.getString("name"));

                table.addCell(rs.getString("blade_no"));
                table.addCell(rs.getString("fare"));
                table.addCell(rs.getString("heads"));
                table.addCell(rs.getString("total"));

            }
            PdfPCell fd = new PdfPCell(new com.itextpdf.text.Paragraph("TOTAL"));
            fd.setColspan(7);
            fd.setHorizontalAlignment(Element.ALIGN_LEFT);
            fd.setBackgroundColor(BaseColor.BLUE);
            table.addCell(fd);
            String sql = "SELECT SUM(total) from BOOKING where Date_booked between '" + date2 + "' AND '" + date3 + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();

            table.addCell(rs.getString("sum(total)"));
            doc.add(table);

            JOptionPane.showMessageDialog(null, "Report Saved");
            openReport2();

            doc.close();
        } catch (DocumentException | IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void openReport2() {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "Report.pdf");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void generateReport() {

        try {
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document(PageSize.A4);

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("IndividualReport.pdf"));

            doc.open();

            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("report128.png");

            img.scaleAbsolute(70, 70);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);

            Paragraph p;
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = ((JTextField) Taxi_Main.txt_dated.getDateEditor().getUiComponent()).getText();
            // Paragraph p;
            p = new Paragraph("WASAA SAFARI COMPANY", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);
            p = new Paragraph("Experience The Best Service in Town", FontFactory.getFont(FontFactory.TIMES_BOLD, 18));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);
            p = new Paragraph("P.O. Box 4,80403, Kwale", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);
            p = new Paragraph(((JTextField) Taxi_Main.txt_dated.getDateEditor().getUiComponent()).getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            doc.add(p);

            //doc.addTitle(head);
            p = new com.itextpdf.text.Paragraph("BILLING RECORDS FOR VEHICLE REG NO. " + jComboBox1.getSelectedItem(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.RED));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(new com.itextpdf.text.Paragraph("=========================================================================="));

            p = new Paragraph("Department.....III................     Branch....Store..............     Unit....NETWORKS...........\n\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            //doc.add(p);

            p = new Paragraph("Being Bills Received Between Date: " + ((JTextField) date_from_Report.getDateEditor().getUiComponent()).getText() + "       To: " + ((JTextField) date_to_Report.getDateEditor().getUiComponent()).getText() + "\n\n", FontFactory.getFont(FontFactory.COURIER, 12));
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);
            //table.setWidths(new int[]{3, 6, 3});
            PdfPCell hd = new PdfPCell(new com.itextpdf.text.Paragraph("Bill Details"));
            hd.setColspan(7);
            hd.setHorizontalAlignment(Element.ALIGN_CENTER);
            hd.setBackgroundColor(BaseColor.BLUE);
            hd.setFixedHeight(20);
            table.addCell(hd);

            table.addCell("TICKET No");
            table.addCell("DATE");
            table.addCell("PHONE NO.");
            table.addCell("NAME");
            table.addCell("PRICE/H");
            table.addCell("QNTY");
            table.addCell("Total");

            String date2 = ((JTextField) date_from_Report.getDateEditor().getUiComponent()).getText();
            String date3 = ((JTextField) date_to_Report.getDateEditor().getUiComponent()).getText();

            String sql1 = "select * from BOOKING where Blade_no='" + jComboBox1.getSelectedItem() + "' AND Date_booked between '" + date2 + "' AND '" + date3 + "'";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            while (rs.next()) {

                java.util.Date tarehe;
                tarehe = rs.getDate("Date_booked");

                table.addCell(rs.getString("ticket_no"));
                table.addCell(tarehe.toString());
                table.addCell(rs.getString("phone_no"));
                table.addCell(rs.getString("name"));

                table.addCell(rs.getString("fare"));
                table.addCell(rs.getString("heads"));
                table.addCell(rs.getString("total"));

            }
            PdfPCell fd = new PdfPCell(new com.itextpdf.text.Paragraph("TOTAL"));
            fd.setColspan(6);
            fd.setHorizontalAlignment(Element.ALIGN_LEFT);
            fd.setBackgroundColor(BaseColor.BLUE);
            table.addCell(fd);
            String sql = "SELECT SUM(total) from BOOKING where Blade_no='" + jComboBox1.getSelectedItem() + "' AND Date_booked between '" + date2 + "' AND '" + date3 + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();

            table.addCell(rs.getString("sum(total)"));
            doc.add(table);

            JOptionPane.showMessageDialog(null, "Report Saved");
            openReport();

            doc.close();
        } catch (DocumentException | IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void openReport() {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "IndividualReport.pdf");
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
        jPanel13 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        date_from_Report = new com.toedter.calendar.JDateChooser();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        date_to_Report = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        searchreport_Table2 = new javax.swing.JTable();

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
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reports", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Modern No. 20", 1, 18))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jPanel14.setBackground(new java.awt.Color(239, 239, 247));
        jPanel14.setName("jPanel14"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/report128.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(239, 239, 247));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)), "Please Select Appropriate Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Monotype Corsiva", 1, 18))); // NOI18N
        jPanel15.setName("jPanel15"); // NOI18N

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel13.setName("jPanel13"); // NOI18N

        jLabel36.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Reports");
        jLabel36.setName("jLabel36"); // NOI18N

        jLabel14.setText("Bills: Between:");
        jLabel14.setName("jLabel14"); // NOI18N

        date_from_Report.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        date_from_Report.setForeground(new java.awt.Color(204, 0, 0));
        date_from_Report.setDateFormatString("yyyy-MM-dd");
        date_from_Report.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        date_from_Report.setMinSelectableDate(null);
        date_from_Report.setName("date_from_Report"); // NOI18N
        date_from_Report.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                date_from_ReportKeyReleased(evt);
            }
        });

        jButton11.setText("Generate Report");
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("ALL");
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        date_to_Report.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        date_to_Report.setForeground(new java.awt.Color(204, 0, 0));
        date_to_Report.setDateFormatString("yyyy-MM-dd");
        date_to_Report.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        date_to_Report.setMinSelectableDate(null);
        date_to_Report.setName("date_to_Report"); // NOI18N
        date_to_Report.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                date_to_ReportKeyReleased(evt);
            }
        });

        jLabel16.setText("TO:");
        jLabel16.setName("jLabel16"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---------SELECT Vehicle Reg. No.-----------------" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(date_from_Report, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(date_to_Report, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 303, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton11)
                .addGap(624, 624, 624))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_from_Report, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(date_to_Report, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton12)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        searchreport_Table2.setBackground(java.awt.Color.gray);
        searchreport_Table2.setForeground(new java.awt.Color(102, 255, 102));
        searchreport_Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        searchreport_Table2.setName("searchreport_Table2"); // NOI18N
        jScrollPane5.setViewportView(searchreport_Table2);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void date_from_ReportKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date_from_ReportKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_date_from_ReportKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (date_to_Report.getDate().equals(null)) {
            JOptionPane.showMessageDialog(null, "Make Sure All Fields has the neede data");
        } else if (date_from_Report.getDate().after(Taxi_Main.txt_dated.getDate()) || date_to_Report.getDate().after(Taxi_Main.txt_dated.getDate())) {
            JOptionPane.showMessageDialog(null, "No future dates allowed");
        } else {
            generateReport();
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String dateToday = ((JTextField) date_from_Report.getDateEditor().getUiComponent()).getText();
        if (date_from_Report.getDate().equals(null)) {
            JOptionPane.showMessageDialog(null, "Select Appropriate Date");
        } else if (date_to_Report.getDate().equals(null)) {
            JOptionPane.showMessageDialog(null, "Select Appropriate Date");
        } else if (date_from_Report.getDate().after(Taxi_Main.txt_dated.getDate()) || date_to_Report.getDate().after(Taxi_Main.txt_dated.getDate())) {
            JOptionPane.showMessageDialog(null, "No future dates allowed");
        } else {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = ((JTextField) date_from_Report.getDateEditor().getUiComponent()).getText();
            String date2 = ((JTextField) date_to_Report.getDateEditor().getUiComponent()).getText();

            try {

                String sql1 = "select * from booking where Date_booked Between '" + date1 + "' AND '" + date2 + "'";
                pst = conn.prepareStatement(sql1);
                rs = pst.executeQuery();
                searchreport_Table2.setModel(DbUtils.resultSetToTableModel(rs));
                generateReport1();
                if (rs.next()) {

                    searchreport_Table2.setVisible(true);

                } else {

                    searchreport_Table2.setVisible(true);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                }
            }
        }
       
    }//GEN-LAST:event_jButton12ActionPerformed

    private void date_to_ReportKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date_to_ReportKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_date_to_ReportKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public com.toedter.calendar.JDateChooser date_from_Report;
    public com.toedter.calendar.JDateChooser date_to_Report;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable searchreport_Table2;
    // End of variables declaration//GEN-END:variables

}
