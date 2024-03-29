package controllers;

import com.avaje.ebean.Ebean;
import models.ComponentDescriptor;
import models.KnowledgeSourceFileUpload;
import org.dllearner.core.AnnComponentManager;
import org.dllearner.core.Component;
import org.dllearner.core.config.ConfigOption;
import org.dllearner.exception.NoComponentDefinedException;
import org.dllearner.utils.ComponentDescriptorUtils;
import org.dllearner.utils.ConfigOptionUtils;
import org.dllearner.utils.SimpleComponentDescriptorUtils;
import org.dllearner.utils.SimpleConfigOptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Chris Shellenbarger
 * Date: 2/5/12
 * Time: 12:43 PM
 * <p/>
 * An HTML Based Controller responsible for the managing of Knowledge Sources stored by the server.  We have to manage the Knowlege
 * Sources through a web interface because we can't make the assumption that the user knows anything about the server-side
 * filesystem and its contents (and we wouldn't want to provide direct access to this anyway).
 */
public class KnowledgeSource extends Controller {

    private final static Logger logger = LoggerFactory.getLogger(KnowledgeSource.class);
    private static Collection<Class<? extends Component>> knowledgeSources =
            AnnComponentManager.getInstance().getComponentsOfType(org.dllearner.core.KnowledgeSource.class);

    private final static ComponentDescriptorUtils descriptorUtils = new SimpleComponentDescriptorUtils();
    private final static ConfigOptionUtils configOptionUtils = new SimpleConfigOptionUtils();

    /**
     * HTML Page for selecting a Knowledge Source to add.
     *
     * @return An HTML Page for selecting a Knowledge Source to add.
     */
    public static Result add() {
        Collection<ComponentDescriptor> descriptors = descriptorUtils.convert(knowledgeSources);
        return ok(views.html.KnowledgeSource.add.render(descriptors));
    }

    /**
     * Present the HTML page for adding a specific instance of a KnowledgeSource.
     *
     * @param ksType The string type of the knowledge source to add - this corresponds with the short name of the component.
     * @return The HTML page for adding a specific instance of a ReasonerComponent.
     */
    public static Result addKnowledgeSource(String ksType) {
        try {
            Collection<ConfigOption> configOptions = configOptionUtils.configOptionsFor(ksType, knowledgeSources);
            return ok(views.html.KnowledgeSource.addKnowledgeSource.render(configOptions));
        } catch (NoComponentDefinedException e) {
            return notFound(e.getMessage());
        }
    }

    /**
     * Save the config options.
     *
     * @return The config options.
     */
    public static Result save(){

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

    /**
     * This is the method which gets called via POST to store a KS File on the server.
     *
     * @return A redirect to the KS Index page if file storage is successful
     */
    public static Result storeFile() {

        /** Extract the Uploaded File from the request */
        Http.MultipartFormData body = request().body().asMultipartFormData();
        if (body == null) {
            return badRequest("No Body Present on Request");
        }

        Http.MultipartFormData.FilePart ksFile = body.getFile("file");
        if (ksFile == null) {
            return badRequest("No 'file' element present in the body");
        }

        File uploadedFile = ksFile.getFile();
        /** Extract the non-file fields */
        Form<KnowledgeSourceFileUpload> form = form(KnowledgeSourceFileUpload.class);
        KnowledgeSourceFileUpload knowledgeSourceFileUpload = form.bindFromRequest().get();

        try {
            FileInputStream fis = new FileInputStream(uploadedFile);
            /** Convert bytes -  Limit of 4GB, seems reasonable */
            byte[] bytes = new byte[(int) uploadedFile.length()];
            fis.read(bytes);

            knowledgeSourceFileUpload.file = bytes;
            if (knowledgeSourceFileUpload.file != null) {
                knowledgeSourceFileUpload.save();
                logger.debug("Successfully Saved Uploaded File");
                return redirect(routes.KnowledgeSource.index());
            } else {
                logger.error("Could not save uploaded file - it was null.");
                flash("error", "Missing File");
                return redirect(routes.Application.index());
            }

        } catch (FileNotFoundException e) {
            return internalServerError("Problem with the uploaded file");
        } catch (IOException e) {
            return internalServerError("Problem reading raw bytes");
        }
    }

    /**
     * Retrieve the binary file associated for the Knowledge Source with the matching id.
     *
     * @param id The id of the Knowledge Source to retrieve.
     * @return The raw, binary file of the knowledge source.
     */
    public static Result retrieve(Long id) {
        KnowledgeSourceFileUpload ksFile = KnowledgeSourceFileUpload.find.byId(id);

        Result result = null;
        if (ksFile != null) {
            result = ok(ksFile.file);
        } else {
            result = notFound("No Knowledge Source found with id: " + id);
        }

        return result;
    }

    /**
     * Provide an HTML index page listing all of the currently stored Knowledge Sources.
     *
     * @return An HTML index page listing all of the currently stored Knowledge Sources.
     */
    public static Result index() {
        List<KnowledgeSourceFileUpload> knowledgeSources = Ebean.find(KnowledgeSourceFileUpload.class).findList();
        return ok(views.html.KnowledgeSource.index.render(knowledgeSources));
    }

    /**
     * This method returns an HTML view where a user can enter the information related to a KS file.
     *
     * @return HTML Display For Uploading a KS File
     */
    public static Result upload() {
        return ok(views.html.KnowledgeSource.upload.render(form(KnowledgeSourceFileUpload.class)));
    }
}

