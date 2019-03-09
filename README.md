# RateCalculationSystem

The calculation can be made on the basis that the loan is equal to the sum of the repayments discounted to present value.

With:

- s = value of loan
- d = periodic repayment
- interestRate = annual interest rate
- r = periodic interest rate
- n = number of periods

Where total monthly compound interest is calculated by:
- r = (1 + interestRate)^(1/12) - 1; 
- d = (r s)/(1 - (1 + r)^-n)

#### Requiremnts
for compile JDK 8
```mvn clean install```

for execution JRE 8
``` java -jar target/RateCalculationSystem-1.0-SNAPSHOT-jar-with-dependencies.jar "src/test/resources/Market Data for Exercise - .csv.csv" 1000```