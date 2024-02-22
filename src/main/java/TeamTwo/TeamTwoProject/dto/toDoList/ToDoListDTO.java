package TeamTwo.TeamTwoProject.dto.toDoList;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Builder
public class ToDoListDTO {
    private int todoId;
    private int id;
    private String todoContent;
    private LocalDate createdAt;
    private String deadline;
    private String state;
}
