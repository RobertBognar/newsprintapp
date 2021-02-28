package sprintapp.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprintapp.model.User;
import sprintapp.service.UserService;
import sprintapp.web.dto.UserDTO;

@Component
public class UserDtoToUser implements Converter<UserDTO, User> {
	
	@Autowired
	private UserService userService;

	@Override
	public User convert(UserDTO source) {
		User target = null;
		if(source.getId() != null) {
			target = userService.findOne(source.getId()).get();
		}
		
		if(target == null) { 
			target = new User();
		}
				
		target.setUsername(source.getEmail());
		target.setDateOfBirth(source.getDateOfBirth());
		target.setEmail(source.getEmail());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		
		return target;
	}
	
	

}
