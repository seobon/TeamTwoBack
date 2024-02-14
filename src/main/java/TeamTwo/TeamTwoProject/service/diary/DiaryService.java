package TeamTwo.TeamTwoProject.service.diary;

import TeamTwo.TeamTwoProject.repository.diary.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;
}
