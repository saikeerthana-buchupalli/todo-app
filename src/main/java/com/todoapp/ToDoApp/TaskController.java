package com.todoapp.ToDoApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    @GetMapping("/")
    public String getTasks(Model model) {
        List<Task> activeTasks = new ArrayList<>();
        List<Task> completedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            } else {
                activeTasks.add(task);
            }
        }

        model.addAttribute("activeTasks", activeTasks);
        model.addAttribute("completedTasks", completedTasks);
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam("title") String title, @RequestParam("dueDate") String dueDate) {
        if (title != null && !title.trim().isEmpty()) {
            tasks.add(new Task(nextId++, title, dueDate));
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
        return "redirect:/";
    }

    @GetMapping("/toggle/{id}")
    public String toggleTask(@PathVariable("id") Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setCompleted(!task.isCompleted());
                break;
            }
        }
        return "redirect:/";
    }

    // --- NEW: Show Edit Form ---
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                model.addAttribute("task", task);
                return "edit";
            }
        }
        return "redirect:/";
    }

    // --- NEW: Process Update ---
    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable("id") Long id, 
                             @RequestParam("title") String title, 
                             @RequestParam("dueDate") String dueDate) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setTitle(title);
                task.setDueDate(dueDate);
                break;
            }
        }
        return "redirect:/";
    }
}