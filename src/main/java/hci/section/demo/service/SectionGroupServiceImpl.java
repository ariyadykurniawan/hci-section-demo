package hci.section.demo.service;

import hci.section.demo.entity.SectionGroup;
import hci.section.demo.entity.SectionGroupDetail;
import hci.section.demo.repository.SectionGroupDetailRepository;
import hci.section.demo.repository.SectionGroupRepository;
import hci.section.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionGroupServiceImpl implements SectionGroupService{
    @Autowired
    private SectionGroupRepository sectionGroupRepository;

    @Override
    public List<SectionGroup> getAllSectionGroup() {
        return sectionGroupRepository.findAll();
    }

    @Override
    public SectionGroup getSectionGroupById(Long id) {
        return sectionGroupRepository.findById(id).get();
    }

    @Override
    public SectionGroup getSectionGroupByName(String name) {
        return sectionGroupRepository.findBySectionGroupName(name);
    }

    @Override
    public SectionGroup addSectionGroup(SectionGroup sectionGroup) {
        return sectionGroupRepository.save(sectionGroup);

    }

    @Override
    public SectionGroup editSectionGroup(SectionGroup sectionGroup, Long id) {
        sectionGroup.setId(id);
        return sectionGroupRepository.save(sectionGroup);
    }

    @Override
    public void deleteSectionGroup(Long id) {
        sectionGroupRepository.deleteById(id);
    }
}
