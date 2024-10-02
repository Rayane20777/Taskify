package dto;


import models.Member;
import models.enums.Role;


public class MemberDTO {
	
	private int id;
	private String fname;
	private String lname;
	private String email;
	private Role role;
	private int team_id;
	

	
	public MemberDTO() {
		
	}
	
	public MemberDTO(int id, String fname, String lname, String email, Role role, int team_id) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.role = role;
		this.team_id = team_id;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getTeamId() {
        return team_id;
    }

    public void setTeamId(int team_id) {
        this.team_id = team_id;
    }
    
    public Member dtoToModel() {
        return new Member(id, fname, lname, email, role, team_id);
    }
    
    public static MemberDTO modelToDTO(Member member) {
        return new MemberDTO(
            member.getId(),
            member.getFname(),
            member.getLname(),
            member.getEmail(),
            member.getRole(),
            member.getTeamId()
        );
    }
		
}
