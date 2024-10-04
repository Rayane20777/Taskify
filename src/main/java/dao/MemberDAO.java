package dao;

import java.sql.SQLException;
import java.util.List;
import models.Member;

public interface MemberDAO {
    public Member addMember(Member member) throws SQLException;
    public Member updateMember(Member member) throws SQLException;
    void deleteMember(int id) throws SQLException;
    public List<Member> getAllMembers() throws SQLException;
    public Member getMemberById(int id) throws SQLException;
}
