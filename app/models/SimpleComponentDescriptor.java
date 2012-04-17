package models;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/17/12
 * Time: 8:51 PM
 *
 *
 */
public class SimpleComponentDescriptor implements ComponentDescriptor {

    private String name;
    private String shortName;
    private String description;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
