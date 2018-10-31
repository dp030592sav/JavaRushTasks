package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class HtmlView implements View {
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";
    public Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some exception");
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document = null;

        try {
            document = getDocument();
            Element element = document.getElementsByClass("template").first();
            Element elementCopy = element.clone();
            elementCopy.removeAttr("style");
            elementCopy.removeClass("template");
            document.select("tr[class=vacancy]").remove().not("tr[class=vacancy template");

            for (Vacancy vacancy : vacancies) {
                Element elementNew = elementCopy.clone();
                elementNew.getElementsByClass("city").first().text(vacancy.getCity());
                elementNew.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                elementNew.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element link = elementNew.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                element.before(elementNew.outerHtml());
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Some exception occurred";
        }

        return document.html();
    }

    private void updateFile(String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException{
        File input = new File(filePath);

        return Jsoup.parse(input, "UTF-8");
    }
}
