package com.glory.service;

import java.util.List;

import com.glory.dto.RoleDto;

public interface RoleService {

    RoleDto createRole(RoleDto roleDto);

    RoleDto getRoleById(Long roleId);

    List<RoleDto> getAllRoles();

    RoleDto updateRole(RoleDto roleDto, Long roleId);

    void deleteRole(Long id);

}
