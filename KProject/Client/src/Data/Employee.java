package Data;

public class Employee
{
public int id;
protected String firstname;
protected String lastname;
    protected String employee_status;
    protected float oklad;
    protected float premierPercent;
    private float to_issue;
    protected String name_of_allowance;
    protected float percent_of_allowance;
    private int access_level;

    public Employee(int id, String firstname, String lastname,int access_level, String employee_status,
                    float oklad, float premierPercent,String name_of_allowance,  float percent_of_allowance, float to_issue) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.access_level=access_level;
        this.employee_status = employee_status;
        this.oklad = oklad;
        this.premierPercent = premierPercent;
        this.to_issue = to_issue;
        this.name_of_allowance = name_of_allowance;
        this.percent_of_allowance = percent_of_allowance;
    }

    public int getAccess_level() {
        return access_level;
    }

    public void setAccess_level(int access_level) {
        this.access_level = access_level;
    }

    public Employee(int id, String firstname, String lastname, String employee_status,
                    float oklad, float premierPercent, String name_of_allowance, float percent_of_allowance, float to_issue) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.employee_status = employee_status;
        this.oklad = oklad;
        this.premierPercent = premierPercent;
        this.to_issue = to_issue;
        this.name_of_allowance = name_of_allowance;
        this.percent_of_allowance = percent_of_allowance;
    }

    public void setEmployee_status(String employee_status) {
        this.employee_status = employee_status;
    }

    public void setOklad(float oklad) {
        this.oklad = oklad;
    }

    public void setPremierPercent(float premierPercent) {
        this.premierPercent = premierPercent;
    }

    public void setTo_issue(float to_issue) {
        this.to_issue = to_issue;
    }

    public void setName_of_allowance(String name_of_allowance) {
        this.name_of_allowance = name_of_allowance;
    }

    public void setPercent_of_allowance(float percent_of_allowance) {
        this.percent_of_allowance = percent_of_allowance;
    }

    public String getEmployee_status() {
        return employee_status;
    }

    public float getOklad() {
        return oklad;
    }

    public float getPremierPercent() {
        return premierPercent;
    }

    public float getTo_issue() {
        return to_issue;
    }

    public String getName_of_allowance() {
        return name_of_allowance;
    }

    public float getPercent_of_allowance() {
        return percent_of_allowance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }





}
