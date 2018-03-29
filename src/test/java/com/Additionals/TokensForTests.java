package com.Additionals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public final class TokensForTests {
    private TokensForTests() { }

    public static String getCorrectTokenUnlimited() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYXJ0aW1leXNlIiwiaXNzIjoiYXV0aDAiLCJpYXQiOjE1MjIyNDI4MTF9.oKzkg1pjkVVurCb0jt2tr5yU56SXY4ZRNq_qosyymiU";
    }

    public static String getNotCorrectTokenUnlimited() {
        return "QyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYXJ0aW1leXNlIiwiaXNzIjoiYXV0aDAiLCJpYXQiOjE1MjIyNDI4MTF9.oKzkg1pjkVVurCb0jt2tr5yU56SXY4ZRNq_qosyymiU";
    }
}
