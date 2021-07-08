/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Classes;

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
public class Genre {
    private int id;
    private String name;
    public Genre(){};
    
    public Genre(int _id, String _name){
        this.id = _id;
        this.name = _name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public void setName(String _name) {
        this.name = _name;
    }
    
    public void addGenre(String _name) {
        String insertQuery = "INSERT INTO `berita_genres` (`name`) VALUES (?)";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _name);
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Genre berhasil ditambahkan!", "Tambah Genre", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Genre gagal ditambahkan!", "ERROR", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editGenre(int _id, String _name) {
        String editQuery = "UPDATE `berita_genres` SET `name` = ? WHERE `id` = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _name);
            ps.setInt(2, _id);
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Genre berhasil diedit!", "Edit Genre", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Genre gagal diedit!", "ERROR", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeGenre(int _id) {
        String editQuery = "DELETE FROM `berita_genres` WHERE `id` = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(editQuery);
            ps.setInt(1, _id);
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Genre berhasil dihapus!", "Delete Genre", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Genre gagal dihapus!", "ERROR", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public ArrayList<Genre> genreList(){
        ArrayList<Genre> gList = new ArrayList<>();
        String selectQuery = "SELECT * FROM `berita_genres`";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = DB.getConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            
            Genre genre;
            while(rs.next()){
                genre = new Genre(rs.getInt("id"), rs.getString("name"));
                gList.add(genre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageGenresForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gList;
    }    
}
