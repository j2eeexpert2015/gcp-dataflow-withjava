package dataflowwithjava.cloudfunction;

/**
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpResponse;
import dataflowwithjava.common.GCPConstants;
**/

import java.util.Collections;

public class TriggerDAG {
        /**
        implements HttpFunction {


    @Override
    public void service(com.google.cloud.functions.HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        String projectId = GCPConstants.PROJECT_ID;
        String location = GCPConstants.GCP_REGION;
        String composerEnvironment = "your-composer-environment";
        String dagId = "your-dag-id";

        // Load credential from the service account key file
        Credential credential = GoogleCredential.getApplicationDefault()
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/cloud-platform"));

        // Create an HTTP transport and JSON factory
        com.google.api.client.http.HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        // Build the request factory
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);

        // Create the URL to trigger the DAG
        String url = String.format("https://composer.googleapis.com/v1beta1/projects/%s/locations/%s/environments/%s/dags/%s/dagRuns",
                projectId, location, composerEnvironment, dagId);

        // Build and execute the request
        com.google.api.client.http.HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(url), null);
        request.getHeaders().setContentType("application/json");
        com.google.api.client.http.HttpResponse response = request.execute();

        if (response.isSuccessStatusCode()) {
            httpResponse.getWriter().write("DAG " + dagId + " triggered successfully.");
        } else {
            httpResponse.getWriter().write("Failed to trigger DAG. HTTP response code: " + response.getStatusCode());
        }

    }
*/

}
