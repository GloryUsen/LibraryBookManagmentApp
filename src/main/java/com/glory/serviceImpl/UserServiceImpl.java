package com.glory.serviceImpl;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

      @Override
        public UserDto getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        return mapUserEntityToUserDto(user);
    }


        

            @Override
        public List<UserDto> getAllUsers() {
            return userRepository.findAll()
                    .stream()
                    .map(this::mapUserEntityToUserDto)
                    .collect(Collectors.toList());
        }
  @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        Role role = roleRepository.findByName(userDto.getRoleName())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", userDto.getRoleName()));

        user.setRole(role);

        User updatedUser = userRepository.save(user);

        return mapUserEntityToUserDto(updatedUser);
    }

         @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(user);
    }


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
