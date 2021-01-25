package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.data.dto.ProjectDto;
import softuni.workshop.data.dto.ProjectsRootDto;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.web.models.ProjectViewModel;
import softuni.workshop.service.services.ProjectService;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectServiceImpl implements ProjectService {
    private final String XML_PATH = "src/main/resources/files/xmls/projects.xml";
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, CompanyRepository companyRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importProjects() throws JAXBException, FileNotFoundException {

        ProjectsRootDto projectsRootDto = this.xmlParser.unmarshalFromFile(XML_PATH, ProjectsRootDto.class);

        for (ProjectDto projectDto : projectsRootDto.getProjectDtoList()) {
            Project project = this.modelMapper.map(projectDto, Project.class);
            project.setCompany(this.companyRepository.findByName(projectDto.getCompanyDto().getName()));

            this.projectRepository.saveAndFlush(project);
        }
    }

    @Override
    public boolean areImported() {
       return projectRepository.count() > 0;
    }

    @Override
    public String readProjectsXmlFile() {
        String xml = "";
        try {
            xml = String.join("\n", Files.readAllLines(Paths.get(XML_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @Override
    public String exportFinishedProjects(){
        StringBuilder sb = new StringBuilder();

        List<ProjectViewModel> projects = findAllFinishedProjects();

        for (ProjectViewModel project : projects) {
            sb.append(String.format("Project Name: %s", project.getName()))
                    .append(System.lineSeparator())
                    .append(String.format("  Description: %s", project.getDescription()))
                    .append(System.lineSeparator())
                    .append(String.format("  Salary: %.2f", project.getPayment()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public List<ProjectViewModel> findAllFinishedProjects() {
        return this.projectRepository.findAllByFinishedTrue()
                .stream()
                .map(project -> this.modelMapper.map(project, ProjectViewModel.class))
                .collect(Collectors.toList());
    }

}
