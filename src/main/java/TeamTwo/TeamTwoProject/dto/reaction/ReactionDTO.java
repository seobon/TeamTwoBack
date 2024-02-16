package TeamTwo.TeamTwoProject.dto.reaction;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class ReactionDTO {
    private int reactionId;
    private int diaryId;
    private int id;
    private boolean likey;
    private boolean love;
    private boolean haha;
    private boolean wow;
    private boolean sad;
    private boolean angry;
}
