package TeamTwo.TeamTwoProject.entity.reaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "diary")
public class ReactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reactionId")
    private int reactionId;

    @Column(name = "diaryId", nullable = false)
    private int diaryId;

    @Column(name = "id", nullable = false)
    private int id;

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
