javac -d . -cp .;junit-4.11.jar;hamcrest-core-1.3.jar *.java
java -cp .;junit-4.11.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore simulation.Tester