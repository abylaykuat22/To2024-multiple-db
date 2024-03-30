package bitlab.to2024g1multipledb.service;

import bitlab.to2024g1multipledb.tilidb.entity.Developer;
import bitlab.to2024g1multipledb.tilidb.repository.DeveloperRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {

  @Autowired
  private DeveloperRepository developerRepository;

  public List<Developer> getDevelopers() {
    return developerRepository.findAll();
  }
}
