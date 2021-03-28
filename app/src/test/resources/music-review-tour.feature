Feature: 음악 리뷰 둘러보기
  Scenario: 이미 생성된 음악 리뷰들 둘어보기
    Given 음악 리뷰가 이미 생성되었을 때
    When 모든 리뷰 리스트를 가져온다면
    Then 이미 생성된 모든 리뷰가 포함된다