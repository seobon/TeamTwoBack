package TeamTwo.TeamTwoProject.repository.user;

import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUserid(String userid);

    UserEntity findByEmail(String email);
    UserEntity findById(int id);

    UserEntity findByNickname(String nickname);
}
