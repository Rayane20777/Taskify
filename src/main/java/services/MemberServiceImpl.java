package services;

import dao.MemberDAO;
import dto.MemberDTO;
import models.Member;
import repositories.MemberRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements MemberService{
    private MemberDAO memberDAO;
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberDAO memberDAO, MemberRepository memberRepository) {
        this.memberDAO = memberDAO;
        this.memberRepository = memberRepository;
    }

    @Override
    public void addMember(MemberDTO memberDTO) throws SQLException{
        Member member = new Member(memberDTO.getId(), memberDTO.getFname(), memberDTO.getLname(),
                memberDTO.getEmail(), memberDTO.getRole(), memberDTO.getTeamId());
        memberDAO.addMember(member);
    }
    
    @Override
    public List<MemberDTO> getAllMembers() throws SQLException{
        List<Member> members = memberDAO.getAllMembers();
        List<MemberDTO> memberDTOs = new ArrayList<>();
        for (Member member : members) {
            memberDTOs.add(new MemberDTO(member.getId(), member.getFname(), member.getLname(),
                    member.getEmail(), member.getRole(), member.getTeamId()));
        }
        return memberDTOs;
    }
    
    @Override
    public List<MemberDTO> searchMembersByName(String name) {
        List<Member> members = memberRepository.searchMembersByName(name);
        List<MemberDTO> memberDTOs = new ArrayList<>();
        for (Member member : members) {
            memberDTOs.add(new MemberDTO(member.getId(), member.getFname(), member.getLname(),
                    member.getEmail(), member.getRole(), member.getTeamId()));
        }
        return memberDTOs;
    }
    
    @Override
    public void updateMember(MemberDTO memberDTO) throws SQLException{
        Member member = new Member(memberDTO.getId(), memberDTO.getFname(), memberDTO.getLname(),
                memberDTO.getEmail(), memberDTO.getRole(), memberDTO.getTeamId());
        memberDAO.updateMember(member);
    }

    @Override
    public void deleteMember(int id) throws SQLException{
        memberDAO.deleteMember(id);
    }


}
