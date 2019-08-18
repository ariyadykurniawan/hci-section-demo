package hci.section.demo.service;

import hci.section.demo.entity.Section;

import java.util.List;

public interface SectionService {
    List<Section> getAllSection();
    Section getSectionById(Long id);
    Section getSectionByName(String sectionName);
    Section addSection(Section section);
    Section editSection(Section section, Long id);
    void deleteSection(Long id);
}
