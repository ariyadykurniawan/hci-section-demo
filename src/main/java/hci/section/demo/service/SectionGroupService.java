package hci.section.demo.service;

import hci.section.demo.entity.SectionGroup;

import java.util.List;

public interface SectionGroupService {
    List<SectionGroup> getAllSectionGroup();
    SectionGroup getSectionGroupById(Long id);
    SectionGroup getSectionGroupByName(String name);
    SectionGroup addSectionGroup(SectionGroup sectionGroup);
    SectionGroup editSectionGroup(SectionGroup sectionGroup, Long id);
    void deleteSectionGroup(Long id);
}
