import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        FamilyController familyController = new FamilyController();
        String fileName = "families.txt";
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
                    11. Write families to your binary text file.
                    12. Download families from binary text.""");

            System.out.print("Your option:");
            String option = scanner.next();
            if(option.equalsIgnoreCase("exit")) break;

            try {
                switch (Integer.parseInt(option)) {
                    case 1:
                        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                            String line;
                            while((line = reader.readLine()) != null) System.out.println(line);
                        }catch (FileNotFoundException x) {
                            System.out.printf("File `%s` not found.", fileName);}
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
                        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                            familyController.getAllFamilies().forEach(family -> {
                                try {
                                    writer.write(String.format("%d)%s\n", familyController.getAllFamilies().indexOf(family) + 1,family.prettyFormat()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                        break;
                    case 11:
                        try(FileOutputStream f = new FileOutputStream("myBinaryData.bin");
                            ObjectOutputStream o = new ObjectOutputStream(f)) {
                            System.out.print("Please, enter the name of mother: ");
                            String name3 = scanner.next();
                            System.out.print("Please, enter the surname of mother: ");
                            String surname3 = scanner.next();
                            System.out.print("Please, enter the birth date of mother: ");
                            String birthDate3 = scanner.next();
                            System.out.print("Please, enter the iq of mother: ");
                            int iq3 = scanner.nextInt();
                            Human mother3 = new Woman(name3, surname3, birthDate3, iq3);
                            System.out.print("Please, enter the name of father: ");
                            String name4 = scanner.next();
                            System.out.print("Please, enter the surname of father: ");
                            String surname4 = scanner.next();
                            System.out.print("Please, enter the birth date of father: ");
                            String birthDate4 = scanner.next();
                            System.out.print("Please, enter the iq of father: ");
                            int iq4 = scanner.nextInt();
                            Human father4 = new Man(name4, surname4, birthDate4, iq4);

                            Family family = new Family(father4, mother3);
                            System.out.println(family);

                            o.writeObject(family);
                        }
                        catch (Exception e){
                            System.out.println("A problem occurred during writing.");
                            scanner.nextLine();
                        }
                        break;
                    case 12:
                        List<Family> data = new ArrayList<>();
                        try(FileInputStream fi = new FileInputStream("myBinaryData.bin");
                            ObjectInputStream oi = new ObjectInputStream(fi)){
                            Family family;
                            while((family = (Family) oi.readObject()) != null){
                                System.out.println(family);
                                data.add(family);
                            }
                            familyController.loadData(data);
                        } catch (EOFException e) {
                            scanner.nextLine();
                        } catch (ClassNotFoundException | FileNotFoundException e ){
                            System.out.println("A problem occurred.");
                            scanner.nextLine();
                        }
                        break;
                    default:
                        System.out.println("Please, write a number between 1-10 or write \"exit\" to finish the application.\n");
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Please, provide a number.\n");
                scanner.nextLine();
            }
            catch (DateTimeParseException e) {
                System.out.println("Please, provide a date in a format \"dd/MM/yyyy\".\n");
                scanner.nextLine();
            }
        }
    }
}
