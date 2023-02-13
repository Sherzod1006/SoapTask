package transformer;

import entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import service.AddUserRequest;

@Component
public class UserHelper {
    public User prepareUserModel(AddUserRequest request){
        User user = new User();
        BeanUtils.copyProperties(request.getUserInfo(),user);
        return user;
    }
}
