package com.openwebinars.todo.task.service;

import com.openwebinars.todo.category.model.Category;
import com.openwebinars.todo.category.model.CategoryRepository;
import com.openwebinars.todo.tag.service.TagService;
import com.openwebinars.todo.task.dto.CreateTaskRequest;
import com.openwebinars.todo.task.exception.EmptyTaskListException;
import com.openwebinars.todo.task.exception.TaskNotFoundException;
import com.openwebinars.todo.task.model.Task;
import com.openwebinars.todo.task.model.TaskRepository;
import com.openwebinars.todo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final TagService tagService;

    public List<Task> findAll() {

        List<Task> result = taskRepository.findAll();

        if (result.isEmpty())
            throw new EmptyTaskListException();

        return result;

    }

    public Task createTask(CreateTaskRequest req, User author) {
        return createOrEditTask(req, author);
    }

    private Task createOrEditTask(CreateTaskRequest req, User author) {

        Task task = Task.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .build();

        if (req.getCategoryId() == null || req.getCategoryId() == -1L)
            req.setCategoryId(1L);
        Category category = categoryRepository.getReferenceById(req.getCategoryId());
        if (category == null) // Categoría por defecto
            category = categoryRepository.getReferenceById(1L);

        task.setCategory(category);

        // Procesamos los tags que vienen en forma de tag1,tag2,tag3
        List<String> textTags = Arrays.stream(req.getTags().split(","))
                .map(String::trim)
                .toList();
        // Los añadimos a task
        task.getTags().addAll(tagService.saveOrGet(textTags));

        // Lo dejamos aparte porque lo modificaremos para editar
        task.setAuthor(author);

        return taskRepository.save(task);
    }


}
