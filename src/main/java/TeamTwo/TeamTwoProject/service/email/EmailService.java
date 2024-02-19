package TeamTwo.TeamTwoProject.service.email;

import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.repository.user.UserRepository;
import TeamTwo.TeamTwoProject.service.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private SecureRandom random = new SecureRandom();


    // 임시 비밀번호 발급
    public void resetPassword(String email) {
        try {
            UserEntity user = userRepository.findByEmail(email);

            if (user == null) {
                throw new IllegalArgumentException("해당 이메일을 가진 사용자가 없습니다.");
            } else {
                String newPassword = UUID.randomUUID().toString(); // 임시 비밀번호 만들기
                String newSalt = new BigInteger(130, random).toString(32);
                String hashedPassword = passwordEncoder.encode(newPassword + newSalt);

                UserEntity findPassword = UserEntity.builder()
                        .id(user.getId())
                        .userid(user.getUserid())
                        .password(hashedPassword)
                        .salt(newSalt)
                        .email(user.getEmail())
                        .nickname(user.getNickname())
                        .image(user.getImage())
                        .build();

                userRepository.save(findPassword);

                sendEmail(email, newPassword);
                System.out.println(email);
            }

        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송 실패",e);
        }
    }

    // 메일 전송
    public void sendEmail(String email, String newPassword ) throws MessagingException {// to: 누구에게, subject: 제목, content: 내용
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setSubject("임시 비밀번호 발급 안내");
        helper.setText("귀하의 임시 비밀번호는 " + newPassword + " 입니다.");

        javaMailSender.send(message);
    }




}
