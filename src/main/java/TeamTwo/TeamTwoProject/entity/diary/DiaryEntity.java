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
    @Column(name = "diary_id", nullable = true)
    private int diary_id;

//    @Column(name = "user_id", nullable = false)
//    private String user_id;

    @Column(name = "diary_title", nullable = false)
    private String diary_title;

//    @Column(name = "diary_content", nullable = false)
//    private String diary_content;

    @Column(name = "diary_file", nullable = false)
    private String diary_file;

//    @Column(name = "diary_create", nullable = false)
//    private String diary_create;
}
