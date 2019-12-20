Feature: User should register, login and change password

  Scenario Outline: User can register
    Given login "<login>" and password "<password>"
    When I send request to register my account
    Then I got status "200 OK"

    Examples:
      | login         | password       |
      | wiktor2345666 | wiktorpassword |

  Scenario Outline: User can't register with already registered login
    Given login "<login>" and password "<password>"
    When I send request to register my account
    Then I got status "409 CONFLICT"

    Examples:
      | login         | password       |
      | wiktor2345666 | wiktorpassword |

  Scenario Outline: User can login
    Given login "<login>" and password "<password>"
    When I send request to login my account
    Then I got status "200 OK"
    And I got token
    Examples:
      | login         | password       |
      | wiktor2345666 | wiktorpassword |

  Scenario Outline: User can't login with invalid login or password
    Given login "<login>" and password "<password>"
    When I send request to login my account
    Then I got status "409 CONFLICT"
    And I don't have token
    Examples:
      | login         | password       |
      | wiktor2345666 | wiktorpasswor  |
      | wiktor2345664 | wiktorpassword |

  Scenario Outline: User can delete his account
    Given logged account with credentials "<login>" and "<password>"
    When I send request to delete my account
    Then I got status "200 OK"

    Examples:
      | login         | password       |
      | wiktor2345666 | wiktorpassword |

