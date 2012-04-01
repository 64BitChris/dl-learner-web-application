package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Chris Shellenbarger
 * Date: 2/5/12
 * Time: 12:43 PM
 */
public class KnowledgeSource extends Controller {

    public static Result upload(){

        Http.MultipartFormData body = request().body().asMultipartFormData();

        Http.MultipartFormData.FilePart kbFile = body.getFile("kbfile");

        if (kbFile != null) {

            String fileName = kbFile.getFilename();
            String contentType = kbFile.getContentType();
            File file = kbFile.getFile();

            return ok("File Uploaded");
        }else{
            flash("error", "Missing File");
            return redirect(routes.Application.index());
        }

    }

    public static Result index(){

        return ok(views.html.knowledgesource.index.render());
//        return ok();
    }

//    public static void addKBFile(KBFileUpload upload) {
//        upload.save();
//
//
//
//        // Give the location of the newly created object.
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("id", upload.id);
//        String location = Router.reverse("knowledgesource.get", params).url;
//
//        // Proper response is created if we succeed
//        response().setHeader("Location", location);
//        Result response = created();
//        //response.setHeader("Location", location);
//    }

//    public static void get(Long id) {
//        KBFileUpload result = KBFileUpload.findById(id);
//        if (result == null) {
//            notFound();
//        }else {
//            renderBinary(result.file.get(),result.name);
//        }
//    }
//
//    public static void delete(Long id) {
//        KBFileUpload result = KBFileUpload.findById(id);
//        if (result == null) {
//            notFound("Could not find file with id: " + id);
//        }else{
//            result.delete();
//            ok();
//        }
//    }




}

