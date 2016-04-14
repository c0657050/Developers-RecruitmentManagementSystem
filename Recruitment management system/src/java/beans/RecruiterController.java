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
 * @author c0657050
 */
@ManagedBean
@ApplicationScoped
public class RecruiterController {

    private List<Recruiter> recruiters;
    private static RecruiterController instance;
    private Recruiter r;
    int i;

    public Recruiter getR() {
        return r;
    }

    public void setR(Recruiter r) {
        this.r = r;
    }

    public RecruiterController() {
        r = new Recruiter();
        getRecruitersFromDB();
    }

    private void getRecruitersFromDB() {
        try (Connection conn = (Connection) DBUtils.getConnection()) {
            recruiters = new ArrayList<>();
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM recruiters");
            while (rs.next()) {
                Recruiter rr = new Recruiter(
                        rs.getString("username"),
                        rs.getString("password")
                );
                recruiters.add(rr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
            recruiters = new ArrayList<>();
        }
    }

    public List<Recruiter> getRecruiter() {
        return recruiters;
    }

    public static RecruiterController getInstance() {
        return instance;
    }

    public String viewJobSeekers() {

        return "recruiterViewResume";
    }

    public String addRecruiter() {
        try (Connection conn = (Connection) DBUtils.getConnection()) {

            String sql = "INSERT INTO recruiters (first_name, last_name,company, address, phone, email, username, password, confirm_password) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, r.getFirstName());
            pstmt.setString(2, r.getLastName());
            pstmt.setString(3, r.getCompany());
            pstmt.setString(4, r.getAddress());
            pstmt.setString(5, r.getPhone());
            pstmt.setString(6, r.getEmail());
            pstmt.setString(7, r.getUsername());
            pstmt.setString(8, r.getPassword());
            pstmt.setString(9, r.getConfirmPassword());
            i = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getRecruitersFromDB();
        if (i > 0) {
            return "recruiterLogin";
        } else {
            return "recruiterSignUp";
        }
    }

}
