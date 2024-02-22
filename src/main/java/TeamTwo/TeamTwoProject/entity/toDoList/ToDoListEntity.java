package TeamTwo.TeamTwoProject.entity.toDoList;

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

    @ManyToOne(cascade = CascadeType.REMOVE)
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
        NOTSTART("notstart"),
        DONE("done");

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
            throw new IllegalArgumentException("Invalid state value");
        }
    }
}
