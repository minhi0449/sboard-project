package com.sboard.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {
    // date transfer object
    // service 로 넘기는 거 클라이언트랑 가까워
    // 엔티티를 따라간다고 생각하면 좋아
    // 여기서 놓쳐선 안될게 폼 데이터를 수신한다는 것
    // 폼에 데이터 file1, file2 가 빠져있음 !!


    private int no;

    @Builder.Default
    private String cate = "free"; // 초기화 해주면 좋음


    private String title;
    private String content;
    private int comment;

    private List<MultipartFile> files;
    // file 전송 폼으로 바꿀 거기 떄문에 String -> Multipartfile

    @Builder.Default
    private int file = 0;

    @Builder.Default
    private int hit = 0;

    private String writer;
    private String regip;
    private String rdate;

    /*
        Entity 변환 메서드 대신 ModelMapper 사용
     */



}
