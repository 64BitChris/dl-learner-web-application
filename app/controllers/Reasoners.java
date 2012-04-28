package controllers;

import models.ComponentDescriptor;
import org.dllearner.core.AnnComponentManager;
import org.dllearner.core.Component;
import org.dllearner.core.ReasonerComponent;
import org.dllearner.core.config.ConfigOption;
import org.dllearner.exception.NoComponentDefinedException;
import org.dllearner.utils.ComponentDescriptorUtils;
import org.dllearner.utils.ConfigOptionUtils;
import org.dllearner.utils.SimpleComponentDescriptorUtils;
import org.dllearner.utils.SimpleConfigOptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/17/12
 * Time: 8:43 PM
 * <p/>
 * Controller for managing Reasoners.
 */
public class Reasoners extends Controller {

    private static Collection<Class<? extends Component>> reasoners =
            AnnComponentManager.getInstance().getComponentsOfType(ReasonerComponent.class);


    private final static Logger logger = LoggerFactory.getLogger(Reasoners.class);
    private final static ComponentDescriptorUtils descriptorUtils = new SimpleComponentDescriptorUtils();
    private final static ConfigOptionUtils configOptionUtils = new SimpleConfigOptionUtils();

    /**
     * Present the page for managing Reasoners.
     *
     * @return The HTML Page for managing reasoners.
     */
    public static Result index() {

        return ok(views.html.Reasoners.index.render());
    }


    /**
     * Present the menu for adding a Reasoner to the configuration.
     *
     * @return The HTML page containing the menu for adding a Reasoner to the configuration.
     */
    public static Result add() {
        Collection<ComponentDescriptor> descriptors = descriptorUtils.convert(reasoners);
        return ok(views.html.Reasoners.add.render(descriptors));
    }

    /**
     * Present the HTML page for adding a specific instance of a ReasonerComponent.
     *
     * @param reasonerType The string type of the reasoner to add - this corresponds with the short name of the component.
     * @return The HTML page for adding a specific instance of a ReasonerComponent.
     */
    public static Result addReasoner(String reasonerType) {

        try {
            Collection<ConfigOption> configOptions = configOptionUtils.configOptionsFor(reasonerType, reasoners);
            return ok(views.html.Reasoners.addReasoner.render(configOptions));
        } catch (NoComponentDefinedException e) {
            return notFound(e.getMessage());
        }
    }


    public static Result save() {

        logger.info(request().body().asText());
        Map<String, String[]> stringMap = request().body().asFormUrlEncoded();

        for (String s : stringMap.keySet()) {
            logger.info("Key: " + s);
            logger.info("Values:");
            for (String value : stringMap.get(s)) {
                logger.info("\t" + value);
            }
        }
        return ok("Save complete");
    }

}
