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
public class JobSeekerResumeController {

    private List<JobSeekerResume> jobSeekersResume;
    private static JobSeekerResumeController instance;
    private JobSeekerResume jsr;
    int i;

    public List<JobSeekerResume> getJobSeekersResume() {
        return jobSeekersResume;
    }

    public void setJobSeekersResume(List<JobSeekerResume> jobSeekersResume) {
        this.jobSeekersResume = jobSeekersResume;
    }

    public JobSeekerResume getJsr() {
        return jsr;
    }

    public void setJs(JobSeekerResume jsr) {
        this.jsr = jsr;
    }

    public JobSeekerResumeController() {

        jsr = new JobSeekerResume();
        getJobSeekersResumeFromDB();
    }

    public String addJobSeekersResume() {

        try (Connection conn = (Connection) DBUtils.getConnection()) {
            String sql = "INSERT INTO jobseekers_resume ( position, preferred_location, first_name, last_name, gender, phone, email, area_of_interest, primary_skills, experience, current_location, highest_qualification, year_of_passing ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, jsr.getPosition());
            pstmt.setString(2, jsr.getPreferredLocation());
            pstmt.setString(3, jsr.getFirstName());
            pstmt.setString(4, jsr.getLastName());
            pstmt.setString(5, jsr.getGender());
            pstmt.setString(6, jsr.getPhone());
            pstmt.setString(7, jsr.getEmail());
            pstmt.setString(8, jsr.getAreaOfInterest());
            pstmt.setString(9, jsr.getPrimarySkills());
            pstmt.setString(10, jsr.getExperience());
            pstmt.setString(11, jsr.getCurrentLocation());
            pstmt.setString(12, jsr.getHighestQualification());
            pstmt.setString(13, jsr.getYearOfPassing());
            i = pstmt.executeUpdate();
            System.out.println(i);

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JobSeekerResumeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (i > 0) {
            return "jsLoggedIn";
        } else {
            return "jobSeekerResume";
        }
    }

    private void getJobSeekersResumeFromDB() {

        try (Connection conn = (Connection) DBUtils.getConnection()) {
            jobSeekersResume = new ArrayList<>();
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jobseekers_resume ");
            while (rs.next()) {
                JobSeekerResume jsrc = new JobSeekerResume(
                        rs.getString("position"),
                        rs.getString("preferred_location"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("area_of_interest"),
                        rs.getString("primary_skills"),
                        rs.getString("experience"),
                        rs.getString("current_location"),
                        rs.getString("highest_qualification"),
                        rs.getString("year_of_passing")
                );
                jobSeekersResume.add(jsrc);

            }
        } catch (SQLException ex) {
            Logger.getLogger(JobSeekerResumeController.class.getName()).log(Level.SEVERE, null, ex);
            jobSeekersResume = new ArrayList<>();
        }
    }

}
