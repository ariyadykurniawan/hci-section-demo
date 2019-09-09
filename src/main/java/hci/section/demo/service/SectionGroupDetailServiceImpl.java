package hci.section.demo.service;

import hci.section.demo.entity.SectionGroupDetail;
import hci.section.demo.repository.SectionGroupDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionGroupDetailServiceImpl implements SectionGroupDetailService {
    @Autowired
    private SectionGroupDetailRepository sectionGroupDetailRepository;

    @Override
    public List<SectionGroupDetail> getAllSectionGroupDetail() {
        return null;
    }

    @Override
    public SectionGroupDetail addSectionGroupDetail(SectionGroupDetail sectionGroupDetail) {

        return sectionGroupDetailRepository.save(sectionGroupDetail);
    }
}
