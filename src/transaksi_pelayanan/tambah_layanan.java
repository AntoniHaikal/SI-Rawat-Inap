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
    DefaultTableModel TableModels1;
    DefaultTableModel TableModels2;
    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    String layanan_id;
    String petugas_id;
    String id_ICD;
    String regid;
    private String awal;
    private String akhir;
    String generate_trxlayananid;
   
    
    public tambah_layanan(String regid) {
        initComponents();
        this.regid = regid;
    
        SettingTableCariMasterLayanan();
        SettingTableCariMasterPetugasMedis();
        SettingTableCariMasterICD();
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
        TableViews.table(tblcarimasterlayan, new int[]{150, 150, 150});
        
        TampilCariMasterLayanan();
    }
    
     private void SettingTableCariMasterPetugasMedis() {
        TableModels1 = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_Petugas Medis", 
                    "Nama Petugas Medis", 
                    "Jenis Kelamin",
                    "No Telp",
                    "Alamat",
                    "Kategori"
                    
                    },
                null, new int[]{}, null);
        tblcaripetugasmedis.setModel(TableModels1);
        TableViews.table(tblcaripetugasmedis, new int[]{150, 100, 150, 100, 100, 110});
        
        TampilCariMasterPetugasMedis();
    }
     
     private void SettingTableCariMasterICD() {
        TableModels2 = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_ICD", 
                    "Kode ICD", 
                    "Nama Diagnosa",
                    
                    },
                null, new int[]{}, null);
        tblcariicd.setModel(TableModels2);
        TableViews.table(tblcariicd, new int[]{150, 150, 150});
        
        TampilCariMasterICD();
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
     
    
     private void TampilCariMasterPetugasMedis(){
        try {
            TableModels1.getDataVector().removeAllElements();
            String query = "SELECT * FROM master_petugasmedis";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();            
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels1.addRow(new Object[]{
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5),
                            res.getString(6)
                        });
                tblcaripetugasmedis.setModel(TableModels1); 
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    
     private void TampilCariMasterICD(){
        try {
            TableModels2.getDataVector().removeAllElements();
            String query = "SELECT * FROM icd10";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();            
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels2.addRow(new Object[]{
                            res.getString(1),
                            res.getString(2),
                            res.getString(3)
                            
                        });
                tblcariicd.setModel(TableModels2); 
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
      
      private void caripetugasmedis(){
        TableModels1.getDataVector().removeAllElements();
          TableModels1 = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_Petugas Medis", 
                    "Nama Petugas Medis", 
                    "Jenis Kelamin",
                    "No Telp",
                    "Alamat",
                    "Kategori"
                    
                    },
                null, new int[]{}, null);
        tblcaripetugasmedis.setModel(TableModels1);
        TableViews.table(tblcaripetugasmedis, new int[]{150, 100, 150, 100, 100, 110});
        
               try{
                    String query = "SELECT * FROM master_petugasmedis where petugasmedis_id like ? "
                            + "AND nama_petugasmedis like ? "
                            + "AND kategori like ?";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtidcaripetugasmedis.getText()+ "%");
                    statement.setString(2, "%" +txtnamacaripetugasmedis.getText()+ "%");
                    statement.setString(3, "%" +txtstatuspetugasmedis.getText()+ "%");
                    ResultSet res = statement.executeQuery();
                    int baris = 0;
                    while (res.next()) {
                        baris = res.getRow();
                        TableModels1.addRow(new Object[]{
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3),
                                    res.getString(4),
                                    res.getString(5),
                                    res.getString(6)

                                });
                        tblcaripetugasmedis.setModel(TableModels1); 
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
      
      private void carimasterICD(){
        TableModels2.getDataVector().removeAllElements();
           TableModels2 = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_ICD", 
                    "Kode ICD", 
                    "Nama Diagnosa" 
                    },
                null, new int[]{}, null);
        tblcariicd.setModel(TableModels2);
        TableViews.table(tblcariicd, new int[]{150, 150, 150});
        
               try{
                    String query = "SELECT * FROM icd10 where ID_ICD like ? "
                            + "AND NM_DIAGNOSA like ? ";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtidcariicd.getText()+ "%");
                    statement.setString(2, "%" +txtnamadiagnosa.getText()+ "%");
                    ResultSet res = statement.executeQuery();
                    int baris = 0;
                    while (res.next()) {
                        baris = res.getRow();
                        TableModels2.addRow(new Object[]{
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3)


                                });
                        tblcariicd.setModel(TableModels2); 
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
      
      private void ambilcaripetugasmedis(){
            int row = tblcaripetugasmedis.getSelectedRow();
            int col = tblcaripetugasmedis.getSelectedColumn();
            String dataterpilih = tblcaripetugasmedis.getValueAt(row, col).toString();
            String idpetugas = tblcaripetugasmedis.getValueAt(row, 0).toString();
            String namapetugas = tblcaripetugasmedis.getValueAt(row, 1).toString();
           
           petugas_id = idpetugas;
           txtpetugasmedis.setText(namapetugas);          
          
     }
      
     private void ambilcariICD(){
            int row = tblcariicd.getSelectedRow();
            int col = tblcariicd.getSelectedColumn();
            String dataterpilih = tblcariicd.getValueAt(row, col).toString();
            String idICD = tblcariicd.getValueAt(row, 1).toString();
            String namaICD = tblcariicd.getValueAt(row, 2).toString();
           
           id_ICD = idICD;
           txtdiagnosa.setText(namaICD);          
          
     }
      
      private void inserttransaksilayanan(){
        try {
            generate();             
            String query = "insert into trx_layanan values(?,?,?,?,?,?,?,1,now())";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, generate_trxlayananid);
            statement.setString(2, regid);
            statement.setString(3, petugas_id);
            statement.setString(4, layanan_id);
            statement.setString(5, txtharga.getText());
            statement.setString(6, txtsatuan.getText());
            statement.setString(7, txttotal.getText());
            statement.executeUpdate();
            statement.close();           
           JOptionPane.showMessageDialog(null, "Data Berhasil Masuk...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dimasukan : " + e);
        }
        
     }
      
      private void updatediagnosa(){
            try {        
            String query = "UPDATE diagnosapasien SET diagnosautama = ?,"
                    + "petugasbuat = 1, tanggalbuat = now() "
                    + "WHERE regid = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, id_ICD);
            statement.setString(2, regid);
            statement.executeUpdate();
            statement.close();          
          
          //JOptionPane.showMessageDialog(null, "Data Diagnosa Berhasil diubah...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Diagnosa Gagal diubah : " + ex);
        }
         
      }
       private void generate(){
         DateFormat hour = new SimpleDateFormat("HHmmssddMMyyyy");
         Date date = new Date();
         String now = hour.format(date);
         generate_trxlayananid = ("trxlayan" + now); 
       }
       
       private void hitung(){
        int total = (Integer.parseInt(txtharga.getText()) * (Integer.parseInt(txtsatuan.getText())));
        txttotal.setText(Integer.toString(total));
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
        txtdiagnosa = new javax.swing.JTextField();
        btnsimpanlayan = new javax.swing.JButton();
        btnbersihlayan = new javax.swing.JButton();
        txtlayanan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txtpetugasmedis = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblcaripetugasmedis = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtidcaripetugasmedis = new javax.swing.JTextField();
        txtnamacaripetugasmedis = new javax.swing.JTextField();
        txtstatuspetugasmedis = new javax.swing.JTextField();
        btncaripetugasmedis = new javax.swing.JButton();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblcariicd = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtidcariicd = new javax.swing.JTextField();
        txtnamadiagnosa = new javax.swing.JTextField();
        btncariICD = new javax.swing.JButton();

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

        jLabel7.setText("Diagnosa");
        jLabel7.setBounds(20, 270, 100, 30);
        jLayeredPane2.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtsatuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsatuanKeyPressed(evt);
            }
        });
        txtsatuan.setBounds(140, 120, 110, 30);
        jLayeredPane2.add(txtsatuan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtdiagnosa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdiagnosaMouseClicked(evt);
            }
        });
        txtdiagnosa.setBounds(140, 270, 170, 30);
        jLayeredPane2.add(txtdiagnosa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnsimpanlayan.setText("Simpan");
        btnsimpanlayan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanlayanActionPerformed(evt);
            }
        });
        btnsimpanlayan.setBounds(20, 320, 90, 23);
        jLayeredPane2.add(btnsimpanlayan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnbersihlayan.setText("Bersihkan");
        btnbersihlayan.setBounds(250, 330, 100, 23);
        jLayeredPane2.add(btnbersihlayan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtlayanan.setBounds(140, 20, 170, 30);
        jLayeredPane2.add(txtlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel9.setText("Total Harga");
        jLabel9.setBounds(20, 170, 70, 30);
        jLayeredPane2.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txttotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttotalMouseClicked(evt);
            }
        });
        txttotal.setBounds(140, 170, 170, 30);
        jLayeredPane2.add(txttotal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtpetugasmedis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpetugasmedisMouseClicked(evt);
            }
        });
        txtpetugasmedis.setBounds(140, 220, 170, 30);
        jLayeredPane2.add(txtpetugasmedis, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel16.setText("Petugas Medis");
        jLabel16.setBounds(20, 220, 100, 30);
        jLayeredPane2.add(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        jScrollPane1.setBounds(10, 120, 420, 250);
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
        btncari.setBounds(320, 70, 90, 30);
        jLayeredPane3.add(btncari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("Master Layanan", jLayeredPane3);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Petugas Medis"));

        tblcaripetugasmedis.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcaripetugasmedis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcaripetugasmedisMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblcaripetugasmedis);

        jScrollPane2.setBounds(10, 120, 420, 250);
        jLayeredPane4.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel10.setText("ID Petugas Medis");
        jLabel10.setBounds(20, 30, 130, 30);
        jLayeredPane4.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel11.setText("Nama Petugas Medis");
        jLabel11.setBounds(20, 70, 140, 30);
        jLayeredPane4.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel12.setText("Status");
        jLabel12.setBounds(270, 30, 60, 30);
        jLayeredPane4.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtidcaripetugasmedis.setBounds(170, 30, 90, 30);
        jLayeredPane4.add(txtidcaripetugasmedis, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnamacaripetugasmedis.setBounds(170, 70, 90, 30);
        jLayeredPane4.add(txtnamacaripetugasmedis, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtstatuspetugasmedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstatuspetugasmedisActionPerformed(evt);
            }
        });
        txtstatuspetugasmedis.setBounds(340, 30, 90, 30);
        jLayeredPane4.add(txtstatuspetugasmedis, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncaripetugasmedis.setText("Cari");
        btncaripetugasmedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripetugasmedisActionPerformed(evt);
            }
        });
        btncaripetugasmedis.setBounds(340, 70, 90, 30);
        jLayeredPane4.add(btncaripetugasmedis, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("Petugas Medis", jLayeredPane4);

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel ICD"));

        tblcariicd.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcariicd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcariicdMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblcariicd);

        jScrollPane3.setBounds(10, 120, 420, 250);
        jLayeredPane5.add(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel13.setText("ID ICD");
        jLabel13.setBounds(20, 30, 90, 30);
        jLayeredPane5.add(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel15.setText("Nama Diagnosa");
        jLabel15.setBounds(220, 30, 110, 30);
        jLayeredPane5.add(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtidcariicd.setBounds(120, 30, 90, 30);
        jLayeredPane5.add(txtidcariicd, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtnamadiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamadiagnosaActionPerformed(evt);
            }
        });
        txtnamadiagnosa.setBounds(340, 30, 90, 30);
        jLayeredPane5.add(txtnamadiagnosa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncariICD.setText("Cari");
        btncariICD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariICDActionPerformed(evt);
            }
        });
        btncariICD.setBounds(340, 70, 90, 30);
        jLayeredPane5.add(btncariICD, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("ICD 10", jLayeredPane5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addComponent(jLayeredPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtdiagnosaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdiagnosaMouseClicked
       hitung();
    }//GEN-LAST:event_txtdiagnosaMouseClicked

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
       updatediagnosa();
    }//GEN-LAST:event_btnsimpanlayanActionPerformed

    private void txttotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttotalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalMouseClicked

    private void tblcaripetugasmedisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcaripetugasmedisMouseClicked
       ambilcaripetugasmedis();
    }//GEN-LAST:event_tblcaripetugasmedisMouseClicked

    private void txtstatuspetugasmedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstatuspetugasmedisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstatuspetugasmedisActionPerformed

    private void btncaripetugasmedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripetugasmedisActionPerformed
        caripetugasmedis();
    }//GEN-LAST:event_btncaripetugasmedisActionPerformed

    private void txtsatuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsatuanKeyPressed
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
             hitung();
         };
    }//GEN-LAST:event_txtsatuanKeyPressed

    private void tblcariicdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcariicdMouseClicked
        ambilcariICD();
    }//GEN-LAST:event_tblcariicdMouseClicked

    private void txtnamadiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamadiagnosaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamadiagnosaActionPerformed

    private void btncariICDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariICDActionPerformed
        carimasterICD();
    }//GEN-LAST:event_btncariICDActionPerformed

    private void txtpetugasmedisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpetugasmedisMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpetugasmedisMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbersihlayan;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btncariICD;
    private javax.swing.JButton btncaripetugasmedis;
    private javax.swing.JButton btnsimpanlayan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblcariicd;
    private javax.swing.JTable tblcarimasterlayan;
    private javax.swing.JTable tblcaripetugasmedis;
    private javax.swing.JTextField txtdiagnosa;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txthargacari;
    private javax.swing.JTextField txtidcari;
    private javax.swing.JTextField txtidcariicd;
    private javax.swing.JTextField txtidcaripetugasmedis;
    private static javax.swing.JTextField txtlayanan;
    private javax.swing.JTextField txtnamacari;
    private javax.swing.JTextField txtnamacaripetugasmedis;
    private javax.swing.JTextField txtnamadiagnosa;
    private javax.swing.JTextField txtpetugasmedis;
    private javax.swing.JTextField txtsatuan;
    private javax.swing.JTextField txtstatuspetugasmedis;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
