// Group Members: Gabrielle Coronel, Viviana Luna
public class FeeInvoice {

    public static void main(String[] args) {
        Student s;

        s = new PhdStudent ("Zaydoun BenSellam", "zb5954" , "Gary Richarson", "Fuzzy Topology" , 2599 );
        s.printInvoice();

        int [] gradCrnsTaken = {7587,8997} ;
        s = new MsStudent ("Emily Jones", "em1254", gradCrnsTaken, 1997);
        s.printInvoice();

        int [] undergradCrnsTaken = {4587,2599};
        s = new UndergraduateStudent ("Jamila Jones" , "ja5225" , undergradCrnsTaken , 3.0, false );
        s.printInvoice();
    }
}

abstract class Student {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    abstract public void printInvoice();
}

class UndergraduateStudent extends Student{
    private int []undergradCrnsTaken;
    private double gpa;
    private boolean resident;

    private String course[][] = {{"4587", "MAT 236", "4"}, {"4599", "COP 220", "3"}, {"4580", "MAT 136", "1"},
            {"2599", "COP 260", "3"}, {"1997", "CAP 424", "1"}, {"1599", "COP 111", "3"}, {"2696", "COP 101", "3"},
            {"2099", "COP 268", "3"}, {"4997", "CAP 427", "1"}, {"3696", "KOL 910", "2"}};

    public int[] getUndergradCrnsTaken() {
        return undergradCrnsTaken;
    }

    public void setUndergradCrnsTaken(int[] undergradCrnsTaken) {
        this.undergradCrnsTaken = undergradCrnsTaken;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }

    public UndergraduateStudent(String name , String id , int [] undergradCrnsTaken , double gpa, boolean resident)
    {
        super(name, id);
        this.undergradCrnsTaken = undergradCrnsTaken;
        this.gpa = gpa;
        this.resident = resident;
    }
    @Override
    public void printInvoice() {
        System.out.println("VALENCE COLLEGE");
        System.out.println("ORLANDO FL 10101");
        System.out.println("---------------------");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(getId() + "-" + getName());
        System.out.println("1 Credit Hour = $120.25");
        System.out.println("CRN\t\tCR_PREFIX\tCR_HOURS");
        if (resident) {
            for (Integer crn : undergradCrnsTaken) {
                for (String[] courseInfo : course) {
                    if (Integer.parseInt(courseInfo[0]) == crn) {
                        int crHours = Integer.parseInt(courseInfo[2]);
                        double cost = crHours * 120.25;
                        System.out.printf("%s\t%s\t\t%s\t\t\t$%.2f\n", courseInfo[0], courseInfo[1], courseInfo[2], cost);
                    }
                }
            }
            System.out.println("Health & Id fees $35.00");
            System.out.println("---------------------------------------");
            double total = 0, discount = 0;
            for (Integer crn : undergradCrnsTaken) {
                for (String[] courseInfo : course) {
                    if (Integer.parseInt(courseInfo[0]) == crn) {
                        int cred = Integer.parseInt(courseInfo[2]);
                        total += 120.25 * cred;
                    }
                }
            }
            total += 35.00;
            if (total > 700 && getGpa() >= 3.5) {
                discount = total * 0.25;
                if (discount > 0) {
                    System.out.printf("\t\t\t\t\t $ %.2f\n", total);
                    System.out.printf("\t\t\t\t\t-$ %.2f\n", discount);
                }
            }
            System.out.println("\t\t\t\t\t----------");
            System.out.printf("\t  TOTAL PAYMENTS $ %.2f\n", total-discount);
        }
        else {
            for (Integer crn : undergradCrnsTaken) {
                for (String[] courseInfo : course) {
                    if (Integer.parseInt(courseInfo[0]) == crn) {
                        int crHours = Integer.parseInt(courseInfo[2]);
                        double cost = crHours * 120.25 * 2;
                        System.out.printf("%s\t%s\t\t%s\t\t\t$%.2f\n", courseInfo[0], courseInfo[1], courseInfo[2], cost);
                    }
                }
            }
            System.out.println("\t\t\t\tHealth & Id fees $35.00");
            System.out.println("----------------------------------------");
            double total = 0, discount = 0;
            for (Integer crn : undergradCrnsTaken) {
                for (String[] courseInfo : course) {
                    if (Integer.parseInt(courseInfo[0]) == crn) {
                        int cred = Integer.parseInt(courseInfo[2]);
                        total += 120.25 * cred * 2;
                    }
                }
            }
            total += 35.00;
            if (total > 700 && getGpa() >= 3.5) {
                discount = total * 0.25;
            }
            if (discount > 0) {
                System.out.printf("\t\t\t\t$ %.2f\n", total, "\t\t\t\t-$ %.2f\n", discount);
            }
            System.out.printf("\t\t\t\tTOTAL PAYMENTS $ %.2f\n", total-discount);
            System.out.println();
        }
    }
}

abstract class GraduateStudent extends Student {
    private int crn;
    public GraduateStudent (String name, String id, int crn) {
        //crn is the crn that the grad student is a teaching assistant for
        super (name , id );
        this.crn = crn;
    }
}

class PhdStudent extends GraduateStudent {
    String advisor;
    String researchSubject;
    public PhdStudent (String name, String id , String advisor, String researchSubject , int crn ) {
        //crn is the course number that the Phd student is a teaching assistant for
        super (name , id , crn );
        this.advisor = advisor;
        this.researchSubject = researchSubject;

    }
    @Override
    public void printInvoice() {
        System.out.println("VALENCE COLLEGE");
        System.out.println("ORLANDO FL 10101");
        System.out.println("---------------------");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(getId() + "-" + getName());
        System.out.println("RESEARCH");
        System.out.println(researchSubject + "\t\t\t\t$700.00");
        System.out.println("\t\t\tHealth & Id fees $35.00");
        System.out.println("----------------------------------------");
        System.out.println("\t\t\tTOTAL PAYMENTS $735.00");
        System.out.println();
    }
}

class MsStudent extends GraduateStudent {
    private int [] gradCrnsTaken;
    private String course[][] = {{"8997", "GOL 124", "1"}, {"9696", "COP 100", "3"}, {"5696", "KOL 110", "2"},
            {"7587", "MAT 936", "5"}, {"6997", "GOL 109", "1"}, {"5580", "MAT 636", "2"}};
    public MsStudent (String name, String id , int [] gradCrnsTaken , int crn ) {
        // gradCoursesTaken is the array of the crns that the Ms student is taking
        //crn is the course number that the Phd student is a teaching assistant for
        super (name , id , crn );
        this.gradCrnsTaken= gradCrnsTaken;
    }
    @Override
    public void printInvoice() {
        System.out.println("VALENCE COLLEGE");
        System.out.println("ORLANDO FL 10101");
        System.out.println("---------------------");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(getId() + "-" + getName());
        System.out.println("1 Credit Hour = $300.00");
        System.out.println("CRN\t\tCR_PREFIX\tCR_HOURS");
        for (Integer crn : gradCrnsTaken) {
            for (String[] courseInfo : course) {
                if (Integer.parseInt(courseInfo[0]) == crn) {
                    int crHours = Integer.parseInt(courseInfo[2]);
                    double cost = crHours * 300;
                    System.out.printf("%s\t%s\t\t%s\t\t\t$%.2f\n", courseInfo[0], courseInfo[1], courseInfo[2], cost);
                }
            }
        }
        System.out.println("\t\t\t\tHealth & Id fees $35.00");
        System.out.println("----------------------------------------");
        double total = 0;
        for (Integer crn : gradCrnsTaken) {
            for (String[] courseInfo : course) {
                if (Integer.parseInt(courseInfo[0]) == crn) {
                    int cred = Integer.parseInt(courseInfo[2]);
                    total += 300 * cred;
                }
            }
        }
        total += 35;
        System.out.printf("\t\t\t\tTOTAL PAYMENTS $ %.2f\n", total);
        System.out.println();
    }
}