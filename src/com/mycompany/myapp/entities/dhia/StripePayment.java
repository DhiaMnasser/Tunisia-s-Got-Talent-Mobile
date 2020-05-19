/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.dhia;

import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Klaizer
 */
public class StripePayment implements Serializable {

    private static Charge payementcharge;

    /* private String  api_key;
    private String devise;
    private String source;*/
    public static Token getToken(String api_key, String numerocarte, int month, int year, String ccv, String mymail) {
        Token mytoken = null;
        int etat = -1;

        try {

            Stripe.apiKey = api_key;
            Map<String, Object> tokenParams = new HashMap<String, Object>();
            Map<String, Object> cardParams = new HashMap<String, Object>();

            cardParams.put("number", numerocarte);
            cardParams.put("exp_month", month);
            cardParams.put("exp_year", year);
            cardParams.put("cvc", ccv);
            tokenParams.put("card", cardParams);
            tokenParams.put("email", mymail);

            mytoken = Token.create(tokenParams);
            etat = 0;
            System.out.println("okkkkkkkkkkkk");
        } catch (CardException e) {
            // Since it's a decline, CardException will be caught

            System.out.println("Status is: " + e.getCode());
            System.out.println("Message is: " + e.getMessage());
            etat += 1;
        } catch (RateLimitException e) {
            // Too many requests made to the API too quickly
            etat += 2;
            System.out.println("mssage1: " + e.getMessage());
        } catch (InvalidRequestException e) {
            System.out.println("mssage2: " + e.getMessage());
            etat += 3;
        } catch (AuthenticationException e) {
            System.out.println("mssage3: " + e.getMessage());
            etat += 4;
        } //        catch (APIConnectionException e) {
        //                    System.out.println(e.getMessage());
        //                      etat+=5;
        //                } 
        catch (StripeException e) {
            System.out.println("mssage4: " + e.getMessage());
            etat += 6;
        } catch (Exception e) {
            System.out.println("mssage5: " + e.getMessage());
            etat += 7;
        } finally {
            System.out.println("valeur etat: " + etat);

        }
        return mytoken;
    }

    public static Charge ChargePayement(String key, String devise, String source, double montant, String api_key, String numerocarte, int month, int year, String ccv, String mymail) {
        Charge mycharge = null;

//  RequestOptions requestOptions = (new RequestOptionsBuilder()).setApiKey(key).build();
        Stripe.apiKey = key;
        Map<String, Object> chargercommpte = new HashMap<String, Object>();
        chargercommpte.put("amount", (int) montant);
        chargercommpte.put("currency", devise);
        chargercommpte.put("source", source);
        //chargercommpte.put("customers", createCustomer(api_key,numerocarte,month,year,ccv,mymail));
        chargercommpte.put("description", createCustomer(api_key, numerocarte, month, year, ccv, mymail));

        try {
            mycharge = Charge.create(chargercommpte);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return mycharge;
    }

    public static Customer createCustomer(String api_key, String numerocarte, int month, int year, String ccv, String mymail) {
        Customer mycustomer = null;
        int etat = -1;
        Stripe.apiKey = api_key;
        Map<String, Object> customerinfomap = new HashMap<String, Object>();

        customerinfomap.put("email", mymail);
        customerinfomap.put("description", getToken(api_key, numerocarte, month, year, ccv, mymail));

        try {
            mycustomer = Customer.create(customerinfomap);
            etat = 0;
        } catch (CardException e) {
            // Since it's a decline, CardException will be caught

            System.out.println("Status is: " + e.getCode());
            System.out.println("Message is: " + e.getMessage());
            etat += 1;
        } catch (RateLimitException e) {
            // Too many requests made to the API too quickly
            etat += 2;
            System.out.println("mssage1: " + e.getMessage());
        } catch (InvalidRequestException e) {
            System.out.println("mssage2: " + e.getMessage());
            etat += 3;
        } catch (AuthenticationException e) {
            System.out.println("mssage3: " + e.getMessage());
            etat += 4;
        } //        catch (APIConnectionException e) {
        //                System.out.println(e.getMessage());
        //                  etat+=5;
        //            } 
        catch (StripeException e) {
            System.out.println("mssage4: " + e.getMessage());
            etat += 6;
        } catch (Exception e) {
            System.out.println("mssage5: " + e.getMessage());
            etat += 7;
        } finally {
            System.out.println("valeur etat: " + etat);

        }

        return mycustomer;
    }

}
