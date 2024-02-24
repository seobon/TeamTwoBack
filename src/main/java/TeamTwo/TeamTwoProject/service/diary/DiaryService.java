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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
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

    // 다이어리를 작성한 날짜 조회 (캘린더 정보)
    public List<DiaryDTO> getCalendar(int id, String createdAt) {
        List<DiaryEntity> getCalendarData = diaryRepository.findByCreatedAtContainingAndUser_id(createdAt, id);
        List<DiaryDTO> getCalendarResult = new ArrayList<>();

        if (!getCalendarData.isEmpty()) {
            for (DiaryEntity CalendarData : getCalendarData) {
                DiaryDTO diaryDTO = DiaryDTO.builder()
                        .diaryId(CalendarData.getDiaryId())
                        .mood(CalendarData.getMood())
                        .createdAt(CalendarData.getCreatedAt())
                        .msg("Get Calendar Success : 캘린더 정보 조회를 성공했습니다.")
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

    // 내 다이어리 조회
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

        if (getMyDiaryData != null) {
            DiaryUserReactionDTO diaryUserReactionDTO = DiaryUserReactionDTO.builder()
                    .nickname(getMyDiaryData.getUser().getNickname())
                    .image(getMyDiaryData.getUser().getImage())
                    .diaryId(getMyDiaryData.getDiaryId())
                    .diaryTitle(getMyDiaryData.getDiaryTitle())
                    .diaryContent(getMyDiaryData.getDiaryContent())
                    .mood(getMyDiaryData.getMood())
                    .createdAt(getMyDiaryData.getCreatedAt())
                    .updatedAt(getMyDiaryData.getUpdatedAt())
                    .currentLocation(getMyDiaryData.getCurrentLocation())
                    .weather(getMyDiaryData.getWeather())
                    .isPublic(getMyDiaryData.isPublic())
                    .likey(getMyDiaryLikeyData.size())
                    .love(getMyDiaryLoveData.size())
                    .haha(getMyDiaryHahaData.size())
                    .wow(getMyDiaryWowData.size())
                    .sad(getMyDiarySadData.size())
                    .angry(getMyDiaryAngryData.size())
                    .msg("Get My Diary Success : 다이어리 조회를 성공했습니다.")
                    .build();
            return diaryUserReactionDTO;
        } else {
            DiaryUserReactionDTO diaryUserReactionDTO = DiaryUserReactionDTO.builder()
                    .msg("Get My Diary Error : 다이어리 조회를 실패했습니다.")
                    .build();
            return diaryUserReactionDTO;
        }
    }

    // 다이어리 작성
    public DiaryEntity postDiary(DiaryDTO diaryDTO) {
        if (diaryDTO.getDiaryTitle() == null) {
            throw new RuntimeException("Post Diary Error : 다이어리의 제목을 작성해주세요.");
        }

        if (diaryDTO.getDiaryContent() == null) {
            throw new RuntimeException("Post Diary Error : 다이어리의 내용을 작성해주세요.");
        }

        if (diaryDTO.getMood() == null) {
            throw new RuntimeException("Post Diary Error : 오늘의 기분을 입력해주세요.");
        }

        if (diaryDTO.getCurrentLocation() == null) {
            throw new RuntimeException("Post Diary Error : 현재 위치정보를 가져오지 못했습니다.");
        }

        UserEntity userData = userRepository.findById(diaryDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        DiaryEntity postDiaryData = DiaryEntity.builder()
                .user(userData)
                .diaryTitle(diaryDTO.getDiaryTitle())
                .diaryContent(diaryDTO.getDiaryContent())
                .mood(diaryDTO.getMood())
                .currentLocation(diaryDTO.getCurrentLocation())
                .isPublic(diaryDTO.isPublic())
                .build();
        return diaryRepository.save(postDiaryData);
    }

    // 공개 다이어리 모두 조회
    public List<DiaryUserDTO> getEveryDiary(Integer page) {
        boolean isPublic = true;
        List<DiaryUserDTO> getEveryDiaryResult = new ArrayList<>();

        if (page == null) {
            List<DiaryEntity> getEveryDiaryData;
            getEveryDiaryData = diaryRepository.findByIsPublic(isPublic);

            if (!getEveryDiaryData.isEmpty()) {
                for (DiaryEntity DiaryData : getEveryDiaryData) {
                    DiaryUserDTO diaryUserDTO = DiaryUserDTO.builder()
                            .nickname(DiaryData.getUser().getNickname())
                            .image(DiaryData.getUser().getImage())
                            .diaryId(DiaryData.getDiaryId())
                            .diaryTitle(DiaryData.getDiaryTitle())
                            .diaryContent(DiaryData.getDiaryContent())
                            .isPublic(DiaryData.isPublic())
                            .msg("Get Every Diary Success : 다이어리 조회를 성공했습니다.")
                            .build();
                    getEveryDiaryResult.add(diaryUserDTO);
                }
            } else {
                DiaryUserDTO diaryUserDTO = DiaryUserDTO.builder()
                        .msg("Get Every Diary Error : 다이어리 조회를 실패했습니다.")
                        .build();
                getEveryDiaryResult.add(diaryUserDTO);
            }
        } else {
            PageRequest pageRequest = PageRequest.of(page, 6, Sort.by("createdAt").descending());
            Page<DiaryEntity> getEveryDiaryPageData = diaryRepository.findByIsPublic(isPublic, pageRequest);
            List<DiaryEntity> getEveryDiaryData = getEveryDiaryPageData.getContent();

            if (!getEveryDiaryPageData.getContent().isEmpty()) {
                for (DiaryEntity DiaryData : getEveryDiaryData) {
                    DiaryUserDTO diaryUserDTO = DiaryUserDTO.builder()
                            .nickname(DiaryData.getUser().getNickname())
                            .image(DiaryData.getUser().getImage())
                            .diaryId(DiaryData.getDiaryId())
                            .diaryTitle(DiaryData.getDiaryTitle())
                            .diaryContent(DiaryData.getDiaryContent())
                            .isPublic(DiaryData.isPublic())
                            .pageCount(getEveryDiaryPageData.getTotalPages())
                            .msg("Get Every Diary Success : 다이어리 조회를 성공했습니다.")
                            .build();
                    getEveryDiaryResult.add(diaryUserDTO);
                }
            } else {
                DiaryUserDTO diaryUserDTO = DiaryUserDTO.builder()
                        .msg("Get Every Diary Error : 다이어리 조회를 실패했습니다.")
                        .build();
                getEveryDiaryResult.add(diaryUserDTO);
            }
        }
        return getEveryDiaryResult;
    }

    // 타인의 다이어리 상세 조회
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
        if (getOneDiaryData != null) {
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
                    .msg("Get One Diary Success : 다이어리 조회를 성공했습니다.")
                    .build();
            return diaryUserReactionDTO;
        } else {
            DiaryUserReactionDTO diaryUserReactionDTO = DiaryUserReactionDTO.builder()
                    .msg("Get One Diary Error : 다이어리 조회를 실패했습니다.")
                    .build();
            return diaryUserReactionDTO;
        }
    }

    // 검색
    public List<DiaryUserDTO> search(String searchWord) {
        String diaryTitle = searchWord;
        String diaryContent = searchWord;

        List<DiaryEntity> searchData = diaryRepository.findByDiaryTitleContainingOrDiaryContentContaining(diaryTitle, diaryContent);
        List<DiaryUserDTO> searchResult = new ArrayList<>();

        if (!searchData.isEmpty()) {
            for (DiaryEntity DiaryData : searchData) {
                DiaryUserDTO diaryUserDTO = DiaryUserDTO.builder()
                        .nickname(DiaryData.getUser().getNickname())
                        .image(DiaryData.getUser().getImage())
                        .diaryId(DiaryData.getDiaryId())
                        .diaryTitle(DiaryData.getDiaryTitle())
                        .diaryContent(DiaryData.getDiaryContent())
                        .msg("Search Success : 검색을 성공했습니다.")
                        .build();
                searchResult.add(diaryUserDTO);
            }
        } else {
            DiaryUserDTO diaryUserDTO = DiaryUserDTO.builder()
                    .msg("Get Every Diary Error : 검색 결과가 없습니다.")
                    .build();
            searchResult.add(diaryUserDTO);
        }
        return searchResult;
    }

    // 다이어리 수정
    public DiaryEntity patchDiary(DiaryDTO diaryDTO) {
        DiaryEntity OriginDiaryData = diaryRepository.findByDiaryId(diaryDTO.getDiaryId());

        if (OriginDiaryData == null) {
            throw new RuntimeException("Patch Diary Error : 수정할 다이어리가 없습니다.");
        }

        DiaryEntity patchDiaryData = DiaryEntity.builder()
                .user(OriginDiaryData.getUser())
                .diaryId(OriginDiaryData.getDiaryId())
                .diaryTitle(Optional.ofNullable(diaryDTO.getDiaryTitle()).orElse(OriginDiaryData.getDiaryTitle()))
                .diaryContent(Optional.ofNullable(diaryDTO.getDiaryContent()).orElse(OriginDiaryData.getDiaryContent()))
                .mood(Optional.ofNullable(diaryDTO.getMood()).orElse(OriginDiaryData.getMood()))
                .createdAt(OriginDiaryData.getCreatedAt())
                .currentLocation(Optional.ofNullable(diaryDTO.getCurrentLocation()).orElse(OriginDiaryData.getCurrentLocation()))
                .weather(Optional.ofNullable(diaryDTO.getWeather()).orElse(OriginDiaryData.getWeather()))
                .isPublic(Optional.of(diaryDTO.isPublic()).orElse(OriginDiaryData.isPublic()))
                .build();

        return diaryRepository.save(patchDiaryData);
    }

    // 다이어리 삭제
    public void deleteDiary(DiaryDTO diaryDTO) {
        try {
            ReactionEntity ReactionData = reactionRepository.findByDiary_diaryId(diaryDTO.getDiaryId());
            reactionRepository.delete(ReactionData);
            DiaryEntity DiaryData = diaryRepository.findByDiaryId(diaryDTO.getDiaryId());
            diaryRepository.delete(DiaryData);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Delete Diary Error : 삭제할 다이어리가 없습니다.");
        }
    }
}