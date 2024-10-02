package repositories;


import config.DatabaseConnection;
import models.Member;
import models.enums.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepository {
	
	 @Override
	    public List<Member> searchMembers(String name) {
	        String query = "SELECT * FROM members WHERE fname LIKE ? OR lname LIKE ?";
	        List<Member> members = new ArrayList<>();

	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, "%" + name + "%");
	            stmt.setString(2, "%" + name + "%");
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                Member member = new Member(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"),
	                        rs.getString("email"), Role.valueOf(rs.getString("role")), rs.getInt("team_id"));
	                members.add(member);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return members;
	    }
}
