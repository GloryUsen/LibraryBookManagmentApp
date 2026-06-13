package com.glory.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.glory.dto.RoleRequestDto;
import com.glory.dto.RoleResponseDto;
import com.glory.dto.PageRoleResponse;
import com.glory.entity.Role;
import com.glory.exception.ResourceNotFoundException;
import com.glory.repository.RoleRepository;
import com.glory.service.RoleService;

@Service

public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    private ModelMapper mapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper){
        this.roleRepository = roleRepository;
        this.mapper = mapper;

    }

    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        Role role = mapRoleRequestDtoToRoleEntity(roleRequestDto);
        Role saveRole = roleRepository.save(role);

         return mapRoleEntityToRoleResponseDto(saveRole);
    }


    @Override
    public RoleResponseDto getRoleById(Long roleId){
       Role role = roleRepository.findById(roleId)
        .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
        return mapRoleEntityToRoleResponseDto(role);
    }

    @Override
    public PageRoleResponse getAllRoles(int pageNo, int pageSize, String sortBy, String direction) {
        
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Role> roles = roleRepository.findAll(pageable);

        List<RoleResponseDto> contents = roles.getContent()
                .stream()
                .map(this::mapRoleEntityToRoleResponseDto)
                .collect(Collectors.toList());

        PageRoleResponse response = new PageRoleResponse();
        response.setContent(contents);
        response.setPageNo(roles.getNumber());
        response.setPageSize(roles.getSize());
        response.setTotalElements(roles.getTotalElements());
        response.setTotalPages(roles.getTotalPages());
        response.setLast(roles.isLast());

        return response;
    }

    
    @Override
    public RoleResponseDto updateRole(RoleRequestDto roleRequestDto, Long roleId){

        Role role = roleRepository.findById(roleId)
        .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));

        role.setName(roleRequestDto.getName());

        Role updatedRole = roleRepository.save(role);
        return mapRoleEntityToRoleResponseDto(updatedRole);
    }


    @Override
    public void deleteRole(Long id){
        Role role =  roleRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));

        roleRepository.delete(role);
    }


    private Role mapRoleRequestDtoToRoleEntity(RoleRequestDto roleRequestDto){

        Role role = mapper.map(roleRequestDto, Role.class);
         return role;
        
    }


    
    private RoleResponseDto mapRoleEntityToRoleResponseDto(Role role){
      return mapper.map(role, RoleResponseDto.class);
   
}
}
