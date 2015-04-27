/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi_pelayanan;

import Class.TableViews;
import Class.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class tambah_layanan extends javax.swing.JPanel {

    private ResultSet res;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    String layanan_id;
    String regid;
    private String awal;
    private String akhir;
    String generate_trxlayananid;
   
    
    public tambah_layanan(String regid) {
        initComponents();
        this.regid = regid;
    
        SettingTableCariMasterLayanan();
    }
    
    private void SettingTableCariMasterLayanan() {
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_Layanan", 
                    "Nama_Layanan", 
                    "Harga" 
                    },
                null, new int[]{}, null);
        tblcarimasterlayan.setModel(TableModels);
        TableViews.table(tblcarimasterlayan, new int[]{150, 100, 150});
        
        TampilCariMasterLayanan();
    }
    
     private void TampilCariMasterLayanan(){
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
                tblcarimasterlayan.setModel(TableModels); 
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
      private void carimasterlayanan(){
        TableModels.getDataVector().removeAllElements();
           TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_Layanan", 
                    "Nama_Layanan", 
                    "Harga" 
                    },
                null, new int[]{}, null);
        tblcarimasterlayan.setModel(TableModels);
        TableViews.table(tblcarimasterlayan, new int[]{150, 100, 150});
        
               try{
                    String query = "SELECT * FROM master_layanan where layanan_id like ? "
                            + "AND namalayanan like ? "
                            + "AND defaultharga like ?";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtidcari.getText()+ "%");
                    statement.setString(2, "%" +txtnamacari.getText()+ "%");
                    statement.setString(3, "%" +txthargacari.getText()+ "%");
                    ResultSet res = statement.executeQuery();
                    int baris = 0;
                    while (res.next()) {
                        baris = res.getRow();
                        TableModels.addRow(new Object[]{
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3)

                                });
                        tblcarimasterlayan.setModel(TableModels); 
                    }
                    /*if (baris == 0) {
                            JOptionPane.showMessageDialog(null, "Hasil Pencarian = " + baris);
                        }else{
                        JOptionPane.showMessageDialog(null, "Hasil Pencarian = " + baris);
                    }*/
                        statement.close();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            
      }
      
      private void ambilcarimasterlayanan(){
            int row = tblcarimasterlayan.getSelectedRow();
            int col = tblcarimasterlayan.getSelectedColumn();
            String dataterpilih = tblcarimasterlayan.getValueAt(row, col).toString();
            String idlayan = tblcarimasterlayan.getValueAt(row, 0).toString();
            String namalayan = tblcarimasterlayan.getValueAt(row, 1).toString();
            String hargalayanan = tblcarimasterlayan.getValueAt(row, 2).toString();
           
           layanan_id = idlayan;
           txtlayanan.setText(namalayan);
           txtharga.setText(hargalayanan);
           
          
     }
      
      private void inserttransaksilayanan(){
        try {
            generate();
            TableModels.getDataVector().removeAllElements();              
            String query = "insert into trx_layanan values(?,?,?,?,?,?,1,now())";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, generate_trxlayananid);
            statement.setString(2, regid);
            statement.setString(3, layanan_id);
            statement.setString(4, txtharga.getText());
            statement.setString(5, txtsatuan.getText());
            statement.setString(6, txttotal.getText());
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            statement.close();           
           JOptionPane.showMessageDialog(null, "Data Berhasil Masuk...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dimasukan : " + e);
        }
        
     }
      
       private void generate(){
         DateFormat hour = new SimpleDateFormat("HHmmssddMMyyyy");
         Date date = new Date();
         String now = hour.format(date);
         generate_trxlayananid = ("trxlayan" + now); 
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtsatuan = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        btnsimpanlayan = new javax.swing.JButton();
        btnbersihlayan = new javax.swing.JButton();
        txtlayanan = new javax.swing.JTextField();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcarimasterlayan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtidcari = new javax.swing.JTextField();
        txtnamacari = new javax.swing.JTextField();
        txthargacari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Masukan Transaksi Layanan");
        jLabel1.setBounds(110, 10, 160, 14);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data"));

        jLabel3.setText("Layanan");
        jLabel3.setBounds(20, 20, 110, 30);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("Harga");
        jLabel4.setBounds(20, 70, 100, 30);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtharga.setBounds(140, 70, 170, 30);
        jLayeredPane2.add(txtharga, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Satuan");
        jLabel5.setBounds(20, 120, 60, 30);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText("Total Harga");
        jLabel7.setBounds(20, 170, 70, 30);
        jLayeredPane2.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtsatuan.setBounds(140, 120, 110, 30);
        jLayeredPane2.add(txtsatuan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txttotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttotalMouseClicked(evt);
            }
        });
        txttotal.setBounds(140, 170, 170, 30);
        jLayeredPane2.add(txttotal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnsimpanlayan.setText("Simpan");
        btnsimpanlayan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanlayanActionPerformed(evt);
            }
        });
        btnsimpanlayan.setBounds(20, 220, 90, 23);
        jLayeredPane2.add(btnsimpanlayan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnbersihlayan.setText("Bersihkan");
        btnbersihlayan.setBounds(243, 220, 100, 23);
        jLayeredPane2.add(btnbersihlayan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtlayanan.setBounds(140, 20, 170, 30);
        jLayeredPane2.add(txtlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Layanan"));

        tblcarimasterlayan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcarimasterlayan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcarimasterlayanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcarimasterlayan);

        jScrollPane1.setBounds(10, 120, 400, 180);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText("ID Layanan");
        jLabel2.setBounds(20, 30, 70, 14);
        jLayeredPane3.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Nama Layanan");
        jLabel6.setBounds(20, 70, 90, 30);
        jLayeredPane3.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setText("Harga Layanan");
        jLabel8.setBounds(220, 30, 90, 30);
        jLayeredPane3.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtidcari.setBounds(120, 30, 90, 30);
        jLayeredPane3.add(txtidcari, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnamacari.setBounds(120, 70, 90, 30);
        jLayeredPane3.add(txtnamacari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txthargacari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargacariActionPerformed(evt);
            }
        });
        txthargacari.setBounds(320, 30, 90, 30);
        jLayeredPane3.add(txthargacari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        btncari.setBounds(310, 70, 100, 30);
        jLayeredPane3.add(btncari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addComponent(jLayeredPane2))
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txttotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttotalMouseClicked
        int total = (Integer.parseInt(txtharga.getText()) * (Integer.parseInt(txtsatuan.getText())));
        txttotal.setText(Integer.toString(total));
    }//GEN-LAST:event_txttotalMouseClicked

    private void txthargacariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargacariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargacariActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        carimasterlayanan();
    }//GEN-LAST:event_btncariActionPerformed

    private void tblcarimasterlayanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcarimasterlayanMouseClicked
        ambilcarimasterlayanan();
    }//GEN-LAST:event_tblcarimasterlayanMouseClicked

    private void btnsimpanlayanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanlayanActionPerformed
       inserttransaksilayanan();
    }//GEN-LAST:event_btnsimpanlayanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbersihlayan;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnsimpanlayan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblcarimasterlayan;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txthargacari;
    private javax.swing.JTextField txtidcari;
    private static javax.swing.JTextField txtlayanan;
    private javax.swing.JTextField txtnamacari;
    private javax.swing.JTextField txtsatuan;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
