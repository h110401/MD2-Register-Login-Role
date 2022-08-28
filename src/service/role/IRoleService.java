package service.role;

import model.Role;
import model.RoleName;


public interface IRoleService {

    Role findByRoleName(RoleName roleName);

}
