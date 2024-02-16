package TeamTwo.TeamTwoProject.service.user;

import TeamTwo.TeamTwoProject.repository.user.UserTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTestService {

    @Autowired
    private UserTestRepository userTestRepository;
}
