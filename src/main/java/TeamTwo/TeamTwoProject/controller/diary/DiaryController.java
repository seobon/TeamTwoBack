package TeamTwo.TeamTwoProject.controller.diary;

import TeamTwo.TeamTwoProject.dto.diary.DiaryDTO;
import TeamTwo.TeamTwoProject.dto.diary.DiaryUserDTO;
import TeamTwo.TeamTwoProject.dto.diary.DiaryUserReactionDTO;
import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.service.diary.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    @Autowired


    DiaryService diaryService;

    @GetMapping("/getCalendar")
    public List<DiaryDTO> getCalendar(@RequestParam int id, @RequestParam String month){
        String createdAt = "-" + month + "-";
        List<DiaryDTO> result = diaryService.getCalendar(id, createdAt);
        return result;
    }

    @GetMapping("/getMyDiary")
    public List<DiaryUserReactionDTO> getMyDiary(@RequestParam int diaryId){
        List<DiaryUserReactionDTO> result = diaryService.getMyDiary(diaryId);
//        if (result == null) {
//            throw new RuntimeException("실패");
//        예외처리 필요
//        }
        return result;
    }

    @PostMapping("/postDiary")
    public DiaryEntity postDiary(@RequestBody DiaryDTO diaryDTO){
        DiaryEntity result = diaryService.postDiary(diaryDTO);
        return result;
    }

    @GetMapping("/getEveryDiary")
    public List<DiaryUserDTO> getEveryDiary(){
        List<DiaryUserDTO> result = diaryService.getEveryDiary();
        return result;
    }

    @GetMapping("/getOneDiary")
    public List<DiaryUserReactionDTO> getOneDiary(@RequestParam int diaryId){
        List<DiaryUserReactionDTO> result = diaryService.getOneDiary(diaryId);
        return result;
    }

    @GetMapping("/search")
    public List<DiaryUserDTO> search(String searchWord){
        List<DiaryUserDTO> result = diaryService.search(searchWord);
        return result;
    }
}
