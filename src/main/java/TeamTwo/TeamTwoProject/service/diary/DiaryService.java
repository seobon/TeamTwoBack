//package TeamTwo.TeamTwoProject.service.diary;
//
//import TeamTwo.TeamTwoProject.dto.diary.DiaryDTO;
//import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
//import TeamTwo.TeamTwoProject.repository.diary.DiaryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class DiaryService {
//    @Autowired
//    private DiaryRepository diaryRepository;
//
////    public List<DiaryDTO> getCalendar() {
////        List<DiaryEntity> getCalendarData = diaryRepository.findByIdAndCreatedAtContaining();
////        List<DiaryDTO> getCalendarResult = new ArrayList<>();
////
////        for (DiaryEntity oneCalendarData : getCalendarData) {
////            DiaryDTO diaryDTO = DiaryDTO.builder()
////                    .mood(oneCalendarData.getMood())
////                    .createdAt(oneCalendarData.getCreatedAt())
////                    .build();
////            getCalendarResult.add(diaryDTO);
////        }
////        return getCalendarResult;
////    }
////
////    public List<DiaryDTO> getMyDiary() {
////        List<DiaryEntity> getMyDiaryData = diaryRepository.findByIdAndCreatedAtStartingWith();
////        List<DiaryDTO> getMyDiaryResult = new ArrayList<>();
////
////        for (DiaryEntity oneMyDiaryData : getMyDiaryData) {
////            // 리더님 여기 유저 테이블(리액션 테이블도)의 정보를 조인해서 가져오고 싶은데 어떤 방식을 사용해야 할까요
////            // 1. DiaryRepository 에서 조인하는 기본규칙을 사용한다(그런 기본 규칙이 있는지는 모르겠습니다.)
////            // ex) DiaryRepository
////            // List<DiaryEntity> id로유저테이블에접근하는기본규칙~~~~(int id, Timestamp createdAt);
////            // 2. diaryDTO 에 userImg 등을 정의하고, UserEntity 에 접근 메소드를 추가해 가져온다.
////            // ex) List<UserEntity> getMyDiaryData = userRepository.~~~();
////            DiaryDTO diaryDTO = DiaryDTO.builder()
////                    .diaryTitle(oneMyDiaryData.getDiaryTitle())
////                    .diaryContent(oneMyDiaryData.getDiaryContent())
////                    .mood(oneMyDiaryData.getMood())
////                    .createdAt(oneMyDiaryData.getCreatedAt())
////                    .updatedAt(oneMyDiaryData.getUpdatedAt())
////                    .location(oneMyDiaryData.getLocation())
////                    .weather(oneMyDiaryData.getWeather())
////                    .build();
////            getMyDiaryResult.add(diaryDTO);
////        }
////        return getMyDiaryResult;
////    }
////
////    public List<DiaryDTO> getEveryDiary() {
////        List<DiaryEntity> getEveryDiaryData = diaryRepository.findByIsPublic();
////        List<DiaryDTO> getEveryDiaryResult = new ArrayList<>();
////
////        for (DiaryEntity oneDiaryData : getEveryDiaryData) {
////            DiaryDTO diaryDTO = DiaryDTO.builder()
////                    .mood(oneDiaryData.getMood())
////                    .createdAt(oneDiaryData.getCreatedAt())
////                    .build();
////            getEveryDiaryResult.add(diaryDTO);
////        }
////        return getEveryDiaryResult;
////    }
////
////    public List<DiaryDTO> search() {
////        List<DiaryEntity> getCalendarData = diaryRepository.findByDiaryTitleContainingAndDiaryContentContaining();
////        List<DiaryDTO> getCalendarResult = new ArrayList<>();
////
////        for (DiaryEntity oneCalendarData : getCalendarData) {
////            DiaryDTO diaryDTO = DiaryDTO.builder()
////                    .mood(oneCalendarData.getMood())
////                    .createdAt(oneCalendarData.getCreatedAt())
////                    .build();
////            getCalendarResult.add(diaryDTO);
////        }
////        return getCalendarResult;
////    }
//
////    public boolean postDiary() {
////    }
////
////    public boolean patchDiary() {
////    }
////
////    public boolean deleteDiary() {
////    }
////
////    public boolean reaction() {
////    }
//
//}
