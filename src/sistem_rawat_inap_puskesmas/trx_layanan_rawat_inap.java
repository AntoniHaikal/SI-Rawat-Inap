/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem_rawat_inap_puskesmas;

import Class.TableViews;
import Class.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class trx_layanan_rawat_inap extends javax.swing.JInternalFrame {

    private String dataku = null;
    private ResultSet res;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    JDesktopPane DP;
    private String regid;
    private int status;

    public trx_layanan_rawat_inap(JDesktopPane DP, String regid) {
        initComponents();
        this.DP = DP;
        clear();
        this.regid = regid;

        txtreg_id.setText(regid);
        tampil_cari();
    }

    private void table_model_layanan() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
                    "Trx Layanan ID",
                    "Registrasi ID",
                    "Medrec ID",
                    "Nama Pasien",
                    "Nama Dokter",
                    "Nama Layanan",
                    "Harga",
                    "Satuan",
                    "Total",
                    "Petugas",
                    "Tanggal Dibuat"
                },
                null, new int[]{}, null);
        tbltransaksi.setModel(TableModels);
        TableViews.table(tbltransaksi, new int[]{100, 100, 100, 150, 150, 150, 100, 50, 100, 150, 100});

        tampil_layanan();

    }

    private void table_model_kamar() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
                    "ID_Layanan",
                    "Nama_Layanan",
                    "Harga"
                },
                null, new int[]{}, null);
        tbltransaksi.setModel(TableModels);
        TableViews.table(tbltransaksi, new int[]{150, 250, 250});

        //tampil();

    }

    private void table_model_obat() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
                    "ID_Layanan",
                    "Nama_Layanan",
                    "Harga"
                },
                null, new int[]{}, null);
        tbltransaksi.setModel(TableModels);
        TableViews.table(tbltransaksi, new int[]{150, 250, 250});

        // tampil();
    }

    private void tampil_layanan() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM transaksi_layanan where medrec_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, txtmedrec.getText());
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
                tbltransaksi.setModel(TableModels);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampil_cari() {
        try {
            String query = "SELECT medrec_id, nama, namadokter , namalayanan, "
                    + "defaultharga FROM transaksi_layanan WHERE regid = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, regid);
            ResultSet res = statement.executeQuery();
            int baris = 0;
            while (res.next()) {
                baris = res.getRow();
                txtmedrec.setText(res.getString(1));
                txtnama.setText(res.getString(2));
                txtdokter.setText(res.getString(3));
                txtlayanan.setText(res.getString(4));
                txtharga.setText(res.getString(5));

            }
            if ("".equals(txtreg_id.getText())) {
               
            }else{
                if(baris == 0){
                JOptionPane.showMessageDialog(rootPane, "Pasien Belum Melakukan Transaksi");
                }else{
                    
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void clear() {
        txtdokter.setText("");
        txtharga.setText("");
        txtlayanan.setText("");
        txtmedrec.setText("");
        txtnama.setText("");
        txtreg_id.setText("");
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtreg_id = new javax.swing.JTextField();
        txtdokter = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtlayanan = new javax.swing.JTextField();
        btncaripasien = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtmedrec = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltransaksi = new javax.swing.JTable();
        btntransaksi = new javax.swing.JButton();
        btnlayanan = new javax.swing.JButton();
        btnobat = new javax.swing.JButton();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Transaksi Layanan Rawat Inap");
        jLabel1.setBounds(310, 20, 250, 17);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Layanan Rawat Inap"));

        jLabel2.setText("Registrasi ID");
        jLabel2.setBounds(20, 30, 90, 14);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("Dokter");
        jLabel3.setBounds(20, 70, 70, 14);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtreg_id.setText("jTextField1");
        txtreg_id.setBounds(120, 30, 170, 30);
        jLayeredPane2.add(txtreg_id, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtdokter.setText("jTextField2");
        txtdokter.setBounds(120, 70, 170, 30);
        jLayeredPane2.add(txtdokter, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("Layanan");
        jLabel4.setBounds(20, 110, 60, 14);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtlayanan.setText("jTextField3");
        txtlayanan.setBounds(120, 110, 170, 30);
        jLayeredPane2.add(txtlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncaripasien.setText("Cari Pasien");
        btncaripasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripasienActionPerformed(evt);
            }
        });
        btncaripasien.setBounds(300, 30, 110, 23);
        jLayeredPane2.add(btncaripasien, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Medrec");
        jLabel5.setBounds(440, 30, 70, 14);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtmedrec.setText("jTextField4");
        txtmedrec.setBounds(520, 30, 150, 30);
        jLayeredPane2.add(txtmedrec, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Nama");
        jLabel6.setBounds(440, 70, 70, 14);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtnama.setText("jTextField5");
        txtnama.setBounds(520, 70, 150, 30);
        jLayeredPane2.add(txtnama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton3.setText("Detail Pasien");
        jButton3.setBounds(690, 30, 130, 23);
        jLayeredPane2.add(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText("Harga");
        jLabel7.setBounds(20, 150, 70, 14);
        jLayeredPane2.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtharga.setText("jTextField6");
        txtharga.setBounds(120, 150, 170, 30);
        jLayeredPane2.add(txtharga, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        jButton1.setText("Kelola");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(10, 30, 90, 23);
        jLayeredPane4.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setText("Mutasi");
        jButton2.setBounds(110, 30, 90, 23);
        jLayeredPane4.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton4.setText("Rujukan");
        jButton4.setBounds(210, 30, 100, 23);
        jLayeredPane4.add(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton5.setText("Cetak");
        jButton5.setBounds(320, 30, 100, 23);
        jLayeredPane4.add(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane4.setBounds(390, 110, 430, 70);
        jLayeredPane2.add(jLayeredPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Layanan"));

        tbltransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbltransaksi);

        jScrollPane1.setBounds(10, 60, 820, 170);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btntransaksi.setText("Transaksi Layanan");
        btntransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransaksiActionPerformed(evt);
            }
        });
        btntransaksi.setBounds(10, 30, 140, 23);
        jLayeredPane3.add(btntransaksi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnlayanan.setText("Transaksi Kamar");
        btnlayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlayananActionPerformed(evt);
            }
        });
        btnlayanan.setBounds(160, 30, 150, 23);
        jLayeredPane3.add(btnlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnobat.setText("Transaksi Obat");
        btnobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnobatActionPerformed(evt);
            }
        });
        btnobat.setBounds(320, 30, 140, 23);
        jLayeredPane3.add(btnobat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                    .addComponent(jLayeredPane2)
                    .addComponent(jLayeredPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnlayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnlayananActionPerformed

    private void btnobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnobatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnobatActionPerformed

    private void btncaripasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripasienActionPerformed
        pencarian_pelayanan pencarian = new pencarian_pelayanan(DP);
        DP.add(pencarian);
        pencarian.show();
    }//GEN-LAST:event_btncaripasienActionPerformed

    private void btntransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransaksiActionPerformed
        table_model_layanan();
        status = 1;
    }//GEN-LAST:event_btntransaksiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(status == 1){
            form_kelola_layanan kelola = new form_kelola_layanan(DP);
            DP.add(kelola);
            kelola.show();
        }else{
            JOptionPane.showMessageDialog(rootPane, "Silahkan Pilih Transaksi Terlebih Dahulu");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncaripasien;
    private javax.swing.JButton btnlayanan;
    private javax.swing.JButton btnobat;
    private javax.swing.JButton btntransaksi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbltransaksi;
    private javax.swing.JTextField txtdokter;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtlayanan;
    private javax.swing.JTextField txtmedrec;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtreg_id;
    // End of variables declaration//GEN-END:variables
}
