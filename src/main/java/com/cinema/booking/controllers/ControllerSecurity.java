package com.cinema.booking.controllers;

import com.cinema.booking.sacurity.repository.PermissionRepository;
import com.cinema.booking.sacurity.repository.RoleRepository;
import com.cinema.booking.sacurity.repository.UserRepository;
import com.cinema.booking.sacurity.securityEntites.Permission;
import com.cinema.booking.sacurity.securityEntites.Role;
import com.cinema.booking.sacurity.securityEntites.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ControllerSecurity {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;


    //TODO //SAVING DATA///////////////////////////////////////////////////////////////////////////////
    @PostMapping("/save/user")
    private User saveUser(@RequestBody User user){

        return userRepository.save(user);
    }
    @PostMapping("/save/role")
    private Role saveRole(@RequestBody Role role){

        return roleRepository.save(role);
    }
    @PostMapping("/save/permission")
    private Permission savePermission(@RequestBody Permission permission){
        return permissionRepository.save(permission);
    }

    //TODO //ŁĄCZENIE/////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/userid/{userId}/roleid/{roleId}")
    User addingRoleToUser (@PathVariable ("userId") Long userID,
                           @PathVariable ("roleId") Long roleID)
    {
        User user = userRepository.findById(userID).get();
        Role role = roleRepository.findById(roleID).get();
        user.enrolledRole(role);
        return userRepository.save(user);
    }
    //ADD PERMISSION TO ROLE, recursion problem check JSON IGNORE or MAPSTRUCT
    @PostMapping("/roleid/{roleId}/permiid/{permiId}")
    Role addingPermisssionToRole (@PathVariable ("roleId") Long roleID,
                                  @PathVariable ("permiId") Long permiID)
    {
        Role role = roleRepository.findById(roleID).get();
        Permission permission = permissionRepository.findById(permiID).get();
        role.enrolledPermission(permission);
        return roleRepository.save(role);
    }


    //TODO// api do sprawdzania SECURITY? ale nie wiem czy one poprwnie działją bo bazują na find all a nie na wyciąganiu bazy////////////////
    @GetMapping("/show/roles")
    private List<Role> showRoles(){
        return roleRepository.findAll();
    }
    @GetMapping("/show/roless")
    private List<Role> showRoless(){
        return roleRepository.findAll();
    }
    @GetMapping("/show/permission")
    private List<Permission> showPermissions(){
        return permissionRepository.findAll();
    }
    @GetMapping("/findAllUser")
    public List<User> findAllUser(){
        return userRepository.findAll();
    }



    //TODO//api wyciągające użytkownika z bazy po username, email, natywnie, w celu sprawdzenia pobierania do loadByUsername()
//    @GetMapping("/email/{username}")
//    public User findEmails(@PathVariable ("username") String username){
//        return userRepository.findEmailtsw(username);
//    }
    @GetMapping("/mailReal/{email}")
    public User findRealEmails(@PathVariable ("email") String email){
        return userRepository.findRealEmail(email);
    }

    //TODO//DON'T WORK CHYBA
    @GetMapping("/emailnative")
    public User findEmailsNative(){
        return userRepository.findEmailNativeQuery();
    }

//    @GetMapping("/username/{username}")
//    public User findByNormalUsername(@PathVariable("username") String username){
//        return userRepository.findByUsername(username);
//    }

    //TODO napiszmy testujące api poniżej sprawdzjące działanie @PreAutorize i hasAuthority oraz hasRole
    //TODO 1. pytanko jak działają jak odpowiednio uprawnić w configure(https) metody z @PathVariable,@RequestMapping etc

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @PreAuthorize("hasAuthority('READ_PERM')")
    @GetMapping("/hellopre")
    public String sayHelloo(){
        return "Hello Pre";
    }

    //TODO post with encoding password
    @PostMapping("/encode")
    public User saveUserWithEncodingPwd(@RequestBody User user){
        String encryptedPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPwd);
        return userRepository.save(user);
    }

}
