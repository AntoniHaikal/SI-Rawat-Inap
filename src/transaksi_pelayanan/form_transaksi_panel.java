/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi_pelayanan;

import Class.TableViews;
import Class.koneksi;
import Sistem_monitoring_mutasi_ri.frmCariMedrec;
import java.awt.event.KeyEvent;
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
import sistem_rawat_inap_puskesmas.pencarian_pelayanan;

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
                    "ID Petugas Medis",
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
        TableViews.table(tbltransaksilayanan, new int[]{200, 100, 100, 150, 150, 150, 150, 100, 50, 100, 100, 100});

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
                            res.getString(11),
                            res.getString(12)
                          
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
                    "Harga",
                    "Satuan",
                    "Total",
                    "Petugas",
                    "Tanggal Dibuat"
                },
                null, new int[]{1,4,7,8}, null);
        tbltransaksiobt.setModel(TableModels);
        TableViews.table(tbltransaksiobt, new int[]{200, 100, 150, 150, 150, 150, 100, 50, 100, 100, 100});

        tampil_trxobat();

    }
    
      private void tampil_trxobat() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM transaksi_obat where regid = ?";
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
                tbltransaksiobt.setModel(TableModels);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      
       private void updatetrxlayanan(){
            int row = tbltransaksilayanan.getSelectedRow();
            int col = tbltransaksilayanan.getSelectedColumn();
            String dataterpilih = tbltransaksilayanan.getValueAt(row, col).toString();
            String trxlyanan_id = tbltransaksilayanan.getValueAt(row, 0).toString();
            String regid = tbltransaksilayanan.getValueAt(row, 1).toString();
            String id_layanan = tbltransaksilayanan.getValueAt(row, 4).toString();
            String harga_layanan = tbltransaksilayanan.getValueAt(row, 6).toString();
            String satuan_layanan = tbltransaksilayanan.getValueAt(row, 7).toString();
            String total_layanan = Integer.toString((Integer.parseInt(harga_layanan))*(Integer.parseInt(satuan_layanan)));
         //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
          try {        
            TableModels.getDataVector().removeAllElements();   
            String query = "UPDATE trx_layanan SET regid = ?,"
                    + "layanan_id = ?,defaultharga = ?,satuan = ?,totalharga = ?,"
                    + "petugasbuat = 1, tanggalbuat = now() "
                    + "WHERE trxlayanan_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, regid);
            statement.setString(2, id_layanan);
            statement.setString(3, harga_layanan);
            statement.setString(4, satuan_layanan);
            statement.setString(5, total_layanan);
            statement.setString(6, trxlyanan_id);
            statement.executeUpdate();
            statement.close();          
     
          TableModels.getDataVector().removeAllElements();
          table_model_trxlayanan();
          
          JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + ex);
        }
         
         /*int x = 0;
        int y = tbltransaksilayanan.getColumnCount();
        int n = 0;
        System.out.println(x + " - " + y);
        for (x = 0; x < y; x++) {
            n = 0;
            String a = (String) tbltransaksilayanan.getValueAt(x, 0);
            String b = (String) tbltransaksilayanan.getValueAt(x, 1);
            String c = (String) tbltransaksilayanan.getValueAt(x, 2);
            String d = (String) tbltransaksilayanan.getValueAt(x, 3);
            String e = (String) tbltransaksilayanan.getValueAt(x, 4);
            String f = (String) tbltransaksilayanan.getValueAt(x, 5);
            String g = (String) tbltransaksilayanan.getValueAt(x, 6);
            String h = (String) tbltransaksilayanan.getValueAt(x, 7);
            String i = (String) tbltransaksilayanan.getValueAt(x, 8);
            String j = (String) tbltransaksilayanan.getValueAt(x, 9);
            String k = (String) tbltransaksilayanan.getValueAt(x, 10);
            
           // System.out.println(a + " - " + b + " - " + c );
          try {        
            String query = "UPDATE trx_layanan SET regid = ?,"
                    + "layanan_id = ?,defaultharga = ?,satuan = ?,totalharga = ?,"
                    + "petugasbuat = 1, tanggalbuat = now() "
                    + "WHERE trxlayanan_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, b);
            statement.setString(2, e);
            statement.setString(3, g);
            statement.setString(4, h);
            statement.setString(5, i);
            statement.setString(6, a);
            statement.executeUpdate();
            statement.close();           
          
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + ex);
        }
          n++;
      }
       TableModels.getDataVector().removeAllElements();
       table_model_trxlayanan();
       JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
       cbtransaksilayanan.setSelected(false);*/
      }
       
      private void updatetrxobat(){
            int row = tbltransaksiobt.getSelectedRow();
            int col = tbltransaksiobt.getSelectedColumn();
            String dataterpilih = tbltransaksiobt.getValueAt(row, col).toString();
            String transobat_id = tbltransaksiobt.getValueAt(row, 0).toString();
            String regid = tbltransaksiobt.getValueAt(row, 1).toString();
            String id_obat = tbltransaksiobt.getValueAt(row, 4).toString();
            String harga_obat = tbltransaksiobt.getValueAt(row, 6).toString();
            String satuan_obat = tbltransaksiobt.getValueAt(row, 7).toString();
            String total_obat = Integer.toString((Integer.parseInt(harga_obat))*(Integer.parseInt(satuan_obat)));
         //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
          try {        
            TableModels.getDataVector().removeAllElements();   
            String query = "UPDATE trx_obat SET regid = ?,"
                    + "obat_id = ?,satuan = ?,total = ?,"
                    + "petugasbuat = 1, tanggalbuat = now() "
                    + "WHERE trxobt_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, regid);
            statement.setString(2, id_obat);
            statement.setString(3, satuan_obat);
            statement.setString(4, total_obat);
            statement.setString(5, transobat_id);
            statement.executeUpdate();
            statement.close();          
     
          TableModels.getDataVector().removeAllElements();
          table_model_trxobat();
          
          JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + ex);
        }
         
      }
        
      private void deletetrxlayanan(){
            int row = tbltransaksilayanan.getSelectedRow();
            int col = tbltransaksilayanan.getSelectedColumn();
            String dataterpilih = tbltransaksilayanan.getValueAt(row, col).toString();
            String translayan_id = tbltransaksilayanan.getValueAt(row, 0).toString();
         //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
          try {        
            TableModels.getDataVector().removeAllElements();   
            String query = "DELETE FROM trx_layanan WHERE trxlayanan_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, translayan_id);
            statement.executeUpdate();
            statement.close();          
     
          TableModels.getDataVector().removeAllElements();
          table_model_trxlayanan();
          
          JOptionPane.showMessageDialog(rootPane, "Data Berhasil Dihapus...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus : " + ex);
        }
         
      }

        private void deletetrxobat(){
            int row = tbltransaksiobt.getSelectedRow();
            int col = tbltransaksiobt.getSelectedColumn();
            String dataterpilih = tbltransaksiobt.getValueAt(row, col).toString();
            String transobat_id = tbltransaksiobt.getValueAt(row, 0).toString();
          
         //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
          try {        
            TableModels.getDataVector().removeAllElements();   
            String query = "DELETE FROM trx_obat WHERE trxobt_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, transobat_id);
           
            statement.executeUpdate();
            statement.close();          
     
          TableModels.getDataVector().removeAllElements();
          table_model_trxobat();
          
          JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + ex);
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
        btncarimasterpasien = new javax.swing.JButton();
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
        btnhapuslayanan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        btntambahlayanan1 = new javax.swing.JButton();
        btnhapuslayanan1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
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

        btncarimasterpasien.setText("Cari Pasien");
        btncarimasterpasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarimasterpasienActionPerformed(evt);
            }
        });
        btncarimasterpasien.setBounds(330, 30, 120, 30);
        jLayeredPane2.add(btncarimasterpasien, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        tbltransaksilayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbltransaksilayananKeyPressed(evt);
            }
        });
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

        btnhapuslayanan.setText("Hapus");
        btnhapuslayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapuslayananActionPerformed(evt);
            }
        });
        btnhapuslayanan.setBounds(130, 30, 110, 23);
        jLayeredPane7.add(btnhapuslayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("Note : Enter to submit update data");
        jLabel4.setBounds(580, 20, 210, 40);
        jLayeredPane7.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        btnhapuslayanan1.setText("Hapus");
        btnhapuslayanan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapuslayanan1ActionPerformed(evt);
            }
        });
        btnhapuslayanan1.setBounds(140, 30, 110, 23);
        jLayeredPane9.add(btnhapuslayanan1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Note : Enter to submit update data");
        jLabel5.setBounds(580, 20, 210, 40);
        jLayeredPane9.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        tbltransaksiobt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbltransaksiobtKeyPressed(evt);
            }
        });
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncarimasterpasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarimasterpasienActionPerformed
        pencarian_pelayanan carpas = new pencarian_pelayanan(DP);
        DP.add(carpas);
        carpas.show();
        
    }//GEN-LAST:event_btncarimasterpasienActionPerformed

    private void btntambahlayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahlayananActionPerformed
       tambah_layanan layan = new tambah_layanan(regid);
        //DP.add(layan);
        //layan.show();
        layan.add(new JButton("click"));
        layan.add(new JTextField(20));
        layan.add(new JLabel("Label"));
        //JOptionPane.showMessageDialog(null, layan,"information",JOptionPane.INFORMATION_MESSAGE);
        int jop = JOptionPane.showOptionDialog(null, 
        layan,"",JOptionPane.YES_NO_OPTION, 
        JOptionPane.PLAIN_MESSAGE,null, 
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
        obat,"",JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE, 
        null,new String[]{"Selesai"}, // this is the array
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

    private void tbltransaksilayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbltransaksilayananKeyPressed
       if(evt.getKeyCode()== KeyEvent.VK_ENTER){
           updatetrxlayanan();
       }
    }//GEN-LAST:event_tbltransaksilayananKeyPressed

    private void tbltransaksiobtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbltransaksiobtKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
           updatetrxobat();
       }
    }//GEN-LAST:event_tbltransaksiobtKeyPressed

    private void btnhapuslayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapuslayananActionPerformed
       deletetrxlayanan();
    }//GEN-LAST:event_btnhapuslayananActionPerformed

    private void btnhapuslayanan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapuslayanan1ActionPerformed
        deletetrxobat();
    }//GEN-LAST:event_btnhapuslayanan1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncarimasterpasien;
    private javax.swing.JButton btndetailpasien;
    private javax.swing.JButton btnhapuslayanan;
    private javax.swing.JButton btnhapuslayanan1;
    private javax.swing.JButton btntambahlayanan;
    private javax.swing.JButton btntambahlayanan1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
