package TeamTwo.TeamTwoProject.service.user;

import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private SecureRandom random = new SecureRandom();

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
    public UserEntity login(String userid, String password) {
        UserEntity user = userRepository.findByUserid(userid);

        if (user != null && passwordEncoder.matches(password + user.getSalt(), user.getPassword())) {
            return user;
        }
        return null;
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