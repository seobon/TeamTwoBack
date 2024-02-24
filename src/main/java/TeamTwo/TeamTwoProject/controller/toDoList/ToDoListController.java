package TeamTwo.TeamTwoProject.controller.toDoList;


import TeamTwo.TeamTwoProject.dto.toDoList.ToDoListDTO;
import TeamTwo.TeamTwoProject.dto.toDoList.TodoListUpdateDTO;
import TeamTwo.TeamTwoProject.service.toDoList.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/todolist/{id}")
    public ResponseEntity<?> getTodosByUserId(@PathVariable int id) {
        try {
            List<ToDoListDTO> toDoList = toDoListService.findAllByUserId(id);
            return ResponseEntity.ok(toDoList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/todolistupdate/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable int todoId, @RequestBody TodoListUpdateDTO updateDTO) {
        try {
            ToDoListDTO updatedTodo = toDoListService.updateTodo(todoId, updateDTO);
            return ResponseEntity.ok("Todo가 성공적으로 수정 되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/tododelete/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable int todoId) {
        try {
            toDoListService.deleteById(todoId);
            return ResponseEntity.ok("Todo가 성공적으로 삭제 되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
