package TeamTwo.TeamTwoProject.repository.diary;

import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {
    // postDiary

    // getCalendar
    List<DiaryEntity> findByCreatedAtContainingAndUser_id(String createdAt, Integer id);

    // getMyDiary & getOneDiary
    List<DiaryEntity> findByDiaryId(Integer diaryId);

    // postDiary


    // getEveryDiary
    List<DiaryEntity> findByIsPublic(boolean isPublic);

    // search
    List<DiaryEntity> findByDiaryTitleContainingOrDiaryContentContaining(String diaryTitle, String diaryContent);
}