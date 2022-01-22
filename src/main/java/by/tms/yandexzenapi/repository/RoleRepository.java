package by.tms.yandexzenapi.repository;

import by.tms.yandexzenapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByTypeOfRole(String typeOfRole);
}
