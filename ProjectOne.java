package Project1;

import java.sql.*;
import java.util.Scanner;

public class ProjectOne {

    public void createTable(){

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "Olatunji@1");
            Statement statement = connection.createStatement()){

            String createTable = "CREATE TABLE IF NOT EXISTS user( name Text, email Text, age Int, location Text, designation Text)";
            statement.execute(createTable);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int populateTable(){
        int count = 0;

        try(Scanner scanner = new Scanner(System.in); Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "Olatunji@1");
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO user(name, email, age, location, designation) VALUES(?, ?, ?, ?, ?)")){

            for(int i = 0; i < 10; i++){
                System.out.println("Enter your Name");
                String name = scanner.nextLine();

                System.out.println("Enter your email");
                String email = scanner.nextLine();

                System.out.println("Kindly input your age");
                int age = scanner.nextInt();

                System.out.println("Enter your Location");
                String location = scanner.next();
                scanner.nextLine();

                System.out.println("Enter your Designation");
                String designation = scanner.nextLine();

                insertStatement.setString(1, name);
                insertStatement.setString(2, email);
                insertStatement.setInt(3, age);
                insertStatement.setString(4, location);
                insertStatement.setString(5, designation);

                insertStatement.execute();
                count++;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {

        ProjectOne projectOne = new ProjectOne();
        projectOne.createTable();
        projectOne.populateTable();
    }
}
