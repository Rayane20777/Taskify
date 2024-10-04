package dao;

import config.DatabaseConnection;
import models.Member;
import models.enums.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
	
	 @Override
	    public void addMember(Member member) throws SQLException {
	        String query = "INSERT INTO members (fname, lname, email, role, team_id) VALUES (?, ?, ?, ?, ?)";

	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, member.getFname());
	            stmt.setString(2, member.getLname());
	            stmt.setString(3, member.getEmail());
	            stmt.setString(4, member.getRole().toString());
	            stmt.setInt(5, member.getTeamId());

	            stmt.executeUpdate();
	        } 
	    }
	 
	    @Override
	    public List<Member> getAllMembers() throws SQLException {
	        String query = "SELECT * FROM members";
	        List<Member> members = new ArrayList<>();

	        try (Connection conn = DatabaseConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {

	            while (rs.next()) {
	                Member member = new Member(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"),
	                        rs.getString("email"), Role.valueOf(rs.getString("role")), rs.getInt("team_id"));
	                members.add(member);
	            }
	        }

	        return members;
	    }
	    
	    @Override
	    public void updateMember(Member member) throws SQLException{
	        String query = "UPDATE members SET fname = ?, lname = ?, email = ?, role = ?, team_id = ? WHERE id = ?";

	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, member.getFname());
	            stmt.setString(2, member.getLname());
	            stmt.setString(3, member.getEmail());
	            stmt.setString(4, member.getRole().toString());
	            stmt.setInt(5, member.getTeamId());
	            stmt.setInt(6, member.getId());

	            stmt.executeUpdate();
	        } 
	    }
	    

	    @Override
	    public void deleteMember(int id) throws SQLException {
	        String query = "DELETE FROM members WHERE id = ?";

	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, id);
	            stmt.executeUpdate();
	        } 
	    }
}
