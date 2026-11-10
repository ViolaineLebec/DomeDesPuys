package fr.simplon.domedespuys.services.interfaces;

public interface ICreate<T, D> {
    T create(D dto);
}
