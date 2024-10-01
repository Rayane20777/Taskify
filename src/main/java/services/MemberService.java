package services;

import dto.MemberDTO;
import java.util.List;

public interface MemberService {
    void addMember(MemberDTO memberDTO);
    List<MemberDTO> getAllMembers();
    void updateMember(MemberDTO memberDTO);
    void deleteMember(int id);
}
