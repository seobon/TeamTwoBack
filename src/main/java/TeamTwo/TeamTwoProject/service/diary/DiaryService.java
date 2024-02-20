package TeamTwo.TeamTwoProject.service.diary;

import TeamTwo.TeamTwoProject.dto.diary.DiaryDTO;
import TeamTwo.TeamTwoProject.dto.diary.DiaryUserDTO;
import TeamTwo.TeamTwoProject.dto.diary.DiaryUserReactionDTO;
import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import TeamTwo.TeamTwoProject.entity.reaction.ReactionEntity;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.repository.diary.DiaryRepository;
import TeamTwo.TeamTwoProject.repository.reaction.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private ReactionRepository reactionRepository;

//    public boolean postDiary() {
//    }

    public List<DiaryDTO> getCalendar(int id, String createdAt) {
        List<DiaryEntity> getCalendarData = diaryRepository.findByCreatedAtContainingAndUser_id(createdAt, id);
        List<DiaryDTO> getCalendarResult = new ArrayList<>();

        if(getCalendarData.size()!=0) {
            for (DiaryEntity CalendarData : getCalendarData) {
                DiaryDTO diaryDTO = DiaryDTO.builder()
                        .diaryId(CalendarData.getDiaryId())
                        .mood(CalendarData.getMood())
                        .createdAt(CalendarData.getCreatedAt())
                        .msg("Get Calendar Success")
                        .build();
                getCalendarResult.add(diaryDTO);
            }
        } else {
            DiaryDTO diaryDTO = DiaryDTO.builder()
                    .msg("Get Calendar Error : 조회할 다이어리가 없습니다.")
                    .build();
            getCalendarResult.add(diaryDTO);
        }
        return getCalendarResult;
    }

    public List<DiaryUserReactionDTO> getMyDiary(int diaryId) {
        boolean likey = true;
        List<ReactionEntity> getMyDiaryLikeyData = reactionRepository.findByLikeyAndDiary_diaryId(likey, diaryId);
        boolean love = true;
        List<ReactionEntity> getMyDiaryLoveData = reactionRepository.findByLoveAndDiary_diaryId(love, diaryId);
        boolean haha = true;
        List<ReactionEntity> getMyDiaryHahaData = reactionRepository.findByHahaAndDiary_diaryId(haha, diaryId);
        boolean wow = true;
        List<ReactionEntity> getMyDiaryWowData = reactionRepository.findByWowAndDiary_diaryId(wow, diaryId);
        boolean sad = true;
        List<ReactionEntity> getMyDiarySadData = reactionRepository.findBySadAndDiary_diaryId(sad, diaryId);
        boolean angry = true;
        List<ReactionEntity> getMyDiaryAngryData = reactionRepository.findByAngryAndDiary_diaryId(angry, diaryId);

        List<DiaryEntity> getMyDiaryData = diaryRepository.findByDiaryId(diaryId);
        List<DiaryUserReactionDTO> getMyDiaryResult = new ArrayList<>();
        DiaryUserReactionDTO diaryUserReactionDTO = DiaryUserReactionDTO.builder()
                .nickname(getMyDiaryData.get(0).getUser().getNickname())
                .image(getMyDiaryData.get(0).getUser().getImage())
                .diaryId(getMyDiaryData.get(0).getDiaryId())
                .diaryTitle(getMyDiaryData.get(0).getDiaryTitle())
                .diaryContent(getMyDiaryData.get(0).getDiaryContent())
                .mood(getMyDiaryData.get(0).getMood())
                .createdAt(getMyDiaryData.get(0).getCreatedAt())
                .updatedAt(getMyDiaryData.get(0).getUpdatedAt())
                .location(getMyDiaryData.get(0).getLocation())
                .weather(getMyDiaryData.get(0).getWeather())
                .isPublic(getMyDiaryData.get(0).isPublic())
                .likey(getMyDiaryLikeyData.size())
                .love(getMyDiaryLoveData.size())
                .haha(getMyDiaryHahaData.size())
                .wow(getMyDiaryWowData.size())
                .sad(getMyDiarySadData.size())
                .angry(getMyDiaryAngryData.size())
                .msg("Get My Diary Success")
                .build();
            getMyDiaryResult.add(diaryUserReactionDTO);
        return getMyDiaryResult;
    }

    public DiaryEntity postDiary(DiaryDTO diaryDTO) {
        UserEntity userEntityId = UserEntity.builder()
                .id(diaryDTO.getId())
                .build();
        DiaryEntity postDiaryData = DiaryEntity.builder()
//                .id(userEntityId)
                .diaryTitle(diaryDTO.getDiaryTitle())
                .diaryContent(diaryDTO.getDiaryContent())
                .mood(diaryDTO.getMood())
                .location(diaryDTO.getLocation())
                .isPublic(diaryDTO.isPublic())
                .build();
        return diaryRepository.save(postDiaryData);
//        return postDiaryData;
    }

    public List<DiaryUserDTO> getEveryDiary() {
        boolean isPublic = true;
        List<DiaryEntity> getEveryDiaryData = diaryRepository.findByIsPublic(isPublic);
        List<DiaryUserDTO> getEveryDiaryResult = new ArrayList<>();

        for (DiaryEntity DiaryData : getEveryDiaryData) {
            DiaryUserDTO diaryUserDTO = DiaryUserDTO.builder()
                    .nickname(DiaryData.getUser().getNickname())
                    .image(DiaryData.getUser().getImage())
                    .diaryId(DiaryData.getDiaryId())
                    .diaryTitle(DiaryData.getDiaryTitle())
                    .diaryContent(DiaryData.getDiaryContent())
                    .isPublic(DiaryData.isPublic())
                    .msg("Get Every Diary Success")
                    .build();
            getEveryDiaryResult.add(diaryUserDTO);
        }
        return getEveryDiaryResult;
    }

    public List<DiaryUserReactionDTO> getOneDiary(int diaryId) {
        boolean likey = true;
        List<ReactionEntity> getOneDiaryLikeyData = reactionRepository.findByLikeyAndDiary_diaryId(likey, diaryId);
        boolean love = true;
        List<ReactionEntity> getOneDiaryLoveData = reactionRepository.findByLikeyAndDiary_diaryId(love, diaryId);
        boolean haha = true;
        List<ReactionEntity> getOneDiaryHahaData = reactionRepository.findByHahaAndDiary_diaryId(haha, diaryId);
        boolean wow = true;
        List<ReactionEntity> getOneDiaryWowData = reactionRepository.findByWowAndDiary_diaryId(wow, diaryId);
        boolean sad = true;
        List<ReactionEntity> getOneDiarySadData = reactionRepository.findBySadAndDiary_diaryId(sad, diaryId);
        boolean angry = true;
        List<ReactionEntity> getOneDiaryAngryData = reactionRepository.findByAngryAndDiary_diaryId(angry, diaryId);

        List<DiaryEntity> getOneDiaryData = diaryRepository.findByDiaryId(diaryId);
        List<DiaryUserReactionDTO> getOneDiaryResult = new ArrayList<>();

        for (DiaryEntity DiaryData : getOneDiaryData) {
            DiaryUserReactionDTO diaryUserReactionDTO = DiaryUserReactionDTO.builder()
                    .nickname(DiaryData.getUser().getNickname())
                    .image(DiaryData.getUser().getImage())
                    .diaryId(DiaryData.getDiaryId())
                    .diaryTitle(DiaryData.getDiaryTitle())
                    .diaryContent(DiaryData.getDiaryContent())
                    .likey(getOneDiaryLikeyData.size())
                    .love(getOneDiaryLoveData.size())
                    .haha(getOneDiaryHahaData.size())
                    .wow(getOneDiaryWowData.size())
                    .sad(getOneDiarySadData.size())
                    .angry(getOneDiaryAngryData.size())
                    .msg("Get One Diary Success")
                    .build();
            getOneDiaryResult.add(diaryUserReactionDTO);
        }
        return getOneDiaryResult;
    }

    public List<DiaryUserDTO> search(String searchWord) {
        String diaryTitle = searchWord;
        String diaryContent = searchWord;

        List<DiaryEntity> searchData = diaryRepository.findByDiaryTitleContainingOrDiaryContentContaining(diaryTitle, diaryContent);
        List<DiaryUserDTO> searchResult = new ArrayList<>();

        for (DiaryEntity DiaryData : searchData) {
            DiaryUserDTO diaryUserDTO = DiaryUserDTO.builder()
                    .nickname(DiaryData.getUser().getNickname())
                    .image(DiaryData.getUser().getImage())
                    .diaryId(DiaryData.getDiaryId())
                    .diaryTitle(DiaryData.getDiaryTitle())
                    .diaryContent(DiaryData.getDiaryContent())
                    .msg("Search Success")
                    .build();
            searchResult.add(diaryUserDTO);
        }
        return searchResult;
    }

//    public boolean patchDiary() {
//    }
//
//    public boolean deleteDiary() {
//    }
//
//    public boolean reaction() {
//    }

}