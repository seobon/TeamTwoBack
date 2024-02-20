package TeamTwo.TeamTwoProject.service.reaction;

import TeamTwo.TeamTwoProject.dto.reaction.ReactionDTO;
import TeamTwo.TeamTwoProject.entity.reaction.ReactionEntity;
import TeamTwo.TeamTwoProject.repository.reaction.ReactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    public ReactionEntity reaction(ReactionDTO reactionDTO) {
        ReactionEntity OriginReactionData = reactionRepository.findByDiary_diaryId(reactionDTO.getDiaryId());

//        ReactionEntity reactionData = ReactionEntity.builder()
//                .diary(OriginReactionData.getDiary())
//                .user(OriginReactionData.getUser())
//                .reactionId(OriginReactionData.getReactionId())
//                .likey(Optional.ofNullable(reactionDTO.isLikey()).orElse(OriginReactionData.isLikey()))
//                .love(Optional.ofNullable(reactionDTO.isLove()).orElse(OriginReactionData.isLove()))
//                .haha(Optional.ofNullable(reactionDTO.isHaha()).orElse(OriginReactionData.isHaha()))
//                .wow(Optional.ofNullable(reactionDTO.isWow()).orElse(OriginReactionData.isWow()))
//                .sad(Optional.ofNullable(reactionDTO.isSad()).orElse(OriginReactionData.isSad()))
//                .angry(Optional.ofNullable(reactionDTO.isAngry()).orElse(OriginReactionData.isAngry()))
//                .build();
//        return reactionRepository.save(reactionData);
        return OriginReactionData;
    }
}