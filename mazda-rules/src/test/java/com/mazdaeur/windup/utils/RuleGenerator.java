package com.mazdaeur.windup.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * Usage of a JUnit test as an application runner.
 *
 * This class allows to generate some rules that are annoying to write by hand. Alternatively, I could have extended
 * Windup, but didn't want to go there right now.
 */
public class RuleGenerator {

    private static final Pomlibs pomlibs = new Pomlibs();
    static {
        configurePomlibs();
    }

    private static void configurePomlibs() {
        pomlib("org.springframework.essential").contains(
                "org.springframework:com.mazdaeur.org.springframework.core",
                "org.springframework:com.mazdaeur.org.springframework.expression",
                "org.springframework:com.mazdaeur.org.springframework.beans",
                "org.springframework:com.mazdaeur.org.springframework.aop",
                "org.springframework:com.mazdaeur.org.springframework.context",
                "org.apache.commons:com.mazdaeur.commons-io",
                "org.apache.commons:com.mazdaeur.commons-lang",
                "org.apache.commons:com.mazdaeur.commons-collections",
                "com.mazdaeur.common:mazda-common",
                "org.aspectj:com.mazdaeur.aspectjrt",
                "cglib:com.mazdaeur.cglib-nodep",
                "aopalliance:com.mazdaeur.aopalliance",
                "org.aspectj:com.mazdaeur.aspectjweaver"
        );
        pomlib("org.springframework.orm").contains(
                "org.springframework.essential",
                "org.springframework:com.mazdaeur.org.springframework.transaction",
                "org.springframework:com.mazdaeur.org.springframework.jdbc",
                "org.springframework:com.mazdaeur.org.springframework.orm",
                "org.hibernate:com.mazdaeur.hibernate-core",
                "antlr:com.mazdaeur.antlr",
                "org.apache.commons:com.mazdaeur.commons-collections",
                "dom4j:com.mazdaeur.dom4j-nodep",
                "jaxen:com.mazdaeur.jaxen-nodep",
                "javassist:com.mazdaeur.javassist",
                "cglib:com.mazdaeur.cglib-nodep",
                "net.sf.ehcache:com.mazdaeur.ehcache",
                "org.hibernate:com.mazdaeur.hibernate-validator"
        );
        pomlib("org.springframework.orm.h4").contains(
                "org.springframework.essential",
                "org.springframework:com.mazdaeur.org.springframework.transaction",
                "org.springframework:com.mazdaeur.org.springframework.jdbc",
                "org.springframework:com.mazdaeur.org.springframework.orm",
                "org.hibernate:com.mazdaeur.hibernate-core",
                "org.hibernate:com.mazdaeur.hibernate-entitymanager",
                "org.hibernate:com.mazdaeur.hibernate-ehcache",
                "org.hibernate:com.mazdaeur.hibernate-commons-annotations",
                "antlr:com.mazdaeur.antlr",
                "org.apache.commons:com.mazdaeur.commons-collections",
                "dom4j:com.mazdaeur.dom4j-nodep",
                "org.jboss.logging:com.mazdaeur.jboss-logging",
                "org.javassist:com.mazdaeur.javassist",
                "net.sf.ehcache:com.mazdaeur.ehcache-core",
                "org.hibernate:com.mazdaeur.hibernate-validator"
        );
        pomlib("org.springframework.batch").contains(
                "org.springframework.essential",
                "org.springframework.orm",
                "org.springframework:com.mazdaeur.org.springframework.oxm",
                "org.springframework.batch:com.mazdaeur.spring-batch-core",
                "org.springframework.batch:com.mazdaeur.spring-batch-infrastructure",
                "org.springframework.batch:com.mazdaeur.spring-batch-test",
                "org.aspectj:com.mazdaeur.aspectjrt",
                "org.springmodules:com.mazdaeur.spring-modules-validation",
                "org.apache.commons:com.mazdaeur.commons-io",
                "org.springframework.batch:com.mazdaeur.xstream",
                "org.codehaus.jettison:com.mazdaeur.jettison",
                "org.codehaus.woodstox:com.mazdaeur.stax2-api",
                "org.codehaus.woodstox:com.mazdaeur.woodstox-core-asl",
                "javax.xml:com.mazdaeur.jsr173"
        );
        pomlib("org.springframework.security").contains(
                "org.springframework.essential",
                "org.springframework.security:com.mazdaeur.spring-security-config",
                "org.springframework.security:com.mazdaeur.spring-security-core",
                "org.springframework.security:com.mazdaeur.spring-security-web",
                "org.springframework.security:com.mazdaeur.spring-security-cas",
                "org.springframework.security:com.mazdaeur.spring-security-remoting",
                "org.jasig.cas:com.mazdaeur.cas-client-core",
                "com.mazdaeur.mum:mum-client",
                "com.mazdaeur.cts2:cts2-client",
                "org.springframework:com.mazdaeur.org.springframework.transaction",
                "org.aspectj:com.mazdaeur.aspectjrt",
                "org.apache.velocity:com.mazdaeur.velocity",
                "org.springframework:com.mazdaeur.org.springframework.context.support",
                "org.apache.commons:com.mazdaeur.commons-codec"
        );
        pomlib("org.springframework.web").contains(
                "org.springframework.essential",
                "org.springframework:com.mazdaeur.org.springframework.web",
                "org.springframework:com.mazdaeur.org.springframework.web.servlet",
                "org.apache.velocity:com.mazdaeur.velocity",
                "org.apache.velocity:com.mazdaeur.velocity-tools-view",
                "org.springframework:com.mazdaeur.org.springframework.context.support"
        );
        pomlib("com.mazdaeur.jee").withScope("provided").contains(
                "javax.jms:com.mazdaeur.jms",
                "javax.activation:com.mazdaeur.activation",
                "javax.mail:com.mazdaeur.mail",
                "javax.inject:com.mazdaeur.javax.inject",
                "javax.validation:com.mazdaeur.validation-api",
                "org.apache.geronimo.specs:com.mazdaeur.geronimo-annotation_1.1_spec",
                "org.apache.geronimo.specs:com.mazdaeur.geronimo-servlet_3.0_spec",
                "org.apache.geronimo.specs:com.mazdaeur.geronimo-jsp_2.2_spec",
                "org.apache.geronimo.specs:com.mazdaeur.geronimo-el_2.2_spec",
                "org.apache.geronimo.bundles:com.mazdaeur.jstl",
                "org.apache.geronimo.specs:com.mazdaeur.geronimo-jta_1.1_spec",
                "org.apache.geronimo.specs:com.mazdaeur.geronimo-jpa_2.0_spec",
                "org.apache.geronimo.specs:com.mazdaeur.geronimo-jaxws_2.2_spec",
                "org.apache.geronimo.specs:com.mazdaeur.geronimo-ws-metadata_2.0_spec",
                "org.apache.geronimo.specs:com.mazdaeur.geronimo-jaxrs_1.1_spec"
        );
        pomlib("org.springframework.test").withScope("test").contains(
                "com.mazdaeur.jee",
                "org.springframework:com.mazdaeur.org.springframework.test",
                "com.oracle:com.mazdaeur.ojdbc14",
                "junit:com.mazdaeur.junit",
                "org.mockito:com.mazdaeur.mockito-core",
                "org.objenesis:com.mazdaeur.objenesis",
                "org.hamcrest:com.mazdaeur.hamcrest-core",
                "org.hamcrest:com.mazdaeur.hamcrest-library",
                "org.springframework.batch:com.mazdaeur.xstream"
        );
        pomlib("org.apache.cxf").contains(
                "org.springframework.web",
                "org.apache.cxf:com.mazdaeur.cxf-bundle",
                "org.apache.neethi:com.mazdaeur.neethi",
                "org.apache.httpcomponents:com.mazdaeur.httpcore",
                "org.apache.httpcomponents:com.mazdaeur.httpcore-nio",
                "org.apache.httpcomponents:com.mazdaeur.httpasyncclient",
                "org.apache.httpcomponents:com.mazdaeur.httpclient",
                "org.apache.geronimo:com.mazdaeur.geronimo-jaxws",
                "org.apache.ws.commons:com.mazdaeur.xmlschema",
                "org.codehaus.woodstox:com.mazdaeur.woodstox-core-asl",
                "org.codehaus.woodstox:com.mazdaeur.stax2-api",
                "com.sun.xml.fastinfoset:com.mazdaeur.FastInfoset"
        );
        pomlib("org.apache.poi").contains(
                "org.apache.poi:com.mazdaeur.poi",
                "org.apache.poi:com.mazdaeur.poi-ooxml",
                "org.apache.poi:com.mazdaeur.poi-ooxml-schemas",
                "org.apache.xmlbeans:com.mazdaeur.xmlbeans"
        );
        pomlib("com.mazdaeur.logging").contains(
                "ch.qos.logback:com.mazdaeur.logback-classic",
                "ch.qos.logback:com.mazdaeur.logback-core",
                "org.slf4j:com.mazdaeur.slf4j-api",
                "org.slf4j:com.mazdaeur.jcl-over-slf4j",
                "org.codehaus.janino:com.mazdaeur.janino"
        );
        pomlib("com.mazdaeur.log4j").contains(
                "org.slf4j:com.mazdaeur.slf4j-log4j12",
                "org.slf4j:com.mazdaeur.slf4j-api",
                "org.slf4j:com.mazdaeur.jcl-over-slf4j",
                "log4j:com.mazdaeur.log4j"
        );
        pomlib("com.mazdaeur.base.core").contains(
                "org.springframework.essential",
                "org.springframework.orm",
                "org.springframework.security",
                "org.springframework.web",
                "com.lowagie:com.mazdaeur.itext",
                "com.enterprisedt:com.mazdaeur.edtFTPj",
                "org.freemarker:com.mazdaeur.freemarker",
                "jcifs:com.mazdaeur.jcifs",
                "org.codehaus.castor:com.mazdaeur.castor-xml",
                "org.apache.commons:com.mazdaeur.commons-digester",
                "org.apache.commons:com.mazdaeur.commons-beanutils",
                "org.apache.xmlbeans:com.mazdaeur.xmlbeans"
        );
        pomlib("com.atomikos.transactions").contains(
                "com.atomikos:com.mazdaeur.atomikos-util",
                "com.atomikos:com.mazdaeur.transactions",
                "com.atomikos:com.mazdaeur.transactions-api",
                "com.atomikos:com.mazdaeur.transactions-jta",
                "com.atomikos:com.mazdaeur.transactions-jdbc",
                "com.atomikos:com.mazdaeur.transactions-hibernate3",
                "com.atomikos:com.mazdaeur.transactions-jms"
        );
    }

    private static Pomlib pomlib(String name) {
        Pomlib pomlib = new Pomlib(name);
        pomlibs.add(pomlib);
        return pomlib;
    }


    private static Configuration freemarker = initFreeMarker();

    public static Configuration initFreeMarker() {
        Configuration config = new Configuration();
        config.setDefaultEncoding("UTF-8");
        config.setClassForTemplateLoading(RuleGenerator.class, "");
        return config;
    }

    @Test
    public void generatePomLibVersionXpathValues() {
        System.out.println("<!-- START: List of generated POM Lib version checks, see RuleGenerator-->");
        for (MavenId pomlib : pomlibs.getPomLibIds()) {
            Map<String, Object> model = new HashMap<>();
            model.put("pomlibId", pomlib);
            String xpathValue = processTemplate("pomlib_version_xpathvalue", model);
            System.out.println(xpathValue);
        }
        System.out.println("<!-- END: List of generated POM Lib version checks -->");
    }

    @Test
    public void generateDirectInsteadOfPomlibDependencyXpathValues() {
        System.out.println("<!-- START: Generated list of XPath Values to detect usage of direct dependencies where a POM Lib is more appropriate. See RuleGenerator. -->");
        for (MavenId dependency : pomlibs.getPomLibDependencies()) {
            Set<Pomlib> pomlibsOfferingDependency = pomlibs.getPomlibsForDependency(dependency);

            Map<String, Object> model = new HashMap<>();
            model.put("depId", dependency);
            model.put("pomlibs", pomlibsOfferingDependency);

            String xpathValue = processTemplate("direct_io_pomlib_dep_xpathvalue", model);
            System.out.println(xpathValue);
        }
        System.out.println("<!-- END: Generated list of XPath Values to detect usage of direct dependencies where a POM Lib is more appropriate. See RuleGenerator. -->");
    }

    @Test
    public void generateEmbeddedPomlibXpathValues() {
        System.out.println("<!-- START: Generated list of XPath Values to detect POM lib dependencies that are already part of another POM lib dependency. See RuleGenerator. -->");
        for (Pomlib embeddedPomlib : pomlibs.getEmbeddedPomlibs()) {
            Set<Pomlib> parentPomlibs = pomlibs.getParentPomlibsFor(embeddedPomlib);
            for (Pomlib parentPomlib : parentPomlibs) {
                Map<String, Object> model = new HashMap<>();
                model.put("embeddedPomlib", embeddedPomlib);
                model.put("parentPomlib", parentPomlib);

                String xpathValue = processTemplate("embedded_pomlib_xpathvalue", model);
                System.out.println(xpathValue);
            }
        }
        System.out.println("<!-- END: Generated list of XPath Values to detect POM lib dependencies that are already part of another POM lib dependency. See RuleGenerator. -->");
    }

    private String processTemplate(String templateName, Map<String, Object> model) {
        try {
            Template template = freemarker.getTemplate("ftl/"+templateName+".ftl");
            StringWriter writer = new StringWriter();
            template.process(model, writer);
            return writer.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to process template", e);
        } catch (TemplateException e) {
            throw new RuntimeException("Failed to process template", e);
        }
    }

}
