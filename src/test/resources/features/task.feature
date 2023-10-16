
Feature: As a candidate I need to find the block with less weight

  Scenario: Need to find a block with less weight
    Given user is on the  fetch challenge page
    When user put one bar on the left bowl and comparing the weight of other bars in right bowl
    And user click the bar with lighter weight
    Then user must see the pop up window with message "Yay! You find it!"


