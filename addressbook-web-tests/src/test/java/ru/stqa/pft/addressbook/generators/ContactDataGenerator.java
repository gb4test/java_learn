package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXML(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
            contact.getFirstname(), contact.getLastname(), contact.getAddress(),
            contact.getHome_phone(), contact.getMobile_phone(), contact.getWork_phone(),
            contact.getFax(), contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
      }
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    File photo = new File("src/test/resources/images.png");

    for (int i = 0; i < count; i++) {
      if (format.equals("json")) {
        contacts.add(new ContactData()
            .withFirstname(String.format("firstName %s", i))
            .withLastname(String.format("lastName %s", i))
            .withAddress(String.format("address %s", i))
            .withHome_phone(String.format("885-54-857 %s", i))
            .withMobile_phone(String.format("+7(495) 254-98-98 %s", i))
            .withWork_phone(String.format("78 78 78 %s", i))
            .withFax(String.format("885-54-857 %s", i))
            .withEmail(String.format("%s_dfhdh@gmail.com", i))
            .withEmail2(String.format("%s.sfsf@awd.ru", i))
            .withEmail3(String.format("%s-SAFF@jsf.snj", i)));
      } else {
        contacts.add(new ContactData()
            .withFirstname(String.format("firstName %s", i))
            .withLastname(String.format("lastName %s", i))
            .withPhoto(photo)
            .withAddress(String.format("address %s", i))
            .withHome_phone(String.format("885-54-857 %s", i))
            .withMobile_phone(String.format("+7(495) 254-98-98 %s", i))
            .withWork_phone(String.format("78 78 78 %s", i))
            .withFax(String.format("885-54-857 %s", i))
            .withEmail(String.format("%s_dfhdh@gmail.com", i))
            .withEmail2(String.format("%s.sfsf@awd.ru", i))
            .withEmail3(String.format("%s-SAFF@jsf.snj", i)));
      }
    }
    return contacts;
  }
}

//  -f src/test/resources/contacts.json -c 3 -d json