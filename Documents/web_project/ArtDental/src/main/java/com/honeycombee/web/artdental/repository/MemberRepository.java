package com.honeycombee.web.artdental.repository;

import com.honeycombee.web.artdental.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by gizmo4u on 15. 6. 1..
 */
public interface MemberRepository extends MongoRepository<Member, String> {

    public Member findByEmail(String email);
}
