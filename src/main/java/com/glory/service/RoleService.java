package com.glory.service;

import java.util.List;

import com.glory.dto.RoleRequestDto;
import com.glory.dto.RoleResponseDto;
import com.glory.dto.PageRoleResponse;

public interface RoleService {

    RoleResponseDto createRole(RoleRequestDto roleRequestDto);

    RoleResponseDto getRoleById(Long roleId);

    PageRoleResponse getAllRoles(int pageNo, int pageSize, String sortBy, String direction);

    RoleResponseDto updateRole(RoleRequestDto roleRequestDto, Long roleId);

    void deleteRole(Long id);

}
