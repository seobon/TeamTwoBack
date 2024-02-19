package TeamTwo.TeamTwoProject.controller.email;

import TeamTwo.TeamTwoProject.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/reset-password")
    public void sendNewPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        emailService.resetPassword(email);
    }
//    // 이메일 전송
//    @PostMapping("/send")
//    public ResponseEntity<String> sendEmail(@RequestBody EmailPostDTO emailPostDTO) {
//        emailService.sendEmail(emailPostDTO.getTo(), emailPostDTO.getSubject(), emailPostDTO.getContent());
//        return new ResponseEntity<>("이메일 보내기 성공", HttpStatus.OK);
//    }




}
