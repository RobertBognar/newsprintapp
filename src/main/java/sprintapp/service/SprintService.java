package sprintapp.service;

import java.util.List;
import java.util.Optional;

import sprintapp.model.Sprint;

public interface SprintService {
	
	List<Sprint> findAll();
	Optional<Sprint> findOne(Long id);
	Sprint save(Sprint sprint);

}
