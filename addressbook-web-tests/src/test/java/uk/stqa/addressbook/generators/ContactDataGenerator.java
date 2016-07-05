package uk.stqa.addressbook.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uk.stqa.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by natla on 26/06/2016.
 */
public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        saveAsJSON(contacts, file);
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i= 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("First %s", i))
                    .withLastName(String.format("Last %s", i))
                    .withAddress("Тридевятое царство\n" + "Дуб у Лукоморья №13\n" + "Ученому Коту")
                    .withEmail(String.format("first%s.last%s@dreamcompany.com", i, i))
                    .withHomeT(String.format("123 %s", i)).withPhoto("src/test/resources/logo.jpeg"));
        }
        return contacts;
    }

    private static void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(),
                        contact.getEmail(), contact.getHomeT()));
            }
        }
    }

    private static void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }
}
