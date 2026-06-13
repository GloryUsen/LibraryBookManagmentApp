package com.glory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glory.dto.RoleRequestDto;
import com.glory.dto.RoleResponseDto;
import com.glory.dto.PageRoleResponse;
import com.glory.service.RoleService;
import com.glory.utils.AppConstants;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto roleRequestDto){
        RoleResponseDto savedRole = roleService.createRole(roleRequestDto);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable Long roleId){
        RoleResponseDto role = roleService.getRoleById(roleId);
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<PageRoleResponse> getAllRoles(
        @RequestParam(value = "PageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
        @RequestParam(value = "PageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_PAGE_SORT_BY) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_PAGE_DIRECTION) String direction
    ){
        return ResponseEntity.ok(roleService.getAllRoles(pageNo, pageSize, sortBy, direction));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> updateRole(@RequestBody RoleRequestDto roleRequestDto, @PathVariable Long roleId){
        RoleResponseDto updatedRole = roleService.updateRole(roleRequestDto, roleId);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable Long roleId){
        roleService.deleteRole(roleId);
        return ResponseEntity.ok("Role deleted successfully");
    }

}
