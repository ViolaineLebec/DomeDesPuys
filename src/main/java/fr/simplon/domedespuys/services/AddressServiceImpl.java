package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.AddressDTO;
import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.repositories.AddressRepository;
import fr.simplon.domedespuys.repositories.UserRepository;
import fr.simplon.domedespuys.services.interfaces.IAddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private AddressRepository addressRepository;
    private UserRepository userRepository;


    @Override
    public Address create(AddressDTO dto) {
        Address address = new Address();
        address.setLine(dto.line);
        address.setExtraLine(dto.extraLine);
        address.setCity(dto.city);
        address.setZipCode(dto.zipCode);
        address.setCountry(dto.country);
        address.setPhone(dto.phone);
        address.setFirstname(dto.firstName);
        address.setLastname(dto.lastName);

        Optional<User> oUser = userRepository.findById((long) 1);
        User user = oUser.get();
        address.setUser(user);

        LocalDateTime date = LocalDateTime.now();
        address.setCreatedAt(date);

        addressRepository.saveAndFlush(address);
        return address;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Address update(String id, AddressDTO dto) {
        return null;
    }

}
