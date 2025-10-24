package com.timkelly.springaimcp;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void addTask(String name) {
        todoRepository.save(new Task(new ObjectId(), name));
    }

    public void completeTask(String name) {
        todoRepository.findByName(name)
                .stream()
                .findFirst()
                .ifPresent(task -> {
                    task.setCompleted(true);
                    todoRepository.save(task);
                });
    }

    public List<Task> getTasks(String filter) {
        if (filter == null || filter.isBlank() || filter.equalsIgnoreCase("all")) {
            return todoRepository.findAll();
        }

        return switch (filter.toLowerCase()) {
            case "incomplete" -> todoRepository.findByCompletedFalse();
            case "complete" -> todoRepository.findByCompletedTrue();
            default -> todoRepository.findAll();
        };
    }
}
