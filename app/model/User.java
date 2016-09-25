package model;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User Model
 */
public class User {

    String id;

    String email;

    String hash;

    ConcurrentHashMap<String, Session> activeSessions = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, SearchItems> searchItems = new ConcurrentHashMap<>();

    /**
     * Constructor
     *
     * @param id    String
     * @param email String
     * @param hash  String
     */
    public User(String id, String email, String hash) {
        this.id = id;
        this.email = email;
        this.hash = hash;
    }

    //Getters and Setters

    /**
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return String
     */
    public String getHash() {
        return hash;
    }

    /**
     * Is a particular session active on this user?
     *
     * @param sessionId String
     * @return boolean
     */
    public boolean hasSession(String sessionId) {
        return activeSessions.containsKey(sessionId);
    }

    /**
     * @return Session[]
     */
    public Session[] getSessions() {
        Collection<Session> values = activeSessions.values();
        return values.toArray(new Session[values.size()]);
    }

    /**
     * @return SearchItems[]
     */
    public SearchItems[] getSearchItems() {
        Collection<SearchItems> values = searchItems.values();
        return values.toArray(new SearchItems[values.size()]);
    }

    /**
     * Add a SearchItem to the user
     *
     * @param s SearchItems
     */
    public void pushSearchItems(SearchItems s) {
        searchItems.put(s.getName(), s);
    }

    /**
     * Record that a particular session is logged in as this user
     *
     * @param s Session
     */
    public void pushSession(Session s) {
        activeSessions.put(s.id, s);
    }

    /**
     * Remove an active session from this user
     *
     * @param sessionId String
     */
    public void removeSession(String sessionId) {
        activeSessions.remove(sessionId);
    }


}