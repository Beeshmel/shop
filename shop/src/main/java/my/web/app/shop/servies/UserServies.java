package my.web.app.shop.servies;

import my.web.app.shop.models.User;

public interface UserServies {
        void create(User user);

        User read(String tocken);

}
