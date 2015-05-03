/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi_pelayanan;

import Class.TableViews;
import Class.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class form_periksa extends javax.swing.JInternalFrame {

    private String dataku = null;
    private ResultSet res;
    JDesktopPane DP;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    private String awal;
    private String akhir;
    
    public form_periksa(JDesktopPane DP) {
        initComponents();
        
        this.DP = DP;
        SettingTableModel();
        clear();
        set_tanggal();
    }
    
      private void tampil(){
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "select * from periksa_medis";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();            
            while (res.next()) {
                TableModels.addRow(new Object[]{
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5),
                            res.getString(6),
                            res.getString(7)
                            
                            
                        });
                tblcari.setModel(TableModels); 
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void set_tanggal(){
        try {
            Date date;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            dc_awal.setDate(date = format.parse("2015-01-01"));
            dc_akhir.setDate(date = format.parse("2015-01-01"));
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void SettingTableModel() {
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "Transaksi layanan ID", 
                    "Registrasi ID", 
                    "Nama",  
                    "Nama Petugas Medis", 
                    "Nama Layanan",
                    "Nama User", 
                    "Tanggal Periksa"
                },
                null, new int[]{}, null);
        tblcari.setModel(TableModels);
        TableViews.table(tblcari, new int[]{150, 70, 150, 150, 150, 100, 190});
        
        tampil();

    }
    
    private void cari(){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            awal = format.format(dc_awal.getDate());
            akhir = format.format(dc_akhir.getDate());
            TableModels.getDataVector().removeAllElements();
            if(cb_tanggal.isSelected() == false){
               try{
                    String query = "SELECT trxlayanan_id, regid, nama, "
                            + "nama_petugasmedis, namalayanan, namauser, tanggalbuat"
                            + " from periksa_medis WHERE trxlayanan_id like ? "
                            + "And regid like ? "
                            + "AND nama like ? "
                            + "AND nama_petugasmedis like ? "
                            + "AND namalayanan like ? ";
                   
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtIDtransaksi.getText()+ "%");
                    statement.setString(2, "%" +txtregid.getText()+ "%");
                    statement.setString(3, "%" +txtnama.getText()+ "%");
                    statement.setString(4, "%" +txtnamapetugas.getText()+ "%");
                    statement.setString(5, "%" + txtnamalayanan.getText() + "%");
                    ResultSet res = statement.executeQuery();
                    System.out.println(statement);
                    int baris = 0;
                    while (res.next()) {
                        baris = res.getRow();
                        TableModels.addRow(new Object[]{
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3),
                                    res.getString(4),
                                    res.getString(5),
                                    res.getString(6),
                                    res.getString(7)

                                });
                        tblcari.setModel(TableModels); 
                    }
                    if (baris == 0) {
                            JOptionPane.showMessageDialog(rootPane, "Hasil Pencarian = " + baris);
                        }else{
                        JOptionPane.showMessageDialog(rootPane, "Hasil Pencarian = " + baris);
                    }
                        statement.close();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }else{
              try{
                String query = "SELECT * from periksa_medis WHERE trxlayanan_id like ? "
                            + "And regid like ? "
                            + "AND nama like ? "
                            + "AND nama_petugasmedis like ?"
                            + "AND namalayanan like ? "
                            + "AND tanggalbuat between ? "
                            + "AND ?";                  
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtIDtransaksi.getText()+ "%");
                    statement.setString(2, "%" +txtregid.getText()+ "%");
                    statement.setString(3, "%" + txtnama.getText() + "%");
                    statement.setString(4, "%" +txtnamapetugas.getText()+ "%");
                    statement.setString(5, "%" + txtnamalayanan.getText() + "%");
                    statement.setString(6, awal);
                    statement.setString(7, akhir);
                    ResultSet res = statement.executeQuery();
                    int baris = 0;
                    while (res.next()) {
                        baris = res.getRow();
                        TableModels.addRow(new Object[]{
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3),
                                    res.getString(4),
                                    res.getString(5),
                                    res.getString(6),
                                    res.getString(7)

                                });
                        tblcari.setModel(TableModels);
                    }
                    if (baris == 0) {
                            JOptionPane.showMessageDialog(rootPane, "Hasil Pencarian = " + baris);
                    }else{
                            JOptionPane.showMessageDialog(rootPane, "Hasil Pencarian = " + baris);
                    }
                        statement.close();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void clear(){
        dc_awal.setVisible(false);
        dc_akhir.setVisible(false);
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
        txtnamapetugas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        dc_awal = new com.toedter.calendar.JDateChooser();
        dc_akhir = new com.toedter.calendar.JDateChooser();
        cb_tanggal = new javax.swing.JCheckBox();
        txtIDtransaksi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtregid = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtnamalayanan = new javax.swing.JTextField();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcari = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("PERIKSA PASIEN");
        jLabel1.setBounds(410, 10, 190, 20);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pencarian"));

        jLabel2.setText("Nama Petugas Medis");
        jLabel2.setBounds(290, 20, 130, 30);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtnamapetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamapetugasActionPerformed(evt);
            }
        });
        txtnamapetugas.setBounds(430, 20, 170, 30);
        jLayeredPane2.add(txtnamapetugas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("s/d");
        jLabel4.setBounds(630, 70, 20, 30);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Nama Layanan");
        jLabel5.setBounds(610, 20, 110, 30);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Nama Pasien");
        jLabel6.setBounds(20, 120, 80, 30);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaActionPerformed(evt);
            }
        });
        txtnama.setBounds(120, 120, 160, 30);
        jLayeredPane2.add(txtnama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        btn_cari.setBounds(740, 120, 110, 30);
        jLayeredPane2.add(btn_cari, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dc_awal.setBounds(430, 70, 160, 30);
        jLayeredPane2.add(dc_awal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dc_akhir.setBounds(690, 70, 160, 30);
        jLayeredPane2.add(dc_akhir, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cb_tanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cb_tanggal.setText("Tanggal");
        cb_tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tanggalActionPerformed(evt);
            }
        });
        cb_tanggal.setBounds(290, 70, 100, 30);
        jLayeredPane2.add(cb_tanggal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtIDtransaksi.setBounds(120, 20, 160, 30);
        jLayeredPane2.add(txtIDtransaksi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("ID Transaksi");
        jLabel3.setBounds(20, 20, 80, 30);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtregid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtregidActionPerformed(evt);
            }
        });
        txtregid.setBounds(120, 70, 160, 30);
        jLayeredPane2.add(txtregid, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText("Regid");
        jLabel7.setBounds(20, 70, 40, 30);
        jLayeredPane2.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtnamalayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamalayananActionPerformed(evt);
            }
        });
        txtnamalayanan.setBounds(740, 20, 120, 30);
        jLayeredPane2.add(txtnamalayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Pencarian"));

        tblcari.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcariMouseClicked(evt);
            }
        });
        tblcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblcariKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblcari);

        jScrollPane1.setBounds(10, 20, 850, 200);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                    .addComponent(jLayeredPane2)
                    .addComponent(jLayeredPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnamalayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamalayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamalayananActionPerformed

    private void txtnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        cari();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void cb_tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tanggalActionPerformed
        if(cb_tanggal.isSelected() == true){
            dc_awal.setVisible(true);
            dc_akhir.setVisible(true);
        }else {
            dc_awal.setVisible(false);
            dc_akhir.setVisible(false);
        }
    }//GEN-LAST:event_cb_tanggalActionPerformed

    private void tblcariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcariMouseClicked

    }//GEN-LAST:event_tblcariMouseClicked

    private void tblcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblcariKeyPressed
        //if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            //updatediagnosa();
       // }
    }//GEN-LAST:event_tblcariKeyPressed

    private void txtregidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtregidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtregidActionPerformed

    private void txtnamapetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamapetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamapetugasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JCheckBox cb_tanggal;
    private com.toedter.calendar.JDateChooser dc_akhir;
    private com.toedter.calendar.JDateChooser dc_awal;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblcari;
    private javax.swing.JTextField txtIDtransaksi;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnamalayanan;
    private javax.swing.JTextField txtnamapetugas;
    private javax.swing.JTextField txtregid;
    // End of variables declaration//GEN-END:variables
}
