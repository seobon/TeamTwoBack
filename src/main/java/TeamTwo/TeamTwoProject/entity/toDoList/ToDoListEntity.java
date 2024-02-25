package TeamTwo.TeamTwoProject.entity.toDoList;

import TeamTwo.TeamTwoProject.dto.toDoList.TodoListUpdateDTO;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "toDoList")
public class ToDoListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todoId", nullable = false)
    private int todoId;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private UserEntity user;

    @Column(name = "todoContent", nullable = false)
    private String todoContent;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @Column(name = "deadline")
    private String deadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;
    public enum State {
        notstart("notstart"),
        done("done");

        private String value;

        State(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static State fromString(String value) {
            for (State state : State.values()) {
                if (state.getValue().equalsIgnoreCase(value)) {
                    return state;
                }
            }
            System.out.println("Invalid state value: " + value);
            throw new IllegalArgumentException("Invalid state Value");
        }
    }
    public void update(TodoListUpdateDTO updateDTO) {
        if (updateDTO.getNewTodoContent() != null) {
            this.todoContent = updateDTO.getNewTodoContent();
        }
        if (updateDTO.getNewCreatedAt() != null) {
            this.createdAt = updateDTO.getNewCreatedAt();
        }
        if (updateDTO.getNewState() != null) {
            this.state = State.fromString(updateDTO.getNewState());
        }
        if (updateDTO.getNewDeadline() != null) {
            this.deadline = updateDTO.getNewDeadline();
        }
    }
}
