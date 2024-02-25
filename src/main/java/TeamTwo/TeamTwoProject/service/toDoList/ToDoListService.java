package TeamTwo.TeamTwoProject.service.toDoList;

import TeamTwo.TeamTwoProject.dto.toDoList.ToDoListDTO;
import TeamTwo.TeamTwoProject.dto.toDoList.TodoListUpdateDTO;
import TeamTwo.TeamTwoProject.entity.toDoList.ToDoListEntity;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import TeamTwo.TeamTwoProject.repository.toDoList.ToDoListRepository;
import TeamTwo.TeamTwoProject.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        if (toDoListDTO == null) {
            throw new IllegalArgumentException("입력 값이 없습니다.");
        }
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        ToDoListEntity toDoListEntity = convertToEntity(toDoListDTO, user);
        try {
            toDoListRepository.save(toDoListEntity);
        } catch (DataAccessException e) {
            throw new RuntimeException("데이터베이스 접근 중 오류가 발생했습니다.", e);
        }
        toDoListRepository.save(toDoListEntity);
        return toDoListDTO;
    }

    private ToDoListEntity convertToEntity(ToDoListDTO dto, UserEntity user) {
        ToDoListEntity.State state = ToDoListEntity.State.fromString(dto.getState());
        if (state == null) {
            throw new IllegalArgumentException("잘못된 Todo의 state가 주어졌습니다.");
        }

        return ToDoListEntity.builder()
                .user(user)
                .todoContent(dto.getTodoContent())
                .createdAt(LocalDate.now())
                .deadline(dto.getDeadline())
                .state(state)
                .build();
    }
    // 특정 ToDo 항목 조회
    public List<ToDoListDTO> findAllByUserId(int id) {
        return toDoListRepository.findAllByUserId(id).stream()
                .map(entity -> convertToDto(entity))
                .collect(Collectors.toList());
    }

    private ToDoListDTO convertToDto(ToDoListEntity entity) {
        return ToDoListDTO.builder()
                .todoId(entity.getTodoId())
                .id(entity.getUser().getId())
                .todoContent(entity.getTodoContent())
                .createdAt(entity.getCreatedAt())
                .deadline(entity.getDeadline())
                .state(entity.getState().toString())
                .build();
    }

    public ToDoListDTO updateTodo(int todoId, TodoListUpdateDTO updateDTO) {
        if (updateDTO == null) {
            throw new IllegalArgumentException("입력 값이 없습니다.");
        }
        ToDoListEntity entity = toDoListRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ToDo 항목을 찾을 수 없습니다."));

        entity.update(updateDTO);
        ToDoListEntity updatedEntity = toDoListRepository.save(entity);

        return convertToDto(updatedEntity);
    }

    public void deleteById(int todoId) {
        ToDoListEntity todo = toDoListRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 할 일을 찾을 수 없습니다."));
        toDoListRepository.delete(todo);
    }

}
