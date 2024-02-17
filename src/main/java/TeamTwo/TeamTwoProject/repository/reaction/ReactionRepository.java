package TeamTwo.TeamTwoProject.repository.reaction;

import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import TeamTwo.TeamTwoProject.entity.reaction.ReactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<DiaryEntity, Integer> {
    // getMyDiary & getOneDiary & search
    List<ReactionEntity> findByDiaryId(Integer diaryId);
}
