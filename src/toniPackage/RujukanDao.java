/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toniPackage;

import Class.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Toni
 */
public class RujukanDao {

    public RujukanDao() {
    }
    String insertRujukan = "INSERT INTO `rawat_inap`.`historyrujukan` "
            + "(`historyrujukan_id`, `regid`, `medrec_id`, `asalrujukan`, `petugasdirujuk`, `tujuanrujukan`, `perlakuanSebelumnya`, `tanggalrujukan`, `ket_rujuk`, `status_rujukan`) "
            + "VALUES ('sdfa', '2', '000001', 'asdf', 'asdf', 'sadf', 'sadf', CURRENT_DATE(), 'sadf', 'external')";

    public List<HistoryRujukanEntity> semua() {
        String sql = "select * from historyrujukan";
        List<HistoryRujukanEntity> hasil = new ArrayList<>();
        PreparedStatement stmt;
        try {
            stmt = koneksi.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HistoryRujukanEntity md = new HistoryRujukanEntity();
                md.setHistoryrujukan_id(rs.getString("historyrujukan_id"));
                md.setRegid(rs.getInt("regid"));
                md.setMedrec_id(rs.getString("medrec_id"));
                md.setAsalrujukan(rs.getString("asalrujukan"));
                md.setPetugasdirujuk(rs.getString("petugasdirujuk"));
                md.setTujuanrujukan(rs.getString("tujuanrujukan"));
                md.setPerlakuanSebelumnya(rs.getString("perlakuanSebelumnya"));
                md.setTanggalrujukan(rs.getDate("tanggalrujukan"));
                md.setKet_rujuk(rs.getString("ket_rujuk"));
                md.setStatus_rujukan(rs.getString("status_rujukan"));
                hasil.add(md);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(RujukanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    public List<HistoryRujukanEntity> cariHistory(HistoryRujukanEntity a) {
        String sql = "Select * from historyrujukan where historyrujukan_id = ? AND regid = ?";
        List<HistoryRujukanEntity> hasil = new ArrayList<>();
        try {
            PreparedStatement st = koneksi.getConnection().prepareStatement(sql);
            st.setString(1, a.getHistoryrujukan_id());
            st.setInt(2, a.getRegid());
            System.out.println(st+"masuk perkutut");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                HistoryRujukanEntity md = new HistoryRujukanEntity();
                md.setHistoryrujukan_id(rs.getString("historyrujukan_id"));
                md.setRegid(rs.getInt("regid"));
                md.setMedrec_id(rs.getString("medrec_id"));
                md.setAsalrujukan(rs.getString("asalrujukan"));
                md.setPetugasdirujuk(rs.getString("petugasdirujuk"));
                md.setTujuanrujukan(rs.getString("tujuanrujukan"));
                md.setPerlakuanSebelumnya(rs.getString("perlakuanSebelumnya"));
                md.setTanggalrujukan(rs.getDate("tanggalrujukan"));
                md.setKet_rujuk(rs.getString("ket_rujuk"));
                md.setStatus_rujukan(rs.getString("status_rujukan"));
                hasil.add(md);
            }
        } catch (Exception e) {
        }
        return hasil;
    }
}
