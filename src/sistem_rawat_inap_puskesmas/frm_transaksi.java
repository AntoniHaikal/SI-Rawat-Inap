/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem_rawat_inap_puskesmas;

import Class.TableViews;
import Class.koneksi;
import Sistem_monitoring_mutasi_ri.frmMutasiKamar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class frm_transaksi extends javax.swing.JInternalFrame{

    JDesktopPane DP;
    private ResultSet res;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    DefaultTableModel TableModels1;
    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    String regid;
    String dokter_id;
    String layanan_id;
    String obat_id;
    private String awal;
    private String akhir;
    String generate_trxlayananid;
    String generate_trxobatid;
    
    public frm_transaksi(JDesktopPane DP, String regid) {
        initComponents();
        this.regid = regid;
        txtregid.setText(regid);
        this.DP = DP;
        table_model_trxlayanan();
        table_model_trxobat();
        tampil_cari();
        SettingTableCariMasterLayanan();
        SettingTableCariMasterobt();
        generate();
    }
   

    
    private void table_model_trxlayanan() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
                    "Transaksi Layanan ID",
                    "Registrasi_id",
                    "Nama Pasien",
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
        TableViews.table(tbltransaksilayanan, new int[]{200, 100, 100, 150, 150, 150, 100, 50, 100, 100, 100});

        tampil_trxlayanan();

    }
    
    private void tampil_trxlayanan() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM transaksi_layanan";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
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
                    "Satuan",
                    "Total",
                    "Petugas",
                    "Tanggal Dibuat"
                },
                null, new int[]{}, null);
        tbltransaksiobt.setModel(TableModels);
        TableViews.table(tbltransaksiobt, new int[]{200, 100, 150, 150, 150, 100, 50, 100, 100, 100});

        tampil_trxobat();

    }
    
    private void tampil_trxobat() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM transaksi_obat";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
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
                            res.getString(10)
                          
                        });
                tbltransaksiobt.setModel(TableModels);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void SettingTableCariMasterLayanan() {
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_Layanan", 
                    "Nama_Layanan", 
                    "Harga" 
                    },
                null, new int[]{}, null);
        tblcarimasterlayanan.setModel(TableModels);
        TableViews.table(tblcarimasterlayanan, new int[]{300, 300, 370});
        
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
                tblcarimasterlayanan.setModel(TableModels); 
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
        tblcarimasterlayanan.setModel(TableModels);
        TableViews.table(tblcarimasterlayanan, new int[]{300, 300, 370});
        
               try{
                    String query = "SELECT * FROM master_layanan where layanan_id like ? "
                            + "AND namalayanan like ? "
                            + "AND defaultharga like ?";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtidlayanan.getText()+ "%");
                    statement.setString(2, "%" +txtnamalayanan.getText()+ "%");
                    statement.setString(3, "%" +txthargalayanan.getText()+ "%");
                    ResultSet res = statement.executeQuery();
                    int baris = 0;
                    while (res.next()) {
                        baris = res.getRow();
                        TableModels.addRow(new Object[]{
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3)

                                });
                        tblcarimasterlayanan.setModel(TableModels); 
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
    
    private void ambilcarimasterlayanan(){
            int row = tblcarimasterlayanan.getSelectedRow();
            int col = tblcarimasterlayanan.getSelectedColumn();
            String dataterpilih = tblcarimasterlayanan.getValueAt(row, col).toString();
            String idlayan = tblcarimasterlayanan.getValueAt(row, 0).toString();
            String namalayan = tblcarimasterlayanan.getValueAt(row, 1).toString();
            String hargalayanan = tblcarimasterlayanan.getValueAt(row, 2).toString();
           
           layanan_id = idlayan;
           txtlayanan.setText(namalayan);
           txtharga.setText(hargalayanan);
           
          
     }
    
    private void SettingTableCariMasterobt() {
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID Obat", 
                    "Nama Obat", 
                    "Harga Obat" 
                    },
                null, new int[]{}, null);
        tblcariobt.setModel(TableModels);
        TableViews.table(tblcariobt, new int[]{300, 300, 370});
        
        TampilCariMasterobat();
    }
    
    private void TampilCariMasterobat(){
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
                            res.getString("defaulharga")
                        });
                tblcariobt.setModel(TableModels); 
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void carimasterobat(){
        TableModels.getDataVector().removeAllElements();
           TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID Obat", 
                    "Nama Obat", 
                    "Harga Obat" 
                    },
                null, new int[]{}, null);
        tblcariobt.setModel(TableModels);
        TableViews.table(tblcariobt, new int[]{300, 300, 370});
        
               try{
                    String query = "SELECT * FROM master_obat where obat_id like ? "
                            + "AND namaobat like ? "
                            + "AND defaulharga like ?";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtidcariobt.getText()+ "%");
                    statement.setString(2, "%" +txtnamacariobt.getText()+ "%");
                    statement.setString(3, "%" +txthargacariobt.getText()+ "%");
                    ResultSet res = statement.executeQuery();
                    int baris = 0;
                    while (res.next()) {
                        baris = res.getRow();
                        TableModels.addRow(new Object[]{
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3)

                                });
                        tblcariobt.setModel(TableModels); 
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
    
      private void ambilcarimasterobat(){
            int row = tblcariobt.getSelectedRow();
            int col = tblcariobt.getSelectedColumn();
            String dataterpilih = tblcariobt.getValueAt(row, col).toString();
            String idobat = tblcariobt.getValueAt(row, 0).toString();
            String namaobat = tblcariobt.getValueAt(row, 1).toString();
            String hargaobat = tblcariobt.getValueAt(row, 2).toString();
           
           obat_id = idobat;
           txtobat.setText(namaobat);
           txthargaobt.setText(hargaobat);
           
          
     }
      
    private void inserttransaksilayanan(){
        try {
            generate();
            TableModels.getDataVector().removeAllElements();           
            String query = "insert into trx_layanan values(?,?,?,?,?,?,1,now())";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, generate_trxlayananid);
            statement.setString(2, txtregid.getText());
            statement.setString(3, layanan_id);
            statement.setString(4, txtharga.getText());
            statement.setString(5, txtsatuan.getText());
            statement.setString(6, txttotal.getText());
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            table_model_trxlayanan();
            statement.close();           
           JOptionPane.showMessageDialog(rootPane, "Data Berhasil Masuk...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dimasukan : " + e);
        }
        
     }
    
    private void inserttransaksiobat(){
        try {
            TableModels.getDataVector().removeAllElements();           
            String query = "insert into trx_obat values(?,?,?,?,?,1,now())";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, generate_trxobatid);
            statement.setString(2, txtregid.getText());
            statement.setString(3, obat_id);
            statement.setString(4, txtsatuanobt.getText());
            statement.setString(5, txttotalhargaobt.getText());
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            table_model_trxobat();
            statement.close();           
           JOptionPane.showMessageDialog(rootPane, "Data Berhasil Masuk...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dimasukan : " + e);
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

    
    private void generate(){
         DateFormat hour = new SimpleDateFormat("HHmmssddMMyyyy");
         Date date = new Date();
         String now = hour.format(date);
         generate_trxlayananid = ("trxlayan" + now); 
         generate_trxobatid = ("trxobat" + now); 
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txtsatuan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtlayanan = new javax.swing.JTextField();
        btncarilayanan = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jLayeredPane8 = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltransaksilayanan = new javax.swing.JTable();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        jLabel9 = new javax.swing.JLabel();
        txtidlayanan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtnamalayanan = new javax.swing.JTextField();
        txthargalayanan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnkembali = new javax.swing.JButton();
        btncari1 = new javax.swing.JButton();
        jLayeredPane10 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcarimasterlayanan = new javax.swing.JTable();
        jLayeredPane11 = new javax.swing.JLayeredPane();
        btnsimpanlayanan = new javax.swing.JButton();
        btneditlayanan = new javax.swing.JButton();
        btnhapuslayanan = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLayeredPane13 = new javax.swing.JLayeredPane();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txthargaobt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txttotalhargaobt = new javax.swing.JTextField();
        txtsatuanobt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtobat = new javax.swing.JTextField();
        btncariobt = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jLayeredPane14 = new javax.swing.JLayeredPane();
        jLayeredPane15 = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbltransaksiobt = new javax.swing.JTable();
        jLayeredPane16 = new javax.swing.JLayeredPane();
        jLayeredPane17 = new javax.swing.JLayeredPane();
        jLabel16 = new javax.swing.JLabel();
        txtidcariobt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtnamacariobt = new javax.swing.JTextField();
        txthargacariobt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnkembaliobt = new javax.swing.JButton();
        btncarimasterobt = new javax.swing.JButton();
        jLayeredPane18 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblcariobt = new javax.swing.JTable();
        jLayeredPane19 = new javax.swing.JLayeredPane();
        btnsimpanobt = new javax.swing.JButton();
        btnhapusobt = new javax.swing.JButton();
        btntutupobt = new javax.swing.JButton();
        jLayeredPane12 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        txtregid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtdokter = new javax.swing.JTextField();
        btncarimasterlayanan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtnomedrec = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtnamapasien = new javax.swing.JTextField();
        btndetailpasien = new javax.swing.JButton();

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Masukan Data Transaksi"));

        jLabel3.setText("Layanan");
        jLabel3.setBounds(20, 20, 110, 30);
        jLayeredPane5.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("Harga");
        jLabel4.setBounds(20, 70, 100, 30);
        jLayeredPane5.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtharga.setBounds(150, 70, 170, 30);
        jLayeredPane5.add(txtharga, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Satuan");
        jLabel5.setBounds(510, 20, 60, 30);
        jLayeredPane5.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txttotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttotalMouseClicked(evt);
            }
        });
        txttotal.setBounds(620, 70, 210, 30);
        jLayeredPane5.add(txttotal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtsatuan.setBounds(620, 20, 110, 30);
        jLayeredPane5.add(txtsatuan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText("Total Harga");
        jLabel7.setBounds(510, 70, 70, 30);
        jLayeredPane5.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtlayanan.setBounds(150, 20, 170, 30);
        jLayeredPane5.add(txtlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncarilayanan.setText("Cari Layanan");
        btncarilayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarilayananActionPerformed(evt);
            }
        });
        btncarilayanan.setBounds(360, 20, 120, 30);
        jLayeredPane5.add(btncarilayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane5.setBounds(10, 10, 880, 150);
        jLayeredPane2.add(jLayeredPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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
        jScrollPane3.setViewportView(tbltransaksilayanan);

        jScrollPane3.setBounds(10, 10, 960, 230);
        jLayeredPane8.add(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane8.setBounds(10, 10, 990, 250);
        jLayeredPane6.add(jLayeredPane8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane2.addTab("Tabel Transaksi", jLayeredPane6);

        jLayeredPane9.setBorder(javax.swing.BorderFactory.createTitledBorder("Masukan Kriteria Pencarian"));

        jLabel9.setText("Id Layanan");
        jLabel9.setBounds(80, 20, 90, 20);
        jLayeredPane9.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtidlayanan.setBounds(40, 50, 170, 30);
        jLayeredPane9.add(txtidlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel10.setText("Nama Layanan");
        jLabel10.setBounds(330, 20, 90, 20);
        jLayeredPane9.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnamalayanan.setBounds(280, 50, 200, 30);
        jLayeredPane9.add(txtnamalayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txthargalayanan.setBounds(560, 50, 200, 30);
        jLayeredPane9.add(txthargalayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel11.setText("Harga Layanan");
        jLabel11.setBounds(610, 20, 90, 20);
        jLayeredPane9.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnkembali.setText("Kembali");
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });
        btnkembali.setBounds(820, 60, 120, 30);
        jLayeredPane9.add(btnkembali, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncari1.setText("Cari");
        btncari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncari1ActionPerformed(evt);
            }
        });
        btncari1.setBounds(820, 20, 120, 30);
        jLayeredPane9.add(btncari1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane9.setBounds(10, 10, 980, 100);
        jLayeredPane7.add(jLayeredPane9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane10.setBorder(javax.swing.BorderFactory.createTitledBorder("Table Layanan"));

        tblcarimasterlayanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcarimasterlayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcarimasterlayananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcarimasterlayanan);

        jScrollPane1.setBounds(22, 20, 930, 110);
        jLayeredPane10.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane10.setBounds(10, 120, 980, 140);
        jLayeredPane7.add(jLayeredPane10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane2.addTab("Master Layanan", jLayeredPane7);

        jTabbedPane2.setBounds(10, 170, 1020, 290);
        jLayeredPane2.add(jTabbedPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane11.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        btnsimpanlayanan.setText("Simpan");
        btnsimpanlayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanlayananActionPerformed(evt);
            }
        });
        btnsimpanlayanan.setBounds(20, 20, 90, 30);
        jLayeredPane11.add(btnsimpanlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btneditlayanan.setText("Edit");
        btneditlayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditlayananActionPerformed(evt);
            }
        });
        btneditlayanan.setBounds(20, 60, 90, 30);
        jLayeredPane11.add(btneditlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnhapuslayanan.setText("Hapus");
        btnhapuslayanan.setBounds(20, 100, 90, 30);
        jLayeredPane11.add(btnhapuslayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane11.setBounds(890, 10, 130, 150);
        jLayeredPane2.add(jLayeredPane11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("Transaksi Layanan", jLayeredPane2);
        jTabbedPane1.addTab("Transaksi Kamar", jLayeredPane3);

        jLayeredPane4.setBounds(0, 0, 0, 0);
        jLayeredPane1.add(jLayeredPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane13.setBorder(javax.swing.BorderFactory.createTitledBorder("Masukan Data Transaksi"));

        jLabel12.setText("Obat");
        jLabel12.setBounds(20, 20, 110, 30);
        jLayeredPane13.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel13.setText("Harga Obat");
        jLabel13.setBounds(20, 70, 100, 30);
        jLayeredPane13.add(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txthargaobt.setBounds(160, 70, 160, 30);
        jLayeredPane13.add(txthargaobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel14.setText("Satuan");
        jLabel14.setBounds(510, 20, 60, 30);
        jLayeredPane13.add(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txttotalhargaobt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttotalhargaobtMouseClicked(evt);
            }
        });
        txttotalhargaobt.setBounds(620, 70, 210, 30);
        jLayeredPane13.add(txttotalhargaobt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtsatuanobt.setBounds(620, 20, 110, 30);
        jLayeredPane13.add(txtsatuanobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel15.setText("Total Harga");
        jLabel15.setBounds(510, 70, 70, 30);
        jLayeredPane13.add(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtobat.setBounds(160, 20, 160, 30);
        jLayeredPane13.add(txtobat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncariobt.setText("Cari Obat");
        btncariobt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariobtActionPerformed(evt);
            }
        });
        btncariobt.setBounds(360, 20, 120, 30);
        jLayeredPane13.add(btncariobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane13.setBounds(10, 10, 850, 150);
        jLayeredPane1.add(jLayeredPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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
        jScrollPane4.setViewportView(tbltransaksiobt);

        jScrollPane4.setBounds(10, 10, 960, 230);
        jLayeredPane15.add(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane15.setBounds(10, 10, 990, 250);
        jLayeredPane14.add(jLayeredPane15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane3.addTab("Tabel Transaksi", jLayeredPane14);

        jLayeredPane17.setBorder(javax.swing.BorderFactory.createTitledBorder("Masukan Kriteria Pencarian"));

        jLabel16.setText("Obat ID");
        jLabel16.setBounds(100, 20, 90, 20);
        jLayeredPane17.add(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtidcariobt.setBounds(40, 50, 170, 30);
        jLayeredPane17.add(txtidcariobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel17.setText("Nama Obat");
        jLabel17.setBounds(330, 20, 90, 20);
        jLayeredPane17.add(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnamacariobt.setBounds(280, 50, 200, 30);
        jLayeredPane17.add(txtnamacariobt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txthargacariobt.setBounds(560, 50, 200, 30);
        jLayeredPane17.add(txthargacariobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel18.setText("Harga Obat");
        jLabel18.setBounds(610, 20, 90, 20);
        jLayeredPane17.add(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnkembaliobt.setText("Kembali");
        btnkembaliobt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliobtActionPerformed(evt);
            }
        });
        btnkembaliobt.setBounds(820, 60, 120, 30);
        jLayeredPane17.add(btnkembaliobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncarimasterobt.setText("Cari");
        btncarimasterobt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarimasterobtActionPerformed(evt);
            }
        });
        btncarimasterobt.setBounds(820, 20, 120, 30);
        jLayeredPane17.add(btncarimasterobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane17.setBounds(10, 10, 980, 100);
        jLayeredPane16.add(jLayeredPane17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane18.setBorder(javax.swing.BorderFactory.createTitledBorder("Table Obat"));

        tblcariobt.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcariobt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcariobtMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblcariobt);

        jScrollPane2.setBounds(22, 20, 930, 110);
        jLayeredPane18.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane18.setBounds(10, 120, 980, 140);
        jLayeredPane16.add(jLayeredPane18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane3.addTab("Master Obat", jLayeredPane16);

        jTabbedPane3.setBounds(10, 170, 1020, 290);
        jLayeredPane1.add(jTabbedPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane19.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        btnsimpanobt.setText("Simpan");
        btnsimpanobt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanobtActionPerformed(evt);
            }
        });
        btnsimpanobt.setBounds(20, 30, 90, 30);
        jLayeredPane19.add(btnsimpanobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnhapusobt.setText("Hapus");
        btnhapusobt.setBounds(20, 70, 90, 30);
        jLayeredPane19.add(btnhapusobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btntutupobt.setText("Tutup");
        btntutupobt.setBounds(20, 110, 90, 30);
        jLayeredPane19.add(btntutupobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane19.setBounds(880, 10, 130, 150);
        jLayeredPane1.add(jLayeredPane19, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("Transakasi Obat", jLayeredPane1);

        jLayeredPane12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Pasien", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(6, 6, 6)));

        jLabel1.setText("Registrasi ID");
        jLabel1.setBounds(20, 30, 100, 30);
        jLayeredPane12.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtregid.setBounds(130, 30, 240, 30);
        jLayeredPane12.add(txtregid, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText("Petugas Medis");
        jLabel2.setBounds(20, 70, 90, 30);
        jLayeredPane12.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtdokter.setBounds(130, 70, 240, 30);
        jLayeredPane12.add(txtdokter, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncarimasterlayanan.setText("Cari Pasien");
        btncarimasterlayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarimasterlayananActionPerformed(evt);
            }
        });
        btncarimasterlayanan.setBounds(420, 30, 120, 30);
        jLayeredPane12.add(btncarimasterlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setText("No Medrec");
        jLabel8.setBounds(570, 30, 70, 30);
        jLayeredPane12.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnomedrec.setBounds(650, 30, 190, 30);
        jLayeredPane12.add(txtnomedrec, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Nama");
        jLabel6.setBounds(570, 80, 70, 30);
        jLayeredPane12.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnamapasien.setBounds(650, 80, 190, 30);
        jLayeredPane12.add(txtnamapasien, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btndetailpasien.setText("Detail Pasien");
        btndetailpasien.setBounds(880, 30, 110, 30);
        jLayeredPane12.add(btndetailpasien, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane12)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncarimasterlayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarimasterlayananActionPerformed
            pencarian_pelayanan carpas = new pencarian_pelayanan(DP);
            DP.add(carpas);
            carpas.show();
    }//GEN-LAST:event_btncarimasterlayananActionPerformed

    private void txttotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttotalMouseClicked
        int total = (Integer.parseInt(txtharga.getText()) * (Integer.parseInt(txtsatuan.getText())));
        txttotal.setText(Integer.toString(total));
    }//GEN-LAST:event_txttotalMouseClicked

    private void btncarilayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarilayananActionPerformed
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_btncarilayananActionPerformed

    private void btncari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncari1ActionPerformed
        carimasterlayanan();
    }//GEN-LAST:event_btncari1ActionPerformed

    private void tblcarimasterlayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcarimasterlayananMouseClicked
        ambilcarimasterlayanan();
    }//GEN-LAST:event_tblcarimasterlayananMouseClicked

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
       jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_btnkembaliActionPerformed

    private void btnsimpanlayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanlayananActionPerformed
        inserttransaksilayanan();
    }//GEN-LAST:event_btnsimpanlayananActionPerformed

    private void txttotalhargaobtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttotalhargaobtMouseClicked
        int total = (Integer.parseInt(txthargaobt.getText()) * (Integer.parseInt(txtsatuanobt.getText())));
        txttotalhargaobt.setText(Integer.toString(total));
    }//GEN-LAST:event_txttotalhargaobtMouseClicked

    private void btncariobtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariobtActionPerformed
        jTabbedPane3.setSelectedIndex(1);
    }//GEN-LAST:event_btncariobtActionPerformed

    private void btnkembaliobtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliobtActionPerformed
       jTabbedPane3.setSelectedIndex(0);
    }//GEN-LAST:event_btnkembaliobtActionPerformed

    private void btncarimasterobtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarimasterobtActionPerformed
        carimasterobat();
    }//GEN-LAST:event_btncarimasterobtActionPerformed

    private void tblcariobtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcariobtMouseClicked
        ambilcarimasterobat();
    }//GEN-LAST:event_tblcariobtMouseClicked

    private void btnsimpanobtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanobtActionPerformed
       inserttransaksiobat();
       SettingTableCariMasterobt();
    }//GEN-LAST:event_btnsimpanobtActionPerformed

    private void btneditlayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditlayananActionPerformed
        updatetrxlayanan();
    }//GEN-LAST:event_btneditlayananActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        /*Sistem_monitoring_mutasi_ri.frmMutasiKamar mutasi = new frmMutasiKamar(DP);
        DP.removeAll();
        DP.repaint();
        DP.add(mutasi);
        mutasi.show();*/
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncari1;
    private javax.swing.JButton btncarilayanan;
    private javax.swing.JButton btncarimasterlayanan;
    private javax.swing.JButton btncarimasterobt;
    private javax.swing.JButton btncariobt;
    private javax.swing.JButton btndetailpasien;
    private javax.swing.JButton btneditlayanan;
    private javax.swing.JButton btnhapuslayanan;
    private javax.swing.JButton btnhapusobt;
    private javax.swing.JButton btnkembali;
    private javax.swing.JButton btnkembaliobt;
    private javax.swing.JButton btnsimpanlayanan;
    private javax.swing.JButton btnsimpanobt;
    private javax.swing.JButton btntutupobt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane10;
    private javax.swing.JLayeredPane jLayeredPane11;
    private javax.swing.JLayeredPane jLayeredPane12;
    private javax.swing.JLayeredPane jLayeredPane13;
    private javax.swing.JLayeredPane jLayeredPane14;
    private javax.swing.JLayeredPane jLayeredPane15;
    private javax.swing.JLayeredPane jLayeredPane16;
    private javax.swing.JLayeredPane jLayeredPane17;
    private javax.swing.JLayeredPane jLayeredPane18;
    private javax.swing.JLayeredPane jLayeredPane19;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JLayeredPane jLayeredPane8;
    private javax.swing.JLayeredPane jLayeredPane9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable tblcarimasterlayanan;
    private javax.swing.JTable tblcariobt;
    private javax.swing.JTable tbltransaksilayanan;
    private javax.swing.JTable tbltransaksiobt;
    private javax.swing.JTextField txtdokter;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txthargacariobt;
    private javax.swing.JTextField txthargalayanan;
    private javax.swing.JTextField txthargaobt;
    private javax.swing.JTextField txtidcariobt;
    private javax.swing.JTextField txtidlayanan;
    private static javax.swing.JTextField txtlayanan;
    private javax.swing.JTextField txtnamacariobt;
    private javax.swing.JTextField txtnamalayanan;
    private javax.swing.JTextField txtnamapasien;
    private javax.swing.JTextField txtnomedrec;
    private static javax.swing.JTextField txtobat;
    private static javax.swing.JTextField txtregid;
    private javax.swing.JTextField txtsatuan;
    private javax.swing.JTextField txtsatuanobt;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttotalhargaobt;
    // End of variables declaration//GEN-END:variables

    
    
}
