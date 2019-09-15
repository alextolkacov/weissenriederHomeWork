Feature: Weissenrieder home work

  Scenario: Check email validation
    Given open js.devexpress page
    When we open "Editing" category
    And click "Data Validation" link
    And Data Validation page should appear
    And Open "Angular" tab
    Then we switch to iframe
    And wait until iframe is loaded
    Then push 'Add a row' button
    And add new incorrect email and check correctness:
      | hello2@weissenrieder2    |
      | helloweissenrieder.com   |
      | hello!@weissenrieder.com |
      |                          |
    And add new correct email and check correctness:
      | hello@weissenrieder.com |
    And close browser