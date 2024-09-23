package com.sboard.dto;

import com.sboard.entity.User;
import lombok.*;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String uid;
    private String pass;
    private String name;
    private String nick;
    private String email;
    private String hp;

    @Builder.Default
    private String role = "USER";

    private String zip;
    private String addr1;
    private String addr2;
    private String regip;
    private LocalDateTime regDate;
    private LocalDateTime leaveDate;



    // 변환 메서드
    public User toEntity() {
        return User.builder()
                .uid(uid)
                .pass(pass)
                .name(name)
                .nick(nick)
                .email(email)
                .hp(hp)
                .role(role)
                .zip(zip)
                .addr1(addr1)
                .addr2(addr2)
                .regip(regip)
                .regDate(regDate)
                .leaveDate(leaveDate)
                .build();
    }
}
