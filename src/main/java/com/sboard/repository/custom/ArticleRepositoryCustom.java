package com.sboard.repository.custom;

import com.querydsl.core.Tuple;
import com.sboard.dto.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {

    public Page<Tuple> selectArticleAllForList(PageRequestDTO pageRequestDTO, Pageable pageable);
    // Paging 처리도 해야 함 -> 해주는 게 따로 있음 
    // 튜플 자료 타입 키-벨류

    public Page<Tuple> selectArticleAllForSearch(PageRequestDTO pageRequestDTO, Pageable pageable);




    
}
