package TeamTwo.TeamTwoProject.repository.toDoList;

import TeamTwo.TeamTwoProject.entity.diary.DiaryEntity;
import TeamTwo.TeamTwoProject.entity.toDoList.ToDoListEntity;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Repository
public interface ToDoListRepository extends JpaRepository<ToDoListEntity, Integer> {
    // get
    Optional<ToDoListEntity> findById(Integer id);
    List<ToDoListEntity> findAllByUserId(int id);

}

