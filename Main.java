import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in).useDelimiter("\n");
    static boolean exit = false;
    static ArrayList<Person> contacts = new ArrayList<>();
    static ArrayList<Message> messages = new ArrayList<>();
    static int firstOption;
    static int contactOption;

    public static void main(String[] args) {

        menu();

        while(!exit) {

            if (firstOption == 1) {

                contactMenu();

                if (contactOption == 1) {
                    showAllContacts();
                    br();
                    continue;
                }

                if (contactOption == 2) {
                    addContact();
                    br();
                    continue;
                }

                if (contactOption == 3) {
                    search();
                    br();
                    continue;
                }

                if (contactOption == 4) {
                    delContact();
                    br();
                    continue;
                }

                if (contactOption == 5) {
                    menu();
                    continue;
                }
            }

            if (firstOption == 2) {
                System.out.println("1.See the list of all messages");
                System.out.println("2.Send a new message");
                System.out.println("3.Go back to the previous menu");

                int contactOption;
                contactOption = scan.nextInt();

                if (contactOption == 1) {
                    showMessages();
                    br();
                    continue;
                }

                if (contactOption == 2) {
                    sendMessage();
                    br();
                    continue;
                }

                if (contactOption == 3) {
                    menu();
                    continue;
                }

            }

            if (firstOption == 3) exit = true;
        }
    }



    private static void menu() {
        System.out.println("Hello and Welcome");
        System.out.println("1)Manage Contacts");
        System.out.println("2)Message");
        System.out.println("3)quit");

        firstOption = scan.nextInt();
    }

    private static void contactMenu() {
        System.out.println("1.Show all contacts");
        System.out.println("2.Add a new contact");
        System.out.println("3.Search for a contact");
        System.out.println("4.Delete a contact");
        System.out.println("5.Go back to the previous menu");

        contactOption = scan.nextInt();
    }

    private static void showAllContacts() {
        for(int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + "." + contacts.get(i).getName() +
                    "  Phone number: " + contacts.get(i).getPhoneNumber());
        }
    }

    private static void addContact() {
        System.out.print("Enter the name of new contact: ");
        String newContact = scan.next();
        System.out.print("Enter phone number: ");
        String newNumber = scan.next();

        contacts.add(new Person(newContact, newNumber));
        System.out.println(MessageFormat.format("New user has added. Name: {0}  Phone number: {1}",
                contacts.get(contacts.size() - 1).getName(), contacts.get(contacts.size() - 1).getPhoneNumber()));
    }

    private static void search() {
        System.out.print("Search: ");
        String searched = scan.next();

        for (Person contact : contacts) {
            if (contact.getName().equals(searched)) {
                System.out.println(MessageFormat.format("Contact exist. \nName: {0} Number: {1}",
                        contact.getName(), contact.getPhoneNumber()));
                return;
            }
        }
        System.out.println("Contact does not exist.");
    }

    private static void delContact() {
        System.out.print("Enter contact to delete: ");
        String delContact = scan.next();

        for (Person contact : contacts) {
            try {
                if (contact.getName().equals(delContact)) {
                    contacts.remove(contact);
                    System.out.println(MessageFormat.format("{0} has deleted.", delContact));
                    return;
                }
                System.out.println("Contact does not exist.");
            }catch (IndexOutOfBoundsException e) {
                contacts.clear();
                System.out.println(MessageFormat.format("{0} has deleted.", delContact));
            }
        }
    }

    private static void showMessages() {
        for (Message message : messages) {
            System.out.println(MessageFormat.format("{0}.to {1}: {2}",
                    messages.size(),
                    message.getReceiver().getName(),
                    message.getText()));
        }
    }

    private static void sendMessage() {
        System.out.print("To whom: ");
        String client = scan.next();

        for (Person contact : contacts) {
            if (contact.getName().equals(client)) {
                System.out.println(MessageFormat.format("Write your message to {0} :", contact.getName()));
                String tmpMes = scan.next();
                messages.add(new Message(tmpMes, contact));
                System.out.println(MessageFormat.format("Message has sent to {0}", contact.getName()));
                return;
            }
        }
        System.out.println(MessageFormat.format("{0} does not exist in the contact", client));
    }

    private static void br() {
        System.out.println("--------------------------------------");
    }
}
