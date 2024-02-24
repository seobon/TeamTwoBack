package TeamTwo.TeamTwoProject.dto.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiaryUserDTO {
    private String nickname;
    private String image;

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

    private int pageCount;
    private String msg;
}
