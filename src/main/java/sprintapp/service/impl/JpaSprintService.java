package sprintapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sprintapp.model.Sprint;
import sprintapp.repository.SprintRepository;
import sprintapp.service.SprintService;

@Service
public class JpaSprintService implements SprintService {
	
	@Autowired
	private SprintRepository sprintRepository;

	@Override
	public List<Sprint> findAll() {
		return sprintRepository.findAll();
	}

	@Override
	public Optional<Sprint> findOne(Long id) {
		return sprintRepository.findById(id);
	}

	@Override
	public Sprint save(Sprint sprint) {
		return sprintRepository.save(sprint);
	}
	
	

}
