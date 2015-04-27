/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem_rawat_inap_puskesmas;

import Class.koneksi;
import Class.TableViews;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author root
 */
public class pencarian_pelayanan extends javax.swing.JInternalFrame {

    private String dataku = null;
    private ResultSet res;
    JDesktopPane DP;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    private String awal;
    private String akhir;
    
    public pencarian_pelayanan(JDesktopPane DP) {
        initComponents();
        this.DP = DP;
        SettingTableModel();
        clear();
        set_tanggal();
        
        
    }
    
    
    private void tampil(){
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT a.regid as f1, a.medrec_id as f2, "
                    + "b.nama as f3, a.tanggalmasuk as f4,c.nama_petugasmedis as f5,"
                    + " a.tanggalpulang as f6, d.namauser as f7,"
                    + "a.tanggalbuat as f8 FROM regpasien a, master_medrec b,"
                    + " master_petugasmedis c, master_user d WHERE "
                    + "a.medrec_id = b.medrec_id AND a.dokter_id = c.petugasmedis_id"
                    + " and d.user_id = a.petugasbuat";
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
                            res.getString(8)
                            
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
                    "Registrasi_id", 
                    "Medrec_id", 
                    "Nama", 
                    "Tanggal Masuk", 
                    "Nama Dokter", 
                    "Tanggal Pulang", 
                    "Petugas", 
                    "Tanggal Buat"},
                null, new int[]{}, null);
        tblcari.setModel(TableModels);
        TableViews.table(tblcari, new int[]{100, 100, 150, 150, 150, 100, 150, 150, 100});
        
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
                    String query = "SELECT a.regid, a.medrec_id, "
                            + "b.nama, a.tanggalmasuk, c.nama_petugasmedis, "
                            + "a.tanggalpulang, d.namauser, "
                            + "a.tanggalbuat FROM regpasien a, master_medrec b, "
                            + "master_petugasmedis c, master_user d WHERE a.medrec_id = "
                            + "b.medrec_id AND a.dokter_id = c.petugasmedis_id "
                            + "And d.user_id = a.petugasbuat "
                            + "AND a.regid like ?"
                            + "AND a.medrec_id like ?"
                            + "AND b.nama like ?";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txt_regid.getText()+ "%");
                    statement.setString(2, "%" +txt_medrec.getText()+ "%");
                    statement.setString(3, "%" + txt_nama.getText() + "%");
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
                                    res.getString(8)

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
                String query = "SELECT a.regid AS f1, a.medrec_id AS f2, "
                                + "b.nama AS f3, a.tanggalmasuk AS f4, c.nama_petugasmedis AS f5, "
                                + "a.tanggalpulang AS f6, d.namauser AS f7, "
                                + "a.tanggalbuat AS f8 FROM regpasien a, master_medrec b, "
                                + "master_petugasmedis c, master_user d WHERE a.medrec_id = "
                                + "b.medrec_id AND a.dokter_id = c.petugasmedis_id AND"
                                + " d.user_id = a.petugasbuat "
                                + "AND a.regid like ?"
                                + "AND a.medrec_id like ?"
                                + "AND b.nama like ?"
                                + "AND a.tanggalmasuk between ? "
                                + "AND ?";                  
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txt_regid.getText()+ "%");
                    statement.setString(2, "%" +txt_medrec.getText()+ "%");
                    statement.setString(3, "%" + txt_nama.getText() + "%");
                    statement.setString(4, awal);
                    statement.setString(5, akhir);
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
                                    res.getString(8)

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
        txt_regid.setText("");
        txt_nama.setText("");
        txt_medrec.setText("");
        dc_awal.setVisible(false);
        dc_akhir.setVisible(false);
    }
    
  
    
     private void ambil(){
            int row = tblcari.getSelectedRow();
            int col = tblcari.getSelectedColumn();
            String dataterpilih = tblcari.getValueAt(row, col).toString();
            String ambil = tblcari.getValueAt(row, 0).toString();
            
            transaksi_pelayanan.form_transaksi_panel trans = new transaksi_pelayanan.form_transaksi_panel(DP, ambil);
            DP.removeAll();
            DP.repaint();
            DP.add(trans);
            trans.show();
            this.dispose();
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
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcari = new javax.swing.JTable();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        txt_regid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_medrec = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        dc_awal = new com.toedter.calendar.JDateChooser();
        dc_akhir = new com.toedter.calendar.JDateChooser();
        cb_tanggal = new javax.swing.JCheckBox();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Halaman Cari Pasien Layanan");
        jLabel1.setBounds(270, 10, 190, 14);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        jScrollPane1.setBounds(10, 20, 690, 170);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pencarian"));

        jLabel2.setText("Registrasi_ id");
        jLabel2.setBounds(20, 20, 80, 30);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txt_regid.setText("jTextField1");
        txt_regid.setBounds(120, 20, 160, 30);
        jLayeredPane2.add(txt_regid, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("s/d");
        jLabel4.setBounds(280, 60, 20, 20);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Medrec");
        jLabel5.setBounds(310, 20, 40, 30);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txt_medrec.setText("jTextField4");
        txt_medrec.setBounds(360, 20, 110, 30);
        jLayeredPane2.add(txt_medrec, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Nama Pasien");
        jLabel6.setBounds(500, 20, 80, 30);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txt_nama.setText("jTextField5");
        txt_nama.setBounds(580, 20, 100, 30);
        jLayeredPane2.add(txt_nama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        btn_cari.setBounds(470, 70, 100, 23);
        jLayeredPane2.add(btn_cari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        btn_batal.setBounds(580, 70, 110, 23);
        jLayeredPane2.add(btn_batal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dc_awal.setBounds(121, 60, 130, 30);
        jLayeredPane2.add(dc_awal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dc_akhir.setBounds(320, 60, 130, 30);
        jLayeredPane2.add(dc_akhir, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cb_tanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cb_tanggal.setText("Tanggal");
        cb_tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tanggalActionPerformed(evt);
            }
        });
        cb_tanggal.setBounds(20, 60, 90, 30);
        jLayeredPane2.add(cb_tanggal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane2)
                    .addComponent(jLayeredPane1)
                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        cari();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void tblcariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcariMouseClicked
        ambil();
    }//GEN-LAST:event_tblcariMouseClicked

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
       this.dispose();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void cb_tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tanggalActionPerformed
        if(cb_tanggal.isSelected() == true){
            dc_awal.setVisible(true);
            dc_akhir.setVisible(true);
        }else {
            dc_awal.setVisible(false);
            dc_akhir.setVisible(false);
        }
    }//GEN-LAST:event_cb_tanggalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JCheckBox cb_tanggal;
    private com.toedter.calendar.JDateChooser dc_akhir;
    private com.toedter.calendar.JDateChooser dc_awal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblcari;
    private javax.swing.JTextField txt_medrec;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_regid;
    // End of variables declaration//GEN-END:variables
}
