package com.te.jdbc.ems;

import java.sql.*;

public class Employee {
	public static void main(String[] args) throws Exception {

//1 step: Load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");

//2 step: Establish Connection 
		String url = "jdbc:mysql://localhost:3306/ems_new_db";
		String username = "root";
		String password = "root";

		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println(con);
		
//		 Connection:interface
//		 DriverManager:class
											
//3 step: create query
		String query = "select * from emp_tbl";
		Statement stmt = con.createStatement();
		ResultSet set = stmt.executeQuery("stmt");

//4 step: process the data first way
		while (set.next()) {
			int id = set.getInt("emp_id");
			String name = set.getNString("ename");
			int sal = set.getInt("salary");

			System.out.println(id);
			System.out.println(name);
			System.out.println(sal);

		}
//	process the data second way
		System.out.println(set);

// 5 step: close the connection
		con.close();

//======================================================================		

//		method calling statements for statement and prepareStatment
// 1. statement 
		
//		fetchData(con);
		insertData(con);
//		insertDataUsingVariable(con);
//		updateData(con);
//		deleteData(con);
		
//	2. Prepare statement
		
//		fetchData1(con);
//		insertData1(con);
//		updateData1(con);  
//		deleteData1(con);

	}

//	using  statement
//	step 1: FETCH
	
	public static void fetchData(Connection con) throws SQLException {
		String str = "select * from emp_tbl";
		Statement stmt = con.createStatement();
		ResultSet set = stmt.executeQuery(str);

		while (set.next()) {
			int id = set.getInt("emp_id");
			String name = set.getNString("ename");
			int sal = set.getInt("salary");

			System.out.println(id);
			System.out.println(name);
			System.out.println(sal);

		}
		con.close();

	}
//	Step 2: insert data
	public static void insertData(Connection con) throws SQLException {
		String str = "insert into emp_tbl values(3,'payal',7000000)";
		Statement stmt = con.createStatement();
		int set = stmt.executeUpdate(str);

		System.out.println(set);
		con.close();

	}
//	insert data using variable 
	public static void insertDataUsingVariable(Connection con) throws SQLException {
		int empID=4;
		String empName="pranav";
		double empSal=80000000;
		String str = "insert into emp_tbl values("+empID+",'"+empName+"',"+empSal+")";
		Statement stmt = con.createStatement();
		int set = stmt.executeUpdate(str);

		System.out.println(set);
		con.close();

	}
//  step 3: update
	public static void updateData(Connection con) throws SQLException {
		String str = "update emp_tbl set salary=10000000 where emp_Id=1";
		Statement stmt = con.createStatement();
		int set = stmt.executeUpdate(str);

		System.out.println(set);
		con.close();

	}
//  step 4: delete
	public static void deleteData(Connection con) throws SQLException {
		String str = "delete from emp_tbl  where emp_Id=3";
		Statement stmt = con.createStatement();
		int set = stmt.executeUpdate(str);

		System.out.println(set);
		con.close();

	}
	
//	using Prepare statement
//	step 1: FETCH
	public static void fetchData1(Connection con) throws SQLException{
		String str="select * from emp_tbl";
		PreparedStatement preStmt=con.prepareStatement(str);
		ResultSet set=preStmt.executeQuery();
		System.out.println(set.next()+" "+set.getInt(1)+" "+set.getString(2)+" "+set.getDouble(3));
		con.close();
	}
//	step 2: INSERT
	public static void insertData1(Connection con) throws SQLException{
		int id=5;
		String name="manoj";
		double sal=600000;
		String query="insert into emp_tbl values(?,?,?)";
		PreparedStatement preStmt=con.prepareStatement(query);
		preStmt.setInt(1, id);
		preStmt.setString(2, name);
		preStmt.setDouble(3, sal);
		int set=preStmt.executeUpdate();
	
		con.close();	
	}
//	step 3:UPDATE
	public static void updateData1(Connection con) throws SQLException{
		String query="update emp_tbl set salary=23000000 where emp_id=2";
		PreparedStatement preStmt=con.prepareStatement(query);
		int set=preStmt.executeUpdate();
		con.close();
	}
//	step 4:DELETE
	public static void deleteData1(Connection con) throws SQLException{
		String query="delete from emp_tbl where emp_id=5 ";
		PreparedStatement preStmt=con.prepareStatement(query);
		int set = preStmt.executeUpdate();
		con.close();
	}
}
