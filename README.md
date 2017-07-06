# PreparedStatementCaching
JAVA PreparedStatement issue if Insert and Retrieve are perform in loop for large data

Follow these steps to reproduce the issue
	1. Create following two tables in database (I used mysql)
	
		CREATE TABLE trn (product_id VARCHAR(256))
		
		CREATE TABLE product (product_id VARCHAR(256));

		
	2. Clone this project and build using maven
	
	3. Open Example.java file this has Junit
	
	4. To add dummy entry in trn table uncomment addDummyEntry() in setupAll() method and after first run comment this line
	
	5. simpleTest() method in Example.java perform following steps to see the behavioue
		
		change insertNew  = true and run the program
		change insertNew = false and run the program
		
	You can see the difference, executeDummyQuery() in ExampleDAO become slower and slower with insertNew=true.
	
		