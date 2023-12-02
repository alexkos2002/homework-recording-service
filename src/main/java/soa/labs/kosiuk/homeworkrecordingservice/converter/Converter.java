package soa.labs.kosiuk.homeworkrecordingservice.converter;

public interface Converter<E, D> {

    E convertDtoToEntity(D data);

    D convertEntityToDto(E entity);

}
