/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utils.Helper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author david
 */
public class User {

    private int ID;
    private String userName;
    private String password;
    
    public User(){
        ID = 0;
        userName = "";
        password = ""; 
    }
    
    public int getID()
    {
        return ID;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public String getPassword(){
        return password; 
    }
    
    public void setID(int id){
        this.ID = id;
    }
    
    public void setUsername(String userName){
        this.userName = userName;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public User findUser(String username, String password){
        String query = "SELECT * FROM User WHERE UserName = '%s' AND password = '%s'";
        
        try{
            ResultSet rs = Helper.getDBA().executeQuery(String.format(query, username, password));
       
            while(rs.next()){
                this.ID = rs.getInt("ID");
                this.userName = rs.getString("UserName");
                this.password = rs.getString("Password");
            }
            rs.close();
            
        } catch (SQLException sqlEx){
            Helper.logException(sqlEx);
        }
        
        return this; 
    }
    
    
}
