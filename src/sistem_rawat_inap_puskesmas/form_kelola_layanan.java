/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem_rawat_inap_puskesmas;

import Class.TableViews;
import Class.koneksi;
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
public class form_kelola_layanan extends javax.swing.JInternalFrame {

    private String dataku = null;
    private ResultSet res;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    DefaultComboBoxModel cmb = new DefaultComboBoxModel();
    JDesktopPane DP;
    private int status;
    private String dokter;
    private String dokterupdate;
    private int statusupdate;
    
    public form_kelola_layanan(JDesktopPane DP) {
        initComponents();
        this.DP = DP;
        clear();
        btnselesai.setVisible(false);
        btnlayanan.setVisible(false);
        btnpasien.setVisible(false);
         

      
      
    }
    
    
    private void table_model_trxlayanan() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
                    "Trx Layanan ID",
                    "Registrasi ID",
                    "Medrec ID",
                    "Nama Pasien",
                    "Nama Dokter",
                    "Nama Layanan",
                    "Harga",
                    "Satuan",
                    "Total",
                    "Petugas",
                    "Tanggal Dibuat"
                },
                null, new int[]{}, null);
        tbltransaksilayanan.setModel(TableModels);
        TableViews.table(tbltransaksilayanan, new int[]{100, 100, 100, 150, 150, 150, 100, 50, 100, 150, 100});

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
    
    private void total(){
        int a = Integer.parseInt(txtharga.getText());
        int b = Integer.parseInt(txtsatuan.getText());
        int c = a*b;
        String total = Integer.toString(c);
        txttotal.setText(total);
    }

    
    
    private void table_model_layanan() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
                    "Layanan ID",
                    "Nama Layanan",
                    "Harga"
                },
                null, new int[]{}, null);
        tbltransaksilayanan.setModel(TableModels);
        TableViews.table(tbltransaksilayanan, new int[]{280, 280, 280});

        tampil_layanan();

    }
    
    private void tampil_layanan() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM master_layanan";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels.addRow(new Object[]{
                            res.getString(1),
                            res.getString(2),
                            res.getString(3)
                            
                        });
                tbltransaksilayanan.setModel(TableModels);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void table_model_pasien() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
                    "Registrasi ID",
                    "No Medrec",
                    "Nama Pasien",
                    "Tanggal Masuk",
                    "Nama Dokter",
                    "Tanggal Pulang",
                    "Nama Petugas",
                    "Tanggal Dibuat"
                },
                null, new int[]{}, null);
        tbltransaksilayanan.setModel(TableModels);
        TableViews.table(tbltransaksilayanan, new int[]{100, 100, 150, 100, 150, 100, 100, 100});

        tampil_pasien();

    }
    
    private void tampil_pasien() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = " SELECT a.regid, a.medrec_id, b.nama, a.tanggalmasuk, "
                    + " c.namadokter, a.tanggalpulang, d.namauser, a.tanggalbuat "
                    + " FROM regpasien a, master_medrec b, master_dokter c, master_user d"
                    + " where a.medrec_id = b.medrec_id AND a.dokter_id = c.dokter_id"
                    + " AND a.petugasbuat = d.user_id";
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
                            res.getString(8)
                           
                            
                        });
                tbltransaksilayanan.setModel(TableModels);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void panggil_pasien(){
        try {
            String query = "SELECT dokter_id FROM regpasien where regid = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, txtregid.getText());
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                dokter = res.getString(1);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     private void ambil(){
            int row = tbltransaksilayanan.getSelectedRow();
            int col = tbltransaksilayanan.getSelectedColumn();
            String dataterpilih = tbltransaksilayanan.getValueAt(row, col).toString();
            if(status == 1){
            txtlayanan_id.setText(tbltransaksilayanan.getValueAt(row, 0).toString());
            txtlayanan.setText(tbltransaksilayanan.getValueAt(row, 1).toString());
            txtharga.setText(tbltransaksilayanan.getValueAt(row, 2).toString());
            }
            if(status == 2){
            txtregid.setText(tbltransaksilayanan.getValueAt(row, 0).toString());
            txtnomedrec.setText(tbltransaksilayanan.getValueAt(row, 1).toString());
            txtnama.setText(tbltransaksilayanan.getValueAt(row, 2).toString());
            txtdokter.setText(tbltransaksilayanan.getValueAt(row, 4).toString());
            }if(status == 0){
                if(statusupdate == 2){
                    
                }else{
            txttransaksi_id.setText(tbltransaksilayanan.getValueAt(row, 0).toString());
            txtharga.setText("");
            txtlayanan.setText("");
            txtlayanan_id.setText("");
            txtnama.setText("");
            txtnomedrec.setText("");
            txtregid.setText("");
            txtsatuan.setText("");
            txttotal.setText("");
            }
            }
            
            
        }
     private void insert(){
        try {
            TableModels.getDataVector().removeAllElements();           
            String query = "insert into trx_layanan values(?,?,?,?,?,?,?,?,422,now())";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, txttransaksi_id.getText());
            statement.setString(2, txtregid.getText());
            statement.setString(3, txtnomedrec.getText());
            statement.setString(4, dokter);
            statement.setString(5, txtlayanan_id.getText());
            statement.setString(6, txtharga.getText());
            statement.setString(7, txtsatuan.getText());
            statement.setString(8, txttotal.getText());
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            clear();
            table_model_trxlayanan();
            statement.close();           
           JOptionPane.showMessageDialog(rootPane, "Data Berhasil Masuk...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dimasukan : " + e);
        }
        
     }
     
      private void update(){
          try {
            TableModels.getDataVector().removeAllElements();           
            String query = "UPDATE trx_layanan SET regid = ?,"
                    + "medrec_id = ?,dokter_id = ?,layanan_id = ?,defaultharga = ?,"
                    + "satuan = ?,totalharga = ?,petugasbuat = 422,tanggalbuat = now() "
                    + "WHERE trxlayanan_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, txtregid.getText());
            statement.setString(2, txtnomedrec.getText());
            statement.setString(3, dokterupdate);
            statement.setString(4, txtlayanan_id.getText());
            statement.setString(5, txtharga.getText());
            statement.setString(6, txtsatuan.getText());
            statement.setString(7, txttotal.getText());
            statement.setString(8, txttransaksi_id.getText());
   
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            clear();
            table_model_trxlayanan();
            statement.close();           
           JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + e);
        }
      }
      
       private void delete(){
        try {
            TableModels.getDataVector().removeAllElements();           
            String query = "delete from trx_layanan where trxlayanan_id =?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, txttransaksi_id.getText());
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            clear();
            tampil_trxlayanan();
            statement.close();           
           JOptionPane.showMessageDialog(rootPane, "Data Berhasil Di hapus...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus : " + e);
        }
    }
     private void panggil_trx(){
         try {
            String query = "SELECT a.regid, a.medrec_id, d.nama, a.dokter_id,"
                    + " b.namadokter, a.layanan_id, c.namalayanan, "
                    + "a.defaultharga, a.satuan, a.totalharga "
                    + "FROM trx_layanan a, master_dokter b, master_layanan c, "
                    + "master_medrec d WHERE a.medrec_id = d.medrec_id "
                    + "AND a.dokter_id = b.dokter_id AND a.layanan_id = c.layanan_id"
                    + " AND trxlayanan_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, txttransaksi_id.getText());
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                txtregid.setText(res.getString(1));
                txtnomedrec.setText(res.getString(2));
                txtnama.setText(res.getString(3));
                dokterupdate = res.getString(4);
                txtdokter.setText(res.getString(5));
                txtlayanan_id.setText(res.getString(6));
                txtlayanan.setText(res.getString(7));
                txtharga.setText(res.getString(8));
                txtsatuan.setText(res.getString(9));
                txttotal.setText(res.getString(10));
                
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     private void generate(){
         DateFormat hour = new SimpleDateFormat("HHmmss");
         Date date = new Date();
         String now = hour.format(date);
         txttransaksi_id.setText((txtregid.getText()+txtnomedrec.getText()+txtlayanan_id.getText()+now)); 
     }
    
     private void clear(){
        txttransaksi_id.setText("");
        txtharga.setText("");
        txtdokter.setText("");
        txtnama.setText("");
        txtnomedrec.setText("");
        txtregid.setText("");
        txtsatuan.setText("");
        txttotal.setText("");
        txtlayanan.setText("");
        txtlayanan_id.setText("");
        
        
    
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
        txttransaksi_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtsatuan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txtnomedrec = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtdokter = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtregid = new javax.swing.JTextField();
        txtlayanan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtlayanan_id = new javax.swing.JTextField();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltransaksilayanan = new javax.swing.JTable();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        btntransaksi = new javax.swing.JButton();
        btnlayanan = new javax.swing.JButton();
        btnpasien = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        Batal = new javax.swing.JLayeredPane();
        btnsimpan = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnselesai = new javax.swing.JButton();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Kelola Transaksi Layanan");
        jLabel1.setBounds(300, 20, 150, 20);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Transaksi Layanan"));

        jLabel2.setText("Transaksi_Id");
        jLabel2.setBounds(30, 30, 90, 30);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txttransaksi_id.setEditable(false);
        txttransaksi_id.setText("jTextField1");
        txttransaksi_id.setBounds(130, 30, 190, 30);
        jLayeredPane2.add(txttransaksi_id, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("Layanan");
        jLabel3.setBounds(30, 110, 60, 30);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("Harga");
        jLabel4.setBounds(30, 150, 60, 30);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtharga.setText("jTextField3");
        txtharga.setBounds(130, 150, 190, 30);
        jLayeredPane2.add(txtharga, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("No_Medrec");
        jLabel5.setBounds(350, 110, 70, 30);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Satuan");
        jLabel6.setBounds(30, 190, 60, 30);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtsatuan.setText("jTextField4");
        txtsatuan.setBounds(130, 190, 190, 30);
        jLayeredPane2.add(txtsatuan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText("Total");
        jLabel7.setBounds(350, 30, 50, 30);
        jLayeredPane2.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txttotal.setText("jTextField5");
        txttotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttotalMouseClicked(evt);
            }
        });
        txttotal.setBounds(470, 30, 190, 30);
        jLayeredPane2.add(txttotal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtnomedrec.setEditable(false);
        txtnomedrec.setText("jTextField6");
        txtnomedrec.setBounds(470, 110, 190, 30);
        jLayeredPane2.add(txtnomedrec, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setText("Nama");
        jLabel8.setBounds(350, 150, 70, 30);
        jLayeredPane2.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtnama.setEditable(false);
        txtnama.setText("jTextField7");
        txtnama.setBounds(470, 150, 190, 30);
        jLayeredPane2.add(txtnama, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel9.setText("Dokter");
        jLabel9.setBounds(350, 190, 70, 30);
        jLayeredPane2.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtdokter.setEditable(false);
        txtdokter.setText("jTextField8");
        txtdokter.setBounds(470, 190, 190, 30);
        jLayeredPane2.add(txtdokter, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel11.setText("Registrasi ID");
        jLabel11.setBounds(350, 70, 90, 30);
        jLayeredPane2.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtregid.setEditable(false);
        txtregid.setText("jTextField10");
        txtregid.setBounds(470, 70, 190, 30);
        jLayeredPane2.add(txtregid, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtlayanan.setEditable(false);
        txtlayanan.setText("jTextField1");
        txtlayanan.setBounds(130, 110, 190, 30);
        jLayeredPane2.add(txtlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel13.setText("Layanan Id");
        jLabel13.setBounds(30, 70, 70, 30);
        jLayeredPane2.add(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtlayanan_id.setEditable(false);
        txtlayanan_id.setText("jTextField2");
        txtlayanan_id.setBounds(130, 70, 190, 30);
        jLayeredPane2.add(txtlayanan_id, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Transaksi Layanan"));

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
        tbltransaksilayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltransaksilayananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbltransaksilayanan);

        jScrollPane1.setBounds(20, 110, 830, 180);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLayeredPane5.setBounds(10, 100, 850, 200);
        jLayeredPane3.add(jLayeredPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btntransaksi.setText("Transaksi Layanan");
        btntransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransaksiActionPerformed(evt);
            }
        });
        btntransaksi.setBounds(20, 30, 150, 23);
        jLayeredPane3.add(btntransaksi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnlayanan.setText("Layanan");
        btnlayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlayananActionPerformed(evt);
            }
        });
        btnlayanan.setBounds(180, 30, 160, 23);
        jLayeredPane3.add(btnlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnpasien.setText("Pasien rawat inap");
        btnpasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpasienActionPerformed(evt);
            }
        });
        btnpasien.setBounds(350, 30, 180, 23);
        jLayeredPane3.add(btnpasien, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel12.setText("Pencarian");
        jLabel12.setBounds(20, 60, 90, 30);
        jLayeredPane3.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBounds(120, 60, 150, 30);
        jLayeredPane3.add(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField1.setText("jTextField1");
        jTextField1.setBounds(280, 60, 270, 30);
        jLayeredPane3.add(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton8.setText("Cari");
        jButton8.setBounds(560, 60, 100, 30);
        jLayeredPane3.add(jButton8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel10.setText("Note : Yang Dapat Dikelola Hanya Transaksi layanan");
        jLabel10.setBounds(570, 30, 300, 20);
        jLayeredPane3.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Batal.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        btnsimpan.setBounds(40, 40, 120, 30);
        Batal.add(btnsimpan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnubah.setText("Ubah");
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });
        btnubah.setBounds(40, 83, 120, 30);
        Batal.add(btnubah, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        btnhapus.setBounds(40, 123, 120, 30);
        Batal.add(btnhapus, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnbatal.setText("Batal");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        btnbatal.setBounds(40, 160, 120, 30);
        Batal.add(btnbatal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnselesai.setText("Selesai");
        btnselesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnselesaiActionPerformed(evt);
            }
        });
        btnselesai.setBounds(40, 200, 120, 30);
        Batal.add(btnselesai, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Batal, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                    .addComponent(jLayeredPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Batal, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btntransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransaksiActionPerformed
        table_model_trxlayanan();
        status = 0;
       
    }//GEN-LAST:event_btntransaksiActionPerformed

    private void btnlayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlayananActionPerformed
        table_model_layanan();
        status = 1;
       
    }//GEN-LAST:event_btnlayananActionPerformed

    private void btnpasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpasienActionPerformed
        table_model_pasien();
        status = 2;
        
    }//GEN-LAST:event_btnpasienActionPerformed

    private void tbltransaksilayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltransaksilayananMouseClicked
       ambil();
       if(status == 0){
           if(statusupdate == 2){
               
           }else{
            panggil_trx();
           }
       }else{
           if(statusupdate == 1){
               
           }else{
                generate();
           }
       }
       panggil_pasien();
    }//GEN-LAST:event_tbltransaksilayananMouseClicked

    private void txttotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttotalMouseClicked
        total();
    }//GEN-LAST:event_txttotalMouseClicked

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        btnselesai.setVisible(true);
        btnhapus.setVisible(false);
        btnbatal.setVisible(false);
        btnubah.setVisible(false);
        btnlayanan.setVisible(true);
        btnpasien.setVisible(true);
        statusupdate = 2;
        if("Simpan".equals(btnsimpan.getText())){    
        btnsimpan.setText("simpan");
        clear();
        }else{
        insert();
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        btnselesai.setVisible(true);
        btnsimpan.setVisible(false);
        btnhapus.setVisible(false);
        btnbatal.setVisible(false);
        btnlayanan.setVisible(true);
        btnpasien.setVisible(true);
        statusupdate = 1 ;
        if("Ubah".equals(btnubah.getText())){    
        btnubah.setText("ubah");
        clear();
        }else{
            update();
        }
    }//GEN-LAST:event_btnubahActionPerformed

    private void btnselesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnselesaiActionPerformed
        btnselesai.setVisible(false);
        btnsimpan.setVisible(true);
        btnhapus.setVisible(true);
        btnbatal.setVisible(true);
        btnubah.setVisible(true);
        btnlayanan.setVisible(false);
        btnpasien.setVisible(false);
        statusupdate = 0;
        btnubah.setText("Ubah");
        btnsimpan.setText("Simpan");
        clear();
    }//GEN-LAST:event_btnselesaiActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        if(status == 0){
            delete();
        }else{
            JOptionPane.showMessageDialog(rootPane, "Tampilkan Tabel Transaksi Terlebih Dahulu");
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane Batal;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnlayanan;
    private javax.swing.JButton btnpasien;
    private javax.swing.JButton btnselesai;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntransaksi;
    private javax.swing.JButton btnubah;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbltransaksilayanan;
    private javax.swing.JTextField txtdokter;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtlayanan;
    private javax.swing.JTextField txtlayanan_id;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnomedrec;
    private javax.swing.JTextField txtregid;
    private javax.swing.JTextField txtsatuan;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttransaksi_id;
    // End of variables declaration//GEN-END:variables
}
