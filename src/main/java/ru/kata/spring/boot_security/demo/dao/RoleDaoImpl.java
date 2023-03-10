package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }


    @Override
    public Role getRole(String userRole) {
        return entityManager.createQuery("select role from Role role where role.name =: name", Role.class)
                .setParameter("name", userRole).getSingleResult();
    }

    @Override
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
