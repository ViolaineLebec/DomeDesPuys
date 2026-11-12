package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.ImageDTO;
import fr.simplon.domedespuys.entities.Image;
import fr.simplon.domedespuys.entities.Product;

public interface IImageService extends IDelete{
    Image create(ImageDTO dto, Product product);
}
