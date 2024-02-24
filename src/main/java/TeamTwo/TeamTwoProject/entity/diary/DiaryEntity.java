package TeamTwo.TeamTwoProject.entity.diary;

import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "diary")
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diaryId", nullable = false, unique = true)
    private int diaryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private UserEntity user;

    @Column(name = "diaryTitle", nullable = false)
    private String diaryTitle;

    @Column(name = "diaryContent", nullable = false)
    private String diaryContent;

    @Column(name = "mood", nullable = false)
    private String mood;

    @CreatedDate
    @Column(name = "createdAt", nullable = false)
    private String createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt")
    private String updatedAt;

    @Column(name = "currentLocation", nullable = false)
    private double[] currentLocation;

    @Column(name = "weather", nullable = false)
    private String weather;

    @Column(name = "isPublic", nullable = false)
    private boolean isPublic;
}
