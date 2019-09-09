package hci.section.demo.service;

import hci.section.demo.entity.Section;
import hci.section.demo.entity.SectionGroupDetail;
import hci.section.demo.repository.SectionGroupDetailRepository;
import hci.section.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    SectionGroupDetailRepository sectionGroupDetailRepository;

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
        Section section1 = sectionRepository.save(section);
        SectionGroupDetail sectionGroupDetail = new SectionGroupDetail();
        sectionGroupDetail.setIdSection(section1.getId());
        sectionGroupDetail.setIdSectionGroup(Long.valueOf(1));

        Long counter = sectionGroupDetailRepository.count();
        sectionGroupDetail.setOrderNo(counter + 1);
        sectionGroupDetailRepository.save(sectionGroupDetail);
        return section1;
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
