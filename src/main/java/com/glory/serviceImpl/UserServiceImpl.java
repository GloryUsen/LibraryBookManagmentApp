package com.glory.serviceImpl;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.glory.dto.UserRequestDto;
import com.glory.dto.UserResponseDto;
import com.glory.dto.PageUserResponse;
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
    private ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper mapper){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());

        Role role = roleRepository.findByName(userRequestDto.getRoleName())
        .orElseThrow(() -> new ResourceNotFoundException("Role", "name", userRequestDto.getRoleName()));

        user.setRole(role);

        User savedUser = userRepository.save(user);

        return mapUserEntityToUserResponseDto(savedUser);
        
    }

      @Override
        public UserResponseDto getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        return mapUserEntityToUserResponseDto(user);
    }


        

            @Override
        public PageUserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String direction) {
            
            Sort sort = direction.equalsIgnoreCase("desc")
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();

            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

            Page<User> users = userRepository.findAll(pageable);

            List<UserResponseDto> contents = users.getContent()
                    .stream()
                    .map(this::mapUserEntityToUserResponseDto)
                    .collect(Collectors.toList());

            PageUserResponse response = new PageUserResponse();
            response.setContent(contents);
            response.setPageNo(users.getNumber());
            response.setPageSize(users.getSize());
            response.setTotalElements(users.getTotalElements());
            response.setTotalPages(users.getTotalPages());
            response.setLast(users.isLast());

            return response;
        }

  @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());

        Role role = roleRepository.findByName(userRequestDto.getRoleName())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", userRequestDto.getRoleName()));

        user.setRole(role);

        User updatedUser = userRepository.save(user);

        return mapUserEntityToUserResponseDto(updatedUser);
    }

         @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(user);
    }


    private UserResponseDto mapUserEntityToUserResponseDto(User user){

        UserResponseDto dto = mapper.map(user, UserResponseDto.class);

        if(user.getRole() != null){
            dto.setRoleName(user.getRole().getName());
        }

        return dto;
        
    }
}
