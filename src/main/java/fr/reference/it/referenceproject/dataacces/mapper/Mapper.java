package fr.reference.it.referenceproject.dataacces.mapper;


public interface Mapper<T,L> {


    L map(T userEntity);

    T inverseMap(L utilisateur);

}
