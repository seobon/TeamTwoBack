package TeamTwo.TeamTwoProject.entity.diary;

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
@Table(name = "diary")
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diaryId", nullable = false)
    private int diaryId;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "diaryTitle", nullable = false)
    private String diaryTitle;

    @Column(name = "diaryContent", nullable = false)
    private String diaryContent;

    @Column(name = "mood", nullable = false)
    private String mood;

    @Column(name = "createdAt", nullable = false)
    private String createdAt;

    @Column(name = "updatedAt")
    private String updatedAt;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "weather", nullable = false)
    private int weather;

    @Column(name = "isPublic", nullable = false)
    private boolean isPublic;
}
