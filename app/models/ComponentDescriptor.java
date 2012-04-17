package models;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/17/12
 * Time: 8:54 PM
 * <p/>
 * Object used to describe a component for use in displaying summary information (name, short name, and description).
 * <p/>
 * This is used to describe components registered with the AnnComponentManager
 *
 * @see org.dllearner.core.AnnComponentManager
 */
public interface ComponentDescriptor {

    /**
     * Get the Name of the Component.
     *
     * @return the name of this component.
     */
    String getName();

    /**
     * Get the short name of the component this is used to reference the component within the AnnComponentManager.
     *
     * @return The Short name of this component
     */
    String getShortName();

    /**
     * Get the description of this component.
     *
     * @return the description of this component.
     */
    String getDescription();
}
