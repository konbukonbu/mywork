package y2022.multipart;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author こんぶおー
 * @see https://kodejava.org/how-do-i-do-multipart-upload-using-httpclient/
 * @see https://www.finddevguides.com/Apache-httpclient-multipart-upload
 */
public class HelloMultiPart {

  public static void main(String[] args) throws Exception, Exception {

    //Creating CloseableHttpClient object
     CloseableHttpClient httpclient = HttpClients.createDefault();

    //Creating a file object
     File file = new File("./input/uploadinput.txt");

    //Creating the FileBody object
     FileBody filebody = new FileBody(file, ContentType.DEFAULT_BINARY);

    //Creating the MultipartEntityBuilder
     MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create();

    //Setting the mode
     entitybuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
     
     entitybuilder.addPart("file", filebody);

     
    //Building a single entity using the parts
     HttpEntity mutiPartHttpEntity = entitybuilder.build();

    //Building the RequestBuilder request object
     RequestBuilder reqbuilder = RequestBuilder.post("http://httpbin.org/post");

    //Set the entity object to the RequestBuilder
     reqbuilder.setEntity(mutiPartHttpEntity);

    //Building the request
     HttpUriRequest multipartRequest = reqbuilder.build();

    //Executing the request
     HttpResponse httpresponse = httpclient.execute(multipartRequest);

    //Printing the status and the contents of the response
     System.out.println(EntityUtils.toString(httpresponse.getEntity()));
     System.out.println(httpresponse.getStatusLine());
  }

//  {
//    "args": {}, 
//    "data": "", 
//    "files": {
//      "file": "123\u3042\u3044\u3046\u3048\u304a\nthis is test!!"
//    }, 
//    "form": {}, 
//    "headers": {
//      "Accept-Encoding": "gzip,deflate", 
//      "Content-Length": "232", 
//      "Content-Type": "multipart/form-data; boundary=ByNJT_fIHIXHI-6dZ7gvwyEnJr7azRZC2P5e", 
//      "Host": "httpbin.org", 
//      "User-Agent": "Apache-HttpClient/4.5.5 (Java/11.0.13)", 
//      "X-Amzn-Trace-Id": "Root=1-62f04173-6206942107a0cedd223379bb"
//    }, 
//    "json": null, 
//    "origin": "180.58.231.129", 
//    "url": "http://httpbin.org/post"
//  }
//
//  HTTP/1.1 200 OK

  
}


