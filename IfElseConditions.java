import java.util.Scanner;
public class IfElseConditions {
    public static void main(String[]args){
    Scanner sc = new Scanner (System.in);
    System.out.println("Enter the energy generated (in kWh): ");
    double energyGenerated = sc.nextDouble();
    if (energyGenerated > 10.00) {
        System.out.println("Good energy generation");
    } 
    else {
        System.out.println("Low energy generation");
    }
    sc.close();
}
}
