package com.openwebinars.todo.task.controller;

import com.openwebinars.todo.category.model.Category;
import com.openwebinars.todo.category.service.CategoryService;
import com.openwebinars.todo.task.dto.CreateTaskRequest;
import com.openwebinars.todo.task.service.TaskService;
import com.openwebinars.todo.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }


    @GetMapping({"/", "/list", "/task"})
    public String taskList(Model model) {
        model.addAttribute("taskList", taskService.findAll());
        model.addAttribute("newTask", new CreateTaskRequest());
        return "task-list";
    }

    @GetMapping(value = {"/", "/list", "/task"}, params = "emptyListError")
    public String createTask(Model model) {
        model.addAttribute("newTask", new CreateTaskRequest());
        return "task-list";
    }

    @PostMapping("/task/submit")
    public String taskSubmit(
            @Valid @ModelAttribute("newTask") CreateTaskRequest req,
            BindingResult bindingResult,
            @AuthenticationPrincipal User author,
            Model model) {


        if (bindingResult.hasErrors()) {
            model.addAttribute("taskList", taskService.findAll());
            return "task-list";
        }

        taskService.createTask(req, author);

        return "redirect:/";
    }




}
