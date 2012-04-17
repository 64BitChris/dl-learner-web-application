package controllers;

import models.ComponentDescriptor;
import models.SimpleComponentDescriptor;
import org.dllearner.core.AnnComponentManager;
import org.dllearner.core.Component;
import org.dllearner.core.ReasonerComponent;
import org.dllearner.core.config.ConfigHelper;
import org.dllearner.core.config.ConfigOption;
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


    final static Logger logger = LoggerFactory.getLogger(Reasoners.class);


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

        List<ComponentDescriptor> reasoners = new ArrayList<ComponentDescriptor>();

        for (Class<? extends Component> component : Reasoners.reasoners) {

            SimpleComponentDescriptor descriptor = new SimpleComponentDescriptor();
            descriptor.setName(AnnComponentManager.getName(component));
            descriptor.setShortName(AnnComponentManager.getShortName(component));
            descriptor.setDescription(AnnComponentManager.getDescription(component));
            reasoners.add(descriptor);

        }

        return ok(views.html.Reasoners.add.render(reasoners));
    }

    /**
     * Present the HTML page for adding a specific instance of a ReasonerComponent.
     *
     * @param reasonerType The string type of the reasoner to add - this corresponds with the short name of the component.
     * @return The HTML page for adding a specific instance of a ReasonerComponent.
     */
    public static Result addReasoner(String reasonerType) {

//        AnnComponentManager.getInstance().getComponentsOfType(Re)

        Class<? extends Component> component = null;
        Iterator<Class<? extends Component>> itr = reasoners.iterator();
        while (component == null && itr.hasNext()) {
            Class<? extends Component> reasoner = itr.next();
            String shortName = AnnComponentManager.getShortName(reasoner);
            if (shortName.equals(reasonerType)) {
              component = reasoner;
            }
        }

        /** If a bad request comes in */
        if(component == null){
            return notFound("No Reasoner with the short name " + reasonerType + " could be found.");
        }

        Map<ConfigOption, Class<?>> configOptionTypes = ConfigHelper.getConfigOptionTypes(component);

        logger.info("Size: " + configOptionTypes.size());

        return ok(views.html.Reasoners.addReasoner.render(configOptionTypes.keySet()));
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
