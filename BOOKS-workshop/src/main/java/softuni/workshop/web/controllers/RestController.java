package softuni.workshop.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.web.models.EmployeeViewModel;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final EmployeeService employeeService;

    public RestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/api/employees", produces = "application/json")
    public List<EmployeeViewModel> employees() {
        return this.employeeService.findAll();
    }
}
