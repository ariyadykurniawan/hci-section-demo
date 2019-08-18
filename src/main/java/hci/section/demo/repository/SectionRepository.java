package hci.section.demo.repository;

import hci.section.demo.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Section findBySectionName(String sectionName);
}
