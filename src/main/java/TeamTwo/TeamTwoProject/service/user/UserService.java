package TeamTwo.TeamTwoProject.service.user;

import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Optional;
import java.lang.Exception;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private SecureRandom random = new SecureRandom();

    // 회원가입
    public UserEntity signup(UserEntity userEntity) {
        if (userEntity == null) {
            throw new RuntimeException("entity null");
        }

        UserEntity existingUser = userRepository.findByUserid(userEntity.getUserid());
        if (existingUser != null) {
            throw new RuntimeException("아이디가 이미 존재합니다");
        }

        existingUser = userRepository.findByNickname(userEntity.getNickname());
        if (existingUser != null) {
            throw new RuntimeException("닉네임이 이미 존재합니다");
        }

        String salt = new BigInteger(130, random).toString(32);
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword() + salt);

        UserEntity user = UserEntity.builder()
                .userid(userEntity.getUserid())
                .password(encodedPassword)
                .salt(salt)
                .email(userEntity.getEmail())
                .nickname(userEntity.getNickname())
                .image(userEntity.getImage())
                .build();
        return userRepository.save(user);
    }
    // 로그인
    public UserEntity login(String userid, String password) {
        UserEntity user = userRepository.findByUserid(userid);

        if (user != null && passwordEncoder.matches(password + user.getSalt(), user.getPassword())) {
            return user;
        }
        return null;
    }

    // 이미지 저장
    public void saveImage(int id, MultipartFile image) throws Exception {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다."));

        // 이미지 파일을 저장할 디렉토리 ( 각자의 경로로 수정해야함)
        Path uploadDir = Paths.get("/Users/kyoungdolee/Desktop/TeamTwoBack/src/main/resources/static/images");

        // 디렉토리가 없으면 생성
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String imageName = saveImageFile(image);  // 이미지 파일을 저장하고 이미지 이름을 반환하는 메소드

        user = user.toBuilder().image(imageName).build();
        userRepository.save(user);
    }

    // 이미지 파일체크
    public String saveImageFile(MultipartFile image) throws Exception {
        // 파일이 비어있는지 확인
        if (image.isEmpty()) {
            throw new Exception("파일이 비어있습니다.");
        }

        // 파일 크기가 5MB 이하인지 확인
        if (image.getSize() > 5 * 1024 * 1024) { // 5MB
            throw new Exception("파일 크기가 너무 큽니다. 5MB 이하의 파일만 업로드 가능합니다.");
        }

        // 파일이 이미지인지 확인
        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new Exception("이미지 파일만 업로드 가능합니다.");
        }

        // 이미지 파일을 저장할 디렉토리
        Path uploadDir = Paths.get("images");

        // 디렉토리가 없으면 생성
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // 파일 이름 (원본 파일 이름에 UUID를 추가)
        String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

        // 파일을 디렉토리에 저장
        Path filePath = uploadDir.resolve(filename);
        image.transferTo(filePath.toFile());

        return filename;
    }

    // 회원 정보 받아오기
    public UserEntity getUser(String userId) {
        try {
            return userRepository.findByUserid(userId);
        } catch (Exception e) {
            return null;
        }

    }

    //회원 수정
    public UserEntity updateUser(String userid, String currentPassword, String newPassword, Optional<String> newNickName) {

        try {
            // 현재 비밀번호가 맞으면 비밀번호 변경 가능하도록
            UserEntity user = userRepository.findByUserid(userid);

            if(passwordEncoder.matches(currentPassword + user.getSalt(), user.getPassword())) {
                String newSalt = new BigInteger(130, random).toString(32);
                String newEncodedPassword = passwordEncoder.encode(newPassword + newSalt);

                UserEntity updateUser = UserEntity.builder()
                        .id(user.getId())
                        .userid(user.getUserid())
                        .password(newEncodedPassword)
                        .salt(newSalt)
                        .email(user.getEmail())
                        .nickname(newNickName.orElse(user.getNickname())) // 닉네임이 변경이 없을 경우도 있으니까
                        .image(user.getImage())
                        .build();
                System.out.println(updateUser);
                return userRepository.save(updateUser);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // 회원 삭제
    public void deleteUser(String userid, String currentPassword) {
        try {
            UserEntity user = userRepository.findByUserid(userid);

            if (user != null && passwordEncoder.matches(currentPassword + user.getSalt(), user.getPassword())) {
                userRepository.delete(user);
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("사용자 삭제 중 오류가 발생했습니다.");
        }
    }
}