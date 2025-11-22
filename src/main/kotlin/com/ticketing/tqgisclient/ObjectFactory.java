
package com.ticketing.tqgisclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ticketing.tqgisclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StartTicket_QNAME = new QName("http://webservice.view.TQGIS/", "startTicket");
    private final static QName _StartNewWorkflowInstance_QNAME = new QName("http://webservice.view.TQGIS/", "StartNewWorkflowInstance");
    private final static QName _StartNewWorkflowInstanceResponse_QNAME = new QName("http://webservice.view.TQGIS/", "StartNewWorkflowInstanceResponse");
    private final static QName _StartTicketResponse_QNAME = new QName("http://webservice.view.TQGIS/", "startTicketResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ticketing.tqgisclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StartNewWorkflowInstance }
     * 
     */
    public StartNewWorkflowInstance createStartNewWorkflowInstance() {
        return new StartNewWorkflowInstance();
    }

    /**
     * Create an instance of {@link StartNewWorkflowInstanceResponse }
     * 
     */
    public StartNewWorkflowInstanceResponse createStartNewWorkflowInstanceResponse() {
        return new StartNewWorkflowInstanceResponse();
    }

    /**
     * Create an instance of {@link StartTicketResponse }
     * 
     */
    public StartTicketResponse createStartTicketResponse() {
        return new StartTicketResponse();
    }

    /**
     * Create an instance of {@link StartTicket }
     * 
     */
    public StartTicket createStartTicket() {
        return new StartTicket();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.view.TQGIS/", name = "startTicket")
    public JAXBElement<StartTicket> createStartTicket(StartTicket value) {
        return new JAXBElement<StartTicket>(_StartTicket_QNAME, StartTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartNewWorkflowInstance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.view.TQGIS/", name = "StartNewWorkflowInstance")
    public JAXBElement<StartNewWorkflowInstance> createStartNewWorkflowInstance(StartNewWorkflowInstance value) {
        return new JAXBElement<StartNewWorkflowInstance>(_StartNewWorkflowInstance_QNAME, StartNewWorkflowInstance.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartNewWorkflowInstanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.view.TQGIS/", name = "StartNewWorkflowInstanceResponse")
    public JAXBElement<StartNewWorkflowInstanceResponse> createStartNewWorkflowInstanceResponse(StartNewWorkflowInstanceResponse value) {
        return new JAXBElement<StartNewWorkflowInstanceResponse>(_StartNewWorkflowInstanceResponse_QNAME, StartNewWorkflowInstanceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.view.TQGIS/", name = "startTicketResponse")
    public JAXBElement<StartTicketResponse> createStartTicketResponse(StartTicketResponse value) {
        return new JAXBElement<StartTicketResponse>(_StartTicketResponse_QNAME, StartTicketResponse.class, null, value);
    }

}
