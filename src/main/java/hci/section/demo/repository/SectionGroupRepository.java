package hci.section.demo.repository;

import hci.section.demo.entity.SectionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionGroupRepository extends JpaRepository<SectionGroup, Long> {
    SectionGroup findBySectionGroupName(String name);
}
