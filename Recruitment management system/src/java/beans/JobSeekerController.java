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
import java.util.List;
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
public class JobSeekerController {

    private List<JobSeeker> jobSeekers;
    private static JobSeekerController instance;
    private JobSeeker js;
    private String username;
    private String password;
    private boolean loggedIn;
    int i;
  

    public JobSeeker getJs() {
        return js;
    }

    public void setJs(JobSeeker js) {
        this.js = js;
    }

   
    public JobSeekerController() {
        js = new JobSeeker();
        getJobSeekersFromDB();
    }

   
   
    private void getJobSeekersFromDB() {
        try (Connection conn = (Connection) DBUtils.getConnection()) {
            jobSeekers = new ArrayList<>();
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jobseekers");
            while (rs.next()) {
                JobSeeker j = new JobSeeker(
                        rs.getString("username"),
                        rs.getString("password")
                        );
                jobSeekers.add(j);
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobSeekerController.class.getName()).log(Level.SEVERE, null, ex);
            jobSeekers = new ArrayList<>();
        }
        
    }

   
    public List<JobSeeker> getJobSeekers() {
        return jobSeekers;
    }

    public static JobSeekerController getInstance() {
        return instance;
    }
  
    public String addJobSeeker() {
        
        try (Connection conn = (Connection) DBUtils.getConnection()) {
           
            
            String sql = "INSERT INTO jobseekers (name, address, phone, email, username, password, confirm_password) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, js.getName());
            pstmt.setString(2, js.getAddress());
            pstmt.setString(3, js.getPhone());
            pstmt.setString(4, js.getEmail());
            pstmt.setString(5, js.getUsername());
            pstmt.setString(6, js.getPassword());
            pstmt.setString(7, js.getConfirmPassword());
            i =  pstmt.executeUpdate();
           

        } catch (SQLException ex) {
            Logger.getLogger(JobSeekerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getJobSeekersFromDB();
        if(i>0){
            return "jobSeekerLogin";
        }else{
            return "jobSeekerSignUp";
        }
    }

       

   
}
       

    


