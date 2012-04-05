package controllers;

import org.junit.Test;
import play.api.mvc.HandlerRef;
import play.mvc.Result;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;

import static play.test.Helpers.*;
/**
 * Created with IntelliJ IDEA.
 * User: chris.shellenbarger
 * Date: 4/4/12
 * Time: 7:46 PM
 *
 * Test the file upload controller.
 */
public class KnowledgeSourceTest{

    @Test
    public void testUpload(){
        HandlerRef<Result> handler = routes.ref.KnowledgeSource.upload();
        FakeRequest request = new FakeRequest();

        request.getWrappedRequest().body().asMultipartFormData();


        Map<String, String> bodyMap = new HashMap<String, String>();
        bodyMap.put("kbfile", "test this");
        request.withFormUrlEncodedBody(bodyMap);
        callAction(handler, request);
    }

}
