package sprintapp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sprintapp.model.User;
import sprintapp.repository.UserRepository;
import sprintapp.service.UserService;
import sprintapp.web.dto.UserPasswordChangeDTO;

@Service
public class JpaUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;

	@Override
	public Optional<User> findOne(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> findAll(int pageNum) {
		return userRepository.findAll(PageRequest.of(pageNum, 10));
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findFirstByUsername(username);
	}

	@Override
	public boolean changePassword(Long id, UserPasswordChangeDTO changeDTO) {

		Optional<User> result = userRepository.findById(id);
		
		if(!result.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		User user = result.get();
		
		if(!user.getUsername().equals(changeDTO.getUsername()) || !user.getPassword().equals(changeDTO.getPassword())) {
			return false;
		}
		
		//String encodedPass = passwordEncoder.encode(changeDTO.getPassword());
		//user.setPassword(encodedPass);
		
		userRepository.save(user);
		return true;
	}
	
	

}
