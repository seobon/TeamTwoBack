package TeamTwo.TeamTwoProject.repository.user;

import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {


}
