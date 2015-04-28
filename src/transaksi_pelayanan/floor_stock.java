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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class floor_stock extends javax.swing.JInternalFrame {

    private String dataku = null;
    private ResultSet res;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    JDesktopPane DP;
    String obat_id;
    
    public floor_stock(JDesktopPane DP) {
        initComponents();
        
        this.DP = DP;
        SettingTableModel();
        generate();
    }
    
     private void SettingTableModel() {
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_Obat", 
                    "Nama_Obat", 
                    "Harga",
                    "Stock"                      
                    },
                null, new int[]{2,3,4}, null);
        tabelobt.setModel(TableModels);
        TableViews.table(tabelobt, new int[]{150, 150, 150, 150});
        
        tampil();

    }
    
    
    private void tampil(){
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM master_obat";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();            
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels.addRow(new Object[]{
                            res.getString("obat_id"),
                            res.getString("namaobat"),
                            res.getString("defaulharga"),
                            res.getString("stok")
                        });
                tabelobt.setModel(TableModels); 
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void insert(){
        generate();
        try {
            TableModels.getDataVector().removeAllElements();           
            String query = "insert into master_obat values(?,?,?,?)";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, obat_id );
            statement.setString(2, txtnama.getText());
            statement.setString(3, txtharga.getText());
            statement.setString(4, txtstok.getText());
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            tampil();
            statement.close();           
           JOptionPane.showMessageDialog(rootPane, "Data Berhasil Masuk...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dimasukan : " + e);
        }
        
    }
    
      private void updateobat(){
            int row = tabelobt.getSelectedRow();
            int col = tabelobt.getSelectedColumn();
            String dataterpilih = tabelobt.getValueAt(row, col).toString();
            String obat_id = tabelobt.getValueAt(row, 0).toString();
            String nama_obat = tabelobt.getValueAt(row, 1).toString();
            String harga_obat = tabelobt.getValueAt(row, 2).toString();
            String stok_obat = tabelobt.getValueAt(row, 3).toString();
           
         //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
          try {        
            TableModels.getDataVector().removeAllElements();   
            String query = "UPDATE master_obat SET namaobat = ?,"
                    + "defaulharga = ?,stok = ? "
                    + "WHERE obat_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, nama_obat);
            statement.setString(2, harga_obat);
            statement.setString(3, stok_obat);
            statement.setString(4, obat_id);
            statement.executeUpdate();
            statement.close();          
     
          TableModels.getDataVector().removeAllElements();
          tampil();
          
          JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + ex);
        }
         
      }
    
    private void deleteobat(){
            int row = tabelobt.getSelectedRow();
            int col = tabelobt.getSelectedColumn();
            String dataterpilih = tabelobt.getValueAt(row, col).toString();
            String obat_id = tabelobt.getValueAt(row, 0).toString();
         //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
          try {        
            TableModels.getDataVector().removeAllElements();   
            String query = "DELETE FROM master_obat WHERE obat_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, obat_id);
            statement.executeUpdate();
            statement.close();          
     
          TableModels.getDataVector().removeAllElements();
          tampil();
          
          JOptionPane.showMessageDialog(rootPane, "Data Berhasil Dihapus...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus : " + ex);
        }
         
      }

    
    private void cari(){
        try {
            String sql = "" ;
            String awal = "";
            String akhir = "";
            if(cmbcari.getSelectedIndex() == 0){
               sql = "SELECT * FROM master_obat where obat_id like ?";
               awal = "%";
               akhir = "%";
            }
           if(cmbcari.getSelectedIndex() == 1){
               sql = "SELECT * FROM master_obat where namaobat like ?";
               awal = "%";
               akhir = "%";
            }
            if(cmbcari.getSelectedIndex() == 2){
                sql = "SELECT * FROM master_obat where defaulharga like ?";
                awal = "%";
                akhir = "%";
            }
            if(cmbcari.getSelectedIndex() == 3){
                sql = "SELECT * FROM master_obat where stok like ?";
                awal = "%";
                akhir = "%";
            }
            String query = sql;
            String cari = txtcari.getText();
            TableModels.getDataVector().removeAllElements();
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, awal + cari + akhir);
            ResultSet res = statement.executeQuery();
            int baris = 0;
            while (res.next()) {
                baris = res.getRow();
                TableModels.addRow(new Object[]{
                            res.getString("obat_id"),
                            res.getString("namaobat"),
                            res.getString("defaulharga"),
                            res.getString("stok")
                        });
                tabelobt.setModel(TableModels);
                TableModels.getDataVector().removeAllElements();
            }
             
            if (baris == 0) {
                JOptionPane.showMessageDialog(rootPane, "Hasil Pencarian = " + baris);
            }else {
                JOptionPane.showMessageDialog(rootPane, "Hasil Pencarian = " + baris);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void generate(){
         DateFormat hour = new SimpleDateFormat("HHmmssddMMyyyy");
         Date date = new Date();
         String now = hour.format(date);
         obat_id = ("OB" + now); 
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
        txtnama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtstok = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        btnsimpan = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelobt = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        cmbcari = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Floor Stock Obat");
        jLabel1.setBounds(260, 20, 150, 14);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Layanan Rawat Inap"));

        jLabel2.setText("Nama Obat");
        jLabel2.setBounds(20, 30, 100, 30);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnama.setBounds(140, 30, 300, 30);
        jLayeredPane2.add(txtnama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("Stok Obat");
        jLabel3.setBounds(20, 110, 90, 30);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtstok.setBounds(140, 110, 110, 30);
        jLayeredPane2.add(txtstok, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtharga.setBounds(140, 70, 180, 30);
        jLayeredPane2.add(txtharga, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Default Harga");
        jLabel5.setBounds(20, 70, 90, 30);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        btnsimpan.setBounds(50, 20, 90, 23);
        jLayeredPane3.add(btnsimpan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        btndelete.setBounds(50, 50, 90, 23);
        jLayeredPane3.add(btndelete, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText("Batal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(50, 80, 90, 23);
        jLayeredPane3.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Layanan"));

        tabelobt.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelobt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelobtMouseClicked(evt);
            }
        });
        tabelobt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelobtKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelobt);

        jScrollPane1.setBounds(10, 80, 650, 170);
        jLayeredPane4.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.setBounds(280, 30, 180, 30);
        jLayeredPane4.add(txtcari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        btncari.setBounds(470, 30, 73, 30);
        jLayeredPane4.add(btncari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cmbcari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID Obat", "Nama Obat", "Harga Obat", "Stock", " " }));
        cmbcari.setBounds(110, 30, 150, 30);
        jLayeredPane4.add(cmbcari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("Pencarian");
        jLabel4.setBounds(20, 30, 80, 30);
        jLayeredPane4.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                    .addComponent(jLayeredPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 44, Short.MAX_VALUE))
                    .addComponent(jLayeredPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        insert();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        deleteobat();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabelobtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelobtMouseClicked

    }//GEN-LAST:event_tabelobtMouseClicked

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        cari();
    }//GEN-LAST:event_txtcariActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        cari();
    }//GEN-LAST:event_btncariActionPerformed

    private void tabelobtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelobtKeyPressed
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
           updateobat();
       }
    }//GEN-LAST:event_tabelobtKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncari;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox cmbcari;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelobt;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtstok;
    // End of variables declaration//GEN-END:variables
}
