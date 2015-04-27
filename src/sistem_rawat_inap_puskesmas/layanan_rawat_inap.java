/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem_rawat_inap_puskesmas;

import Class.TableViews;
import Class.koneksi;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import sun.rmi.transport.ObjectTable;

/**
 *
 * @author root
 */
public class layanan_rawat_inap extends javax.swing.JInternalFrame {

    private String dataku = null;
    private ResultSet res;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    JDesktopPane DP;
    private int id;
    
    
    public layanan_rawat_inap(JDesktopPane DP) {
        initComponents();
        this.DP = DP;
        SettingTableModel();
        clear();
   
    }
   
     private void SettingTableModel() {
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_Layanan", 
                    "Nama_Layanan", 
                    "Harga" 
                    },
                null, new int[]{}, null);
        tabellayanan.setModel(TableModels);
        TableViews.table(tabellayanan, new int[]{150, 250, 250});
        
        tampil();

    }
    
    
    private void tampil(){
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM master_layanan";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();            
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels.addRow(new Object[]{
                            res.getString("layanan_id"),
                            res.getString("namalayanan"),
                            res.getString("defaultharga")
                        });
                tabellayanan.setModel(TableModels); 
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void insert(){
        try {
            int a = 0;
            TableModels.getDataVector().removeAllElements();           
            String query = "insert into master_layanan values(?,?,?)";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, a );
            statement.setString(2, txtnama.getText());
            statement.setString(3, txtharga.getText());
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            clear();
            tampil();
            statement.close();           
           JOptionPane.showMessageDialog(rootPane, "Data Berhasil Masuk...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dimasukan : " + e);
        }
        
    }
    
    
    private void update(){
         try {
            TableModels.getDataVector().removeAllElements();           
            String query = "update master_layanan set namalayanan =?, defaultharga =? where layanan_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, txtnama.getText());
            statement.setString(2, txtharga.getText());
            statement.setInt(3, id);
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            clear();
            tampil();
            statement.close();           
           JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + e);
        }
    }
    
    private void delete(){
        try {
            TableModels.getDataVector().removeAllElements();           
            String query = "delete from master_layanan where layanan_id =?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            clear();
            tampil();
            statement.close();           
           JOptionPane.showMessageDialog(rootPane, "Data Berhasil Di hapus...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus : " + e);
        }
    }
    
    private void cari(){
        try {
            String sql = "" ;
            String awal = "";
            String akhir = "";
            if(cmbcari.getSelectedIndex() == 0){
               sql = "SELECT * FROM master_layanan where layanan_id like ?";
               awal = "%";
               akhir = "%";
            }
           if("nama layanan".equals(cmbcari.getSelectedItem())){
               sql = "SELECT * FROM master_layanan where namalayanan like ?";
               awal = "%";
               akhir = "%";
            }
            if("default harga".equals(cmbcari.getSelectedItem())){
                sql = "SELECT * FROM master_layanan where defaultharga like ?";
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
                            res.getString("layanan_id"),
                            res.getString("namalayanan"),
                            res.getString("defaultharga")
                        });
                tabellayanan.setModel(TableModels);
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
    
    private void ambil(){
            int row = tabellayanan.getSelectedRow();
            int col = tabellayanan.getSelectedColumn();
            String dataterpilih = tabellayanan.getValueAt(row, col).toString();
            String ambil = tabellayanan.getValueAt(row, 0).toString();
            txtnama.setText(tabellayanan.getValueAt(row, 1).toString());
            txtharga.setText(tabellayanan.getValueAt(row, 2).toString());
            
            id = Integer.parseInt(ambil);
    }
    
    private void clear(){
        txtharga.setText("");
        txtnama.setText("");
        txtcari.setText("");
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
        txtharga = new javax.swing.JTextField();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabellayanan = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        cmbcari = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Layanan Rawat Inap");
        jLabel1.setBounds(270, 20, 150, 14);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Layanan Rawat Inap"));

        jLabel2.setText("Nama Layanan");
        jLabel2.setBounds(20, 30, 100, 14);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtnama.setText("jTextField1");
        txtnama.setBounds(140, 30, 300, 30);
        jLayeredPane2.add(txtnama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("Default Harga");
        jLabel3.setBounds(20, 70, 90, 14);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtharga.setText("jTextField2");
        txtharga.setBounds(140, 70, 180, 30);
        jLayeredPane2.add(txtharga, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        btnsimpan.setBounds(50, 20, 90, 23);
        jLayeredPane3.add(btnsimpan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        btnedit.setBounds(50, 50, 90, 23);
        jLayeredPane3.add(btnedit, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        btndelete.setBounds(50, 80, 90, 23);
        jLayeredPane3.add(btndelete, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText("Batal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(50, 110, 90, 23);
        jLayeredPane3.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Layanan"));

        tabellayanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabellayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabellayananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabellayanan);

        jScrollPane1.setBounds(10, 80, 650, 150);
        jLayeredPane4.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtcari.setText("jTextField3");
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

        cmbcari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "layanan id", "nama layanan", "default harga", " " }));
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
                    .addComponent(jLayeredPane4)
                    .addComponent(jLayeredPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(jLayeredPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        insert();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        cari();
    }//GEN-LAST:event_btncariActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        cari();
    }//GEN-LAST:event_txtcariActionPerformed

    private void tabellayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabellayananMouseClicked
        ambil();   
    }//GEN-LAST:event_tabellayananMouseClicked

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        update();
    }//GEN-LAST:event_btneditActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        delete();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncari;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox cmbcari;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabellayanan;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtnama;
    // End of variables declaration//GEN-END:variables
}
