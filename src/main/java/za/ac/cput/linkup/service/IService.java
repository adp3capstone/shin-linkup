package za.ac.cput.linkup.service;

public interface IService<T, ID> {


    T create(T entity);


    T read(ID id);

    T update(T t);
}
