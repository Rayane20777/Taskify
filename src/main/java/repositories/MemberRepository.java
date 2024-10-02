package repositories;

import java.util.List;

import models.Member;

public interface MemberRepository {
    List<Member> searchMembersByName(String name);  
}
