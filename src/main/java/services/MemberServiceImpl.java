package services;

import java.sql.SQLException;
import java.util.List;

import dao.MemberDAO;
import dao.TeamDAO;
import dto.MemberDTO;
import models.Member;
import models.Team;
import repositories.MemberRepository;

public class MemberServiceImpl implements MemberService {
    private MemberDAO memberDAO;
    private MemberRepository memberRepository;
    private TeamDAO teamDAO;

    public MemberServiceImpl(MemberDAO memberDAO, TeamDAO teamDAO) {
        this.memberDAO = memberDAO;
        this.teamDAO = teamDAO;
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
        List<Member> members = memberDAO.getAllMembers();
        for (Member member : members) {
            Team team = teamDAO.getTeamById(member.getTeamId());
            member.setTeamId(team != null ? team.getId() : 0); // Set team ID instead of team name
        }
        return members;
    }



    @Override
    public List<Member> searchMembers(String name) throws SQLException {
        return memberRepository.searchMembers(name);
    }
}
