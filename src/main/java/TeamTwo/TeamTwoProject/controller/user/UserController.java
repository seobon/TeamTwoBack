package TeamTwo.TeamTwoProject.controller.user;


import TeamTwo.TeamTwoProject.dto.user.UserDTO;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.security.TokenProvider;
import TeamTwo.TeamTwoProject.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
        try {
            UserEntity user = UserEntity.builder()
                    .userid(userDTO.getUserid())
                    .password(userDTO.getPassword())
                    .email(userDTO.getEmail())
                    .nickname(userDTO.getNickname())
                    .image(userDTO.getImage())
                    .build();

            UserEntity newUser = userService.signup(user);
            return ResponseEntity.ok().body("회원가입 성공");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpSession session, @RequestBody UserDTO userDTO) {
        try {
            UserEntity user = userService.login(userDTO.getUserid(), userDTO.getPassword());
            if (user == null) {
                throw new RuntimeException("로그인 실패");
            }
                String token = tokenProvider.createToken(user);
                UserDTO responseUserDTO = UserDTO.builder()
                        .id(user.getId())
                        .userid(user.getUserid())
                        .email(user.getEmail())
                        .nickname(user.getNickname())
                        .token(token)
                        .build();
                return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}