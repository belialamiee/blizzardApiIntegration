package model;

/**
 * Session model
 */
public class Session {

    String id;

    String ipAddress;

    long since;

    /**
     * Constructor
     * @param id String
     * @param ipAddress String
     * @param since String
     */
    public Session(String id, String ipAddress, long since) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.since = since;
    }

    //Gettters
    public String getId() {
        return id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public long getSince() {
        return since;
    }

}