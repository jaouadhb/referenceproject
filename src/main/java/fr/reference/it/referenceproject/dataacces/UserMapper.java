package fr.reference.it.referenceproject.dataacces;


import fr.reference.it.referenceproject.domaine.dto.Utilisateur;

public interface UserMapper {

    UserMapper INSTANCE =new UserMapperImpl();

    Utilisateur map(UserEntity userEntity);
    UserEntity map(Utilisateur utilisateur);

}
