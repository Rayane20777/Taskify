package services;

import config.DatabaseConnection;
import dao.MemberDAOImpl;
import dao.TeamDAOImpl;
import dto.MemberDTO;
import models.Member;
import models.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.MemberRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    private MemberServiceImpl memberService;
    private MemberDAOImpl memberDAO;
    private TeamDAOImpl teamDAO;
    private MemberRepositoryImpl memberRepository;

    @BeforeEach
    void setUp() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        memberDAO = new MemberDAOImpl(connection);
        teamDAO = new TeamDAOImpl(connection);
        memberRepository = new MemberRepositoryImpl(connection);
        memberService = new MemberServiceImpl(memberDAO, teamDAO);
    }

    @AfterEach
    void tearDown() throws SQLException {
        List<MemberDTO> allMembers = memberService.getAllMembers(1, 10);
        for (MemberDTO member : allMembers) {
            memberService.deleteMember(member.getId());
        }
    }

    @Test
    void testAddMember() throws SQLException {
        MemberDTO newMember = new MemberDTO(1, "John", "Doe", "john.doe@example.com", "Developer", 1);
        Member addedMember = memberService.addMember(newMember);

        assertNotNull(addedMember);
        assertEquals("John", addedMember.getFname());
        assertEquals("Doe", addedMember.getLname());
        assertEquals("john.doe@example.com", addedMember.getEmail());
    }

    @Test
    void testUpdateMember() throws SQLException {
        MemberDTO newMember = new MemberDTO(1, "Jane", "Smith", "jane.smith@example.com", "Manager", 1);
        Member addedMember = memberService.addMember(newMember);

        MemberDTO updatedMemberDTO = new MemberDTO(addedMember.getId(), "Jane", "Doe", "jane.doe@example.com", "Senior Manager", 1);
        Member updatedMember = memberService.updateMember(updatedMemberDTO);

        assertNotNull(updatedMember);
        assertEquals("Jane", updatedMember.getFname());
        assertEquals("Doe", updatedMember.getLname());
        assertEquals("jane.doe@example.com", updatedMember.getEmail());
    }

    @Test
    void testDeleteMember() throws SQLException {
        MemberDTO newMember = new MemberDTO(1, "Mark", "Taylor", "mark.taylor@example.com", "Tester", 1);
        Member addedMember = memberService.addMember(newMember);

        memberService.deleteMember(addedMember.getId());

        // Ensure the member is deleted
        assertThrows(SQLException.class, () -> memberService.getAllMembers(1, 10));
    }

    @Test
    void testGetAllMembers() throws SQLException {
        MemberDTO newMember = new MemberDTO(1, "Alice", "Johnson", "alice.johnson@example.com", "Designer", 1);
        memberService.addMember(newMember);

        List<MemberDTO> members = memberService.getAllMembers(1, 10);
        assertFalse(members.isEmpty());
    }

    @Test
    void testSearchMembers() throws SQLException {
        MemberDTO newMember = new MemberDTO(1, "Bob", "Brown", "bob.brown@example.com", "Developer", 1);
        memberService.addMember(newMember);

        List<Member> foundMembers = memberService.searchMembers("Bob");
        assertFalse(foundMembers.isEmpty());
        assertEquals("Bob", foundMembers.get(0).getFname());
    }

    @Test
    void testGetTotalMemberCount() throws SQLException {
        MemberDTO newMember = new MemberDTO(1, "Emma", "Wilson", "emma.wilson@example.com", "Analyst", 1);
        memberService.addMember(newMember);

        int totalMembers = memberService.getTotalMemberCount();
        assertTrue(totalMembers > 0);
    }
}
