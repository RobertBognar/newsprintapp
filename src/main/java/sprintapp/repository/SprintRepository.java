package sprintapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sprintapp.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
