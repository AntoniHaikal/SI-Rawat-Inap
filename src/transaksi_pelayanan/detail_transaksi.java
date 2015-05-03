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
public class detail_transaksi extends javax.swing.JInternalFrame {

    private String dataku = null;
    private ResultSet res;
    JDesktopPane DP;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    DefaultTableModel TableModels1;
    private String awallayanan;
    private String akhirlayanan;
    private String awalobat;
    private String akhirobat;
    
    public detail_transaksi(JDesktopPane DP) {
        initComponents();
        this.DP = DP;
        SettingTableModellayanan();
        SettingTableModelobat();
        clear();
        set_tanggal();
    }
    
     private void tampillayanan(){
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "select * from transaksi_layanan";
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
                            res.getString(11),
                            res.getString(12)
                            
                        });
                tblcarilayanan.setModel(TableModels); 
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
            dc_awallayanan.setDate(date = format.parse("2015-01-01"));
            dc_akhirlayanan.setDate(date = format.parse("2015-01-01"));
            dc_awalobat.setDate(date = format.parse("2015-01-01"));
            dc_akhirobat.setDate(date = format.parse("2015-01-01"));
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void SettingTableModellayanan() {
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "Transaksi Layanan ID", 
                    "Registrasi ID", 
                    "Nama",
                    "ID Petugas Medis",
                    "Nama Petugas Medis", 
                    "Layanan ID", 
                    "Nama Layanan", 
                    "Harga", 
                    "Satuan",
                    "Total", 
                    "Nama User",
                    "Tanggal Buat"
                },null, new int[]{}, null);
        tblcarilayanan.setModel(TableModels);
        TableViews.table(tblcarilayanan, new int[]{150, 100, 150, 150, 150, 150, 100, 150, 150, 100, 100, 100});
        
        tampillayanan();

    }
    
    private void carilayanan(){
        TableModels.getDataVector().removeAllElements();
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "Transaksi Layanan ID", 
                    "Registrasi ID", 
                    "Nama",
                    "ID Petugas Medis",
                    "Nama Petugas Medis", 
                    "Layanan ID", 
                    "Nama Layanan", 
                    "Harga", 
                    "Satuan",
                    "Total", 
                    "Nama User",
                    "Tanggal Buat"
                },null, new int[]{}, null);
        tblcarilayanan.setModel(TableModels);
        TableViews.table(tblcarilayanan, new int[]{150, 100, 150, 150, 150, 150, 100, 150, 150, 100, 100, 100});

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            awallayanan = format.format(dc_awallayanan.getDate());
            akhirlayanan = format.format(dc_akhirlayanan.getDate());
            if(cb_tanggallayanan.isSelected() == false){
               try{
                    String query = "SELECT * from transaksi_layanan WHERE "
                            + "trxlayanan_id like ? "
                            + "AND regid like ? "
                            + "AND nama like ? "
                            + "AND namalayanan like ?";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txt_trxlayananID.getText()+ "%");
                    statement.setString(2, "%" +txtregidlayanan.getText()+ "%");
                    statement.setString(3, "%" + txt_namapasienlayanan.getText() + "%");
                    statement.setString(4, "%" + txt_namalayanan.getText() + "%");
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
                                    res.getString(11),
                                    res.getString(12)

                                });
                        tblcarilayanan.setModel(TableModels); 
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
                String query = "SELECT * from transaksi_layanan WHERE "
                            + "trxlayanan_id like ? "
                            + "AND regid like ? "
                            + "AND nama like ? "
                            + "AND namalayanan like ?"
                            + "AND tanggalbuat between ? "
                            + "AND ?";                  
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txt_trxlayananID.getText()+ "%");
                    statement.setString(2, "%" +txtregidlayanan.getText()+ "%");
                    statement.setString(3, "%" + txt_namapasienlayanan.getText() + "%");
                    statement.setString(4, "%" + txt_namalayanan.getText() + "%");
                    statement.setString(5, awallayanan);
                    statement.setString(6, akhirlayanan);
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
                                    res.getString(11),
                                    res.getString(12)

                                });
                        tblcarilayanan.setModel(TableModels); 
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
    
     private void tampilobat(){
        try {
            TableModels1.getDataVector().removeAllElements();
            String query = "select * from transaksi_obat";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();            
            while (res.next()) {
                TableModels1.addRow(new Object[]{
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
                tblcariobat.setModel(TableModels1); 
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void SettingTableModelobat() {
        TableModels1 = TableViews.getDefaultTableModel
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
        tblcariobat.setModel(TableModels1);
        TableViews.table(tblcariobat, new int[]{150, 100, 150, 150, 150, 100, 150, 150, 100, 100, 100});
        
        tampilobat();

    }
    
    private void cariobat(){
        TableModels1.getDataVector().removeAllElements();
        TableModels1 = TableViews.getDefaultTableModel
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
        tblcariobat.setModel(TableModels1);
        TableViews.table(tblcariobat, new int[]{150, 100, 150, 150, 150, 100, 150, 150, 100, 100, 100});

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            awalobat = format.format(dc_awalobat.getDate());
            akhirobat = format.format(dc_akhirobat.getDate());
            if(cb_tanggalobat.isSelected() == false){
               try{
                    String query = "SELECT * from transaksi_obat WHERE "
                            + "trxobt_id like ? "
                            + "AND regid like ? "
                            + "AND nama like ? "
                            + "AND namaobat like ?";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txt_trxobatID.getText()+ "%");
                    statement.setString(2, "%" +txtregidobat.getText()+ "%");
                    statement.setString(3, "%" + txt_namapasienobat.getText() + "%");
                    statement.setString(4, "%" + txt_namaobat.getText() + "%");
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
                                    res.getString(6),
                                    res.getString(7),
                                    res.getString(8),
                                    res.getString(9),
                                    res.getString(10),
                                    res.getString(11)

                                });
                        tblcariobat.setModel(TableModels1); 
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
                    statement.setString(2, "%" +txtregidobat.getText()+ "%");
                    statement.setString(3, "%" + txt_namapasienobat.getText() + "%");
                    statement.setString(4, "%" + txt_namaobat.getText() + "%");
                    statement.setString(5, awalobat);
                    statement.setString(6, akhirobat);
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
                                    res.getString(6),
                                    res.getString(7),
                                    res.getString(8),
                                    res.getString(9),
                                    res.getString(10),
                                    res.getString(11)

                                });
                        tblcariobat.setModel(TableModels1); 
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
        dc_awalobat.setVisible(false);
        dc_akhirobat.setVisible(false);
        dc_akhirlayanan.setVisible(false);
        dc_awallayanan.setVisible(false);
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
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        txt_trxlayananID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtregidlayanan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_namalayanan = new javax.swing.JTextField();
        btn_carilayanan = new javax.swing.JButton();
        dc_awallayanan = new com.toedter.calendar.JDateChooser();
        dc_akhirlayanan = new com.toedter.calendar.JDateChooser();
        cb_tanggallayanan = new javax.swing.JCheckBox();
        txt_namapasienlayanan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcarilayanan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLabel8 = new javax.swing.JLabel();
        txt_trxobatID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtregidobat = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_namaobat = new javax.swing.JTextField();
        btn_cariobat = new javax.swing.JButton();
        dc_awalobat = new com.toedter.calendar.JDateChooser();
        dc_akhirobat = new com.toedter.calendar.JDateChooser();
        cb_tanggalobat = new javax.swing.JCheckBox();
        txt_namapasienobat = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblcariobat = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Halaman Detail Transaksi Layanan");
        jLabel1.setBounds(260, 10, 240, 20);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pencarian"));

        jLabel2.setText("trxlayanan_id");
        jLabel2.setBounds(20, 20, 80, 30);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txt_trxlayananID.setBounds(130, 20, 160, 30);
        jLayeredPane2.add(txt_trxlayananID, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("s/d");
        jLabel4.setBounds(510, 60, 20, 30);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Regid");
        jLabel5.setBounds(310, 20, 40, 30);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtregidlayanan.setBounds(360, 20, 110, 30);
        jLayeredPane2.add(txtregidlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Nama Pasien");
        jLabel6.setBounds(490, 20, 80, 30);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txt_namalayanan.setBounds(130, 60, 110, 30);
        jLayeredPane2.add(txt_namalayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btn_carilayanan.setText("Cari");
        btn_carilayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_carilayananActionPerformed(evt);
            }
        });
        btn_carilayanan.setBounds(570, 110, 120, 23);
        jLayeredPane2.add(btn_carilayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dc_awallayanan.setBounds(350, 60, 130, 30);
        jLayeredPane2.add(dc_awallayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dc_akhirlayanan.setBounds(560, 60, 130, 30);
        jLayeredPane2.add(dc_akhirlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cb_tanggallayanan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cb_tanggallayanan.setText("Tanggal");
        cb_tanggallayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tanggallayananActionPerformed(evt);
            }
        });
        cb_tanggallayanan.setBounds(250, 60, 90, 30);
        jLayeredPane2.add(cb_tanggallayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txt_namapasienlayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namapasienlayananActionPerformed(evt);
            }
        });
        txt_namapasienlayanan.setBounds(580, 20, 110, 30);
        jLayeredPane2.add(txt_namapasienlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText("Nama Layanan");
        jLabel7.setBounds(20, 60, 100, 30);
        jLayeredPane2.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Pencarian"));

        tblcarilayanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcarilayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcarilayananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcarilayanan);

        jScrollPane1.setBounds(10, 20, 690, 210);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addComponent(jLayeredPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Transaksi Layanan", jPanel1);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("Halaman Detail Transaksi Obat");
        jLabel3.setBounds(260, 10, 240, 20);
        jLayeredPane4.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pencarian"));

        jLabel8.setText("trxobat_id");
        jLabel8.setBounds(20, 20, 80, 30);
        jLayeredPane5.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txt_trxobatID.setBounds(130, 20, 160, 30);
        jLayeredPane5.add(txt_trxobatID, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel9.setText("s/d");
        jLabel9.setBounds(510, 60, 20, 30);
        jLayeredPane5.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel10.setText("Regid");
        jLabel10.setBounds(310, 20, 40, 30);
        jLayeredPane5.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtregidobat.setBounds(360, 20, 110, 30);
        jLayeredPane5.add(txtregidobat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel11.setText("Nama Pasien");
        jLabel11.setBounds(490, 20, 80, 30);
        jLayeredPane5.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txt_namaobat.setBounds(130, 60, 110, 30);
        jLayeredPane5.add(txt_namaobat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btn_cariobat.setText("Cari");
        btn_cariobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariobatActionPerformed(evt);
            }
        });
        btn_cariobat.setBounds(570, 110, 120, 23);
        jLayeredPane5.add(btn_cariobat, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dc_awalobat.setBounds(350, 60, 130, 30);
        jLayeredPane5.add(dc_awalobat, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dc_akhirobat.setBounds(560, 60, 130, 30);
        jLayeredPane5.add(dc_akhirobat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cb_tanggalobat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cb_tanggalobat.setText("Tanggal");
        cb_tanggalobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tanggalobatActionPerformed(evt);
            }
        });
        cb_tanggalobat.setBounds(250, 60, 90, 30);
        jLayeredPane5.add(cb_tanggalobat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txt_namapasienobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namapasienobatActionPerformed(evt);
            }
        });
        txt_namapasienobat.setBounds(580, 20, 110, 30);
        jLayeredPane5.add(txt_namapasienobat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel12.setText("Nama Obat");
        jLabel12.setBounds(20, 60, 100, 30);
        jLayeredPane5.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Pencarian"));

        tblcariobat.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcariobat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcariobatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblcariobat);

        jScrollPane2.setBounds(10, 20, 690, 220);
        jLayeredPane6.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane4)
                    .addComponent(jLayeredPane5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLayeredPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Transaksi Obat", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_carilayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_carilayananActionPerformed
        carilayanan();
    }//GEN-LAST:event_btn_carilayananActionPerformed

    private void cb_tanggallayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tanggallayananActionPerformed
        if(cb_tanggallayanan.isSelected() == true){
            dc_awallayanan.setVisible(true);
            dc_akhirlayanan.setVisible(true);
        }else {
            dc_awallayanan.setVisible(false);
            dc_akhirlayanan.setVisible(false);
        }
    }//GEN-LAST:event_cb_tanggallayananActionPerformed

    private void txt_namapasienlayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namapasienlayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namapasienlayananActionPerformed

    private void tblcarilayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcarilayananMouseClicked

    }//GEN-LAST:event_tblcarilayananMouseClicked

    private void btn_cariobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariobatActionPerformed
        cariobat();
    }//GEN-LAST:event_btn_cariobatActionPerformed

    private void cb_tanggalobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tanggalobatActionPerformed
        if(cb_tanggalobat.isSelected() == true){
            dc_awalobat.setVisible(true);
            dc_akhirobat.setVisible(true);
        }else {
            dc_awalobat.setVisible(false);
            dc_akhirobat.setVisible(false);
        }
    }//GEN-LAST:event_cb_tanggalobatActionPerformed

    private void txt_namapasienobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namapasienobatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namapasienobatActionPerformed

    private void tblcariobatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcariobatMouseClicked

    }//GEN-LAST:event_tblcariobatMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_carilayanan;
    private javax.swing.JButton btn_cariobat;
    private javax.swing.JCheckBox cb_tanggallayanan;
    private javax.swing.JCheckBox cb_tanggalobat;
    private com.toedter.calendar.JDateChooser dc_akhirlayanan;
    private com.toedter.calendar.JDateChooser dc_akhirobat;
    private com.toedter.calendar.JDateChooser dc_awallayanan;
    private com.toedter.calendar.JDateChooser dc_awalobat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblcarilayanan;
    private javax.swing.JTable tblcariobat;
    private javax.swing.JTextField txt_namalayanan;
    private javax.swing.JTextField txt_namaobat;
    private javax.swing.JTextField txt_namapasienlayanan;
    private javax.swing.JTextField txt_namapasienobat;
    private javax.swing.JTextField txt_trxlayananID;
    private javax.swing.JTextField txt_trxobatID;
    private javax.swing.JTextField txtregidlayanan;
    private javax.swing.JTextField txtregidobat;
    // End of variables declaration//GEN-END:variables
}
