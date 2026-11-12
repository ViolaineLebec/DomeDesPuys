package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.UserPostDTO;
import fr.simplon.domedespuys.dto.UserPutDTO;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.repositories.UserRepository;
import fr.simplon.domedespuys.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    @Override
    public User getById(String id) {
        return userRepository
                .findById(Long.parseLong(id))
                .orElse(null);
    }

    @Override
    public User create(UserPostDTO dto) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }



    @Override
    public User update(String id, UserPutDTO dto) {
        return null;
    }

}
