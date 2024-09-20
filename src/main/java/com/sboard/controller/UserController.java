package com.sboard.controller;

import com.sboard.config.AppInfo;
import com.sboard.dto.UserDTO;
import com.sboard.entity.Terms;
import com.sboard.service.TermsService;
import com.sboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final TermsService termsService; // 약관을 처리하는 서비스

    private final AppInfo appInfo;


    @Value("${spring.application.version}")
    private String appVersion;

    @GetMapping("/user/login")
    public String login(Model model) {

        model.addAttribute("appVersion", appVersion);
        return "/user/login";
    }

    @GetMapping("/user/success")
    public String success(){
        return "redirect:/article/list";
    }
    @GetMapping("/user/terms")
    public String terms(Model model){
        Terms terms = termsService.terms(); // 약관 및 개인정보 처리방침 가져오기
        model.addAttribute("terms", terms); // Model에 데이터 추가
        return "/user/terms"; // 타임리프 템플릿 파일명 (terms.html)
    }

    @GetMapping("/user/register")
    public String register(){
        return "/user/register";
    }

    // 회원가입 폼 제출 시
    @PostMapping("/user/register")
    public String register(UserDTO userDTO){

       log.info(userDTO);

        userService.insertUser(userDTO);
        return "redirect:/user/login";
//        // 저장 실패 100 / 저장 성공 200
//        if(savedUser != null){
//            model.addAttribute("user", savedUser);
//            return "redirect:/user/login?success=100";
//        }
//        // 회원가입 데이터를 저장한 후, 결과에 따라 로그인 페이지로 리다이렉트
//        return "/user/login?success=200";
    }
}