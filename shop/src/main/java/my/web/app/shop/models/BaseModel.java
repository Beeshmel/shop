package my.web.app.shop.models;

public class BaseModel {
    public static String getNewTocken(String login){
        return "reg".concat(login);
    }
}
