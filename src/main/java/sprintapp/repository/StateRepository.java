package sprintapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sprintapp.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
