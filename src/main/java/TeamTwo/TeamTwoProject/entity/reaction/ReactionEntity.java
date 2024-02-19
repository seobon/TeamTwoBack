package TeamTwo.TeamTwoProject.entity.reaction;

import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "reaction")
public class ReactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reactionId")
    private int reactionId;

    @ManyToOne
    @JoinColumn(name = "diaryId", nullable = false)
//    @Column(name = "diaryId", nullable = false)
    private DiaryEntity diaryId;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
//    @Column(name = "id", nullable = false)
    private UserEntity id;

    @Column(name = "likey", nullable = false)
    private boolean likey;

    @Column(name = "love", nullable = false)
    private boolean love;

    @Column(name = "haha", nullable = false)
    private boolean haha;

    @Column(name = "wow", nullable = false)
    private boolean wow;

    @Column(name = "sad", nullable = false)
    private boolean sad;

    @Column(name = "angry", nullable = false)
    private boolean angry;
}
