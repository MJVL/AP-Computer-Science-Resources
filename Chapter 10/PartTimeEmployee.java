public class PartTimeEmployee extends Employee {

   public PartTimeEmployee() {
      super();
   }
  
   public double getPay() {
      double pay;
      pay = rate * hours;
      totalPay += pay;
      return pay;
   }
}

