package com.sap.cloud.sdk.tutorial;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;

import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultSalesOrderService;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.SalesOrderService;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.salesorder.SalesOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.salesorder.SalesOrderCreateFluentHelper;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.salesorder.SalesOrderFluentHelper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hellosolist")

public class HelloSalesOrders  extends HttpServlet
{
    private SalesOrderService salesOrderService;
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(HelloSalesOrders.class);

    @Override
    protected void doGet( final HttpServletRequest request, final HttpServletResponse response )
            throws IOException
    {
    
        logger.info("I am in!");

        HttpDestination httpDestination = DestinationAccessor.getDestination("S4H_30").asHttp();
        
        logger.info("httpDestination:" + httpDestination.toString());
        
        salesOrderService = new DefaultSalesOrderService();

        //SalesOrder salesOrder = new SalesOrder();
       // SalesOrderCreateFluentHelper chelper = salesOrderService.createSalesOrder(salesOrder);
        //chelper.


        SalesOrderFluentHelper helper = salesOrderService.getAllSalesOrder().top(5);
        
        List<SalesOrder> salesOrders = helper.executeRequest(httpDestination);
        
        logger.info("salesOrderItemTexts size:" + salesOrders.size());
        
        StringBuffer stringBuffer = new StringBuffer();
        for (SalesOrder item: salesOrders) {
            logger.info(item.toString());
            stringBuffer.append(item.toString());
            stringBuffer.append("<br>");
        }
        
        logger.info("I am running!");
        response.getWriter().write(stringBuffer.toString());
    }
}
