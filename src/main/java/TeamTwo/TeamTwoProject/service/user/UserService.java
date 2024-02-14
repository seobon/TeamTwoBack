package TeamTwo.TeamTwoProject.service.user;

import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입 부분
    public UserEntity create(UserEntity userEntity) {
        // 로직 구현 할것!!!
        return userRepository.save(userEntity);
    }

    // 회원 수정
    // 회원 삭제


}
