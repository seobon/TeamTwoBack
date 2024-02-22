package TeamTwo.TeamTwoProject.service.reaction;

import TeamTwo.TeamTwoProject.dto.reaction.ReactionDTO;
import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import TeamTwo.TeamTwoProject.entity.reaction.ReactionEntity;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.repository.diary.DiaryRepository;
import TeamTwo.TeamTwoProject.repository.reaction.ReactionRepository;
import TeamTwo.TeamTwoProject.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    // 타인의 다이어리에 반응하기
    public ReactionEntity reaction(ReactionDTO reactionDTO) {
        UserEntity userData = userRepository.findById(reactionDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        DiaryEntity DiaryData = diaryRepository.findByDiaryId(reactionDTO.getDiaryId());

        if (userData == null) {
            throw new RuntimeException("Patch Diary Error : 로그인이 필요한 서비스입니다.");
        }

        if (DiaryData == null) {
            throw new RuntimeException("Patch Diary Error : 반응을 등록할 다이어리가 없습니다.");
        }

        ReactionEntity OriginReactionData = reactionRepository.findByDiary_diaryIdAndUser_id(reactionDTO.getDiaryId(), reactionDTO.getId());

        if (OriginReactionData == null) {
            ReactionEntity reactionData = ReactionEntity.builder()
                    .user(userData)
                    .diary(DiaryData)
                    .reactionId(reactionDTO.getReactionId())
                    .likey(reactionDTO.isLikey())
                    .love(reactionDTO.isLove())
                    .haha(reactionDTO.isHaha())
                    .wow(reactionDTO.isWow())
                    .sad(reactionDTO.isSad())
                    .angry(reactionDTO.isAngry())
                    .build();
            return reactionRepository.save(reactionData);
        } else {
            ReactionEntity reactionData = ReactionEntity.builder()
                    .diary(Optional.ofNullable(OriginReactionData.getDiary()).orElse(DiaryData))
                    .user(OriginReactionData.getUser())
                    .reactionId(OriginReactionData.getReactionId())
                    .likey(Optional.ofNullable(reactionDTO.isLikey()).orElse(OriginReactionData.isLikey()))
                    .love(Optional.ofNullable(reactionDTO.isLove()).orElse(OriginReactionData.isLove()))
                    .haha(Optional.ofNullable(reactionDTO.isHaha()).orElse(OriginReactionData.isHaha()))
                    .wow(Optional.ofNullable(reactionDTO.isWow()).orElse(OriginReactionData.isWow()))
                    .sad(Optional.ofNullable(reactionDTO.isSad()).orElse(OriginReactionData.isSad()))
                    .angry(Optional.ofNullable(reactionDTO.isAngry()).orElse(OriginReactionData.isAngry()))
                    .build();
            return reactionRepository.save(reactionData);
        }
    }
}