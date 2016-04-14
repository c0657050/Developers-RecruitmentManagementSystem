/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author c0657050
 */
@ManagedBean
@ApplicationScoped
public class RecruiterLogin {
    private String username;
    private String password;
    private boolean loggedIn;
    private JobSeeker currentRecruiter;
    int i;
    
   

    /**
     * No-arg constructor -- establishes as not logged in
     */
    
    public RecruiterLogin() {
        username = null;
        password = null;
        loggedIn = false;
        currentRecruiter = null;
    }

    public RecruiterLogin(String username, String password, boolean loggedIn) {
        this.username = username;
        this.password = password;
        this.loggedIn = loggedIn;
        this.currentRecruiter = currentRecruiter;
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

    
    public boolean isLoggedIn() {
        return loggedIn;
    }
  
  public JobSeeker getCurrentJobSeeker(){
      
       return currentRecruiter;
   }
  
    
    public String login() {
        try (Connection conn = (Connection) DBUtils.getConnection()) {
            String sql = "SELECT * FROM recruiters WHERE username='"+username+"' and password='"+password+"'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery();
             if(rs.next()){
                 return "recruiterLoggedIn";
             }
           } catch (SQLException ex) {
            Logger.getLogger(RecruiterLogin.class.getName()).log(Level.SEVERE, null, ex);
           }
        return "recruiterLogin";
       
            
        }
    }

