package hci.section.demo.repository;

import hci.section.demo.entity.SectionGroupDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionGroupDetailRepository extends JpaRepository<SectionGroupDetail, Long> {
    SectionGroupDetail findByIdSectionGroup(Long idSectionGroup);
}
