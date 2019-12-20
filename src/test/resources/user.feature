Feature: User should register, login and change password

  Scenario Outline: User should register
    Given login "<login>" and password "<password>"
    When I send request to register my account
    Then I got status "<status>" and return message "<return message>"

    Examples:
      | login  | password       | status | return message              |
      | wiktor2 | wiktorpassword | OK     | account has been registered |