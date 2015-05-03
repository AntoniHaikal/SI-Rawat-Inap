/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class koneksi {
    private static Connection koneksi; 
    public static Connection getConnection() throws SQLException {
        if (koneksi == null) {
            // panggil Driver MySQL
            new Driver();
            // buat koneksi
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost:"
                    + "3306/rawat_inap", "root", "hantu123");
        }
        return koneksi;
    } 
    
    public static void main(String args[]) {
        try {
            getConnection();
            
            System.out.println("Koneksi Berhasil");
            
        } catch (SQLException ex) {
            
            System.err.println("Koneksi Gagal");
        }
    }

}
