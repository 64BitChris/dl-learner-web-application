package controllers;

import com.avaje.ebean.Ebean;
import models.KnowledgeSourceFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.*;
import java.util.List;

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

    final static Logger logger = LoggerFactory.getLogger(KnowledgeSource.class);

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

