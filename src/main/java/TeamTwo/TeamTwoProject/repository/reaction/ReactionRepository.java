//package TeamTwo.TeamTwoProject.repository.reaction;
//
//import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
//import TeamTwo.TeamTwoProject.entity.reaction.ReactionEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@Repository
//public interface ReactionRepository extends JpaRepository<DiaryEntity, Integer> {
//    // getMyDiary & getEveryDiary & search
//    // 리더님 이런 식으로 다른 레포에서 가져온 diaryId를 바로 다른 레포에서 이용해서 값을 가져와 두 값을 동시에 res로 보낼 수 있을까요?
//    List<ReactionEntity> findByDiaryId(int diaryId);
//}
