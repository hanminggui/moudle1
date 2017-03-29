package com.interfacetest.core;

import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.URI;


/**
 * Created by song on 2017/3/29.
 */
public class Base {




    @BeforeClass
    @Parameters({"host"})
    public void setUri() {
        try {
            URI uri = new URIBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
