package softuni.workshop.service.services;

import softuni.workshop.web.models.ProjectViewModel;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface ProjectService {

    void importProjects() throws JAXBException, FileNotFoundException;

    boolean areImported();

    String readProjectsXmlFile();

    String exportFinishedProjects();

    List<ProjectViewModel> findAllFinishedProjects();
}
