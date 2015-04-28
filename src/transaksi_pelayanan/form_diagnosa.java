/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi_pelayanan;

import Class.TableViews;
import Class.koneksi;
import java.awt.event.KeyEvent;
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
public class form_diagnosa extends javax.swing.JInternalFrame {

    private String dataku = null;
    private ResultSet res;
    JDesktopPane DP;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    private String awal;
    private String akhir;
    
    public form_diagnosa(JDesktopPane DP) {
        initComponents();
        
        this.DP = DP;
        SettingTableModel();
        clear();
        set_tanggal();
    }
    
    private void tampil(){
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "select * from diagnosapasien";
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
                    "Diagnosa ID", 
                    "Registrasi ID", 
                    "Diagnosa Awal", 
                    "Diagnosa Utama", 
                    "Diagnosa Keluar", 
                    "Petugas", 
                    "Tanggal Buat"},
                null, new int[]{2,3,4}, null);
        tblcari.setModel(TableModels);
        TableViews.table(tblcari, new int[]{100, 100, 150, 150, 150, 100, 150, 150});
        
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
                    String query = "SELECT * from diagnosapasien WHERE "
                            + "diagnosapasien_id like ? "
                            + "AND regid like ?"; 
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtdiagnosaid.getText()+ "%");
                    statement.setString(2, "%" +txtregid.getText()+ "%");
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
            }else{
              try{
                String query = "SELECT * from diagnosapasien WHERE "
                            + "diagnosapasien_id like ? " 
                            + "AND regid like ? "
                            + "AND tanggalbuat between ? "
                            + "AND ?";                  
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtdiagnosaid.getText()+ "%");
                    statement.setString(2, "%" +txtregid.getText()+ "%");
                    statement.setString(3, awal);
                    statement.setString(4, akhir);
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
    
     private void updatediagnosa(){
            int row = tblcari.getSelectedRow();
            int col = tblcari.getSelectedColumn();
            String dataterpilih = tblcari.getValueAt(row, col).toString();
            String diagnosa_id = tblcari.getValueAt(row, 0).toString();
            String diagnosaawal = tblcari.getValueAt(row, 2).toString();
            String diagnosautama = tblcari.getValueAt(row, 3).toString();
            String diagnosakeluar = tblcari.getValueAt(row, 4).toString();
            try {        
            String query = "UPDATE diagnosapasien SET diagnosaawal = ?,"
                    + "diagnosautama = ?,"
                    + "diagnosakeluar = ?,"
                    + "petugasbuat = 1, tanggalbuat = now() "
                    + "WHERE diagnosapasien_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, diagnosaawal);
            statement.setString(2, diagnosautama);
            statement.setString(3, diagnosakeluar);
            statement.setString(4, diagnosa_id);
            statement.executeUpdate();
            statement.close();          
          
            JOptionPane.showMessageDialog(null, "Data Diagnosa Berhasil diubah...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Diagnosa Gagal diubah : " + ex);
        }
         
      }
     
      private void deletediagnosa(){
            int row = tblcari.getSelectedRow();
            int col = tblcari.getSelectedColumn();
            String dataterpilih = tblcari.getValueAt(row, col).toString();
            String diagnosa_id = tblcari.getValueAt(row, 0).toString();
          
         //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
          try {        
            TableModels.getDataVector().removeAllElements();   
            String query = "DELETE FROM diagnosapasien WHERE diagnosapasien_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, diagnosa_id);
           
            statement.executeUpdate();
            statement.close();          
     
          TableModels.getDataVector().removeAllElements();
          SettingTableModel();
          
          JOptionPane.showMessageDialog(rootPane, "Data Berhasil dihapus...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal dihapus : " + ex);
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
        txtdiagnosaid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtregid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        dc_awal = new com.toedter.calendar.JDateChooser();
        dc_akhir = new com.toedter.calendar.JDateChooser();
        cb_tanggal = new javax.swing.JCheckBox();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcari = new javax.swing.JTable();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("DIAGNOSA PASIEN RAWAT INAP");
        jLabel1.setBounds(270, 10, 190, 14);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pencarian"));

        jLabel2.setText("Diagnosa ID");
        jLabel2.setBounds(20, 20, 80, 30);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtdiagnosaid.setBounds(120, 20, 160, 30);
        jLayeredPane2.add(txtdiagnosaid, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("s/d");
        jLabel4.setBounds(280, 60, 20, 20);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Regid");
        jLabel5.setBounds(310, 20, 40, 30);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtregid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtregidActionPerformed(evt);
            }
        });
        txtregid.setBounds(360, 20, 110, 30);
        jLayeredPane2.add(txtregid, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Nama Pasien");
        jLabel6.setBounds(500, 20, 80, 30);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaActionPerformed(evt);
            }
        });
        txtnama.setBounds(580, 20, 100, 30);
        jLayeredPane2.add(txtnama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        btn_cari.setBounds(470, 70, 100, 23);
        jLayeredPane2.add(btn_cari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        btn_hapus.setBounds(580, 70, 110, 23);
        jLayeredPane2.add(btn_hapus, javax.swing.JLayeredPane.DEFAULT_LAYER);
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

        jScrollPane1.setBounds(10, 20, 690, 200);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        cari();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        deletediagnosa();
    }//GEN-LAST:event_btn_hapusActionPerformed

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

    private void txtregidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtregidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtregidActionPerformed

    private void txtnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaActionPerformed

    private void tblcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblcariKeyPressed
       if(evt.getKeyCode()== KeyEvent.VK_ENTER){
           updatediagnosa();
       }
    }//GEN-LAST:event_tblcariKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
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
    private javax.swing.JTextField txtdiagnosaid;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtregid;
    // End of variables declaration//GEN-END:variables
}
