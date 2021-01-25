package softuni.workshop.service.services;

import softuni.workshop.data.entities.Employee;
import softuni.workshop.web.models.EmployeeViewModel;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface EmployeeService {

    void importEmployees() throws JAXBException, FileNotFoundException;

    boolean areImported();

    String readEmployeesXmlFile();

    String exportEmployeesWithAgeAbove();

    List<EmployeeViewModel> findAllByAge();

    List<EmployeeViewModel> findAll();

    String employeesToJson();
}
