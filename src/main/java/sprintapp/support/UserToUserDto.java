package sprintapp.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprintapp.model.User;
import sprintapp.web.dto.UserDTO;

@Component
public class UserToUserDto implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User source) {
		
		UserDTO target = new UserDTO();
		
		target.setId(source.getId());
		target.setDateOfBirth(source.getDateOfBirth());
		target.setEmail(source.getEmail());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setUsername(source.getUsername());
		
		return target;
	}
	
	

}
