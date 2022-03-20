package ru.sstu.notepad.controller;

public interface ApiConstants {

    String PREFIX = "/api";

    String USERS = PREFIX + "/users";
    String LOGIN = "/login";

    String TAGS = PREFIX + "/tags";

    String TASKS = PREFIX + "/tasks";
    String TASKS_ACTUAL = "/actual";
    String TASKS_COMPLETED = "/completed";
    String TASKS_EXPIRED = "/expired";

    String RECORDS_FIND = "/find";
    String RECORDS_GET_ALL = "/all";

    String EXPORT = PREFIX + "/export";
}
