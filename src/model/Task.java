package model;

import java.util.Date;

public class Task {
    private String title;
    private String description;
    private Date limitDate;
    private Priority priority;

    public Task(String title, String description, Date limitDate, Priority priority) {
        this.title = title;
        this.description = description;
        this.limitDate = limitDate;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
