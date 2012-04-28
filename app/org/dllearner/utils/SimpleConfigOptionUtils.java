package org.dllearner.utils;

import org.dllearner.core.AnnComponentManager;
import org.dllearner.core.Component;
import org.dllearner.core.config.ConfigHelper;
import org.dllearner.core.config.ConfigOption;
import org.dllearner.exception.NoComponentDefinedException;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/29/12
 * Time: 12:43 PM
 *
 * Simple implementation of of the ConfigOptionUtils interface.
 */
public class SimpleConfigOptionUtils implements ConfigOptionUtils{

    @Override
    public Collection<ConfigOption> configOptionsFor(String shortName,Collection<Class<? extends Component>> componentTypes) throws NoComponentDefinedException {

        Class<? extends Component> component = null;
        Iterator<Class<? extends Component>> itr = componentTypes.iterator();
        while (component == null && itr.hasNext()) {
            Class<? extends Component> componentType = itr.next();
            String componentShortName = AnnComponentManager.getShortName(componentType);
            if (componentShortName.equals(shortName)) {
                component = componentType;
            }
        }

        /** If a bad request comes in */
        if(component == null) {
            throw new NoComponentDefinedException("No Reasoner with the short name " + shortName + " could be found.");
        }

        Map<ConfigOption, Class<?>> configOptionTypes = ConfigHelper.getConfigOptionTypes(component);

        return configOptionTypes.keySet();
    }
}
