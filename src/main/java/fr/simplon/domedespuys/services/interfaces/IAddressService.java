package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.AddressDTO;
import fr.simplon.domedespuys.entities.Address;

public interface IAddressService extends
        ICreate<Address, AddressDTO>,
        IUpdate<Address, AddressDTO>,
        IDelete
{
    Address getById(String id);
}
