/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class koneksi {
    String jdbcDriver = "com.mysql.jdbc.Driver";
    String dbUrl = "jdbc:mysql://localhost/mahasiswa";
    String user ="root";
    String password ="";
    
    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    
    boolean respons;
    
    public koneksi(){
        try {
            Class.forName(jdbcDriver);
            System.out.println("driver load.");
        } catch (ClassNotFoundException ex) {
            System.out.println("driver tidak ditemukan");
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            con = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("berhasil terkoneksi dengan mysql");
        } catch (SQLException ex) {
            System.out.println("gagal terkoneksi, periksa config mysql!");
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean insertData(String NIM, String Nama, String Program_Studi, String Fakultas){
        String query = "insert into tbl_data (NIM, Nama, Program_Studi, Fakultas) values (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, NIM);
            ps.setString(2, Nama);
            ps.setString(3, Program_Studi);
            ps.setString(4, Fakultas);
            ps.executeUpdate();
            respons = true;
            JOptionPane.showMessageDialog(null, "Berhasil di Insert");
        } catch (SQLException ex) {
            respons = false;
            System.out.println("gagal insert");
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respons;
        
    }
    
}
