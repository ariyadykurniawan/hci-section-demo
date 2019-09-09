package hci.section.demo.service;
import hci.section.demo.entity.SectionGroupDetail;

import java.util.List;

public interface SectionGroupDetailService {
    List<SectionGroupDetail> getAllSectionGroupDetail();
    SectionGroupDetail addSectionGroupDetail(SectionGroupDetail sectionGroupDetail);
}
