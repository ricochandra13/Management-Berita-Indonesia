/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author r
 */
public class Func_Class {
    public void displayImage(int width, int height,byte[] imagebyte, String imagePath, JLabel label){
        ImageIcon imgIco;   
        if(imagebyte != null){
            imgIco = new ImageIcon(imagebyte);
        } else {
            try{
                imgIco = new ImageIcon(getClass().getResource(imagePath));
            } catch(Exception e) {
                imgIco = new ImageIcon(imagePath);
            }
        }
        
        Image image = imgIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(image));
    }
    
    public void customTable(JTable table){
        table.setSelectionBackground(new Color(249, 105, 14));
        table.setSelectionForeground(Color.white);
        table.setRowHeight(35);
        table.setShowGrid(false);
        table.setBackground(new Color(248,248,248));
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.ORANGE);
    }
    
    public void customTableHeader(JTable table,Color back_Color,Integer fontSize){
        table.getTableHeader().setBackground(back_Color);
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setFont(new Font("Verdana",Font.BOLD,fontSize));
        table.getTableHeader().setOpaque(false);
    }    
    
    public ResultSet getData(String query){
        PreparedStatement ps;
        ResultSet rs = null;
        try{
            ps = DB.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Func_Class.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
