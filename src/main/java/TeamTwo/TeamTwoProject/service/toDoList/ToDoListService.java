package TeamTwo.TeamTwoProject.service.toDoList;

import TeamTwo.TeamTwoProject.dto.toDoList.ToDoListDTO;
import TeamTwo.TeamTwoProject.entity.toDoList.ToDoListEntity;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.repository.toDoList.ToDoListRepository;
import TeamTwo.TeamTwoProject.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.lang.IllegalArgumentException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor

public class ToDoListService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ToDoListRepository toDoListRepository;



    public ToDoListDTO create(ToDoListDTO toDoListDTO, int id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        ToDoListEntity toDoListEntity = convertToEntity(toDoListDTO, user);

        toDoListRepository.save(toDoListEntity);
        return toDoListDTO;
    }

    private ToDoListEntity convertToEntity(ToDoListDTO dto, UserEntity user) {
        return ToDoListEntity.builder()
                .user(user)
                .todoContent(dto.getTodoContent())
                .createdAt(LocalDate.now())
                .deadline(dto.getDeadline())
                .state(ToDoListEntity.State.fromString(dto.getState()))
                .build();
    }

}
