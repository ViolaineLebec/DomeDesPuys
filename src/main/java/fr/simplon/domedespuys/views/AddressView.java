package fr.simplon.domedespuys.views;

public class AddressView {
    public interface Id {}
    public interface Line {}
    public interface Extraline {}
    public interface City {}
    public interface Zipcode {}
    public interface Country {}
    public interface Phone {}
    public interface Firstname {}
    public interface Lastname {}


    public interface AddressListView extends Line, City {}
    public interface AddressDetailView extends
            AddressListView,
            Extraline,
            Zipcode,
            Country,
            Phone,
            Firstname,
            Lastname {}
}
