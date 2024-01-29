
 @Regression
Feature: Purcahse Order from the E-Commerce Website
  I want to use this template for my feature file
    
  Background:
  Given I landed on the E-commerce Page
  

  Scenario Outline: Positive Test-Submit the Order
  
    Given Logged in with "<username>" and "<password>"
    When Add the "<productName>" into cart 
    And Checkout "<productName>" and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed

    Examples: 
      | username           |password  |productName		| 
      | psayan10@yahoo.com |Sayan@123 |IPHONE 13 PRO  |
      | psayan12@yahoo.com |Sayan@123 |ADIDAS ORIGINAL|
