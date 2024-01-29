
 @ErrorValidation
Feature: Error Validation
  I want to use this template for my feature file

  Scenario Outline: Title of your scenario outline
  
	  Given I landed on the E-commerce Page
	  When Logged in with "<username>" and "<password>"
	  Then "Incorrect email or password." message is displayed 
    Examples: 
      | username           |password  |productName		| 
      | psayan10@yahoo.com |Sayan@123 |IPHONE 13 PRO  |
      | psayan12@yahoo.com |Sayan@123 |ADIDAS ORIGINAL|
