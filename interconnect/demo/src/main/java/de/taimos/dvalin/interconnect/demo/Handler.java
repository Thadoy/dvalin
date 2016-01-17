package de.taimos.dvalin.interconnect.demo;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.taimos.dvalin.interconnect.core.spring.RequestHandler;
import de.taimos.dvalin.interconnect.demo.api.IUserService;
import de.taimos.dvalin.interconnect.demo.api.UserError;
import de.taimos.dvalin.interconnect.demo.model.UserIVO_v1;
import de.taimos.dvalin.interconnect.demo.model.requests.CreateUserIVO_v1;
import de.taimos.dvalin.interconnect.demo.model.requests.DeleteUserIVO_v1;
import de.taimos.dvalin.interconnect.demo.model.requests.FindUserByIdIVO_v1;
import de.taimos.dvalin.interconnect.demo.model.requests.FindUserIVO_v1;
import de.taimos.dvalin.interconnect.demo.model.requests.UpdateUserIVO_v1;
import de.taimos.dvalin.interconnect.model.ivo.util.IVOQueryResultIVO_v1;
import de.taimos.dvalin.interconnect.model.service.ADaemonHandler;
import de.taimos.dvalin.interconnect.model.service.DaemonError;

@RequestHandler
public class Handler extends ADaemonHandler implements IUserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static ConcurrentHashMap<Long, UserIVO_v1> users = new ConcurrentHashMap<>();

    /**
     * @param context Context
     */
    public Handler(Context context) {
        super(context);
    }

    @Override
    public UserIVO_v1 createUser(CreateUserIVO_v1 ivo) throws DaemonError {
        long max = 0;
        for (Long id : users.keySet()) {
            if (id > max) {
                max = id;
            }
        }
        UserIVO_v1 newUser = ivo.getValue();
        UserIVO_v1.UserIVO_v1Builder builder = newUser.createBuilder();
        builder.withId(Long.toString(max + 1));
        UserIVO_v1 createdUser = builder.build();

        users.put(createdUser.getIdAsLong(), createdUser);
        return createdUser;
    }

    @Override
    public UserIVO_v1 saveUser(UpdateUserIVO_v1 ivo) throws DaemonError {
        UserIVO_v1 user = ivo.getValue();
        if (!users.containsKey(user.getIdAsLong())) {
            throw new DaemonError(UserError.USER_NOT_FOUND);
        }
        users.put(user.getIdAsLong(), user);
        return user;
    }

    @Override
    public UserIVO_v1 findById(FindUserByIdIVO_v1 ivo) throws DaemonError {
        if (!users.containsKey(Long.valueOf(ivo.getId()))) {
            throw new DaemonError(UserError.USER_NOT_FOUND);
        }
        return users.get(Long.valueOf(ivo.getId()));
    }

    @Override
    public void deleteUser(DeleteUserIVO_v1 ivo) throws DaemonError {
        if (!users.containsKey(Long.valueOf(ivo.getId()))) {
            throw new DaemonError(UserError.USER_NOT_FOUND);
        }
        users.remove(Long.valueOf(ivo.getId()));
    }

    @Override
    public IVOQueryResultIVO_v1<UserIVO_v1> findUsers(FindUserIVO_v1 query) throws DaemonError {
        LOGGER.info("Fetching users");
        // TODO filter
        ArrayList<UserIVO_v1> usersList = new ArrayList<>(users.values());
        return IVOQueryResultIVO_v1.create(usersList, usersList.size());
    }
}
