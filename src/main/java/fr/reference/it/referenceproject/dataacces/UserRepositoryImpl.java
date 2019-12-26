package fr.reference.it.referenceproject.dataacces;

import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import fr.reference.it.referenceproject.domaine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JpaUserDAO jpaUserDAO;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Utilisateur> findAll() {
        return jpaUserDAO.findAll().stream().map(userMapper::map).collect(Collectors.toList());
    }

    @Override
    public Optional<Utilisateur> findUserById(int pId) {
        return jpaUserDAO.findById(pId).map(userMapper::map);
    }

    @Override
    public void deleteUser(int pId) {
        jpaUserDAO.deleteById(pId);
    }

    @Override
    public void updateUser(Utilisateur pUtilisateur) {
        jpaUserDAO.save(userMapper.map(pUtilisateur));
    }

    @Override
    public void saveUser(Utilisateur pUtilisateur) {
        jpaUserDAO.save(userMapper.map(pUtilisateur));
    }

    public JpaUserDAO getJpaUserDAO() {
        return jpaUserDAO;
    }

    public void setJpaUserDAO(JpaUserDAO jpaUserDAO) {
        this.jpaUserDAO = jpaUserDAO;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
