package com.monolito.auxo.resources;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.parsers.DocumentBuilder;

@Path("/")
public class TodoItem {

    @GET
    @Path("/")
    @Produces({ "application/json" })
    public JsonObject getHelloWorldJSON() {
        return null;
    }

    @GET
    @Path("/")
    @Produces({ "application/xml" })
    public String getHelloWorldXML() {
        return null;
    }
}
