package com.honeycombee.web.artdental;

import com.honeycombee.web.artdental.domain.Member;
import com.honeycombee.web.artdental.domain.TestBoard;
import com.honeycombee.web.artdental.repository.MemberRepository;
import com.honeycombee.web.artdental.repository.TestBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/**
 * Created by gizmo4u on 15. 5. 31..
 */

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private TestBoardRepository testBoardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testBoardRepository.deleteAll();
        memberRepository.deleteAll();

        Member testMember = new Member("soonystory@naver.com", "1234", "woosoon");

        memberRepository.save(testMember);

        for(int i=0;i<15;i++){
            testBoardRepository.save(new TestBoard("test"+i, "test_content"+i, false, testMember, new Date(), new Date()));
        }
    }
}
