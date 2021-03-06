/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toniPackage;

import Class.TableViews;
import Class.koneksi;
import ToniPopups.DetailHistoryPop;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author toni
 */
public class HistoryPasienPendaftaraan extends javax.swing.JInternalFrame {

    /**
     * Creates new form HistoryMutasi
     */
    SimpleDateFormat format;
    Date perkutut;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;

    public HistoryPasienPendaftaraan() {
        initComponents();
        defaultload();
    }

    private void SettingTableModel() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
            "No. Registrasi",
            "No. Medrec",
            "Nama Pasien",
            "Tanggal Masuk"
        },
                null, new int[]{4, 6, 8}, null);
        tblPasien.setModel(TableModels);
        TableViews.table(tblPasien, new int[]{90, 100, 100, 150});
    }

    private void tampilkansemua() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "Select a.regid, a.medrec_id, b.nama, a.tanggalmasuk from regpasien a, master_medrec b "
                    + "where a.medrec_id=b.medrec_id "
                    + "limit 20 ";
            PreparedStatement s = koneksi.getConnection().prepareStatement(query);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                TableModels.addRow(new Object[]{
                    r.getString(1),
                    r.getString(2),
                    r.getString(3),
                    r.getString(4)});
                tblPasien.setModel(TableModels);
            }
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void cariData() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "Select a.regid, a.medrec_id, b.nama, a.tanggalmasuk from regpasien a, master_medrec b "
                    + "where a.medrec_id=b.medrec_id and a.regid like ? and a.medrec_id like ? "
                    + "and a.tanggalmasuk between ? and ? ";
            PreparedStatement s = koneksi.getConnection().prepareStatement(query);
            s.setString(1, "%" + regpasFil.getText() + "%");
            s.setString(2, "%" + nomedrec.getText() + "%");
            s.setString(3, format.format(jDateChooser1.getDate()));
            s.setString(4, format.format(jDateChooser3.getDate()));
            ResultSet r = s.executeQuery();
            while (r.next()) {
                TableModels.addRow(new Object[]{
                    r.getString(1),
                    r.getString(2),
                    r.getString(3),
                    r.getString(4)});
                tblPasien.setModel(TableModels);
            }
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void defaultload() {
        SettingTableModel();
        tampilkansemua();
        setDate();
    }

    private void setDate() {
        format = new SimpleDateFormat("yyyy-MM-dd");
        Date haha = new Date();
        perkutut = new Date(haha.getTime());
        try {
            jDateChooser1.setDate(haha = format.parse("2015-01-01"));
            jDateChooser3.setDate(perkutut);
        } catch (Exception e) {
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nomedrec = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        regpasFil = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCari = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPasien = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        btnCetakRekap = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HISTORY PASIEN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 24))); // NOI18N
        jPanel1.setLayout(new java.awt.CardLayout());

        jLabel1.setText("No. Medrec ");

        nomedrec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomedrecActionPerformed(evt);
            }
        });

        jLabel2.setText("Regpas");

        regpasFil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regpasFilActionPerformed(evt);
            }
        });

        jLabel3.setText("Tanggal Berobat");

        jLabel4.setText("s/d");

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tblPasien.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPasienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPasien);

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jDateChooser3.setDateFormatString("yyyy-MM-dd");

        btnCetakRekap.setText("Cetak Rekap Per Tanggal");
        btnCetakRekap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakRekapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomedrec, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regpasFil, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCetakRekap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nomedrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(regpasFil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(btnReset))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCetakRekap))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, "card2");

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPasienMouseClicked
    }//GEN-LAST:event_tblPasienMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        SettingTableModel();
        cariData();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        nomedrec.setText("");
        regpasFil.setText("");
        defaultload();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCetakRekapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakRekapActionPerformed
        String awal = format.format(jDateChooser1.getDate());
        String akhir = format.format(jDateChooser3.getDate());
        Map parameters = new HashMap();
        parameters.put("Parameter1", awal);
        parameters.put("Parameter2", akhir);
        try {
            JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/report/HistoryPasienRekap.jrxml"));
            String c = "select a.regid, a.medrec_id, b.nama, a.tanggalmasuk from "
                    + "regpasien a, master_medrec b "
                    + "where a.medrec_id=b.medrec_id and a.tanggalmasuk between '" + awal + "' and '" + akhir + "'";
            JRDesignQuery query = new JRDesignQuery();
            query.setText(c);
            jd.setQuery(query);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, koneksi.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnCetakRekapActionPerformed

    private void nomedrecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomedrecActionPerformed
        // TODO add your handling code here:
        SettingTableModel();
        cariData();
    }//GEN-LAST:event_nomedrecActionPerformed

    private void regpasFilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regpasFilActionPerformed
        // TODO add your handling code here:
        SettingTableModel();
        cariData();
    }//GEN-LAST:event_regpasFilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCetakRekap;
    private javax.swing.JButton btnReset;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomedrec;
    private javax.swing.JTextField regpasFil;
    private javax.swing.JTable tblPasien;
    // End of variables declaration//GEN-END:variables
}
