package com.glory.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.glory.dto.RoleDto;
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
    public RoleDto createRole(RoleDto roleDto) {
        Role role = mapRoleDtoToRoleEntity(roleDto);
        Role saveRole = roleRepository.save(role);

         return mapRoleEntityToRoleDto(saveRole);
    }


    @Override
    public RoleDto getRoleById(Long roleId){
       Role role = roleRepository.findById(roleId)
        .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
        return mapRoleEntityToRoleDto(role);
    }

    @Override
    public List<RoleDto> getAllRoles() {

        return roleRepository.findAll()
        .stream().map(this::mapRoleEntityToRoleDto)
        .collect(Collectors.toList());
    }

    
    @Override
    public RoleDto updateRole(RoleDto dto, Long roleId){

        Role role = roleRepository.findById(roleId)
        .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));

        role.setName(dto.getName());

        Role updatedRole = roleRepository.save(role);
        return mapRoleEntityToRoleDto(updatedRole);
    }


    @Override
    public void deleteRole(Long id){
        Role role =  roleRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));

        roleRepository.delete(role);
    }


    private Role mapRoleDtoToRoleEntity(RoleDto roleDto){

        Role role = mapper.map(roleDto, Role.class);

        // Role role = new Role();
        // role.setName(roleDto.getName());
         return role;
        
    }


    
    private RoleDto mapRoleEntityToRoleDto(Role role){
      //  return new RoleDto(role.getId(), role.getName());
      return mapper.map(role, RoleDto.class);
   
}
}
