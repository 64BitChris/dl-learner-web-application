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
 */
public class KnowledgeSource extends Controller {

    final static Logger logger = LoggerFactory.getLogger(KnowledgeSource.class);

    public static Result storeFile() {

        /** Extract the Uploaded File from the request */
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart ksFile = body.getFile("file");
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
                logger.info("Successfully Saved Uploaded File");
                return redirect(routes.KnowledgeSource.index());
            } else {
                logger.error("Could not save uploaded file - it was null.");
                flash("error", "Missing File");
                return redirect(routes.Application.index());
            }

        } catch (FileNotFoundException e) {
            return badRequest("Problem with the uploaded file");
        } catch (IOException e) {
            return badRequest("Problem reading raw bytes");
        }
    }

    public static Result retrieve(Long id) {
        logger.info("In Retrieve for id:" + id);

        KnowledgeSourceFileUpload ksFile = KnowledgeSourceFileUpload.find.byId(id);


        return ok(ksFile.file);

    }

    public static Result index() {
        List<KnowledgeSourceFileUpload> knowledgeSources = Ebean.find(KnowledgeSourceFileUpload.class).findList();
        return ok(views.html.KnowledgeSource.index.render(knowledgeSources));
    }

    public static Result upload() {
        return ok(views.html.KnowledgeSource.upload.render(form(KnowledgeSourceFileUpload.class)));
    }
}

