/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Classes;

import My_Forms.AddBeritaForm;
import My_Forms.ManageGenresForm;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author r
 */
public class Berita {
    
    private int id;
    private String JBerita;
    private String Author;
    private String Desk;
    private byte[] picture;
    
    public Berita(){}
    
    public Berita(int _id, String JBerita, String Author, String Desk, byte[] _picture){
        this.id = _id;
        this.JBerita = JBerita;
        this.Author = Author;
        this.Desk = Desk;
        this.picture = _picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJBerita(String JBerita) {
        this.JBerita = JBerita;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setDesk(String Desk) {
        this.Desk = Desk;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getJBerita() {
        return JBerita;
    }

    public String getAuthor() {
        return Author;
    }

    public String getDesk() {
        return Desk;
    }

    public byte[] getPicture() {
        return picture;
    }
    
    public void addBerita(String JBerita, String Author, String Desk, byte[] _pic) {
        String insertQuery = "INSERT INTO `berita`(`Judul Berita`, `Author`, `Deskripsi`, `image`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, JBerita);
            ps.setString(2, Author);
            ps.setString(3, Desk);
            ps.setBytes(4, _pic);
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Berita berhasil ditambahkan!", "Tambah Member", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Berita gagal ditambahkan!", "ERROR", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Berita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        

    public void editBerita(int _id, String JBerita, String Author, String Desk, byte[] _pic) {
        String editQuery = "UPDATE `berita` SET `Judul Berita`=?,`Author`=?,`Deskripsi`=?,`image`=? WHERE `id` = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, JBerita);
            ps.setString(2, Author);
            ps.setString(3, Desk);
            ps.setBytes(4, _pic);
            ps.setInt(5, _id);
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Berita berhasil diedit!", "Edit Member", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Berita gagal diedit!", "ERROR", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Berita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
    
    public void removeBerita(int _id) {
        String removeQuery = "DELETE FROM `berita` WHERE `id` = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(removeQuery);
            ps.setInt(1, _id);
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Berita berhasil dihapus!", "Delete Member", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Berita gagal dihapus!", "ERROR", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Berita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
    
    public Berita getBeritaById(String _id) throws SQLException{
        Func_Class func = new Func_Class();
        String query = "SELECT * FROM `berita` WHERE `id`="+_id;
        ResultSet rs = func.getData(query);
        if(rs.next()){
            return new Berita(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBytes(5));
        } else {
            return null;
        }
    }
    
    public ArrayList<Berita> beritaList(){
        ArrayList<Berita> gList = new ArrayList<>();
        String selectQuery = "SELECT * FROM `berita`";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = DB.getConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            
            Berita berita;
            while(rs.next()){
                berita = new Berita(rs.getInt("id"), rs.getString("Judul Berita"), rs.getString("Author"), rs.getString("Deskripsi"), rs.getBytes("image"));
                gList.add(berita);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBeritaForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gList;
    }        
}
