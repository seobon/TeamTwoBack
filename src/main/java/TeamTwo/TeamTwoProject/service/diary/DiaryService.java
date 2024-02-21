package TeamTwo.TeamTwoProject.service.diary;

import TeamTwo.TeamTwoProject.dto.diary.DiaryDTO;
import TeamTwo.TeamTwoProject.dto.diary.DiaryUserDTO;
import TeamTwo.TeamTwoProject.dto.diary.DiaryUserReactionDTO;
import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import TeamTwo.TeamTwoProject.entity.reaction.ReactionEntity;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.repository.diary.DiaryRepository;
import TeamTwo.TeamTwoProject.repository.reaction.ReactionRepository;
import TeamTwo.TeamTwoProject.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private UserRepository userRepository;

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

    public DiaryUserReactionDTO getMyDiary(int diaryId) {
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

        DiaryEntity getMyDiaryData = diaryRepository.findByDiaryId(diaryId);
        DiaryUserReactionDTO diaryUserReactionDTO = DiaryUserReactionDTO.builder()
                .nickname(getMyDiaryData.getUser().getNickname())
                .image(getMyDiaryData.getUser().getImage())
                .diaryId(getMyDiaryData.getDiaryId())
                .diaryTitle(getMyDiaryData.getDiaryTitle())
                .diaryContent(getMyDiaryData.getDiaryContent())
                .mood(getMyDiaryData.getMood())
                .createdAt(getMyDiaryData.getCreatedAt())
                .updatedAt(getMyDiaryData.getUpdatedAt())
                .location(getMyDiaryData.getLocation())
                .weather(getMyDiaryData.getWeather())
                .isPublic(getMyDiaryData.isPublic())
                .likey(getMyDiaryLikeyData.size())
                .love(getMyDiaryLoveData.size())
                .haha(getMyDiaryHahaData.size())
                .wow(getMyDiaryWowData.size())
                .sad(getMyDiarySadData.size())
                .angry(getMyDiaryAngryData.size())
                .msg("Get My Diary Success")
                .build();
        return diaryUserReactionDTO;
    }

    public DiaryEntity postDiary(DiaryDTO diaryDTO) {
        UserEntity userEntity = userRepository.findById(diaryDTO.getId());
        DiaryEntity postDiaryData = DiaryEntity.builder()
                .diaryTitle(diaryDTO.getDiaryTitle())
                .diaryContent(diaryDTO.getDiaryContent())
                .mood(diaryDTO.getMood())
                .location(diaryDTO.getLocation())
                .isPublic(diaryDTO.isPublic())
                .user(userEntity)
                .build();
        return diaryRepository.save(postDiaryData);
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

    public DiaryUserReactionDTO getOneDiary(int diaryId) {
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

        DiaryEntity getOneDiaryData = diaryRepository.findByDiaryId(diaryId);

        DiaryUserReactionDTO diaryUserReactionDTO = DiaryUserReactionDTO.builder()
                .nickname(getOneDiaryData.getUser().getNickname())
                .image(getOneDiaryData.getUser().getImage())
                .diaryId(getOneDiaryData.getDiaryId())
                .diaryTitle(getOneDiaryData.getDiaryTitle())
                .diaryContent(getOneDiaryData.getDiaryContent())
                .likey(getOneDiaryLikeyData.size())
                .love(getOneDiaryLoveData.size())
                .haha(getOneDiaryHahaData.size())
                .wow(getOneDiaryWowData.size())
                .sad(getOneDiarySadData.size())
                .angry(getOneDiaryAngryData.size())
                .msg("Get One Diary Success")
                .build();

        return diaryUserReactionDTO;
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

    public DiaryEntity patchDiary(DiaryDTO diaryDTO) {
        DiaryEntity OriginDiaryData = diaryRepository.findByDiaryId(diaryDTO.getDiaryId());

        DiaryEntity patchDiaryData = DiaryEntity.builder()
                .user(OriginDiaryData.getUser())
                .diaryId(OriginDiaryData.getDiaryId())
                .diaryTitle(Optional.ofNullable(diaryDTO.getDiaryTitle()).orElse(OriginDiaryData.getDiaryTitle()))
                .diaryContent(Optional.ofNullable(diaryDTO.getDiaryContent()).orElse(OriginDiaryData.getDiaryContent()))
                .mood(Optional.ofNullable(diaryDTO.getMood()).orElse(OriginDiaryData.getMood()))
                .createdAt(OriginDiaryData.getCreatedAt())
                .location(Optional.ofNullable(diaryDTO.getLocation()).orElse(OriginDiaryData.getLocation()))
                .weather(Optional.ofNullable(diaryDTO.getWeather()).orElse(OriginDiaryData.getWeather()))
                .isPublic(Optional.of(diaryDTO.isPublic()).orElse(OriginDiaryData.isPublic()))
                .build();
        return diaryRepository.save(patchDiaryData);
    }

    public boolean deleteDiary(DiaryDTO diaryDTO) {
        DiaryEntity DiaryData = diaryRepository.findByDiaryId(diaryDTO.getDiaryId());
        diaryRepository.delete(DiaryData);

        return true;
    }
}