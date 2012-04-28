package org.dllearner.utils;

import org.dllearner.core.Component;
import org.dllearner.core.config.ConfigOption;
import org.dllearner.exception.NoComponentDefinedException;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/29/12
 * Time: 12:40 PM
 *
 * Helper interface for accessing Config Options
 */
public interface ConfigOptionUtils {


    /**
     * Get the collection of config options for the component whose short name is shortName.
     *
     * @param shortName The short name of the component to find the configOptions for
     * @param componentTypes The collection of component types to look through.
     * @return A Collection of config options.
     * @throws NoComponentDefinedException If there is no component in component types with shortName as the short name.
     */
    Collection<ConfigOption> configOptionsFor(String shortName,Collection<Class<? extends Component>> componentTypes) throws NoComponentDefinedException;
}
