/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi_pelayanan;

import Class.TableViews;
import Class.koneksi;
import Sistem_monitoring_mutasi_ri.frmCariMedrec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class form_transaksi_panel extends javax.swing.JInternalFrame {

    JDesktopPane DP;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    private ResultSet res;
    String regid;
    String dokter_id;
    
    public form_transaksi_panel(JDesktopPane DP,String regid) {
        initComponents();
        
        this.DP = DP;
        this.regid = regid;
        txtregid.setText(regid);
        
        tampil_cari();
        table_model_trxlayanan();
        table_model_trxobat();
    }

     public void table_model_trxlayanan() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
                    "Transaksi Layanan ID",
                    "Registrasi_id",
                    "Nama Pasien",
                    "Nama Petugas Medis",
                    "ID Layanan",
                    "Nama Layanan",
                    "Harga",
                    "Satuan",
                    "Total",
                    "Petugas",
                    "Tanggal Dibuat"
                },
                null, new int[]{1,4,6,7,8}, null);
        tbltransaksilayanan.setModel(TableModels);
        TableViews.table(tbltransaksilayanan, new int[]{200, 100, 100, 150, 150, 150, 100, 50, 100, 100, 100});

        tampil_trxlayanan();

    }
    
    private void tampil_trxlayanan() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM transaksi_layanan where regid = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, regid);
            ResultSet res = statement.executeQuery();
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels.addRow(new Object[]{
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5),
                            res.getString(6),
                            res.getString(7),
                            res.getString(8),
                            res.getString(9),
                            res.getString(10),
                            res.getString(11)
                          
                        });
                tbltransaksilayanan.setModel(TableModels);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void tampil_cari() {
        try {
            String query = "SELECT a.medrec_id, b.nama, c.nama_petugasmedis, a.dokter_id"
                    + " FROM regpasien a, master_medrec b, master_petugasmedis c "
                    + "WHERE a.medrec_id = b.medrec_id "
                    + "AND a.dokter_id = c.petugasmedis_id "
                    + "AND a.regid = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, regid);
            ResultSet res = statement.executeQuery();
            int baris = 0;
            while (res.next()) {
                baris = res.getRow();
                txtnomedrec.setText(res.getString(1));
                txtnamapasien.setText(res.getString(2));
                txtdokter.setText(res.getString(3));
                dokter_id = (res.getString(4));

            }
            if ("".equals(txtregid.getText())) {
               
            }else{
                if(baris == 0){
                JOptionPane.showMessageDialog(rootPane, "Pasien Belum Terdaftar");
                }else{
                    
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void table_model_trxobat() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
                    "Transaksi Obat ID",
                    "Registrasi ID",
                    "Nama Pasien",
                    "Petugas Medis",
                    "Obat ID",
                    "Nama Obat",
                    "Satuan",
                    "Total",
                    "Petugas",
                    "Tanggal Dibuat"
                },
                null, new int[]{}, null);
        tbltransaksiobt.setModel(TableModels);
        TableViews.table(tbltransaksiobt, new int[]{200, 100, 150, 150, 150, 100, 50, 100, 100, 100});

        tampil_trxobat();

    }
    
      private void tampil_trxobat() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM transaksi_obat";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels.addRow(new Object[]{
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5),
                            res.getString(6),
                            res.getString(7),
                            res.getString(8),
                            res.getString(9),
                            res.getString(10)
                          
                        });
                tbltransaksiobt.setModel(TableModels);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        txtregid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtdokter = new javax.swing.JTextField();
        btncarimasterlayanan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnomedrec = new javax.swing.JTextField();
        txtnamapasien = new javax.swing.JTextField();
        btndetailpasien = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltransaksilayanan = new javax.swing.JTable();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        btntambahlayanan = new javax.swing.JButton();
        btneditlayanan = new javax.swing.JButton();
        btnhapuslayanan = new javax.swing.JButton();
        btndetaillayanan = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        btntambahlayanan1 = new javax.swing.JButton();
        btneditlayanan1 = new javax.swing.JButton();
        btnhapuslayanan1 = new javax.swing.JButton();
        btndetaillayanan1 = new javax.swing.JButton();
        jLayeredPane15 = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbltransaksiobt = new javax.swing.JTable();
        jLayeredPane5 = new javax.swing.JLayeredPane();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Transaksi Rawat Inap");
        jLabel1.setBounds(340, 10, 130, 14);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Psien"));
        txtregid.setBounds(130, 30, 190, 30);
        jLayeredPane2.add(txtregid, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText("Registrasi ID");
        jLabel2.setBounds(20, 30, 100, 30);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("Petugas Medis");
        jLabel3.setBounds(20, 70, 90, 30);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtdokter.setBounds(130, 70, 190, 30);
        jLayeredPane2.add(txtdokter, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncarimasterlayanan.setText("Cari Pasien");
        btncarimasterlayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarimasterlayananActionPerformed(evt);
            }
        });
        btncarimasterlayanan.setBounds(330, 30, 120, 30);
        jLayeredPane2.add(btncarimasterlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setText("No Medrec");
        jLabel8.setBounds(460, 30, 70, 30);
        jLayeredPane2.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Nama");
        jLabel6.setBounds(460, 70, 70, 30);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnomedrec.setBounds(540, 30, 160, 30);
        jLayeredPane2.add(txtnomedrec, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnamapasien.setBounds(540, 70, 160, 30);
        jLayeredPane2.add(txtnamapasien, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btndetailpasien.setText("Detail Pasien");
        btndetailpasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndetailpasienActionPerformed(evt);
            }
        });
        btndetailpasien.setBounds(710, 30, 110, 30);
        jLayeredPane2.add(btndetailpasien, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tbltransaksilayanan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbltransaksilayanan);

        jScrollPane1.setBounds(22, 110, 770, 220);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Transaksi"));
        jLayeredPane6.setBounds(10, 90, 800, 250);
        jLayeredPane3.add(jLayeredPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane7.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        btntambahlayanan.setText("Tambah");
        btntambahlayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahlayananActionPerformed(evt);
            }
        });
        btntambahlayanan.setBounds(20, 30, 100, 23);
        jLayeredPane7.add(btntambahlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btneditlayanan.setText("Edit");
        btneditlayanan.setBounds(130, 30, 100, 23);
        jLayeredPane7.add(btneditlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnhapuslayanan.setText("Hapus");
        btnhapuslayanan.setBounds(240, 30, 110, 23);
        jLayeredPane7.add(btnhapuslayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btndetaillayanan.setText("Detail");
        btndetaillayanan.setBounds(360, 30, 110, 23);
        jLayeredPane7.add(btndetaillayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane7.setBounds(10, 10, 800, 70);
        jLayeredPane3.add(jLayeredPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("Transaksi Pelayanan", jLayeredPane3);

        jLayeredPane9.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        btntambahlayanan1.setText("Tambah");
        btntambahlayanan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahlayanan1ActionPerformed(evt);
            }
        });
        btntambahlayanan1.setBounds(20, 30, 100, 23);
        jLayeredPane9.add(btntambahlayanan1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btneditlayanan1.setText("Edit");
        btneditlayanan1.setBounds(130, 30, 100, 23);
        jLayeredPane9.add(btneditlayanan1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnhapuslayanan1.setText("Hapus");
        btnhapuslayanan1.setBounds(240, 30, 110, 23);
        jLayeredPane9.add(btnhapuslayanan1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btndetaillayanan1.setText("Detail");
        btndetaillayanan1.setBounds(360, 30, 110, 23);
        jLayeredPane9.add(btndetaillayanan1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane9.setBounds(10, 10, 800, 70);
        jLayeredPane4.add(jLayeredPane9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane15.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Transaksi"));

        tbltransaksiobt.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbltransaksiobt);

        jScrollPane4.setBounds(10, 20, 790, 210);
        jLayeredPane15.add(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane15.setBounds(10, 90, 810, 250);
        jLayeredPane4.add(jLayeredPane15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("Transaksi Obat", jLayeredPane4);
        jTabbedPane1.addTab("Transaksi Kamar", jLayeredPane5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLayeredPane1)
                    .addComponent(jLayeredPane2)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncarimasterlayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarimasterlayananActionPerformed
        sistem_rawat_inap_puskesmas.pencarian_pelayanan carpas = new sistem_rawat_inap_puskesmas.pencarian_pelayanan(DP);
        DP.add(carpas);
        carpas.show();
    }//GEN-LAST:event_btncarimasterlayananActionPerformed

    private void btntambahlayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahlayananActionPerformed
       tambah_layanan layan = new tambah_layanan(regid);
        //DP.add(layan);
        //layan.show();
        layan.add(new JButton("click"));
        layan.add(new JTextField(20));
        layan.add(new JLabel("Label"));
        //JOptionPane.showMessageDialog(null, layan,"information",JOptionPane.INFORMATION_MESSAGE);
        int jop = JOptionPane.showOptionDialog(null, 
        layan, 
        "", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        new String[]{"Selesai"}, // this is the array
        "default");
        if(jop == JOptionPane.YES_OPTION){
            this.repaint();
            table_model_trxlayanan();
        }
    }//GEN-LAST:event_btntambahlayananActionPerformed

    private void btntambahlayanan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahlayanan1ActionPerformed
        tambah_obat obat = new tambah_obat(regid);
        obat.add(new JButton("click"));
        obat.add(new JTextField(20));
        obat.add(new JLabel("Label"));
        //JOptionPane.showMessageDialog(null, layan,"information",JOptionPane.INFORMATION_MESSAGE);
        int jop = JOptionPane.showOptionDialog(null, 
        obat, 
        "", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        new String[]{"Selesai"}, // this is the array
        "default");
        if(jop == JOptionPane.YES_OPTION){
            this.repaint();
            table_model_trxobat();
        }
    }//GEN-LAST:event_btntambahlayanan1ActionPerformed

    private void btndetailpasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndetailpasienActionPerformed
        Sistem_monitoring_mutasi_ri.frmCariMedrec medrec = new Sistem_monitoring_mutasi_ri.frmCariMedrec(DP);
        DP.add(medrec);
        medrec.show();
    }//GEN-LAST:event_btndetailpasienActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncarimasterlayanan;
    private javax.swing.JButton btndetaillayanan;
    private javax.swing.JButton btndetaillayanan1;
    private javax.swing.JButton btndetailpasien;
    private javax.swing.JButton btneditlayanan;
    private javax.swing.JButton btneditlayanan1;
    private javax.swing.JButton btnhapuslayanan;
    private javax.swing.JButton btnhapuslayanan1;
    private javax.swing.JButton btntambahlayanan;
    private javax.swing.JButton btntambahlayanan1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane15;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JLayeredPane jLayeredPane9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbltransaksilayanan;
    private javax.swing.JTable tbltransaksiobt;
    private javax.swing.JTextField txtdokter;
    private javax.swing.JTextField txtnamapasien;
    private javax.swing.JTextField txtnomedrec;
    private static javax.swing.JTextField txtregid;
    // End of variables declaration//GEN-END:variables
}
