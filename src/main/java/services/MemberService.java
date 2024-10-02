package services;

import dto.MemberDTO;

import java.sql.SQLException;
import java.util.List;

public interface MemberService {
    void addMember(MemberDTO memberDTO) throws SQLException;
    List<MemberDTO> getAllMembers() throws SQLException;
    List<MemberDTO> searchMembersByName(String name);
    void updateMember(MemberDTO memberDTO) throws SQLException;
    void deleteMember(int id) throws SQLException;
}
