package sn.ept.galsenshop.restservices;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



@OpenAPIDefinition(
        info = @Info(
                title = "galsenShop",
                description = "galsenShop web services.",
                contact = @Contact(name = "Cheikh Ahmadou Bamba DIOP", email = "dcheikhahmadou@ept.sn", url = "http://ept.sn")
        ),
        servers = @Server(
                url = "http://localhost:8080/galsenshop-1.0-SNAPSHOT/"
        )
)
@ApplicationPath("/api")
public class ApiConfig extends Application {



}