package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.services.interfaces.IImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements IImageService {
    @Override
    public boolean delete(String id) {
        return false;
    }
}
