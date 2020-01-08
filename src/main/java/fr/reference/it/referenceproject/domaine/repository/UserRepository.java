package fr.reference.it.referenceproject.domaine.repository;

import fr.reference.it.referenceproject.dataacces.JpaUser;
import fr.reference.it.referenceproject.dataacces.entity.UserEntity;
import fr.reference.it.referenceproject.dataacces.mapper.Mapper;
import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository{
    @Autowired
    private JpaUser jpaUser;
    @Autowired
    @Qualifier("userMapper")
    private Mapper<UserEntity,Utilisateur> userMapper;

    public List<Utilisateur> findAll() {
        return jpaUser.findAll().stream().map(userMapper::map).collect(Collectors.toList());
    }

    public Optional<Utilisateur> findUserById(int pId) {
        return jpaUser.findById(pId).map(userMapper::map);
    }

    public void deleteUser(int pId) {
        jpaUser.deleteById(pId);
    }

    public void updateUser(Utilisateur pUtilisateur) {
        jpaUser.save(userMapper.inverseMap(pUtilisateur));
    }

    public void saveUser(Utilisateur pUtilisateur) {
        jpaUser.save(userMapper.inverseMap(pUtilisateur));
    }

    public Optional<Utilisateur> findByUsername(String username) {
        List<UserEntity> user = jpaUser.findByUsername(username);
        return user.stream().map(userMapper::map).findFirst();
    }

    public JpaUser getJpaUser() {
        return jpaUser;
    }

    public void setJpaUser(JpaUser jpaUser) {
        this.jpaUser = jpaUser;
    }

    public Mapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(Mapper userMapper) {
        this.userMapper = userMapper;
    }
}
