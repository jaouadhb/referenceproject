package fr.reference.it.referenceproject.dataacces.mapper;

import fr.reference.it.referenceproject.dataacces.entity.UserEntity;
import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2019-11-13T21:41:58+0100",
        comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component("userMapper")
public class UserMapperImpl implements Mapper<UserEntity,Utilisateur> {

    @Override
    public Utilisateur map(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setId(userEntity.getId());
        utilisateur.setEmail(userEntity.getEmail());
        utilisateur.setNom(userEntity.getNom());
        utilisateur.setPrenom(userEntity.getPrenom());
        utilisateur.setDateNaissance(userEntity.getDateNaissance());
        utilisateur.setUsername(userEntity.getUsername());
        utilisateur.setPassword(userEntity.getPassword());
        utilisateur.setRole(userEntity.getRole());
        return utilisateur;
    }

    @Override
    public UserEntity inverseMap(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(utilisateur.getId());
        userEntity.setEmail(utilisateur.getEmail());
        userEntity.setNom(utilisateur.getNom());
        userEntity.setPrenom(utilisateur.getPrenom());
        userEntity.setDateNaissance(utilisateur.getDateNaissance());
        userEntity.setPassword(utilisateur.getPassword());
        userEntity.setUsername(utilisateur.getUsername());
        userEntity.setRole(utilisateur.getRole());
        return userEntity;
    }
}
