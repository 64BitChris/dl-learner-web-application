package test.controllers;

import controllers.routes;
import org.junit.Test;
import static org.junit.Assert.*;

import play.mvc.Result;
import play.test.ExtendedFakeRequest;

import static play.test.Helpers.*;
/**
 * Created with IntelliJ IDEA.
 * User: chris.shellenbarger
 * Date: 4/4/12
 * Time: 7:46 PM
 *
 * Test the file upload controller.
 *
 * Currently this test is not complete as I need to figure out how to attach the test file to the FakeRequest object.
 */
public class KnowledgeSourceTest{

    /**
     * This test is using the Play Scala API for testing as the FakeRequest is more robust.
     */
    @Test
    public void testStoreFile(){
        String url = routes.KnowledgeSource.storeFile().url();
        ExtendedFakeRequest fakeRequest = new ExtendedFakeRequest(POST, url);
        Result result = routeAndCall(fakeRequest);
        assertEquals(400, status(result));

        /** Faking out an MultipartFormData object isn't quite plumbed through in Play 2.0 Java yet */
    }

}
