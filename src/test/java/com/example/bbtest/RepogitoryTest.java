package com.example.bbtest;

import com.example.bbtest.model.MemberEntity;
import com.example.bbtest.persistence.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class RepogitoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testCreate() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            MemberEntity memberEntity = MemberEntity.builder()
                    .username("test"+i)
                    .password("test1pw")
                    .gender("test1gender")
                    .age(25)
                    .email("test1@test.com")
                    .sns("kakao")
                    .img("testimage")
                    .build();

            memberRepository.save(memberEntity);
        });
    }

    @Test
    public void tesetSelect() {

//        List<MemberEntity> member = memberRepository.findAll();

//        String username = "test10";
//
//        List<MemberEntity> memberList = memberRepository.findAllByOrderByUsernameAsc();
        List<MemberEntity> list = memberRepository.findByUsernameLike("%test7%");

        for(MemberEntity li:list ) {
            System.out.println(li);
        }

//        MemberEntity member = memberRepository.findByUsername(username);

//        for(MemberEntity mem:memberList){
//            System.out.println(mem);
//        }
//        System.out.println("---------- Optional test ----------------"+member);
    }

    @Test
    public void testUpdate() {
        Optional<MemberEntity> updateMember = memberRepository.findByUsername("test20");

        int result =  memberRepository.testUpdate("update",updateMember.get().getId());

        System.out.println("update결과 : "+result);

    }


    @Test
    public void testDelete() {
        memberRepository.deleteMemberEntityByUsername("test2");
    }

}
