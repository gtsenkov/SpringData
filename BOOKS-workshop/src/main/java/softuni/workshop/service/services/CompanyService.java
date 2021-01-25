package softuni.workshop.service.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface CompanyService {

    void importCompanies() throws JAXBException, FileNotFoundException;

    boolean areImported();

    String readCompaniesXmlFile();
}
