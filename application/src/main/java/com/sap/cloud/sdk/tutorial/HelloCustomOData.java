package com.sap.cloud.sdk.tutorial;

import com.mycompany.vdm.namespaces.zdemocds30cds.SecDsDemoCds30;
import com.mycompany.vdm.namespaces.zdemocds30cds.SecDsDemoCds30FluentHelper;
import com.mycompany.vdm.services.DefaultZDEMOCDS30CDSService;
import com.mycompany.vdm.services.ZDEMOCDS30CDSService;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hellocbo")
public class HelloCustomOData extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldServlet.class);

    private ZDEMOCDS30CDSService service;

    @Override
    protected void doGet( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException
    {

        logger.info("I am in!");

        HttpDestination httpDestination = DestinationAccessor.getDestination("S4H_30").asHttp();

        logger.info("httpDestination:" + httpDestination.toString());

        service = new DefaultZDEMOCDS30CDSService();
        SecDsDemoCds30FluentHelper helper = service.getAllZDEMOCDS30();

        List<SecDsDemoCds30> secDsDemoCds30s = helper.executeRequest(httpDestination);

        logger.info("secDsDemoCds30s size:" + secDsDemoCds30s.size());
        for (SecDsDemoCds30 item: secDsDemoCds30s) {
            logger.info(item.toString());
        }
        
        logger.info("I am running!");
        response.getWriter().write("Hello World!");
    }
}