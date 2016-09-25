package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import model.*;
import org.mindrot.jbcrypt.BCrypt;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;

/**
 * Controller for logging in, registering , logging out and displaying character information
 */
public class UserController extends Controller {

    public final static String sessionVar = "MY_SESSION";

    /**
     * Get the UserService for obtaining user information.
     *
     * @return UserService
     */
    private UserService getUserService() {
        return UserService.instance;
    }

    /**
     * Get the SessionService for obtaining session information.
     *
     * @return SessionService
     */
    private SessionService getSessionService() {
        return SessionService.instance;
    }

    /**
     * get the session id and allocate one if not.
     *
     * @return String
     */
    protected String getSessionId() {
        String id = session(sessionVar);
        if (!getUserService().isValidId(id)) {
            id = getUserService().allocateId();
            session(sessionVar, id);
        }
        return id;
    }

    /**
     * Display a login form
     *
     * @return Result
     */
    public Result loginForm() {
        return ok(views.application.html.login.render(null));
    }

    /**
     * Display a registration form
     *
     * @return Result
     */
    public Result registerForm() {
        return ok(views.application.html.register.render(null));
    }

    /**
     * Perform the login action.
     *
     * @return Result
     */
    public Result doLogin() {

        String email = request().body().asFormUrlEncoded().get("email")[0];
        String password = request().body().asFormUrlEncoded().get("password")[0];
        String sessionId = getSessionId();
        User u = getUserService().getUser(email, password);
        if (u != null) {
            SessionService.instance.setUserIdForSession(sessionId, u.getId());
            session("sessionId", sessionId);
            session("userId", u.getId());
        } else {
            return ok(views.application.html.register.render("email Address or password incorrect"));
        }
        return redirect("/characters");
    }

    /**
     * Register a new user
     *
     * @return Result
     */
    public Result doRegister() {
        String sessionId = getSessionId();
        String email;
        String password;

        // We're doing this very basically, as Play forms are not in scope for the course
        // (The unit prefers to teach things that are a little closer to the HTTP, rather than convenience wrappers)
        try {
            email = request().body().asFormUrlEncoded().get("email")[0];
            password = request().body().asFormUrlEncoded().get("password")[0];
        } catch (Exception e) {
            return badRequest(views.application.html.login.render("Email and password could not be found in the request"));
        }

        // Create a new user object
        User u = new User(getUserService().allocateId(), email, BCrypt.hashpw(password, BCrypt.gensalt()));

        // Try to register them
        try {
            getUserService().registerUser(u);
        } catch (Exception ex) {
            return badRequest(views.application.html.register.render(ex.getMessage()));
        }

        // Log them in
        u.pushSession(new Session(sessionId, request().remoteAddress(), System.currentTimeMillis()));
        return redirect("/characters");
    }


    /**
     * This is the crux of our application, this is where we get our character data showing properly.
     *
     * @return Result
     */
    public Result characters() {
        if (!isLoggedIn()) {
            return redirect("/");
        }
        return ok(views.application.html.characters.render());
    }

    /**
     * log the user out.
     * todo this is crashing when user is not logged in.
     *
     * @return Result
     */
    public Result logout() {
        if (isLoggedIn()) {
            return redirect("/");
        }
        String sessionId = getSessionId();
        getSessionService().logout(sessionId);
        return redirect("/");
    }


    /**
     * Primitive acl check to see if user is logged in or not.
     *
     * @return boolean
     */
    public boolean isLoggedIn() {
        if (session("userId") != null) {
            User u = getUserService().getUser(session("userId"));
            if (u == null) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Ajax End point for getting the Users previous search items
     * ideally this should also take in a userId so that this can be called remotely without a session.
     * todo implement this properly.
     *
     * @return Result
     */
    public Result ajaxSearchItems() {

        //ideally this will return a list of search items grabbed from the database and converted to json
        ArrayNode an = Json.newArray();
        User u = getUserService().getUser(session("userId"));
        for (SearchItems s : u.getSearchItems()) {
            ObjectNode n = Json.newObject();
            n.put("realm", s.getRealm());
            n.put("name", s.getName());
            an.add(n);
        }
        return ok(an);
    }

    /**
     * Ajax endpoint for adding search items to a user.
     *
     * @return Result
     */
    public Result addSearchItem() {
        JsonNode realm = request().body().asJson().get("realm");
        JsonNode name = request().body().asJson().get("name");
        User u = getUserService().getUser(session("userId"));
        SearchItems s1 = new SearchItems(realm.asText(), name.asText());
        u.pushSearchItems(s1);
        getUserService().update(u);
        return ok();
    }
}