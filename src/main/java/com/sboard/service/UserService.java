package com.sboard.service;

import com.sboard.dto.UserDTO;
import com.sboard.entity.User;
import com.sboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    // 생성자 주입
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void insertUser(UserDTO userDTO) {

        String encoded = passwordEncoder.encode(userDTO.getPass());
        userDTO.setPass(encoded);
        // DTO를 Entity로 변환
        User entity = userDTO.toEntity();
        // Entity 저장 (데이터베이스 Insert)
        userRepository.save(entity);

        //return saveUser.toDTO();

    }


    public UserDTO selectUserByType(String type,String value){
        if(type.equals("uid")){
            Optional<User> user = userRepository.findById(value);
            if(user.isPresent()){
                UserDTO userDTO = user.get().toDTO();
                return userDTO;
            }
        }else if(type.equals("nick")){
            Optional<User> user = userRepository.findByNick("value");
            if(user.isPresent()){
                UserDTO userDTO = user.get().toDTO();
                return userDTO;
            }
        }else if(type.equals("email")){
            Optional<User> user = userRepository.findByEmail(value);
            if(user.isPresent()){
                UserDTO userDTO = user.get().toDTO();
                return userDTO;
            }

        }

        return null;

    }


    public UserDTO selectUser(String Uid) {

        Optional<User> opt = userRepository.findById(Uid);

        // Entity 존재 여부 화인
        if(opt.isPresent()) {
            // Optional 타입으로 정의된 Entity 가져오기
            User user = opt.get();

            // Entity 를 DTO 로 변환해서 반환
            return user.toDTO();
        }
        return null;
    }

    public List<UserDTO> selectUsers() {
        // Entity 전체 조회
        List<User> users = userRepository.findAll();

        // List Stream (내부반복자)를 이용한 Entity 리스트를 DTO 리스트로 반환
        List<UserDTO> UserDTOs = users
                                .stream()
                                .map(entity -> entity.toDTO())
                                .collect(Collectors.toList());
        return UserDTOs;
    }

    public void updateUser(UserDTO userDTO) {

        // Entity 존재 여부 확인
        boolean result = userRepository.existsById(userDTO.getUid());

        if(result){
            // DTO 를 Entity로 변환
            User entity = userDTO.toEntity();
            // Entity 수정 (데이터베이스 Update)
            userRepository.save(entity);
        }
    }


    public void deleteUser(String uid) {
        // Entity 삭제 (데이터베이스 Delete)
        userRepository.deleteById(uid);
    }


}
