package hci.section.demo.service;

import hci.section.demo.entity.Section;
import hci.section.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<Section> getAllSection() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepository.findById(id).get();
    }

    @Override
    public Section getSectionByName(String sectionName) {
        return sectionRepository.findBySectionName(sectionName);
    }

    @Override
    public Section addSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public Section editSection(Section section, Long id) {
        section.setId(id);
        return sectionRepository.save(section);
    }

    @Override
    public void deleteSection(Long id) {
        sectionRepository.deleteById(id);
    }
}
