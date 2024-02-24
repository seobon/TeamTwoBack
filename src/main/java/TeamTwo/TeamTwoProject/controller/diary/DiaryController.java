package TeamTwo.TeamTwoProject.controller.diary;

import TeamTwo.TeamTwoProject.dto.diary.DiaryDTO;
import TeamTwo.TeamTwoProject.dto.diary.DiaryUserDTO;
import TeamTwo.TeamTwoProject.dto.diary.DiaryUserReactionDTO;
import TeamTwo.TeamTwoProject.dto.reaction.ReactionDTO;
import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import TeamTwo.TeamTwoProject.entity.reaction.ReactionEntity;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.service.diary.DiaryService;
import TeamTwo.TeamTwoProject.service.reaction.ReactionService;
import TeamTwo.TeamTwoProject.service.CurrentLocationApi;
import TeamTwo.TeamTwoProject.service.WeatherApi;
import TeamTwo.TeamTwoProject.service.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    DiaryService diaryService;
    @Autowired
    ReactionService reactionService;

    // 다이어리를 작성한 날짜 조회 (캘린더 정보)
    @GetMapping("/getCalendar")
    public ResponseEntity<List<DiaryDTO>> getCalendar(@RequestParam int id, @RequestParam String month){
        String createdAt = month + "/";
        List<DiaryDTO> result = diaryService.getCalendar(id, createdAt);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    // 내 다이어리 조회
    @GetMapping("/getMyDiary")
    public ResponseEntity<DiaryUserReactionDTO> getMyDiary(@RequestParam int diaryId){
        DiaryUserReactionDTO result = diaryService.getMyDiary(diaryId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    // 다이어리 작성
    @PostMapping("/postDiary")
    public ResponseEntity postDiary(@RequestBody DiaryDTO diaryDTO){
        try {
            double[] location = CurrentLocationApi.getCurrentLocation();
            double latitude = location[0];
            double longitude = location[1];
            WeatherData weatherData = WeatherApi.getCurrentWeather(latitude, longitude);
            diaryDTO.setWeather(weatherData.getWeatherDescription());
            diaryDTO.setWeather(String.valueOf(weatherData.getTemperature()));
            diaryService.postDiary(diaryDTO);
            return ResponseEntity.ok().body("Post Diary Success : 다이어리 작성에 성공했습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 공개 다이어리 모두 조회
    @GetMapping("/getEveryDiary")
    public ResponseEntity<List<DiaryUserDTO>> getEveryDiary(){
        List<DiaryUserDTO> result = diaryService.getEveryDiary();
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    // 타인의 다이어리 상세 조회
    @GetMapping("/getOneDiary")
    public ResponseEntity<DiaryUserReactionDTO> getOneDiary(@RequestParam int diaryId){
        DiaryUserReactionDTO result = diaryService.getOneDiary(diaryId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    // 검색
    @GetMapping("/search")
    public ResponseEntity<List<DiaryUserDTO>> search(String searchWord){
        List<DiaryUserDTO> result = diaryService.search(searchWord);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    // 다이어리 수정
    @PatchMapping("/patchDiary")
    public ResponseEntity patchDiary(@RequestBody DiaryDTO diaryDTO){
        try {
            diaryService.patchDiary(diaryDTO);
            return ResponseEntity.ok().body("Patch Diary Success : 다이어리 수정을 성공했습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 다이어리 삭제
    @DeleteMapping("/deleteDiary")
    public ResponseEntity deleteDiary(@RequestBody DiaryDTO diaryDTO){
        try {
            diaryService.deleteDiary(diaryDTO);
            return ResponseEntity.ok("Delete Diary Success : 다이어리가 삭제되었습니다.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // 타인의 다이어리에 반응하기
    @PatchMapping("/reaction")
    public ResponseEntity reaction(@RequestBody ReactionDTO reactionDTO){
        try {
            reactionService.reaction(reactionDTO);
            return ResponseEntity.ok().body("Reaction Success : 반응 등록을 성공했습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
