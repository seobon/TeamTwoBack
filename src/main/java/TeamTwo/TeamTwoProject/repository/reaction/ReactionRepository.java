package TeamTwo.TeamTwoProject.repository.reaction;

import TeamTwo.TeamTwoProject.entity.reaction.ReactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<ReactionEntity, Integer> {
    // getMyDiary & getOneDiary & search
    List<ReactionEntity> findByLikeyAndDiary_diaryId(boolean likey, Integer diaryId);
    List<ReactionEntity> findByLoveAndDiary_diaryId(boolean love, Integer diaryId);
    List<ReactionEntity> findByHahaAndDiary_diaryId(boolean haha, Integer diaryId);
    List<ReactionEntity> findByWowAndDiary_diaryId(boolean wow, Integer diaryId);
    List<ReactionEntity> findBySadAndDiary_diaryId(boolean sad, Integer diaryId);
    List<ReactionEntity> findByAngryAndDiary_diaryId(boolean angry, Integer diaryId);

    // reaction
    ReactionEntity findByDiary_diaryIdAndUser_id(Integer diaryId, Integer id);
}