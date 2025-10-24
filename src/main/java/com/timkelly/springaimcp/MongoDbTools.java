package com.timkelly.springaimcp;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnBean(TodoService.class)
public class MongoDbTools {

    private final TodoService todoService;

    public MongoDbTools(TodoService todoService) {
        this.todoService = todoService;
    }

    @McpTool(
            name = "todo-get-tasks",
            description = "Retrieve to-do list items, optionally filtered by completion status"
    )
    public List<Task> getTasks(
            @McpToolParam(
                    description = "Filter tasks by completion status: 'complete', 'incomplete', or 'all' (default: 'all')",
                    required = false
            ) String filter
    ) {
        return todoService.getTasks(filter);
    }

    @McpTool(
            name = "todo-add-task",
            description = "Add a new to-do task to MongoDB"
    )
    public String addTask(
            @McpToolParam(
                    description = "The name or description of the new task",
                    required = true
            ) String name
    ) {
        todoService.addTask(name);
        return "Task added successfully: " + name;
    }

    @McpTool(
            name = "todo-complete-task",
            description = "Mark a to-do task as complete by name"
    )
    public String completeTask(
            @McpToolParam(
                    description = "The name of the task to mark as complete",
                    required = true
            ) String name
    ) {
        todoService.completeTask(name);
        return "Marked task as complete: " + name;
    }
}
