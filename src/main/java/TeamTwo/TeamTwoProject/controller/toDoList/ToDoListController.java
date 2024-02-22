package TeamTwo.TeamTwoProject.controller.toDoList;


import TeamTwo.TeamTwoProject.dto.toDoList.ToDoListDTO;
import TeamTwo.TeamTwoProject.service.toDoList.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class ToDoListController {
    @Autowired
    private ToDoListService toDoListService;

    @PostMapping("/todocreate/{id}")
    public ResponseEntity<?> createTodo(@RequestBody ToDoListDTO toDoListDTO, @PathVariable int id) {
        try {
            ToDoListDTO createdToDo = toDoListService.create(toDoListDTO, id);
            return ResponseEntity.ok(createdToDo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
