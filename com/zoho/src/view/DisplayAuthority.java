package src.view;

import src.model.Employee;
import src.model.Task;

import java.util.List;
import java.util.Map;

public interface DisplayAuthority {
    public void Display(List<Employee> coordinator);
    public void TaskDisplay(Map<Employee, Task> task, Employee coordinator, String Query);
}
