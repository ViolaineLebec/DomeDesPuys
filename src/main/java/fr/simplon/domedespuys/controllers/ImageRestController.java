package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.dto.AddressDTO;
import fr.simplon.domedespuys.dto.ImageDTO;
import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.services.AddressServiceImpl;
import fr.simplon.domedespuys.services.ImageServiceImpl;
import fr.simplon.domedespuys.views.AddressView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/image")
public class ImageRestController {

    private ImageServiceImpl imageService;


//    @PostMapping
//    @JsonView(AddressView.AddressDetailView.class)
//    public Address createImage(@RequestBody ImageDTO imageDTO) {
//        return imageService.create(imageDTO);
//    }
}
