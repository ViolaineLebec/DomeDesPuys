package fr.simplon.domedespuys.repositories;

import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    public List<Address> findAllByUser(User user);
}
