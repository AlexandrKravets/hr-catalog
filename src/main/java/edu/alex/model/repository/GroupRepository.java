package edu.alex.model.repository;

import edu.alex.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
