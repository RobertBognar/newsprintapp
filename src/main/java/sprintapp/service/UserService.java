package sprintapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import sprintapp.model.User;
import sprintapp.web.dto.UserPasswordChangeDTO;

public interface UserService {
	
	Optional<User> findOne(Long id);
	List<User> findAll();
	Page<User> findAll(int pageNum);
	User save(User user);
	void delete(Long id);
	Optional<User> findByUsername(String username);
	boolean changePassword(Long id, UserPasswordChangeDTO changeDTO);

}
