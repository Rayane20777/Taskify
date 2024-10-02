package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;
import dto.MemberDTO;
import models.Member;
import repositories.MemberRepository;

public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberDAO memberDAO, MemberRepository memberRepository) {
        this.memberDAO = memberDAO;
        this.memberRepository = memberRepository;
    }

    @Override
    public Member addMember(MemberDTO memberDTO) throws SQLException {
        Member member = new Member(memberDTO.getId(), memberDTO.getFname(), memberDTO.getLname(),
                memberDTO.getEmail(), memberDTO.getRole(), memberDTO.getTeamId());
        return memberDAO.addMember(member);
    }

    @Override
    public Member updateMember(MemberDTO memberDTO) throws SQLException {
        if (memberDAO.getMemberById(memberDTO.getId()) == null) {
            return null;
        } else {
            Member member = new Member(memberDTO.getId(), memberDTO.getFname(), memberDTO.getLname(),
                    memberDTO.getEmail(), memberDTO.getRole(), memberDTO.getTeamId());
            return memberDAO.updateMember(member);
        }
    }

    @Override
    public void deleteMember(int id) throws SQLException {
        if (memberDAO.getMemberById(id) == null) {
            throw new SQLException("Member not found with ID: " + id);
        }
        memberDAO.deleteMember(id);
    }

    @Override
    public List<Member> getAllMembers() throws SQLException {
        return memberDAO.getAllMembers();
    }

    @Override
    public List<Member> searchMembers(String name) throws SQLException {
        return memberRepository.searchMembers(name);
    }
}
