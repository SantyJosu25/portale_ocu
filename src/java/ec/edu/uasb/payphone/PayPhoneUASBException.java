/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.payphone;

import java.util.Date;

/**
 *
 * @author victor.barba
 */
public class PayPhoneUASBException extends Exception {

    private int responseCode;
    private Date fecha;
    private String response;
    private String message;
    private int code;
    private String txt;

    public int getResponseCode() {
        return responseCode;
    }

    public PayPhoneUASBException() {
    }

    /**
     * Constructs an instance of <code>PayPhoneException</code> with the
     * specified detail message.
     *
     * @param responseCode
     * @param msg the detail message.
     */
    public PayPhoneUASBException(int responseCode, String msg) {
        super(msg);
        switch (responseCode) {
            case 400:
            case 404:
            case 500:
                this.responseCode = responseCode;
                break;
        }
    }

    public PayPhoneUASBException(String msg) {
        super(msg);
//
//        this.fecha = new Date();
//        JsonReader reader = Json.createReader(new StringReader(msg));
//        JsonObject jsonObject = reader.readObject();
//        this.code = Integer.parseInt(jsonObject.get("errorCode").toString());
//        this.message = jsonObject.get("message").toString();
//        this.txt = jsonObject.get("txt").toString();
//        this.responseCode = 200;
    }
}
