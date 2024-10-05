package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.MemberDAO;
import dao.TeamDAO;
import dto.MemberDTO;
import models.Member;
import models.Team;
import repositories.MemberRepository;

public class MemberServiceImpl implements MemberService {
    private MemberDAO memberDAO;
    private TeamDAO teamDAO;
    private MemberRepository memberRepository; // Keep if needed for search functionality

    public MemberServiceImpl(MemberDAO memberDAO, TeamDAO teamDAO) {
        if (memberDAO == null || teamDAO == null) {
            throw new IllegalArgumentException("DAOs cannot be null");
        }
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
    public List<MemberDTO> getAllMembers(int page, int pageSize) throws SQLException {
        List<Member> allMembers = memberDAO.getAllMembers();
        int skipCount = (page - 1) * pageSize;  // Calculate how many records to skip

        List<MemberDTO> memberDTOs = allMembers.stream()
                .skip(skipCount)  // Skip the records not in this page
                .limit(pageSize)  // Limit the results to the page size
                .map(member -> {
					try {
						return MemberDTO.modelToDTO(member, teamDAO);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}) // Convert to DTO
                .collect(Collectors.toList());

        return memberDTOs;
    }



    @Override
    public List<Team> getAllTeams() throws SQLException {
        // Implement the method to get all teams from teamDAO
        return teamDAO.getAllTeams();
    }

    @Override
    public List<Member> searchMembers(String name) throws SQLException {
        return memberRepository.searchMembers(name);
    }
    
    @Override
    public int getTotalMemberCount() throws SQLException {
        return memberDAO.getTotalCount();
    }
}
