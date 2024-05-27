package my.web.app.shop.servies;

import my.web.app.shop.models.User;
import my.web.app.shop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiesImpl implements UserServies{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public User read(String tocken) {
        return userRepository.findByTocken(tocken);
    }
}
