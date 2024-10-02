package dao;

import java.sql.SQLException;
import java.util.List;

import models.Member;

public interface MemberDAO {
    void addMember(Member member) throws SQLException;
    List<Member> getAllMembers() throws SQLException;
    void updateMember(Member member) throws SQLException;
    void deleteMember(int id) throws SQLException;
}
