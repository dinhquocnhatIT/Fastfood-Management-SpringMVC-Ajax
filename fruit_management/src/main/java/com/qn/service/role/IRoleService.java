package com.qn.service.role;

import com.qn.model.Role;
import com.qn.model.dto.RoleDTO;
import com.qn.service.IGeneralService;

import java.util.List;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);

    List<RoleDTO> getAllRoleDTO();
}
