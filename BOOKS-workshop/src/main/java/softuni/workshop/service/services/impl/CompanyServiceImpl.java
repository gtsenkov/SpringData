package softuni.workshop.service.services.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.data.dto.CompaniesRootDto;
import softuni.workshop.data.dto.CompanyDto;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final String XML_PATH = "src/main/resources/files/xmls/companies.xml";
    private final CompanyRepository companyRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importCompanies() throws JAXBException, FileNotFoundException {
        CompaniesRootDto companiesRootDto = this.xmlParser.unmarshalFromFile(XML_PATH, CompaniesRootDto.class);

        for (CompanyDto companyDto : companiesRootDto.getCompanyDtos()) {
            this.companyRepository.save(this.modelMapper.map(companyDto, Company.class));
        }
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile() {
        String xml = "";
        try {
            xml = String.join("\n", Files.readAllLines(Paths.get(XML_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

}
