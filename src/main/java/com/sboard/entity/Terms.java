package com.sboard.entity;

import com.sboard.dto.TermsDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "terms")
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String terms;
    private String privacy;

    // 변환 메서드
    public TermsDTO toDTO() {
        return TermsDTO.builder()
                .seq(seq)
                .terms(terms)
                .privacy(privacy)
                .build();
    }
}
