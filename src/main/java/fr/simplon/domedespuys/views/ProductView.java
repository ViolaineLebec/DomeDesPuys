package fr.simplon.domedespuys.views;

public class ProductView {
    public interface Id {}
    public interface Label {}
    public interface Description {}
    public interface Price {}
    public interface Slug {}
    public interface Brand {}
    public interface Images {}

    public interface ProductListView extends ProductView.Label, ProductView.Slug, ProductView.Price, ProductView.Brand {}
    public interface ProductDetailView extends ProductView.ProductListView, ProductView.Description, ProductView.Images {}
}
