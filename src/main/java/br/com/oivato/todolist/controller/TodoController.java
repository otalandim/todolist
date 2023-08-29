package br.com.oivato.todolist.controller;

import br.com.oivato.todolist.entity.Todo;
import br.com.oivato.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public List<Todo> create(@RequestBody @Valid Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping
    public List<Todo> list() {
        return todoService.list();
    }

    @PutMapping
    public List<Todo> update(@RequestBody @Valid Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("{id}")
    public List<Todo> create(@PathVariable("id") Long id) {
        return todoService.delete(id);
    }
}
