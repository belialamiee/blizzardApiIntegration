package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;


import java.util.ArrayList;
import java.util.List;

public class UserService {

    public static final UserService instance = new UserService();

    protected MongoClient mongoClient;

    /**
     * Constructor
     */
    protected UserService() {
        mongoClient = new MongoClient("127.0.0.1", 27017);
    }


    /**
     * Get a connection to Mongodb
     *
     * @return MongoDatabase
     */
    protected MongoDatabase getDB() {
        return mongoClient.getDatabase("comp360_bpratt2");
    }

    /**
     * Get the User data out of the database
     *
     * @return MongoCollection
     */
    protected MongoCollection<Document> getUserData() {
        return getDB().getCollection("User");
    }

    /**
     * Allocate and id for a user
     *
     * @return String
     */
    public String allocateId() {
        return new ObjectId().toHexString();
    }

    /**
     * Check tha an id is a valid UUID, UUID are hexidecimal
     *
     * @param id String
     * @return boolean
     */
    public boolean isValidId(String id) {
        try {
            ObjectId i = new ObjectId(id);
            return i.toHexString().equals(id);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Register a user and save them to the dababase
     *
     * @param u User
     * @return User
     */
    public User registerUser(User u) {
        // Let's first check the user isn't already registered
        if (getUserData().find(new Document("email", u.getEmail())).first() != null) {
            throw new IllegalArgumentException("That email address has already been registered");
        }
        insert(u);
        return u;
    }

    /**
     * Get a user from the database
     * Either returns null or converts it from BSON to a User and returns the user
     *
     * @param id String
     * @return User|null
     */
    public User getUser(String id) {
        Document d = getUserData().find(new Document("_id", new ObjectId(id))).first();
        if (d != null) {
            return userFromBson(d);
        } else {
            return null;
        }
    }

    /**
     * Get the user by email and password, returning null if they don't exist (or the password is wrong)
     *
     * @param email    String
     * @param password String
     * @return User|null
     */
    public User getUser(String email, String password) {
        Document d = getUserData().find(new Document("email", email)).first();

        // I wrote userFromBson to accept nulls
        User u = userFromBson(d);
        if (u != null && BCrypt.checkpw(password, u.getHash())) {
            return u;
        } else {
            return null;
        }
    }

    /**
     * Get the user who is logged in with this session, if there is one
     *
     * @param sessionId String
     * @return User
     */
    public User getUserFromSession(String sessionId) {
        Document d = getUserData().find(new Document("sessions._id", new ObjectId(sessionId))).first();
        return userFromBson(d);
    }

    /**
     * Converts a User to Bson so that it can be saved to the database
     *
     * @param u User
     * @return Document
     */
    protected static Document userToBson(User u) {
        List<Document> sessions = new ArrayList<>();
        for (Session s : u.getSessions()) {
            sessions.add(sessionToBson(s));
        }
        List<Document> searchItems = new ArrayList<>();

        for (SearchItems si : u.getSearchItems()) {
            searchItems.add(searchItemToBson(si));
        }

        return new Document("_id", new ObjectId(u.getId()))
                .append("email", u.email)
                .append("hash", u.getHash())
                .append("sessions", sessions)
                .append("searchItems", searchItems);
    }

    /**
     * Converts Bson to a user so that they can be used by the application
     *
     * @param d Document
     * @return User
     */
    protected static User userFromBson(Document d) {
        // This lets us call this method even if d is null
        if (d == null) {
            return null;
        }

        String id = d.getObjectId("_id").toHexString();
        String email = d.getString("email");
        String hash = d.getString("hash");
        User u = new User(id, email, hash);

        // This gives an unchecked warning; we'd need to use the safer means of doing this (which we don't cover)
        // to avoid the warning
        List<Document> sessions = d.get("sessions", List.class);

        for (Document sd : sessions) {
            Session s = sessionFromBson(sd);
            u.pushSession(s);
        }
        List<Document> searchItems = d.get("searchItems", List.class);
        for (Document si : searchItems) {
            SearchItems s = searchItemsFromBson(si);
            u.pushSearchItems(s);
        }
        return u;
    }

    /**
     * Converts Bson to a Session so that it can be used by the application
     *
     * @param d Document
     * @return Session
     */
    protected static Session sessionFromBson(Document d) {
        // This lets us call this method even if d is null
        if (d == null) {
            return null;
        }
        String id = d.getObjectId("_id").toHexString();
        String ip = d.getString("ipAddress");
        long since = d.getLong("since");
        return new Session(id, ip, since);
    }

    /**
     * Converts Bson to a SearchItems so that they can be used by the application
     *
     * @param d Document
     * @return User
     */
    protected static SearchItems searchItemsFromBson(Document d) {
        // This lets us call this method even if d is null
        if (d == null) {
            return null;
        }
        String realm = d.getString("realm");
        String name = d.getString("name");

        return new SearchItems(realm, name);
    }

    /**
     * Converts Session to Bson so that it can be saved to the database
     *
     * @param s Session
     * @return Document
     */
    protected static Document sessionToBson(Session s) {
        return new Document("_id", new ObjectId(s.getId()))
                .append("ipAddress", s.getIpAddress())
                .append("since", s.getSince());
    }

    /**
     * Converts SearchItems to Bson so that it can be saved to the database
     *
     * @param s SearchItems
     * @return Document
     */
    protected static Document searchItemToBson(SearchItems s) {
        return new Document()
                .append("realm", s.getRealm())
                .append("name", s.getName());
    }

    /**
     * Insert new record in the database
     *
     * @param u User
     */
    protected void insert(User u) {
        getUserData().insertOne(userToBson(u));
    }

    /**
     * Updates a record in the database
     *
     * @param u User
     */
    public void update(User u) {
        getUserData().replaceOne(new Document("_id", new ObjectId(u.getId())), userToBson(u));
    }
}