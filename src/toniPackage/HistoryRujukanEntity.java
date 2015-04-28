/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toniPackage;

import java.util.Date;

/**
 *
 * @author Toni
 */
public class HistoryRujukanEntity {
    private String historyrujukan_id;
    private int regid;
    private String medrec_id;
    private String asalrujukan; //Petugas yang merujuk
    private String petugasdirujuk;//petuga yang dituju
    private String tujuanrujukan;//tempat yang dituju
    private String perlakuanSebelumnya; //tindakan yang telah di berikan
    private Date tanggalrujukan;
    private String ket_rujuk;
    private String status_rujukan;

    public String getHistoryrujukan_id() {
        return historyrujukan_id;
    }

    public void setHistoryrujukan_id(String historyrujukan_id) {
        this.historyrujukan_id = historyrujukan_id;
    }

    public int getRegid() {
        return regid;
    }

    public void setRegid(int regid) {
        this.regid = regid;
    }

    public String getMedrec_id() {
        return medrec_id;
    }

    public void setMedrec_id(String medrec_id) {
        this.medrec_id = medrec_id;
    }

    public String getAsalrujukan() {
        return asalrujukan;
    }

    public void setAsalrujukan(String asalrujukan) {
        this.asalrujukan = asalrujukan;
    }

    public String getPetugasdirujuk() {
        return petugasdirujuk;
    }

    public void setPetugasdirujuk(String petugasdirujuk) {
        this.petugasdirujuk = petugasdirujuk;
    }

    public String getTujuanrujukan() {
        return tujuanrujukan;
    }

    public void setTujuanrujukan(String tujuanrujukan) {
        this.tujuanrujukan = tujuanrujukan;
    }

    public String getPerlakuanSebelumnya() {
        return perlakuanSebelumnya;
    }

    public void setPerlakuanSebelumnya(String perlakuanSebelumnya) {
        this.perlakuanSebelumnya = perlakuanSebelumnya;
    }

    public Date getTanggalrujukan() {
        return tanggalrujukan;
    }

    public void setTanggalrujukan(Date tanggalrujukan) {
        this.tanggalrujukan = tanggalrujukan;
    }

    public String getKet_rujuk() {
        return ket_rujuk;
    }

    public void setKet_rujuk(String ket_rujuk) {
        this.ket_rujuk = ket_rujuk;
    }

    public String getStatus_rujukan() {
        return status_rujukan;
    }

    public void setStatus_rujukan(String status_rujukan) {
        this.status_rujukan = status_rujukan;
    }
    
}
