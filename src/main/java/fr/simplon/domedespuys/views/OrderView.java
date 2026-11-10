package fr.simplon.domedespuys.views;

public class OrderView {
    public interface Id {}
    public interface Number {}
    public interface CreatedAt {}
    public interface Status {}
    public interface User {}
    public interface Address {}

    public interface OrderListView extends CreatedAt, Number, Status {}
    public interface OrderDetailView extends
            OrderListView,
            User,
            UserView.UserListView,
            Address
    {}
}
