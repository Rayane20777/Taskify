package dao;

import java.util.List;

import models.Member;

public interface MemberDAO {
    void addMember(Member member);
    List<Member> getAllMembers();
    void updateMember(Member member);
    void deleteMember(int id);
}
