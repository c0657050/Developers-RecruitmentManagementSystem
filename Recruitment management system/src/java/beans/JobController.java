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
 * @author sahil
 */
@ManagedBean
@ApplicationScoped
public class JobController {

    private List<Job> jobs;
    private Job currentJob;
    int i;

    public JobController() {
        currentJob = new Job("", "", "", "", "", "", -1, "");
        getJobsFromDB();

    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Job getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    public String viewJobs() {
        return "jobSeekerViewJob";
    }

    private void getJobsFromDB() {
        try (Connection conn = (Connection) DBUtils.getConnection()) {
            jobs = new ArrayList<>();
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jobs");
            while (rs.next()) {
                Job j = new Job(
                        rs.getString("company"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("contact_no"),
                        rs.getString("location"),
                        rs.getString("specification"),
                        rs.getInt("experience"),
                        rs.getString("skills")
                );
                jobs.add(j);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JobSeekerController.class.getName()).log(Level.SEVERE, null, ex);
            jobs = new ArrayList<>();
        }

    }

    public String addJobs() {

        try (Connection conn = (Connection) DBUtils.getConnection()) {

            String sql = "INSERT INTO jobs (company, address, email, contact_no, location, specification, experience, skills) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentJob.getCompany());
            pstmt.setString(2, currentJob.getAddress());
            pstmt.setString(3, currentJob.getEmail());
            pstmt.setString(4, currentJob.getContact());
            pstmt.setString(5, currentJob.getLocation());
            pstmt.setString(6, currentJob.getSpecification());
            pstmt.setInt(7, currentJob.getExperience());
            pstmt.setString(8, currentJob.getSkills());

            i = pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JobController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getJobsFromDB();
        if (i > 0) {
            return "jobSeekerViewAllJobs";
        } else {
            return "recruiterAddJobs";
        }
    }

    public String viewJob(Job j) {
        currentJob = j;
        return "jobSeekerViewJob";
    }


        public String deleteJobs(Job j)
        {
            currentJob = j;
            Connection conn;
            try{
                conn = (Connection) DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement("Delete from jobs where email=?");
          
            pst.setString(1, currentJob.getEmail());
            pst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JobController.class.getName()).log(Level.SEVERE, null, ex);
     }
            getJobsFromDB();
            return "recruiterDeleteJobs";
            }
        
        
         public String searchJobs()
        {
            Connection conn;
            Job j;
            try{
                
                conn = (Connection) DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM jobs WHERE experience=?, location=?");
         
             
               pst.setInt(1, currentJob.getExperience());
               pst.setString(2, currentJob.getSkills());
               pst.setString(3, currentJob.getLocation());
               ResultSet rs = pst.executeQuery();
               while(rs.next()){
                  j = new Job(
                        rs.getString("company"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("contact_no"),
                        rs.getString("location"),
                        rs.getString("specification"),
                        rs.getInt("experience"),
                        rs.getString("skills")
                );
                  currentJob =j;
               }
               
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JobController.class.getName()).log(Level.SEVERE, null, ex);
             getJobsFromDB();
     }return "jobSeekerViewSearchedJobs";
            }
}


          
        



   
    


