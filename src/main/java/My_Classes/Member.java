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
public class Member {
    
    private int id;
    private String FName;
    private String LName;
    private String PNumber;
    private String Email;
    private String Gender;
    private byte[] picture;
    
    public Member(){}
    
    public Member(int _id, String _FName, String _LName, String _PNumber, String _Email, String _Gender, byte[] _picture){
        this.id = _id;
        this.FName = _FName;
        this.LName = _LName;
        this.PNumber = _PNumber;
        this.Email = _Email;
        this.Gender = _Gender;
        this.picture = _picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public void setPNumber(String PNumber) {
        this.PNumber = PNumber;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getFName() {
        return FName;
    }

    public String getLName() {
        return LName;
    }

    public String getPNumber() {
        return PNumber;
    }

    public String getEmail() {
        return Email;
    }

    public String getGender() {
        return Gender;
    }

    public byte[] getPicture() {
        return picture;
    }
    
    public void addMember(String _fname, String _lname, String _pnumber, String _email, String _gender, byte[] _pic) {
        String insertQuery = "INSERT INTO `members`(`firstName`, `lastName`, `phone`, `email`, `gender`, `picture`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _pnumber);
            ps.setString(4, _email);
            ps.setString(5, _gender);
            ps.setBytes(6, _pic);
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Member berhasil ditambahkan!", "Tambah Member", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Member gagal ditambahkan!", "ERROR", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        

    public void editMember(int _id, String _fname, String _lname, String _pnumber, String _email, String _gender, byte[] _pic) {
        String editQuery = "UPDATE `members` SET `firstName`=?,`lastName`=?,`phone`=?,`email`=?,`gender`=?,`picture`=? WHERE `id` = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _pnumber);
            ps.setString(4, _email);
            ps.setString(5, _gender);
            ps.setBytes(6, _pic);
            ps.setInt(7, _id);
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Member berhasil diedit!", "Edit Member", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Member gagal diedit!", "ERROR", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
    
    public void removeMember(int _id) {
        String removeQuery = "DELETE FROM `members` WHERE `id` = ?";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(removeQuery);
            ps.setInt(1, _id);
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Member berhasil dihapus!", "Delete Member", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Member gagal dihapus!", "ERROR", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
    
    public Member getMemberById(Integer _id) throws SQLException{
        Func_Class func = new Func_Class();
        String query = "SELECT * FROM `members` WHERE `id`="+_id;
        ResultSet rs = func.getData(query);
        if(rs.next()){
            return new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getBytes(7));
        } else {
            return null;
        }
    }
}
