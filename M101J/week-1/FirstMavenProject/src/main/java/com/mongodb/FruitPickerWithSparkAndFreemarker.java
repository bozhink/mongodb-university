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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bozhin on 5/28/16.
 */
public class FruitPickerWithSparkAndFreemarker {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(FruitPickerWithSparkAndFreemarker.class, "/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter stringWriter = new StringWriter();

                List<String> data = Arrays.asList("orange", "apple", "banana", "kiwi");

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("creator", "Freemarker");
                map.put("fruits", data);

                try {
                    Template fruitPickerTemplate = configuration.getTemplate("fruit-picker.ftl");
                    fruitPickerTemplate.process(map, stringWriter);
                } catch (IOException e) {
                    processExceptionToHttpResponse(e);
                } catch (TemplateException e) {
                    processExceptionToHttpResponse(e);
                }


                return stringWriter;
            }

            private void processExceptionToHttpResponse(Exception e) {
                halt(500);
                e.printStackTrace();
            }
        });

        Spark.post(new Route("/favorite_fruit") {
            @Override
            public Object handle(Request request, Response response) {
                String fruit = request.queryParams("fruit");
                if (fruit == null) {
                    return "Why don't you pick one?";
                } else {
                    return "Your favorite fruit is " + fruit;
                }
            }
        });
    }
}
