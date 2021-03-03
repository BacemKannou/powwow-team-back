package fr.docapost.powwow.api.dao;

import fr.docapost.powwow.api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByRoleName(String roleName);
}
