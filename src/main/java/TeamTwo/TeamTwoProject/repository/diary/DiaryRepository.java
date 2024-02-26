package TeamTwo.TeamTwoProject.repository.diary;

import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {
    // getCalendar
    List<DiaryEntity> findByCreatedAtContainingAndUser_id(String createdAt, Integer id);

    // getMyDiary & getOneDiary
    DiaryEntity findByDiaryId(Integer diaryId);

    // getEveryDiary
    List<DiaryEntity> findByIsPublic(boolean isPublic);
    Page<DiaryEntity> findByIsPublic(boolean isPublic, Pageable pageRequest);

    // search
    List<DiaryEntity> findByDiaryTitleContainingOrDiaryContentContaining(String diaryTitle, String diaryContent);
    Page<DiaryEntity> findByDiaryTitleContainingOrDiaryContentContaining(String diaryTitle, String diaryContent, Pageable pageRequest);
}