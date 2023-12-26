package com.example.bbtest.dto;


import com.example.bbtest.model.MemberEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String gender;

    @Min(value = 10, message = "Age must be at least 1")
    @NotNull
    private int age;

    @NotNull
    @Email
    private String email;
    private String sns; // 일단 넣어둠
    private String img; // 일단 넣어둠

    public MemberDTO(final MemberEntity memberEntity) {
        this.username = memberEntity.getUsername();
        this.password = memberEntity.getPassword();
        this.gender = memberEntity.getGender();
        this.age = memberEntity.getAge();
        this.email = memberEntity.getEmail();
        this.sns = memberEntity.getSns();
        this.img = memberEntity.getImg();
    }

    public static MemberEntity memberEntity(final MemberDTO memberDTO) {
        return MemberEntity.builder()
                .username(memberDTO.getUsername())
                .password(memberDTO.getPassword())
                .gender(memberDTO.getGender())
                .age(memberDTO.getAge())
                .email(memberDTO.getEmail())
                .sns(memberDTO.getSns())
                .img(memberDTO.getImg())
                .build();
    }
}
