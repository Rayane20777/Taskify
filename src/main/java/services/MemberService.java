package services;

import java.sql.SQLException;
import java.util.List;

import dto.MemberDTO;
import models.Member;

public interface MemberService {
    public Member addMember(MemberDTO memberDTO) throws SQLException;
    public Member updateMember(MemberDTO memberDTO) throws SQLException;
    public void deleteMember(int id) throws SQLException;
    public List<Member> getAllMembers() throws SQLException;
    public List<Member> searchMembers(String name) throws SQLException;
}
