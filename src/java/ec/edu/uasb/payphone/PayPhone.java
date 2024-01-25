/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.payphone;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author victor.barba
 */
public class PayPhone {

    private static PrintWriter writer;
    private static OutputStream outputStream;
    private static final String BOUNDARY = Long.toString(System.currentTimeMillis());
    private static final String LINE_END = "\r\n";
    private static final String TWOHYPHENS = "--";
    private static PayPhoneConfirmation payPhoneConfirmation;

    //<editor-fold defaultstate="collapsed" desc="Envío y lectura de datos">
    protected static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ex) {
        }
    }

    protected static void sendData(HttpURLConnection httpsURLConn, String inputString) throws IOException {
        OutputStream os = null;
        try {
            System.out.println("entrando sendData ");
            os = httpsURLConn.getOutputStream();
            System.out.println("os " + os);
            byte[] input = inputString.getBytes("utf-8");
            System.out.println("input " + input);
            os.write(input, 0, input.length);
        } catch (IOException ex) {
            System.out.println("Error sendData " + ex.getMessage());
            throw ex;
        } finally {
            PayPhone.closeQuietly(os);
        }
    }

    private static String readData(HttpURLConnection httpsURLConn) throws IOException {
        BufferedReader in = null;
        String inputLine;
        StringBuilder body;
        try {
//            System.out.println("entrando readData ");
            in = new BufferedReader(new InputStreamReader(httpsURLConn.getInputStream()));
            body = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();
            return body.toString();
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            PayPhone.closeQuietly(in);
        }
    }
    //</editor-fold>   

    //<editor-fold defaultstate="collapsed" desc="Confirmar pago">
    protected static void disconnectQuietly(HttpURLConnection httpsURLConnection) {
        if (httpsURLConnection != null) {
            httpsURLConnection.disconnect();
        }
    }

    public static PayPhoneConfirmation getConfirmacionPago(String resourceOwner, String id, String clientTransactionId)
            throws PayPhoneUASBException {
        HttpURLConnection httpsURLConn = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonObjectBuilder jsonObjBuilderSecret = Json.createObjectBuilder().add("id", id).add("clientTxId", clientTransactionId);
//            java.lang.System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
            URL url = new URL("https://pay.payphonetodoesposible.com/api/button/V2/Confirm");
            httpsURLConn = (HttpsURLConnection) url.openConnection();
            httpsURLConn.setRequestProperty("Authorization", "Bearer " + resourceOwner);
            httpsURLConn.setRequestProperty("Content-Type", "application/json; utf-8");
            httpsURLConn.setRequestProperty("Accept", "application/json");
            httpsURLConn.setRequestMethod("POST");
            httpsURLConn.setUseCaches(false);
            httpsURLConn.setDoInput(true);
            httpsURLConn.setDoOutput(true);
            httpsURLConn.setConnectTimeout(5000);
            PayPhone.sendData(httpsURLConn, jsonObjBuilderSecret.build().toString());
//            System.out.println(" httpsURLConn.getResponseCode() " + httpsURLConn.getResponseCode());
            switch (httpsURLConn.getResponseCode()) {
                case 200:
                case 201:
                    String returnValue = PayPhone.readData(httpsURLConn);
                    payPhoneConfirmation = objectMapper.readValue(returnValue, PayPhoneConfirmation.class);
                    break;
                case 404:
                    throw new PayPhoneUASBException(httpsURLConn.getResponseCode(), "La transacción: " + clientTransactionId + " no existe, verifique que el identificador enviado sea correcto.");
                case 400:
                case 500:
                    throw new PayPhoneUASBException(httpsURLConn.getResponseCode(), httpsURLConn.getResponseMessage());
            }
        } catch (MalformedURLException ex) {
            throw new PayPhoneUASBException("MalformedURLException " + ex.getMessage());
        } catch (IOException ex) {
            throw new PayPhoneUASBException("IOException " + ex.getMessage());
        } finally {
            disconnectQuietly(httpsURLConn);
        }
        return payPhoneConfirmation;
    }
    //</editor-fold>
}
