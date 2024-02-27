package TeamTwo.TeamTwoProject.dto.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiaryUserReactionDTO {
    private String nickname;
    private String image;

    private int diaryId;
    private int id;
    private String diaryTitle;
    private String diaryContent;
    private String mood;
    private String createdAt;
    private String updatedAt;
    private double[] currentLocation;
    private String weather;
    private boolean isPublic;

    private int likey;
    private int love;
    private int haha;
    private int wow;
    private int sad;
    private int angry;

    private String msg;

    public void setCurrentLocation(double[] currentLocation) {
        this.currentLocation = currentLocation;
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }
};

