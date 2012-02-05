package controllers;

import org.junit.Before;
import org.junit.Test;
import play.mvc.Http;
import play.mvc.Router;
import play.test.Fixtures;
import play.test.FunctionalTest;
import play.vfs.VirtualFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Chris Shellenbarger
 * Date: 2/5/12
 * Time: 12:58 PM
 */
public class KnowledgeSourceTest extends FunctionalTest{
    
    
    
    @Before
    public void setUp(){
        //Clear the database.
        Fixtures.deleteDatabase();
    }


    @Test
    public void testUpload() {

        String url = Router.reverse("KnowledgeSource.addKBFile").url;


        Map<String, String> params = new HashMap<String, String>();
        String testFileName = "testFileName.dat";
        params.put("upload.name", testFileName);

        Map<String, File> fileMap = new HashMap<String, File>();

        //Access files from play
        VirtualFile file = VirtualFile.fromRelativePath("/conf/application.conf");
        
        fileMap.put("upload.file", file.getRealFile());
        // documentation from here: http://bit.ly/wKJ2lm
        Http.Response response = POST(url, params, fileMap);

        assertEquals((Integer)Http.StatusCode.CREATED, response.status);


        String location = response.getHeader("Location");
        response = GET(location);
        assertIsOk(response);
        
        //Check Content Disposition (file name)
        String contentDisposition = response.getHeader("Content-Disposition");
        assertTrue(contentDisposition.contains(testFileName));
        
        //Now test file size
        assertEquals(file.length(),getContent(response).length());
    }
}
