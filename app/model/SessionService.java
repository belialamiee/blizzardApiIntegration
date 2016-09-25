package model;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Service for Session
 * Created by ben on 5/08/2016.
 */
public class SessionService {

    public static final SessionService instance = new SessionService();

    protected ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    protected ConcurrentHashMap<String, String> sessionToUser = new ConcurrentHashMap<>();

    /**
     * Get Session given a string
     *
     * @param id String
     * @return Session
     */
    public Session get(String id) {
        return sessions.get(id);
    }

    /**
     * Adds a session to the list of Sessions
     *
     * @param s Session
     */
    public void put(Session s) {
        sessions.put(s.getId(), s);
    }

    /**
     * Get the user id from the user allocated to this session
     *
     * @param sessionId String
     * @return String
     */
    public String getUserIdForSession(String sessionId) {
        return sessionToUser.get(sessionId);
    }

    /**
     * Allocates a User to a session
     *
     * @param sessionId String
     * @param userId    String
     */
    public void setUserIdForSession(String sessionId, String userId) {
        sessionToUser.put(sessionId, userId);
    }

    /**
     * Gets a user allocated to a session
     *
     * @param sessionId String
     * @return User
     */
    public User getUserFromSession(String sessionId) {
        return UserService.instance.getUser(getUserIdForSession(sessionId));
    }

    /**
     * Logs a user out, essentially removes the session from the user.
     *
     * @param sessionId String
     */
    public void logout(String sessionId) {
        sessionToUser.remove(sessionId);
    }

}
