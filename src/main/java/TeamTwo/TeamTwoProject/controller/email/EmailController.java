package TeamTwo.TeamTwoProject.controller.email;

import TeamTwo.TeamTwoProject.dto.email.EmailPostDTO;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.service.email.EmailService;
import TeamTwo.TeamTwoProject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/resetPassword")
    public void sendNewPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        emailService.resetPassword(email);
    }

    @PostMapping("/findUserId")
    public ResponseEntity<String> sendUserId(@RequestBody EmailPostDTO emailPostDTO) {
        try {
            emailService.findUserId(emailPostDTO.getEmail());
            return new ResponseEntity<>("사용자 아이디가 이메일로 전송됨...", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("이메일 전송에 실패함!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }





}
