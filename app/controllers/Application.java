package controllers;

import play.mvc.*;

import views.html.*;

/**
 * HTML Based controller for the DL-Learner Web Application
 */
public class Application extends Controller {

    /**
     * Main HTML index page of the DL Learner Web Application.
     *
     * @return The HTML Front page for the DL-Learner application.
     */
    public static Result index() {
        return ok(index.render());
    }

}