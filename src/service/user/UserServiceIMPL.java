package service.user;

import config.Config;
import model.Role;
import model.RoleName;
import model.User;
import service.role.RoleServiceIMPL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceIMPL implements IUserService {

    static String PATH_USER = "src/data/user.txt";
    static String PATH_USER_PRINCIPAL = "src/data/user_principal.txt";

    static Config<List<User>> config = new Config<>();
    static List<User> userList = config.read(PATH_USER);

    static {
        if (userList == null) {
            userList = new ArrayList<>();
            Set<Role> roles = new HashSet<>();
            roles.add(new RoleServiceIMPL().findByRoleName(RoleName.ADMIN));
            userList.add(new User(0, "Admin", "admin", "admin", "admin@admin.admin", roles));
        }
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        updateData();
    }

    @Override
    public User findById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public int getLastId() {
        return userList.get(userList.size() - 1).getId() + 1;
    }

    @Override
    public void updateData() {
        config.write(PATH_USER, userList);
    }

    @Override
    public boolean existsByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
        User currentUser = new Config<User>().read(PATH_USER_PRINCIPAL);
        return currentUser == null ? null : findByUsername(currentUser.getUsername());
    }

    @Override
    public void setCurrentUser(User currentUser) {
        new Config<User>().write(PATH_USER_PRINCIPAL, currentUser);
    }
}
