package controllers;

import org.dllearner.algorithms.celoe.CELOE;
import org.dllearner.algorithms.ocel.OCEL;
import org.dllearner.core.config.ConfigHelper;
import org.dllearner.core.config.ConfigOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.DataBinder;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/16/12
 * Time: 6:13 PM
 */
public class LearningAlgorithms extends Controller {

    final static Logger logger = LoggerFactory.getLogger(LearningAlgorithms.class);


    /**
     * Present the index page for managing Learning Problems.
     */
    public static Result index(){

        return ok(views.html.LearningAlgorithms.index.render());
    }
    /**
     * List all of the Learning Algorithms registered with the system.
     *
     * @return The
     */
    public static Result list() {

        return ok();
    }


    /**
     * Display the page where the user can select the parameters for a new learning algorithm.
     *
     * @return the page where the user can select the parameters for a new learning algorithm.
     */
    public static Result addLearningAlgorithm() {

//        DataBinder binder = new DataBinder(new OCEL());
//        binder.initBeanPropertyAccess();
//        binder.initDirectFieldAccess();

        BeanWrapper wrapper = new BeanWrapperImpl(new CELOE());

        Map<ConfigOption,Class<?>> configOptions = ConfigHelper.getConfigOptionTypes(CELOE.class);
        logger.info("Size: " + configOptions.size());
        return ok(views.html.LearningAlgorithms.add.render(form(CELOE.class), configOptions.keySet()));
    }


    public static Result create() {

        return ok();

    }
}
