package sn.ept.galsenshop.resources;

import sn.ept.galsenshop.entities.Categorie;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;


@FacesConfig(version = JSF_2_3)
@Named(value="Application init")
@ApplicationScoped
public class ApplicationConfig {

    //Add other configurations setup

}
