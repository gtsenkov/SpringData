package softuni.workshop.service.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.data.dto.EmployeeDto;
import softuni.workshop.data.dto.EmployeeRootDto;
import softuni.workshop.data.entities.Employee;
import softuni.workshop.data.repositories.EmployeeRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.util.XmlParser;
import softuni.workshop.web.models.EmployeeViewModel;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final String XML_PATH = "src/main/resources/files/xmls/employees.xml";
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository, XmlParser xmlParser, ModelMapper modelMapper, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void importEmployees() throws JAXBException, FileNotFoundException {
        EmployeeRootDto employeeRootDto = this.xmlParser.unmarshalFromFile(XML_PATH, EmployeeRootDto.class);

        for (EmployeeDto employeeDto : employeeRootDto.getEmployeeDtoList()) {
            Employee employee = this.modelMapper.map(employeeDto, Employee.class);
            employee.setProject(this.projectRepository.findByName(employeeDto.getProjectDto().getName()));

            this.employeeRepository.saveAndFlush(employee);
        }
    }

    @Override
    public boolean areImported() {
       return this.employeeRepository.count() > 0;

    }

    @Override
    public String readEmployeesXmlFile() {
        String xml = "";
        try {
            xml = String.join("\n", Files.readAllLines(Paths.get(XML_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        return findAllByAge()
                .stream()
                .map(EmployeeViewModel::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public List<EmployeeViewModel> findAllByAge() {
        return this.employeeRepository.findAllByAgeGreaterThan(25)
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeViewModel.class))
                .collect(Collectors.toList());


    }

    @Override
    public List<EmployeeViewModel> findAll() {
        return this.employeeRepository.findAll()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public String employeesToJson() {
        return this.gson.toJson(findAll());
    }
}
