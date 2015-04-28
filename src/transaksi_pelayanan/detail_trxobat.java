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
public class detail_trxobat extends javax.swing.JPanel {

    private String dataku = null;
    private ResultSet res;
    JDesktopPane DP;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    private String awal;
    private String akhir;
    
    public detail_trxobat() {
        initComponents();
        
        SettingTableModel();
        clear();
        set_tanggal();
    }
    
     private void tampil(){
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "select * from transaksi_obat";
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
                            res.getString(7),
                            res.getString(8),
                            res.getString(9),
                            res.getString(10),
                            res.getString(11)
                            
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
                    "Transaksi Obat ID", 
                    "Registrasi ID", 
                    "Nama", 
                    "Nama Petugas Medis", 
                    "Obat ID", 
                    "Nama Obat", 
                    "Harga", 
                    "Satuan",
                    "Total", 
                    "Nama User",
                    "Tanggal Buat"
                },null, new int[]{}, null);
        tblcari.setModel(TableModels);
        TableViews.table(tblcari, new int[]{150, 100, 150, 150, 150, 100, 150, 150, 100, 100, 100});
        
        tampil();

    }
    
    private void cari(){
        TableModels.getDataVector().removeAllElements();
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "Transaksi Obat ID", 
                    "Registrasi ID", 
                    "Nama", 
                    "Nama Petugas Medis", 
                    "Obat ID", 
                    "Nama Obat", 
                    "Harga", 
                    "Satuan",
                    "Total", 
                    "Nama User",
                    "Tanggal Buat"
                },null, new int[]{}, null);
        tblcari.setModel(TableModels);
        TableViews.table(tblcari, new int[]{150, 100, 150, 150, 150, 100, 150, 150, 100, 100, 100});

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            awal = format.format(dc_awal.getDate());
            akhir = format.format(dc_akhir.getDate());
            if(cb_tanggal.isSelected() == false){
               try{
                    String query = "SELECT * from transaksi_obat WHERE "
                            + "trxobt_id like ? "
                            + "AND regid like ? "
                            + "AND nama like ? "
                            + "AND namaobat like ?";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txt_trxobatID.getText()+ "%");
                    statement.setString(2, "%" +txtregid.getText()+ "%");
                    statement.setString(3, "%" + txt_nama.getText() + "%");
                    statement.setString(4, "%" + txt_namaobat.getText() + "%");
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
                                    res.getString(7),
                                    res.getString(8),
                                    res.getString(9),
                                    res.getString(10),
                                     res.getString(11)

                                });
                        tblcari.setModel(TableModels); 
                    }
                    if (baris == 0) {
                            JOptionPane.showMessageDialog(null, "Hasil Pencarian = " + baris);
                        }else{
                        JOptionPane.showMessageDialog(null, "Hasil Pencarian = " + baris);
                    }
                        statement.close();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }else{
              try{
                String query = "SELECT * from transaksi_obat WHERE "
                            + "trxobt_id like ? "
                            + "AND regid like ? "
                            + "AND nama like ? "
                            + "AND namaobat like ?"
                            + "AND tanggalbuat between ? "
                            + "AND ?";                  
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txt_trxobatID.getText()+ "%");
                    statement.setString(2, "%" +txtregid.getText()+ "%");
                    statement.setString(3, "%" + txt_nama.getText() + "%");
                    statement.setString(4, "%" + txt_namaobat.getText() + "%");
                    statement.setString(5, awal);
                    statement.setString(6, akhir);
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
                                    res.getString(7),
                                    res.getString(8),
                                    res.getString(9),
                                    res.getString(10),
                                    res.getString(11)

                                });
                        tblcari.setModel(TableModels); 
                    }
                    if (baris == 0) {
                            JOptionPane.showMessageDialog(null, "Hasil Pencarian = " + baris);
                    }else{
                            JOptionPane.showMessageDialog(null, "Hasil Pencarian = " + baris);
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
        txt_trxobatID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtregid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_namaobat = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        dc_awal = new com.toedter.calendar.JDateChooser();
        dc_akhir = new com.toedter.calendar.JDateChooser();
        cb_tanggal = new javax.swing.JCheckBox();
        txt_nama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcari = new javax.swing.JTable();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Halaman Detail Transaksi Obat");
        jLabel1.setBounds(260, 10, 240, 20);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pencarian"));

        jLabel2.setText("trxobat_id");
        jLabel2.setBounds(20, 20, 80, 30);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txt_trxobatID.setBounds(130, 20, 160, 30);
        jLayeredPane2.add(txt_trxobatID, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("s/d");
        jLabel4.setBounds(510, 60, 20, 30);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Regid");
        jLabel5.setBounds(310, 20, 40, 30);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtregid.setBounds(360, 20, 110, 30);
        jLayeredPane2.add(txtregid, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Nama Pasien");
        jLabel6.setBounds(490, 20, 80, 30);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txt_namaobat.setBounds(130, 60, 110, 30);
        jLayeredPane2.add(txt_namaobat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        btn_cari.setBounds(570, 110, 120, 23);
        jLayeredPane2.add(btn_cari, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dc_awal.setBounds(350, 60, 130, 30);
        jLayeredPane2.add(dc_awal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dc_akhir.setBounds(560, 60, 130, 30);
        jLayeredPane2.add(dc_akhir, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cb_tanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cb_tanggal.setText("Tanggal");
        cb_tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tanggalActionPerformed(evt);
            }
        });
        cb_tanggal.setBounds(250, 60, 90, 30);
        jLayeredPane2.add(cb_tanggal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        txt_nama.setBounds(580, 20, 110, 30);
        jLayeredPane2.add(txt_nama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText("Nama Obat");
        jLabel7.setBounds(20, 60, 100, 30);
        jLayeredPane2.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        jScrollPane1.setViewportView(tblcari);

        jScrollPane1.setBounds(10, 20, 690, 200);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addComponent(jLayeredPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void tblcariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcariMouseClicked

    }//GEN-LAST:event_tblcariMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JCheckBox cb_tanggal;
    private com.toedter.calendar.JDateChooser dc_akhir;
    private com.toedter.calendar.JDateChooser dc_awal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblcari;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_namaobat;
    private javax.swing.JTextField txt_trxobatID;
    private javax.swing.JTextField txtregid;
    // End of variables declaration//GEN-END:variables
}
