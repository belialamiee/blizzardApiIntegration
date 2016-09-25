package model;

/**
 * Created by ben on 15/09/2016.
 * Simple object for holding data of searches
 */
public class SearchItems {

    private String realm;
    private String name;

    public SearchItems(String realm, String name) {
        setRealm(realm);
        setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }
}
