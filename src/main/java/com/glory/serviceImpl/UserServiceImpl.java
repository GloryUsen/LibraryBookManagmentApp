package com.glory.serviceImpl;



import org.springframework.stereotype.Service;

import com.glory.dto.UserDto;
import com.glory.entity.Role;
import com.glory.entity.User;
import com.glory.exception.ResourceNotFoundException;
import com.glory.repository.RoleRepository;
import com.glory.repository.UserRepository;
import com.glory.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        Role role = roleRepository.findByName(userDto.getRoleName())
        .orElseThrow(() -> new ResourceNotFoundException("Role", "name", userDto.getRoleName()));

        user.setRole(role);

        User savedUser = userRepository.save(user);

        return mapUserEntityToUserDto(savedUser);
        
    }

    // @Override
    // public UserDto getUserById(Long id) {

    // }

    // @Override
    // public List<UserDto> getAllUsers() {

    // }

    // @Override
    // public UserDto updateUser(Long id, UserDto userDto) {

    // }

    // @Override
    // public void deleteUser(Long id) {
        
    // }


    private UserDto mapUserEntityToUserDto(User user){

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());

        if(user.getRole() != null){
            dto.setRoleName(user.getRole().getName());
        }

        return dto;
        
    }
}
