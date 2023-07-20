package com.salmon.test.step_definitions.gui;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.ValidatableResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import static com.jayway.restassured.RestAssured.given;


public class PrePayGiftCardApi {

    private final String PRE_PAY_STAGING_URL = "https://staging01-webauth.prepaytec.com/webauth/xmlauth?client=";
    private final String SPONSOR_CODE = "825218919";

    private String amount;
    private String currency;
    private String accountNo;
    private String accountPin;


    public PrePayGiftCardApi amount(String amount) {
        this.amount = amount;
        return this;
    }

    public PrePayGiftCardApi currency(String currency) {
        this.currency = currency;
        return this;
    }

    public PrePayGiftCardApi accountNo(String accountNo) {
        this.accountNo = accountNo;
        return this;

    }

    public PrePayGiftCardApi accountPin(String accountPin) {
        this.accountPin = accountPin;
        return this;
    }

    public String getBalance() {
        String uri=PRE_PAY_STAGING_URL + SPONSOR_CODE;
        String body = requestPayload(
                "BAL",
                "0",
                "GBP",
                this.accountNo,
                this.accountPin,
                timeStamp());
        ValidatableResponse resp = given()
                .contentType(ContentType.XML)
                .body(body)
                .post(uri).then();
        XmlPath response =   resp.assertThat().statusCode(200).extract().xmlPath();


        if (response.getInt("response.code") == 00) {
            return response.getString("response.end_bal.@amt");
        } else {
            return response.getString("response.msg");
        }
    }

    public String setBalance() {

        XmlPath response = given()
                .contentType(ContentType.XML)
                .body(requestPayload(
                        "TOP",
                        this.amount,
                        this.currency,
                        this.accountNo,
                        this.accountPin,
                        timeStamp()))
                .post(PRE_PAY_STAGING_URL + SPONSOR_CODE).then().assertThat().statusCode(200).extract()
                .xmlPath();

        if (response.getInt("response.code") == 00) {
            return response.getString("response.end_bal.@amt");
        } else {
            return response.getString("response.msg");
        }
    }


    private String timeStamp() {

        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss ZZZ");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return simpleDateFormat.format(date);
    }

    private String md5Hashing(String requestType, String origin, String accountDetails, String timeStamp, String sharedSecret) {

        String request = "request" + requestType + origin + accountDetails + timeStamp + sharedSecret;

        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            final byte[] digest = messageDigest.digest(request.getBytes("UTF-8"));

            return DatatypeConverter.printHexBinary(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String requestPayload(String type, String amount, String currency, String accountNo, String accountPin, String timeStamp) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        StringWriter stringWriter = new StringWriter();

        try {

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element request = document.createElement("request");
            document.appendChild(request);

            request.setAttribute("version", "15.2");
            request.setAttribute("type", type);
            String ORIGIN = "70283571";
            request.setAttribute("origin", ORIGIN);
            request.setAttribute("action", "Auth");

            Element value = document.createElement("value");
            value.setAttribute("amt", amount);
            value.setAttribute("currency", currency);
            request.appendChild(value);

            Element account = document.createElement("account");
            account.setAttribute("no", accountNo);
            account.setAttribute("pin", accountPin);
            request.appendChild(account);

            Element hash = document.createElement("hash");
            hash.appendChild(document
                    .createTextNode(md5Hashing(type, ORIGIN, accountNo, timeStamp, "W3bAuth_Hybri$_UAT")));
            request.appendChild(hash);

            Element tstamp = document.createElement("tstamp");
            tstamp.setAttribute("val", timeStamp);
            tstamp.setAttribute("format", "DD/MM/CCYY HH24:MI:SS ZZZ");
            request.appendChild(tstamp);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
        return stringWriter.toString();
    }

}
