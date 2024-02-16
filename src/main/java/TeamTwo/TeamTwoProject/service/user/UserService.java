package TeamTwo.TeamTwoProject.service.user;

import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
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
}