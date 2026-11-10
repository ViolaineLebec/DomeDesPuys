package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.BrandDTO;
import fr.simplon.domedespuys.entities.Brand;

public interface IBrandService extends
        ICreate<Brand, BrandDTO>,
        IUpdate<Brand, BrandDTO>,
        ICollection<Brand>,
        IGetItem<Brand>,
        IDelete
{
}