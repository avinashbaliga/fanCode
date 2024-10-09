package org.automation.pojos.allTodos;

import com.google.gson.annotations.SerializedName;

public class AllTodosItem {

    @SerializedName("id")
    public int id;

    @SerializedName("completed")
    public boolean completed;

    @SerializedName("title")
    public String title;

    @SerializedName("userId")
    public int userId;
}