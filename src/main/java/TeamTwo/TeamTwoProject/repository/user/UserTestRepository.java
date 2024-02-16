package TeamTwo.TeamTwoProject.repository.user;

import TeamTwo.TeamTwoProject.entity.user.UserTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestRepository extends JpaRepository<UserTestEntity, Integer> {

    UserTestEntity findByEmail(String email);
}
