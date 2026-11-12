package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.dto.AddressDTO;
import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.services.AddressServiceImpl;
import fr.simplon.domedespuys.views.AddressView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/address")
public class AddressRestController {

    private AddressServiceImpl addressService;

    @PostMapping
    @JsonView(AddressView.AddressDetailView.class)
    public Address createAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }
}
