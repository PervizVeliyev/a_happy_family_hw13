import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        FamilyController familyController = new FamilyController();
        String fileName = "families.bin";
        File file = new File(fileName);
        System.out.println("Console application:");

        while(true){
            System.out.println("""
                    1. Load previously saved data.
                    2. Display the entire list of families.
                    3. Display a list of families where the number of people is greater than the specified number.
                    4. Display a list of families where the number of people is less than the specified number.
                    5. Calculate the number of families where the number of members is:
                    6. Create a new family.
                    7. Delete a family by its index in the general list.
                    8. Edit a family by its index in the general list.
                    9. Remove all children over the age of majority.
                    10. Save data to your computer.
                    11. Download data from file to the application database.""");

            System.out.print("Your option:");
            String option = scanner.next();
            if(option.equalsIgnoreCase("exit")) break;

            try {
                switch (Integer.parseInt(option)) {
                    case 1:
                        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
                            List<Family> families = (List<Family>) reader.readObject();
                            families.forEach(family -> System.out.printf("%d) %s\n",families.indexOf(family) + 1, family));
                        }catch (FileNotFoundException e) {
                            System.out.printf("File `%s` not found.", fileName);
                            Logging.error(e.getMessage());
                        }catch (ClassNotFoundException e) {
                            System.out.println("Class not found exception");
                            Logging.error(e.getMessage());
                        }catch (EOFException e){
                            break;
                        }
                        break;
                    case 2:
                        familyController.displayAllFamilies();
                        break;
                    case 3:
                        System.out.print("Enter your number: ");
                        familyController.getFamiliesBiggerThan(scanner.nextInt());
                        break;
                    case 4:
                        System.out.print("Enter your number: ");
                        familyController.getFamiliesLessThan(scanner.nextInt());
                        break;
                    case 5:
                        System.out.print("Enter your number: ");
                        System.out.println(familyController.countFamiliesWithMemberNumber(scanner.nextInt()));
                        break;
                    case 6:
                        System.out.print("Please, enter the name of mother: ");
                        String name1 = scanner.next();
                        System.out.print("Please, enter the surname of mother: ");
                        String surname1 = scanner.next();
                        System.out.print("Please, enter the birth year of mother: ");
                        String birthYear1 = scanner.next();
                        System.out.print("Please, enter the month of birth of mother: ");
                        String birthMonth1 = scanner.next();
                        System.out.print("Please, enter the birthday of mother: ");
                        String birthDay1 = scanner.next();
                        System.out.print("Please, enter the iq of mother: ");
                        int iq1 = scanner.nextInt();
                        Human mother = new Woman(name1, surname1, String.format("%s/%s/%s", birthDay1, birthMonth1, birthYear1), iq1);
                        System.out.print("Please, enter the name of father: ");
                        String name2 = scanner.next();
                        System.out.print("Please, enter the surname of father: ");
                        String surname2 = scanner.next();
                        System.out.print("Please, enter the birth year of father: ");
                        String birthYear2 = scanner.next();
                        System.out.print("Please, enter the month of birth of father: ");
                        String birthMonth2 = scanner.next();
                        System.out.print("Please, enter the birthday of father: ");
                        String birthDay2 = scanner.next();
                        System.out.print("Please, enter the iq of father: ");
                        int iq2 = scanner.nextInt();
                        Human father = new Man(name2, surname2, String.format("%s/%s/%s", birthDay2, birthMonth2, birthYear2), iq2);
                        familyController.createNewFamily(father, mother);
                        break;
                    case 7:
                        System.out.print("Please, enter family ID: ");
                        familyController.deleteFamilyByIndex(scanner.nextInt());
                        break;
                    case 8:
                        System.out.println("Select one below:");
                        while (true) {
                            boolean breaker = false;
                            System.out.println("""
                                    1. Give birth to a baby.
                                    2. Adopt a child.
                                    3. Return to main menu.""");
                            System.out.print("Your option:");
                            int optionInner = scanner.nextInt();
                            switch (optionInner) {
                                case 1 -> {
                                    System.out.print("Please, enter family ID: ");
                                    int id1 = scanner.nextInt();
                                    System.out.print("The name in the case of boy: ");
                                    String nameBoy = scanner.next();
                                    System.out.print("The name in the case of girl: ");
                                    String nameGirl = scanner.next();
                                    familyController.bornChild(familyController.getFamilyById(id1), nameBoy, nameGirl);
                                }
                                case 2 -> {
                                    System.out.print("Please, enter family ID: ");
                                    int id2 = scanner.nextInt();
                                    System.out.print("Please, enter the gender of adopted child (m/f): ");
                                    String gender = scanner.next();
                                    System.out.print("Please, enter the name of adopted child: ");
                                    String name3 = scanner.next();
                                    System.out.print("Please, enter the surname of adopted child: ");
                                    String surname3 = scanner.next();
                                    System.out.print("Please, enter the birth date of adopted child: ");
                                    String birthDate = scanner.next();
                                    System.out.print("Please, enter the iq of adopted child: ");
                                    int iq3 = scanner.nextInt();
                                    Human adoptedChild;
                                    if (gender.equalsIgnoreCase("m")) {
                                        adoptedChild = new Man(name3, surname3, birthDate, iq3);
                                    } else {
                                        adoptedChild = new Woman(name3, surname3, birthDate, iq3);
                                    }
                                    familyController.adoptChild(familyController.getFamilyById(id2), adoptedChild);
                                }
                                case 3 -> breaker = true;
                                default -> System.out.println("Please, write a choice 1, 2 or 3.\n");
                            }
                            if (breaker) break;
                        }
                        break;
                    case 9:
                        System.out.print("Please, enter the number: ");
                        familyController.deleteAllChildrenOlderThan(scanner.nextInt());
                        break;
                    case 10:
                        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))){
                                try {
                                    writer.writeObject(familyController.getAllFamilies());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        }
                        System.out.println("The data saved to the file \"families.bin\".");
                        break;
                    case 11:
                        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))){
                            List<Family> dataBase = (List<Family>) reader.readObject();
                            familyController.loadData(dataBase);
                        } catch (EOFException e) {
                            break;
                        } catch (ClassNotFoundException | FileNotFoundException e ){
                            System.out.println("A problem occurred.");
                            scanner.nextLine();
                            Logging.error(e.getMessage());
                        }
                        System.out.println("The data downloaded.");
                        break;
                    default:
                        System.out.println("Please, write a number between 1-12 or write \"exit\" to finish the application.\n");
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Please, provide a number.\n");
                scanner.nextLine();
                Logging.error(e.getMessage());
            }
            catch (DateTimeParseException e) {
                System.out.println("Please, provide a date in a format \"dd/MM/yyyy\".\n");
                scanner.nextLine();
                Logging.error(e.getMessage());
            }
        }
    }
}
