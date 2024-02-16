package TeamTwo.TeamTwoProject.entity.diary;

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
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diaryId;

    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "diaryTitle", nullable = false)
    private String diaryTitle;

    @Column(name = "diaryContent", nullable = false)
    private String diaryContent;

    @Column(name = "mood", nullable = false)
    private String mood;

    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updatedAt")
    private Timestamp updatedAt;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "weather", nullable = false)
    private int weather;

    @Column(name = "isPublic", nullable = false)
    private boolean isPublic;
}
