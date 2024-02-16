package TeamTwo.TeamTwoProject.dto.toDoList;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class ToDoListDTO {
    private int todoId;
    private int id;
    private String todoContent;
    private Timestamp createdAt;
    private String deadline;
    private String state;
}
