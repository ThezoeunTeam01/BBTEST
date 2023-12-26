package com.example.bbtest.service;

import com.example.bbtest.check.CheckValidator;
import com.example.bbtest.dto.MemberDTO;
import com.example.bbtest.model.MemberEntity;
import com.example.bbtest.persistence.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@Log4j2
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CheckValidator checkValidator;

    public MemberEntity createMember(MemberDTO dto) {

        checkValidator.validate(dto);

        MemberEntity memberEntity = MemberDTO.memberEntity(dto);
        memberEntity.setId(null);

        return memberRepository.save(memberEntity);
    }

    public MemberEntity retrieveMember(String id) {

        MemberEntity member = memberRepository.findByUsername(id).orElse(null);

        checkValidator.validate(member);

        return member;
    }

    public MemberEntity updateMember(MemberDTO dto) {
        System.out.println("--------dto-------"+dto);
        MemberEntity memberEntity = memberRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new RuntimeException("null"))
        System.out.println(memberEntity);

        memberEntity.setPassword(dto.getPassword());
        memberEntity.setEmail(dto.getEmail());
        memberEntity.setAge(dto.getAge());
        memberEntity.setGender(dto.getGender());
        memberEntity.setSns(dto.getSns());
        memberEntity.setImg(dto.getImg());

        checkValidator.validate(memberEntity);

        return memberRepository.save(memberEntity);
    }

    public void deleteMember(String username) {
        System.out.println("---------delete user name -----------"+username);
        MemberEntity memberEntity = memberRepository.findByUsername(username).orElse(null);
        System.out.println("----------delete entitiy-------"+memberEntity);

        checkValidator.validate(memberEntity);

        memberRepository.delete(memberEntity);
    }

}
