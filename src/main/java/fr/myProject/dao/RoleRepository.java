package fr.myProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.myProject.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByRoleName(String roleName);
}
