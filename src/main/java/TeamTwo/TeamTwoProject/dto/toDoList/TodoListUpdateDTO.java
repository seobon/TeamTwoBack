package TeamTwo.TeamTwoProject.dto.toDoList;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class TodoListUpdateDTO {

    private String newTodoContent;
    private LocalDate newCreatedAt;
    private String newState;
    private String newDeadline;

    public void setState(String newState) {
        if (!"notstart".equalsIgnoreCase(newState) && !"done".equalsIgnoreCase(newState)) {
            throw new IllegalArgumentException("Invalid state value");
        }
        this.newState = newState;
    }
}
