package com.example.lawSearch.global.base.category;

public enum Category {
    OPERATIONS("국회운영위원회"),
    LEGISLATION_JUDICIARY("법제사법위원회"),
    POLITICS("정무위원회"),
    PLANNING_FINANCE("기획재정위원회"),
    EDUCATION("교육위원회"),
    SCIENCE_TECHNOLOGY("과학기술정보방송통신위원회"),
    FOREIGN("외교통일위원회"),
    NATIONAL_DEFENSE("국방위원회"),
    ADMINISTRATION_SAFETY("행정안전위원회"),
    CULTURE_SPORTS_TOURISM("문화체육관광위원회"),
    AGRI_FISHERIES("농림축산식품해양수산위원회"),
    INDUSTRY("산업통상자원벤처기업위원회"),
    HEALTH_WELFARE("보건복지위원회"),
    ENVIRONMENT("환경노동위원회"),
    LAND_TRANSPORT("국토교통위원회"),
    INFORMATION("정보위원회"),
    WOMEN_FAMILY("여성가족위원회");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public static Category categoryConverter(String text) {
        for (Category category : Category.values()) {
            if (category.value.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new CategoryNotFoundException(text);
    }
}
