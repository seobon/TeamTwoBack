//package TeamTwo.TeamTwoProject.repository.diary;
//
//import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@Repository
//public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {
//    // getCalendar
//    List<DiaryEntity> findByIdAndCreatedAtContaining(int id, Timestamp createdAt);
//    // getMyDiary
//    List<DiaryEntity> findByIdAndCreatedAtStartingWith(int id, Timestamp createdAt);
//    // getEveryDiary
//    List<DiaryEntity> findByIsPublic(boolean isPublic);
//    // search
//    List<DiaryEntity> findByDiaryTitleContainingAndDiaryContentContaining(String diaryTitle, String diaryContent);
//}
