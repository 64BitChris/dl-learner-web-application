package controllers;

import models.KBFileUpload;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Router;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Chris Shellenbarger
 * Date: 2/5/12
 * Time: 12:43 PM
 */
public class KnowledgeSource extends Controller {


    public static void addKBFile(KBFileUpload upload) {
        upload.save();

        // Proper response is created if we succeed
        response.status = Http.StatusCode.CREATED;

        // Give the location of the newly created object.
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", upload.id);
        String location = Router.reverse("KnowledgeSource.get",params).url;

        response.setHeader("Location", location);
    }

    public static void get(Long id) {
        KBFileUpload result = KBFileUpload.findById(id);
        if (result == null) {
            notFound();
        }else {
            renderBinary(result.file.get(),result.name);
        }
    }

    public static void delete(Long id) {
        KBFileUpload result = KBFileUpload.findById(id);
        if (result == null) {
            notFound("Could not find file with id: " + id);
        }else{
            result.delete();
            ok();
        }
    }




}

