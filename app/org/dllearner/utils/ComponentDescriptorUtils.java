package org.dllearner.utils;

import models.ComponentDescriptor;
import org.dllearner.core.Component;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/28/12
 * Time: 4:31 PM
 * <p/>
 * Untili
 */
public interface ComponentDescriptorUtils {


    /**
     * Convert a class which extends Component into a ComponentDescriptor
     *
     * @param component The component to convert.
     * @return The component descriptor.
     */
    ComponentDescriptor convert(Class<? extends Component> component);

    /**
     * Convert a collection of classes which extend Component into a collection of component descriptors.
     *
     * @param components The components to convert
     * @return A collection of component descriptors.
     */
    Collection<ComponentDescriptor> convert(Collection<Class<? extends Component>> components);

}
