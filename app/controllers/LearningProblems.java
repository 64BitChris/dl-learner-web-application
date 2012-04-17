package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/22/12
 * Time: 7:49 PM
 *
 * Controller for the Learning Problems
 */
public class LearningProblems extends Controller {


    /**
     * Present the index page for managing Learning Problems.
     */
    public static Result index(){

        return ok(views.html.LearningProblems.index.render());
    }
}
