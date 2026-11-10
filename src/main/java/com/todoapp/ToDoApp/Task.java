package com.todoapp.ToDoApp;

public class Task {
    private Long id;
    private String title;
    private boolean completed;
    private String dueDate;

    public Task(Long id, String title, String dueDate) {
        this.id = id;
        this.title = title;
        this.completed = false;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}