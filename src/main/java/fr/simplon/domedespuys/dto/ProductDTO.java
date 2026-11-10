package fr.simplon.domedespuys.dto;

import fr.simplon.domedespuys.entities.Image;

import java.util.List;

public class ProductDTO {
    public String label;
    public String description;
    public String price;
    public String brand;
    List<Image> images;
}
