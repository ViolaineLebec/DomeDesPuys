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
    public Address getById(String id) {
        return addressRepository
                .findById(Long.parseLong(id))
                .orElse(null);
    }

    @Override
    public Address create(AddressDTO dto) {
        Address address = new Address();

        dtoToObject(dto, address);

        Optional<User> oUser = userRepository.findById(1L);
        User user = oUser.get();
        address.setUser(user);

        LocalDateTime date = LocalDateTime.now();
        address.setCreatedAt(date);

        addressRepository.saveAndFlush(address);
        return address;
    }

    @Override
    public boolean delete(String id) {
        try {
            addressRepository.deleteById(Long.parseLong(id));
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Address update(String id, AddressDTO dto) {
        Address address = getById(id);
        if (address != null) {
            dtoToObject(dto, address);
        }
        addressRepository.saveAndFlush(address);
        return address;
    }

    private void dtoToObject(AddressDTO dto, Address address) {
        address.setLine(dto.line);
        address.setExtraLine(dto.extraLine);
        address.setCity(dto.city);
        address.setZipCode(dto.zipCode);
        address.setCountry(dto.country);
        address.setPhone(dto.phone);
        address.setFirstname(dto.firstName);
        address.setLastname(dto.lastName);
    }

}
