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

    public void setState(String state) {
        if (!"notstart".equalsIgnoreCase(state) && !"done".equalsIgnoreCase(state)) {
            throw new IllegalArgumentException("Invalid state value");
        }
        this.state = state;
    }

}
