Feature: 음악 추천받기
  Scenario: 음악 추천함 만들기
    Given 어떤 유저가 존재할 때
    When 음악 추천함을 만들면
    Then 그 유저만의 음악 추천함이 만들어진다

  Scenario: 새로운 음악 추천함 만들기
    Given 어떤 유저가 존재할 때
    And 음악 추천함을 만들었으면
    When 새로운 음악 추천함을 만든다면
    Then 에러가 나며 음악 추천함을 만들 수 없다

  Scenario: 나의 음악 추천함 찾기
    Given 어떤 유저가 존재할 때
    And 음악 추천함을 만들었으면
    When 그 유저의 음악 추천함을 찾는다면
    Then 음악 추천함이 존재해야한다

  Scenario: 어떤 유저가 나에게 음악을 추천한다
    Given 어떤 유저가 존재할 때
    And 음악 추천함을 만들었으면
    When 그 추천함에 음악을 추천 한다면
    Then 음악 추천함에 새로운 추천이 생긴다
