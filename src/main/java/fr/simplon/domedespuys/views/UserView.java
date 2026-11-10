package fr.simplon.domedespuys.views;

public class UserView {
    public interface Id {}
    public interface Email {}
    public interface Password {}
    public interface Firstname {}
    public interface Lastname {}
    public interface Phone {}
    public interface Purchases {}
    public interface Addresses {}

    public interface UserListView extends UserView.Firstname, UserView.Lastname {}
    public interface UserDetailView extends UserView.UserListView, UserView.Email, UserView.Purchases, UserView.Addresses {}
}
