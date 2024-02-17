package TeamTwo.TeamTwoProject.repository.diary;

import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {
    // getCalendar
    List<DiaryEntity> findByUserIdAndCreatedAtLike(Integer userId, String createdAt);

    // getMyDiary & getOneDiary
    List<DiaryEntity> findByDiaryId(Integer diaryId);

    // getEveryDiary
    List<DiaryEntity> findByIsPublic(boolean isPublic);

    // search
    List<DiaryEntity> findByDiaryTitleLikeOrDiaryContentLike(String diaryTitle, String diaryContent);
}