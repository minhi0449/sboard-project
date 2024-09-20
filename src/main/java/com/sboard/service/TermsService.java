package com.sboard.service;

import com.sboard.entity.Terms;
import com.sboard.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TermsService {
    // 생성자 주입
    private final TermsRepository termsRepository;

    public Terms terms() {
        return termsRepository.findAll().get(0);
    }
}
