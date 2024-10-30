package src.model;

public class Task {
    private int id;
    private String code;
    private String subject;
    private String description;
    private String dueDate;
    private String priority;

    private String status;

    public Task(int id,String Code, String name, String description, String dueDate, String status) {
        this.id = id;
        this.code = Code;
        this.subject = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }
    public Task(String code,String name, String description, String dueDate, String Priority) {
        this.code = code;
        this.subject = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = Priority;
    }

    public int getId() {
        return id;
    }
    public String getCode()
    {
        return code;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setName(String name) {
        this.subject = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
