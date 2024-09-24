package com.sboard.service;

import com.querydsl.core.Tuple;
import com.sboard.dto.Article;
import com.sboard.dto.ArticleDTO;
import com.sboard.dto.PageRequestDTO;
import com.sboard.dto.PageResponseDTO;
import com.sboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper; // 그대로 주입 받으면 됨


    public int insertArticle(ArticleDTO articleDTO) {

        // ModelMapper 를 이용해서 DTO를 Entity로 변환
        Article article = modelMapper.map(articleDTO, Article.class);
        log.info(article);

        // 저장
        Article savedArticle = articleRepository.save(article);

        // 저장된 글번호 리턴
        return savedArticle.getNo();


        /*
        // 첨부 파일 객체 (MultipartFile) 가져오기
        List<MultipartFile> files = articleDTO.getFiles();
        log.info("files size : " + files.size());

        for (MultipartFile file : files) {
            log.info("file name : " + file.getOriginalFilename());
        }

        // ModelMapper를 이용해서 DTO를 Entity로 변환
        Article article = modelMapper.map(articleDTO, Article.class);
                                    // 그 오브젝트에 대해서 일괄 처리 map
        log.info(article);
        // 저장
        articleRepository.save(article);
        */
    }

    public ArticleDTO selectArticle(int no) {
        return null;
    }


    public PageResponseDTO selectArticleForSearch(PageRequestDTO pageRequestDTO){

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Tuple> pageArticle = articleRepository.selectArticleAllForSearch(pageRequestDTO, pageable);

        // 엔티티 리스트를 DTO 리스트 변환
        List<ArticleDTO> articleList = pageArticle.stream().map(tuple -> {

            Article article = tuple.get(0, Article.class);
            String nick = tuple.get(1, String.class);
            article.setNick(nick);

            return modelMapper.map(article, ArticleDTO.class); // 그냥 바로 리턴된 거

        }).toList();

        int total = (int) pageArticle.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(articleList)
                .total(total)
                .build();
    }


    public PageResponseDTO selectArticleAll(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("no"); // 페이지 번호 받는 (sort 값)

        Page<Tuple> pageArticle = null;

        // 엔티티 조회
        if(pageRequestDTO.getKeyword() == null){
            // 일반 글목록 조회
            pageArticle = articleRepository.selectArticleAllForList(pageRequestDTO, pageable);
        }else {
            // 검색 글목록 조회
            pageArticle = articleRepository.selectArticleAllForSearch(pageRequestDTO, pageable);
        }

        // 엔티티 조회
        // List<Article> articles = articleRepository.findAll();
        // Page<Tuple> pageArticle = articleRepository.selectArticleAllForList(pageRequestDTO, pageable);
        // 어떤 자료 구조인지 먼저 봐야함 (테스트 해봐야 함)


        // 엔티티 리스트를 DTO 리스트 변환
        List<ArticleDTO> articleList = pageArticle.stream().map(tuple -> {

            Article article = tuple.get(0, Article.class);
            String nick = tuple.get(1, String.class);
            article.setNick(nick);

            return modelMapper.map(article, ArticleDTO.class); // 그냥 바로 리턴된 거

        }).toList();

        int total = (int) pageArticle.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(articleList)
                .total(total)
                .build();
    }


    public void updateArticle(ArticleDTO articleDTO) {

    }

    public void deleteArticle(int no) {

    }

}
