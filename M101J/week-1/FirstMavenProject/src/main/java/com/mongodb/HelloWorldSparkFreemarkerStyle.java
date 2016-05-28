package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bozhin on 5/28/16.
 */
public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {

                StringWriter stringWriter = new StringWriter();
                try {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", "Freemaker");
                    map.put("date", (new Date()).toString());

                    Template template = configuration.getTemplate("hello.ftl");
                    template.process(map, stringWriter);
                } catch (IOException e) {
                    processException(e);
                } catch (TemplateException e) {
                    processException(e);
                }

                return stringWriter;
            }

            private void processException(Exception e) {
                halt(500);
                e.printStackTrace();
            }
        });
    }
}
