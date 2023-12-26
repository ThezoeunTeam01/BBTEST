package com.example.bbtest.controller;

import com.example.bbtest.check.CheckValidator;
import com.example.bbtest.dto.MemberDTO;
import com.example.bbtest.model.MemberEntity;
import com.example.bbtest.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("Member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    CheckValidator checkValidator;

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberDTO dto) {

        try{
            checkValidator.validate(dto);
            log.info("----------dto 정보 확인-----------"+dto);

            MemberEntity member = memberService.createMember(dto);

            return ResponseEntity.ok().body(member);
        }catch (Exception e) {
            String error = e.getMessage();

            return ResponseEntity.badRequest().body(error);
        }
    }
    @GetMapping
    public ResponseEntity<?> retrieveMember() {

        try{
            String id = "test3";
            MemberEntity member = memberService.retrieveMember(id);

            return ResponseEntity.ok().body(member);
        }catch (Exception e) {

            String error = e.getMessage();

            return ResponseEntity.badRequest().body(error);
        }
    }
    @PutMapping
    public ResponseEntity<?> updateMember(@RequestBody MemberDTO dto) {

        try {

            MemberEntity memberEntity = memberService.updateMember(dto);

            return ResponseEntity.ok().body(memberEntity);

        } catch (Exception e) {

            String error = e.getMessage();

            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMember(@RequestBody MemberDTO dto) {
        try{

            memberService.deleteMember(dto.getUsername());

            return ResponseEntity.ok().body("success delete");

        }catch (Exception e) {

            String error = e.getMessage();

            return ResponseEntity.badRequest().body(error);
        }
    }
}
