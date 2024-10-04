package models;

import models.enums.Role;

public class Member {
	private int id;
	private String fname;
	private String lname;
	private String email;
	private Role role;
	private int team_id;
	

	
	public Member() {
		
	}
	
	public Member(int id, String fname, String lname, String email, Role role, int team_id) {
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

	@Override
	public String toString() {
		return "Member{" +
				"id=" + id +
				", fname='" + fname + '\'' +
				", lname='" + lname + '\'' +
				", email='" + email + '\'' +
				", role=" + role +
				", team_id=" + team_id +
				'}';
	}
}
	
	


