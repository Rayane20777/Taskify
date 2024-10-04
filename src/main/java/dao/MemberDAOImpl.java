package dao;

import config.DatabaseConnection;
import models.Member;
import models.enums.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    private final Connection connection;

    public MemberDAOImpl(Connection connection) {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public Member addMember(Member member) throws SQLException {
        String query = "INSERT INTO members (fname, lname, email, role, team_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, member.getFname());
            stmt.setString(2, member.getLname());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getRole().toString());
            stmt.setInt(5, member.getTeamId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        member.setId(generatedKeys.getInt(1)); 
                    }
                }
            }
        }

        return member; 
    }

    @Override
    public Member updateMember(Member member) throws SQLException {
        String query = "UPDATE members SET fname = ?, lname = ?, email = ?, role = ?, team_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, member.getFname());
            stmt.setString(2, member.getLname());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getRole().toString());
            stmt.setInt(5, member.getTeamId());
            stmt.setInt(6, member.getId());

            stmt.executeUpdate();
        }

        return member; 
    }

    @Override
    public void deleteMember(int id) throws SQLException {
        String query = "DELETE FROM members WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Member member = new Member(
                        rs.getInt("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email"),
                        Role.valueOf(rs.getString("role")),
                        rs.getInt("team_id")
                );
                members.add(member);
            }
        }

        return members; 
    }

    @Override
    public Member getMemberById(int id) throws SQLException {
        String query = "SELECT * FROM members WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Member(
                            rs.getInt("id"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("email"),
                            Role.valueOf(rs.getString("role")),
                            rs.getInt("team_id")
                    );
                } else {
                    return null; 
                }
            }
        }
    }
}
