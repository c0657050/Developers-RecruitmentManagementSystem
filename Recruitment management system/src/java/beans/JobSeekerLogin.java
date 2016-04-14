/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author c0657966
 */
@ManagedBean
@ApplicationScoped
public class JobSeekerLogin {
    private String username;
    private String password;
    private JobSeeker currentJobSeeker;
    int i;
    
   

    /**
     * No-arg constructor -- establishes as not logged in
     */
    
    public JobSeekerLogin() {
        username = null;
        password = null;
        currentJobSeeker = null;
    }

    public JobSeekerLogin(String username, String password, boolean loggedIn) {
        this.username = username;
        this.password = password;
        this.currentJobSeeker = currentJobSeeker;
    }

   
    
    
    public String getUsername() {
        return username;
    }

  
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    
  
  
  public JobSeeker getCurrentJobSeeker(){
      
       return currentJobSeeker;
   }
  
    
    public String login() {
        try (Connection conn = (Connection) DBUtils.getConnection()) {
            String sql = "SELECT * FROM jobseekers WHERE username='"+username+"' and password='"+password+"'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery();
             if(rs.next()){
                 return "jsLoggedIn";
             }
           } catch (SQLException ex) {
            Logger.getLogger(JobSeekerLogin.class.getName()).log(Level.SEVERE, null, ex);
           }
        return "jobSeekerLogin";
       
            
        }
    }

        
    

       
    

