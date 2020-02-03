package fr.reference.it.referenceproject.domaine.dto;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = -561617689703108345L;
    private String message;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
