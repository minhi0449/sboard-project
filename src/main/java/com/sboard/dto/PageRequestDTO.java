package com.sboard.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PageRequestDTO {

    @Builder.Default
    private int no = 1;

    @Builder.Default
    private int pg = 1;

    @Builder.Default
    private int size = 10;

    @Builder.Default
    private String cate = "free";

    private String type;
    private String keyword;


    public Pageable getPageable(String sort) { // page 처리하기 위해서 이 DTO 써야 됨
        return PageRequest.of(this.pg - 1, this.size, Sort.by(sort).descending());

    }

}
