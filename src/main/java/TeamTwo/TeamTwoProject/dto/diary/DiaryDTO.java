package TeamTwo.TeamTwoProject.dto.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class DiaryDTO {
    private int diaryId;
    private String id;
    private String diaryTitle;
    private String diaryContent;
    private String mood;
    private String createdAt;
    private String updatedAt;
    private String location;
    private int weather;
    private boolean isPublic;
}
