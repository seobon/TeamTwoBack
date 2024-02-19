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
    List<DiaryEntity> findByIdAndCreatedAtContaining(UserEntity id, String createdAt);

    // getMyDiary & getOneDiary
    List<DiaryEntity> findByDiaryId(Integer diaryId);
//    @Query(nativeQuery = true, value = "SELECT d.*, u.nickname, u.email FROM diary d JOIN user u ON d.userId = u.id WHERE d.diaryId = :diaryId")
//    List<DiaryEntity> findDiaryWithUserInfo(@Param("diaryId") Integer diaryId);

    // postDiary


    // getEveryDiary
    List<DiaryEntity> findByIsPublic(boolean isPublic);

    // search
    List<DiaryEntity> findByDiaryTitleContainingOrDiaryContentContaining(String diaryTitle, String diaryContent);
}