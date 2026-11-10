package fr.simplon.domedespuys.services.interfaces;

public interface IUpdate<T, D> {

    T update(String id, D dto);

}