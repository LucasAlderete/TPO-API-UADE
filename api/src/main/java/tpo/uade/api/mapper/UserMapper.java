package tpo.uade.api.mapper;

import org.mapstruct.Mapper;
import tpo.uade.api.model.database.UserDB;
import tpo.uade.api.model.frontend.User;

@Mapper
public interface UserMapper {

    User mapFromDatabaseEntity (UserDB userDB);

    UserDB mapToDatabaseEntity (User user);

}
