package org.urlTester.miniPostman.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class MainController {
    
    private final RestClient restClient;

    @FXML
    private ComboBox<String> methodComboBox;

    @FXML
    private TextField urlField;

    @FXML
    private TextArea body;

    @FXML
    private Button sendButton;

    @FXML
    private VBox headersContainer;

    private Map<HBox, Map.Entry<TextField, TextField>> headerFields = new HashMap<>();

    @FXML
    public void initialize() {
        methodComboBox.getItems().addAll("GET", "POST", "PUT", "DELETE");
        methodComboBox.getSelectionModel().selectFirst();
        addHeader(); // Add first header by default
    }

    @FXML
    private void sendRequestButton(){
        log.info("Sending request...");
        String response = sendRequest();
        log.info("Response: {}", response);
    }

    @FXML
    private void addHeader() {
        HBox headerRow = new HBox(5);
        TextField keyField = new TextField();
        TextField valueField = new TextField();
        Button deleteButton = new Button("X");

        keyField.setPromptText("Header Key");
        valueField.setPromptText("Header Value");

        deleteButton.setOnAction(e -> removeHeader(headerRow));

        headerRow.getChildren().addAll(keyField, valueField, deleteButton);
        headersContainer.getChildren().add(headerRow);

        headerFields.put(headerRow, Map.entry(keyField, valueField));
    }

    private void removeHeader(HBox headerRow) {
        headersContainer.getChildren().remove(headerRow);
        headerFields.remove(headerRow);
    }

    private Consumer<HttpHeaders> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headerFields.forEach((headerRow , entry) -> {
            String key = entry.getKey().getText();
            String value = entry.getValue().getText();
            if(!key.isEmpty() && !value.isEmpty())
                headers.add(key, value);
        });

        return new Consumer<HttpHeaders>() {
            @Override
            public void accept(HttpHeaders httpHeaders) {
                httpHeaders.putAll(headers);
            }
        };
    }

    private String sendRequest(){
        switch (methodComboBox.getValue()) {
            case "GET":
                return getRequest("GET");
            case "POST":
                return postRequest("POST");
            case "PUT":
                return putRequest("PUT");
            case "DELETE":
                return deleteRequest("DELETE");
            default:
                return "Invalid method";
        }
    }

    private String getRequest(String method){
        ResponseEntity<String> response = null;
        try {
            response = restClient.get()
                    .uri(urlField.getText())
                    .headers(getHeaders())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(String.class);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        if (response == null) {
            return "No response";
        }
        return response.getBody();
    }
    
    private String postRequest(String method) {
        ResponseEntity<String> response = null;
        try {
            response = restClient.post()
                    .uri(urlField.getText())
                    .headers(getHeaders())
                    .body(body.getText())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(String.class);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        if (response == null) {
            return "No response";
        }
        return response.getBody();
    }
    
    private String putRequest(String method) {
        ResponseEntity<String> response = null;
        try {
            response = restClient.put()
                    .uri(urlField.getText())
                    .headers(getHeaders())
                    .body(body.getText())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(String.class);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        if (response == null) {
            return "No response";
        }
        return response.getBody();
    }

    private String deleteRequest(String method) {
        ResponseEntity<String> response = null;
        try {
            response = restClient.delete()
                    .uri(urlField.getText())
                    .headers(getHeaders())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(String.class);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        if (response == null) {
            return "No response";
        }
        return response.getBody();
    }
}
