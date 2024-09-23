package com.sboard.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private String title;
    private String content;
    private int comment;
    private int file;
    private int hit;
    private String writer;
    private String regip;
    
    @CreationTimestamp // now() 라고 생각하기
    private String rdate;

    // 변환 메서드 전부 다 작성하기 귀찮음 그거 다 작성해주는 라이브러리 있음
    // 모델 메퍼 = ModelMapper
    /*
        DTO 변환 메서드 대신 ModelMapper 사용
        public ArticleDTO toDTO(){
        return ArticleDTO.builder()
                .no(no)
                .cate(cate)
                .title(title)
                .build();
        }
     */




}
