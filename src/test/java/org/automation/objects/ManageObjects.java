package org.automation.objects;

import org.automation.middleware.UserTodo;

public class ManageObjects {

    private static UserTodo userTodo = null;
    public UserTodo getUserTodo() {
        return (userTodo == null) ? userTodo = new UserTodo() : userTodo;
    }
}
