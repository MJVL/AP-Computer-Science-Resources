public class Ch5_1 {
    public static void main(String argv[]) {
        // call constructor to initialize vars and allocate storage.
        Receipt r = new Receipt("The Right Price - School Supplies");

        r.addItem("pencil", 0.39);
        r.addItem("eraser", 0.99);
        r.addItem("paper", 1.4);

        r.printReceipt();

            // another receipt object
                Receipt r2 = new Receipt("Fast Mart");

        r2.addItem("candy", 0.50);
        r2.addItem("eraser", 0.99);
        r2.addItem("staples", 1.41);

        r2.printReceipt();
    }
} 

