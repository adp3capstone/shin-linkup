package com.ethan.adatingapp.service;

public interface IService<T, ID> {


    T create(T entity);


    T read(ID id);

    T update(T t);
}
