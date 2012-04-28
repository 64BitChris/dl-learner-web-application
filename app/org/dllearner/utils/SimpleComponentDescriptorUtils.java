package org.dllearner.utils;

import models.ComponentDescriptor;
import models.SimpleComponentDescriptor;
import org.dllearner.core.AnnComponentManager;
import org.dllearner.core.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/28/12
 * Time: 4:40 PM
 *
 * Simple Implementation of ComponentDescriptorUtils.
 */
public class SimpleComponentDescriptorUtils implements ComponentDescriptorUtils {

    @Override
    public ComponentDescriptor convert(Class<? extends Component> component) {
        SimpleComponentDescriptor descriptor = new SimpleComponentDescriptor();
        descriptor.setName(AnnComponentManager.getName(component));
        descriptor.setShortName(AnnComponentManager.getShortName(component));
        descriptor.setDescription(AnnComponentManager.getDescription(component));
        return descriptor;
    }

    @Override
    public Collection<ComponentDescriptor> convert(Collection<Class<? extends Component>> components) {
        List<ComponentDescriptor> descriptors = new ArrayList<ComponentDescriptor>(components.size());
        for (Class<? extends Component> component : components) {
            descriptors.add(convert(component));
        }
        return descriptors;
    }
}
