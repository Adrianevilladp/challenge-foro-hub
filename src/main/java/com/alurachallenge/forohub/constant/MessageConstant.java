package com.alurachallenge.forohub.constant;

import com.alurachallenge.forohub.enums.EProfile;

public class MessageConstant {
    public static final String USER_NOT_FOUND = "User with email [%s] not found in DB";
    public static final String USER_ID_NOT_FOUND = "User with id [%s] not found in DB";
    public static final String TOPIC_NOT_FOUND = "Topic with id [%s] not found in DB";
    public static final String RESPONSE_NOT_FOUND = "Response with id [%s] not found in DB";

    public static final String COURSE_NOT_FOUND = "Course with title [%s] not found in DB";
    public static final String UNAUTHORIZED_MESSAGE = "Sorry, You're not authorized to access this resource.";
    public static final EProfile DEFAULT_USER_PROFILE = EProfile.ROLE_USER;
    public static final EProfile ADMIN_USER_PROFILE = EProfile.ROLE_STAFF;
    public static final String USER_REGISTERED = "User registered successfully!";
    public static final String TOPIC_ALREADY_EXISTS = "Topic with title [%s] and message [%s] already exists in DB";
    public static final String NEW_ADMIN_USER = "User with name [%s] is admin now!";

}
