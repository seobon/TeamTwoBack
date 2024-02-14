package TeamTwo.TeamTwoProject.dto.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiaryDTO {
    private int diary_id;
//    private String user_id;
    private String diary_title;
//    private String diary_content;
    private String diary_file;
//    private String diary_create;
}
