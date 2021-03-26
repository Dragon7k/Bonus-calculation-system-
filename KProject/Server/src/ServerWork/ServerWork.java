package ServerWork;

import Database.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerWork {
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Database database;

    public ServerWork(BufferedReader bufferedReader, PrintWriter printWriter, Database database)
    {
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
        this.database = database;
    }
    public void getId (int idOperation) throws IOException, SQLException
    {
        switch (idOperation)
        {
            case 1:
                SignUp();
                break;
            case 2:
                SignIn();
                break;
            case 3:
                sendInformationForUser();
                break;
                case 4:
                changeInfo();
                break;
            case 5:
                calculatePremier();
                break;
            case  6:
                sendInformationForAdmin();
                break;
            case  7:
                changeInfoInAdminPanel();
                break;
            case  8:
                deleteEmployee();
                break;
            case  9:
                addUsers();
                break;
            case  10:
                addEmployee();
                break;
            case  11:
                getDiagram();
                break;

        }
    }

    private void getDiagram() throws SQLException {
        ResultSet rez1 = database.select("select cash from prem where cash > 0.0 and cash<300.0 ");
        int count1=0,count2=0,count3=0,count4=0,count5=0;
        if(rez1.last())
        {
            count1=rez1.getRow();

        }
        ResultSet rez2 = database.select("select cash from prem where cash >= 300.0 and cash<500.0 ");
        if(rez2.last())
        {
            count2=rez2.getRow();

        }
        ResultSet rez3 = database.select("select cash from prem where cash >= 500.0 and cash<700.0 ");
        if(rez3.last())
        {
            count3=rez3.getRow();

        }
        ResultSet rez4 = database.select("select cash from prem where cash >= 700.0 and cash<1000.0 ");
        if(rez4.last())
        {
            count4=rez4.getRow();

        }
        ResultSet rez5 = database.select("select cash from prem where cash >=1000.0 ");

       if(rez5.last())
        {
            count5=rez5.getRow();

        }
        printWriter.println(count1);
        printWriter.println(count2);
        printWriter.println(count3);
        printWriter.println(count4);
        printWriter.println(count5);

    }


    private void addEmployee()throws IOException, SQLException
    {
        int spec_id=0,spe_i=0;
        String firstname = bufferedReader.readLine();
        String lastname=bufferedReader.readLine();
        String spec_name = bufferedReader.readLine();

        String sqlSpec = "select spec_id from spec where spec_name = '" + spec_name + "' ";
        ResultSet rezSpec = database.select(sqlSpec);

        if(rezSpec.next())
        {
            spec_id = Integer.parseInt(rezSpec.getString("spec_id"));
            String insEmp = "insert into employee (fname, lname, spec_id) values ('" +firstname + "','" +lastname + "', " + spec_id+ " )";
            database.insert(insEmp);

        } else
        {
          String insSpec = "insert into spec (spec_name) values('"+ spec_name+"')";
          database.insert(insSpec);
          String rez = " select spec_id from spec where spec_name = '" + spec_name+ "' ";
          ResultSet rezult = database.select(rez);
          while (rezult.next())
          {
              spe_i = Integer.parseInt(rezult.getString("spec_id"));
              String insEm = "insert into employee (fname, lname, spec_id) values ('" +firstname + "','" +lastname + "', " + spe_i+ " )";
              database.insert(insEm);
          }

        }

    }

    private void addUsers() throws IOException, SQLException {

        String firstname =bufferedReader.readLine();
        String lastname=bufferedReader.readLine();
        String login=bufferedReader.readLine();
        String password = bufferedReader.readLine();

        int empID=0;
        String sqlUsers = "select emp_id from users where login = '"+ login + "' ";
        ResultSet rezLog = database.select(sqlUsers);
        if(rezLog.next())
        {
            printWriter.println(1);
            return;
        }
        else
        {
            printWriter.println(2);

            database.insert("insert into employee (fname, lname) values('" + firstname + "', '" + lastname + "') ");

            String resEmployee = "select emp_id from employee  where fname = '"+ firstname +"' and lname = '"+ lastname +"' ";
            ResultSet REZ ;
            REZ= database.select(resEmployee);

            while (REZ.next()) {
                empID = Integer.parseInt(REZ.getString("emp_id"));
            }
            database.insert("insert into users(emp_id, login, pwd, access_level) values ("+ empID+", '"+ login +"', '"+ password +"', " +2 + " ) ");

        }

    }

    private void deleteEmployee() throws IOException {
        int id_emp = Integer.parseInt(bufferedReader.readLine());

        database.delete("delete from employee where emp_id=" +id_emp +"");
    }

    private void changeInfoInAdminPanel() throws IOException, SQLException {

        int employee_id = Integer.parseInt(bufferedReader.readLine());
        int all_id=0;
        int spec_id=0;
        int f=0;
        String name = bufferedReader.readLine();
        String lname = bufferedReader.readLine();
        int access = Integer.parseInt(bufferedReader.readLine());
        String spec_name = bufferedReader.readLine();
        float Okl = Float.parseFloat(bufferedReader.readLine());
        float OklPercent = Float.parseFloat(bufferedReader.readLine());
        String Allowance = bufferedReader.readLine();
        float AllowancePercent = Float.parseFloat(bufferedReader.readLine());

        database.update("update employee set fname ='"+name+"', lname ='"+lname +"' where emp_id = "+ employee_id +" ");


        String sqlUs = "select login from users where emp_id = "+ employee_id +"";
        ResultSet sqlUsers;
        sqlUsers = database.select(sqlUs);

        if(sqlUsers.next())
        {
            database.update("update users set access_level = "+access+" where emp_id = "+ employee_id+"");
        }


        int sp=0;
        String sqlAll1 = "select spec_id from spec where spec_name = '" + spec_name + "'";
        ResultSet rezAll1;
        rezAll1=database.select(sqlAll1);
        if(rezAll1.next())
        {
            sp = Integer.parseInt(rezAll1.getString("spec_id"));
            database.update("update spec set wage ="+ Okl+ " , stavka = " + OklPercent +" where spec_id = " +sp + "");
            database.update("update employee set spec_id =" + sp + " where emp_id = "+employee_id +"");

        }
        else {
            int spe_id = 0;
            database.insert(" insert into spec (spec_name, wage,stavka) values ('" + spec_name + "', '" + Okl + "','" + OklPercent + "')");

            String sqlAll2 = "select spec_id from spec where spec_name = '" + spec_name + "'";
            ResultSet rezAll2;
            rezAll2 = database.select(sqlAll2);

            while (rezAll2.next()) {
                spe_id = Integer.parseInt(rezAll2.getString("spec_id"));
            }
            database.update("update employee set spec_id = " + spe_id + " where emp_id = " + employee_id + "");

        }


        int al=0;
        String sqlAllowance = "select all_id from allowance where al_name = '"+ Allowance+"'";
        ResultSet sqlAl;
        sqlAl = database.select(sqlAllowance);

        if(sqlAl.next())
        {
            al = Integer.parseInt(sqlAl.getString("all_id"));
            database.update("update employee set all_id = "+ al +" where emp_id = "+ employee_id+"");
        }
        else
            {
                int a_id = 0;
                database.insert(" insert into allowance (al_name, al_value) values ('" + Allowance + "', '" + AllowancePercent + "')");

                String sqlAll2 = "select all_id from allowance where al_name = '" + Allowance + "'";
                ResultSet rezAll2;
                rezAll2 = database.select(sqlAll2);

                while (rezAll2.next())
                {
                    a_id = Integer.parseInt(rezAll2.getString("all_id"));
                }

                database.update("update employee set all_id = " + a_id + " where emp_id = " + employee_id + "");

            }

    }

    private void sendInformationForAdmin() throws SQLException {
        ResultSet resultSet;
        resultSet = database.select("\n" +
                "select ifnull( users.access_level,3) as access, employee.emp_id, employee.fname, employee.lname,spec.spec_name,spec.wage," +
                "spec.stavka,ifnull(prem.cash, 0) as cash,allowance.al_name,allowance.al_value\n" +
                "from employee left join spec on employee.spec_id=spec.spec_id\n" +
                "left join prem on prem.emp_id=employee.emp_id\n" +
                "left join allowance on allowance.all_id=employee.all_id\n" +
                "left join users on users.emp_id=employee.emp_id");
        int rowAmount = 0;
        if (resultSet.last())
        {
            rowAmount = resultSet.getRow();
            resultSet.beforeFirst();
        }
        printWriter.println(rowAmount);
        while (resultSet.next())
        {
            printWriter.println(resultSet.getString("access"));
            printWriter.println(resultSet.getString("employee.emp_id"));
            printWriter.println(resultSet.getString("employee.fname"));
            printWriter.println(resultSet.getString("employee.lname"));
            printWriter.println(resultSet.getString("spec.spec_name"));
            printWriter.println(resultSet.getString("spec.wage"));
            printWriter.println(resultSet.getString("spec.stavka"));
            printWriter.println(resultSet.getString("allowance.al_name"));
            printWriter.println(resultSet.getString("allowance.al_value"));
            printWriter.println(resultSet.getString("cash"));
        }
    }

    private void calculatePremier() throws IOException, SQLException
    {
        ResultSet resultSet;
        ResultSet resultSet2;
        ResultSet resultSet3;
        int spec_id = 0;
        int all_id=0;

        float oklad = 0,stav_perc=0,allowance_perc=0,rez=0;
        int emp_ID = Integer.parseInt(bufferedReader.readLine());
        int ret = Integer.parseInt(bufferedReader.readLine());
        String emp = "select spec_id, all_id from employee where emp_id = '"+emp_ID+"'";
        resultSet = database.select(emp);
        while (resultSet.next())
        {
            spec_id= Integer.parseInt(resultSet.getString("spec_id"));
            all_id=Integer.parseInt(resultSet.getString("all_id"));
        }

        String viborka_spec = "select wage, stavka from spec where spec_id ='"+spec_id+"'";
        String viborka_allow = "select al_value from allowance where all_id ='"+all_id+"'";


        resultSet2=database.select(viborka_spec);
        resultSet3=database.select(viborka_allow);
        while (resultSet2.next())
        {
            oklad=Float.parseFloat(resultSet2.getString("wage"));
            stav_perc=Float.parseFloat(resultSet2.getString("stavka"));
        }
        while (resultSet3.next())
        {
            allowance_perc=Float.parseFloat(resultSet3.getString("al_value"));
        }

        rez=(oklad+(oklad*stav_perc/100)+(oklad*allowance_perc/100));
        float fullRez = rez-(rez*ret/100);


        database.insert("insert into prem (emp_id, cash) values ("+ emp_ID +", "+ fullRez+" )");
    }
    private void changeInfo() throws IOException, SQLException
    {
        int emp_id=Integer.parseInt(bufferedReader.readLine());
        int all_id=0;
        int spec_id=0;
        float Okl = Float.parseFloat(bufferedReader.readLine());
        float OklPercent = Float.parseFloat(bufferedReader.readLine());
        String Allowance = bufferedReader.readLine();
        float AllowancePercent = Float.parseFloat(bufferedReader.readLine());


        String sqlAll = "select all_id from allowance where al_name = '" + Allowance + "'";
        ResultSet rezAll;
        rezAll=database.select(sqlAll);

        if(rezAll.next())
        {
           all_id=Integer.parseInt(rezAll.getString("all_id"));

            database.update("update employee set all_id =" + all_id + " where emp_id = " + emp_id+ "");
        }
        else
        {
            int Al_id=0;
            database.insert(" insert into allowance (al_name, al_value) values ('" + Allowance + "', '" + AllowancePercent + "')");

            String sqlAll2 = "select all_id from allowance where al_name = '" + Allowance + "'";
            ResultSet rezAll2;
            rezAll2=database.select(sqlAll2);

            while (rezAll2.next())
            {
                Al_id=Integer.parseInt(rezAll2.getString("all_id"));
            }
            database.update("update employee set all_id = "+Al_id +" where emp_id = "+ emp_id+"");
        }


        String emp = "select spec_id, all_id from employee where emp_id = '"+emp_id+"'";
        ResultSet resultSet;
        resultSet = database.select(emp);

        while (resultSet.next())
        {
            spec_id= Integer.parseInt(resultSet.getString("spec_id"));
            all_id=Integer.parseInt(resultSet.getString("all_id"));
        }


        database.update("update spec set wage = " +Okl+ ", stavka=" +OklPercent+ " where spec_id = " +spec_id+ "");


    }
    private void sendInformationForUser() throws IOException, SQLException
    {
        ResultSet resultSet = null;
        String info = bufferedReader.readLine();

        if(info.equals("")) {

            resultSet = database.select("\n" +
                    "select employee.emp_id, employee.fname, employee.lname,spec.spec_name,spec.wage,spec.stavka,ifnull(prem.cash, 0) as cash,allowance.al_name,allowance.al_value\n" +
                    "from employee left join spec on employee.spec_id=spec.spec_id\n" +
                    "left join prem on prem.emp_id=employee.emp_id\n" +
                    "left join allowance on allowance.all_id=employee.all_id ");


        }else
        {
            resultSet = database.select("\n" +
                    "select employee.emp_id, employee.fname, employee.lname,spec.spec_name,spec.wage,spec.stavka,ifnull(prem.cash, 0) as cash,allowance.al_name,allowance.al_value\n" +
                    "from employee left join spec on employee.spec_id=spec.spec_id\n" +
                    "left join prem on prem.emp_id=employee.emp_id\n" +
                    "left join allowance on allowance.all_id=employee.all_id " +
                    " where employee.lname = '" +info + "'");
        }

        int rowAmount = 0;
        if (resultSet.last())
        {
            rowAmount = resultSet.getRow();
            resultSet.beforeFirst();
        }
        printWriter.println(rowAmount);
        while (resultSet.next())
        {
            printWriter.println(resultSet.getString("employee.emp_id"));
            printWriter.println(resultSet.getString("employee.fname"));
            printWriter.println(resultSet.getString("employee.lname"));
            printWriter.println(resultSet.getString("spec.spec_name"));
            printWriter.println(resultSet.getString("spec.wage"));
            printWriter.println(resultSet.getString("spec.stavka"));
            printWriter.println(resultSet.getString("allowance.al_name"));
            printWriter.println(resultSet.getString("allowance.al_value"));
            printWriter.println(resultSet.getString("cash"));
        }
    }
    private void SignIn() throws IOException, SQLException {

        String login = bufferedReader.readLine();
        String password = bufferedReader.readLine();

        ResultSet resultSet = database.select("select * from users where login = '" + login + "' and pwd = '" + password + "'");

        if(resultSet.next())
        {
            printWriter.println(resultSet.getString("access_level"));

        }
        else
        {
            printWriter.println("Error");
        }

    }

    private void SignUp() throws IOException, SQLException {
        int workerId=0;
        int newSpecId=0;
        int SpecId=0;
        String login = bufferedReader.readLine();
        String password = bufferedReader.readLine();
        String name = bufferedReader.readLine();
        String surname = bufferedReader.readLine();
        String dolzhnost = bufferedReader.readLine();
        String sqlString = "SELECT * FROM users where login = '" + login + "'";
        String sqlString1 = "SELECT * FROM spec where spec_name = '" + dolzhnost + "'";

        ResultSet resultSet = database.select(sqlString);
        ResultSet resultSet1 = database.select(sqlString1);
        if(resultSet.next())
        {
            printWriter.println("Error");
        }
        else
        {
            printWriter.println("Ok");
        }

        if(resultSet1.next())
        {
            String sqlSpec_id1 = "select spec_id from spec where (spec_name = '"+dolzhnost+"')";
            ResultSet res1 = database.select(sqlSpec_id1);
            while (res1.next()) {
                SpecId = Integer.parseInt(res1.getString("spec_id"));
            }
            database.insert("insert into employee (fname, lname,spec_id) values ('" + name + "', '" + surname + "','"+SpecId+"')");
        }
        else
        {
            database.insert("insert into spec (spec_name) values ('" + dolzhnost + "') ");
            String sqlSpec_id = "select spec_id from spec where (spec_name = '"+dolzhnost+"')";
            ResultSet res2 = database.select(sqlSpec_id);
            while (res2.next()) {
                newSpecId = Integer.parseInt(res2.getString("spec_id"));
            }
            database.insert("insert into employee (fname, lname,spec_id) values ('" + name + "', '" + surname + "','"+newSpecId+"')");
        }

        String sqlEmp_id = "select emp_id from employee where (fname = '"+name+"' and lname='"+surname+"')";
        ResultSet res = database.select(sqlEmp_id);
        while (res.next()) {
             workerId = Integer.parseInt(res.getString("emp_id"));
        }


        database.insert("insert into users (emp_id, login, pwd, access_level) values ("+ workerId+",'" + login + "', '" + password + "'," + 2 + ")");


    }

}
